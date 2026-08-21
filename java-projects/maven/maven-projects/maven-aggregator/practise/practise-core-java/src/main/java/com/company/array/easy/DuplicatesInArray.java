package com.company.array.easy;

public class DuplicatesInArray {

    public boolean hasDuplicates(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int countDuplicates(int[] arr) {
        int duplicates = 0;

        for (int i = 0; i < arr.length; i++) {

            boolean alreadyCounted = false;

            // Check whether this value appeared before
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    alreadyCounted = true;
                    break;
                }
            }

            if (alreadyCounted) {
                continue;
            }

            // Check whether this value appears again
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    duplicates++;
                    break;
                }
            }
        }

        return duplicates;
    }

    public int[] findDuplicates(int[] arr) {
        int[] duplicates = new int[arr.length];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {

            boolean alreadyCounted = false;

            // Check whether this value appeared before
            for (int j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    alreadyCounted = true;
                    break;
                }
            }

            if (alreadyCounted) {
                continue;
            }

            // Check whether this value appears again
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    duplicates[index++] = arr[i];
                    break;
                }
            }
        }

        int[] result = new int[index];
        System.arraycopy(duplicates, 0, result, 0, index);
        return result;
    }
}
