package dsa.questions.utils;

import dsa.questions.random.QuestionTarget;

import java.util.List;

public class Algorithm {
	public static long size(QuestionTarget target, long elements) {
		assert(elements > 0);

		return (long)test(target.getAgo(),
				target.getTimes(),
				elements,
				AlgorithmSettings.current
		);
	}

	public static double test(
			double ago,
			List<Boolean> times,
			long elements,
			AlgorithmSettings set) {
		if (ago <= 0)
			return 0;


		double size = 0;
		double appFac_1 = (100.0-set.aparitionFactor)/100;

		double ratioFailsShort = calculateRatioFailsShortTerm(times, set.timesRememberShort);
		double ratioFailsLong = calculateRatioFailsLongTerm(times, set.timesRememberLong);

		if (ratioFailsShort > 0) {
			double ratioAgo = shortTerm(ago, set.f, set.p);
			size = Math.pow(ratioAgo * elements, 1 + ratioFailsShort)*Math.pow(appFac_1, 10) + ago;
		} else {
			size = agoFunc(ago, elements*set.agoReappearenceFactor, set.agoFactor);

			// Long Term
			if (elements > set.longTermMin && ago > elements*set.longTermFactor) {
				if (ratioFailsLong > 0) {
					double diff = ago - elements*set.longTermFactor;
					size += diff*diff;
				}
			}
		}
		double ponderation = Math.pow(appFac_1, 5);
		size = size * ponderation + agoFunc(ago/2*Math.pow(elements, 1.0/4), elements*set.agoReappearenceFactor, set.agoFactor)*(1-ponderation);

		if (times.size() >= set.timesRememberLong && ratioFailsLong == 0) {
			size = size / elements + 1;
		}

		size = Math.max( size, 0 );
		size = Math.min( size, Long.MAX_VALUE/elements );
		return size;
	}

	public static double shortTerm(double x, double f, double p) {
		if (p == 10 && f == 1.5) {
			switch((int)x) {
				case 0: case 1: return 0;
				case 2: return 0.37037037;
				case 3: return 1.047565602;
				case 4: return 1.924500897;
				case 5: return 2.962962963;
				case 6: return 4.140866625;
				case 7: return 5.44331054;
				case 8: return 6.859355251;
				case 9: return 8.380524814;
				case 10: return 10;
				case 11: return 11.71213948;
				case 12: return 13.51217507;
				case 13: return 15.39600718;
				case 14: return 17.3600617;
				case 15: return 19.40118645;
				case 16: return 21.51657415;
				case 17: return 23.7037037;
				case 18: return 25.96029468;
				case 19: return 28.28427125;
				case 20: return 30.67373331;

			}
		}
		return Math.pow(x-1, f) / ( Math.pow(p-1,f-1)-(Math.pow(p-1, f-1) / p) );
	}

	public static double agoFunc(double a, long from, double agoFactor) {
		if (a <= from)
			return (a*agoFactor);
		else {
			return AlgorithmSettings.current.FORCE_APPEARANCE;
		}
	}

	public static double calculateRatioFailsShortTerm(List<Boolean> times, int timesRememberShort) {
		int n = timesShortTerm(times, timesRememberShort);
		int fails = failsShortTerm(times, timesRememberShort);
		return fails/(n-fails+0.1);
	}

	public static double calculateRatioFailsLongTerm(List<Boolean> times, int timesRememberLong) {
		int n = timesLongTerm(times, timesRememberLong);
		int fails = failsTimes(times, n);
		return fails/(n-fails+0.1);
	}

	public static int failsShortTerm(List<Boolean> times, int timesRememberShort) {
		int n = timesShortTerm(times, timesRememberShort);
		int fails = failsTimes(times, n);
		return fails;
	}

	public static int failsLongTerm(List<Boolean> times, int timesRememberLong) {
		int n = timesLongTerm(times, timesRememberLong);
		int fails = failsTimes(times, n);
		return fails;
	}

	public static int timesShortTerm(List<Boolean> times, int timesRememberShort) {
		return (int)Math.min(timesRememberShort, times.size());
	}

	public static int timesLongTerm(List<Boolean> times, int timesRememberLong) {
		return (int)Math.min(timesRememberLong, times.size());
	}

	public static int failsTimes(List<Boolean> times, int n) {
		int fails = 0;
		for(int i = 0; i < n; i++)
			if (!times.get(times.size()-1-i))
				fails++;

		return fails;
	}

	public static Boolean[] failsTimesArray(List<Boolean> times, int n) {
		Boolean[] ret = new Boolean[n];
		for(int i = 0; i < n; i++)
			ret[n-1-i] = times.get(times.size()-1-i);

		return ret;
	}

	public static class AlgorithmSettings {
		public static AlgorithmSettings current = new AlgorithmSettings();

		public double p = 5;
		public double f = 4;
		public int timesRememberShort = 5;
		public int aparitionFactor = 0; // Slider

		public int timesRememberLong = 10;
		public double longTermFactor = 0.5; // elements*factor: momento en que empieza a aplicar el LongTerm de fallos
		public int longTermMin = 40;	// Mínimo número de elementos para aplicar el 'LongTerm' a los fallos

		public double agoFactor = 0.5; // Pendiente del ago normal
		public int agoReappearenceFactor = 30; // elements*factor : momento en que se fuerza la reaparici�n

		public static final long FORCE_APPEARANCE = 9999999;

		public int listeningSpeed = 1;
	}
}
