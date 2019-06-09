package dsa.questions.utils;

import java.util.Random;

public class RandomUtils {
    public static int[] generateRandomArray(final int size) {
        Random rd = new Random(); // creating RandomUtils object
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(); // storing random integers in an array
        }

        return arr;
    }

    public static long[] generateRandomLongArray(final int size) {
        Random rd = new Random(); // creating RandomUtils object
        long[] arr = new long[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextLong(); // storing random integers in an array
        }

        return arr;
    }
}
