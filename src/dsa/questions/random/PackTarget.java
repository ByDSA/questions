package dsa.questions.random;

import java.security.SecureRandom;
import java.util.*;

public class PackTarget<T extends Target> extends ArrayList<T> implements Target {
	private static Random rand;
	static {
		setNormalRandom();
	}

	public PackTarget() {
	}

	public static void setSecureRandom() {
		rand = new SecureRandom();
	}

	public static void setNormalRandom() {
		rand = new Random();
	}

	@Override
	public Target pick(long dart) {
		if (size() == 0)
			throw new EmptyException();

		beforeOnPick();

		long acc = 0;
		Target dartTarget = null;
		for(Target t : this) {
			acc += Math.max(0, t.surface());
			if (dart < acc) {
				dartTarget = t;
				acc -= t.surface();
				break;
			}
		}

		Objects.requireNonNull(dartTarget);
		Target ret = dartTarget.pick(dart - acc);

		afterOnPick();

		return ret;
	}

	@Override
	public long surface() {
		long size = 0;
		for(T t : this) {
			size += Math.max(t.surface(), 0);
		}

		return size;
	}

	@Override
	public void beforeOnPick() {
	}

	@Override
	public void afterOnPick() {
	}

	@Override
	public void next() {
	}

	private long surfaceWithNext() {
		long size = 0;
		for(T t : this) {
			t.next();
			size += Math.max(t.surface(), 0);
		}

		return size;
	}

	private static long rand(long max) {
		return ( rand.nextLong() & Long.MAX_VALUE ) % max;
	}

	public T pick() {
		if (size() == 0)
			throw new EmptyException();

		long surface = surfaceWithNext();

		if (surface <= 0)
			throw new NoSurfaceException();

		if (size() == 1) { // Para evitar el rand y sea más eficiente, sobre todo para el SecureRandom
			T t = get(0);
			beforeOnPick();
			t.pick();
			return t;
		}

		long dart = rand(surface);

		return (T) pick(dart);
	}

	public static class NoSurfaceException extends RuntimeException {
		@SuppressWarnings("WeakerAccess")
		public NoSurfaceException() {
			super("El tamaño de la suma de los target es 0");
		}
	}

	public static class EmptyException extends RuntimeException {
		@SuppressWarnings("WeakerAccess")
		public EmptyException() {
			super("PackTarget vacío");
		}
	}
}
