package com.company.sort.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest {

    @Test
    void insertionSort() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort(arr);

        assertArrayEquals(expected, arr);
    }
}