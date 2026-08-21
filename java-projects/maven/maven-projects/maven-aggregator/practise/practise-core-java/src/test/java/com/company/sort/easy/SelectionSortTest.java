package com.company.sort.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SelectionSortTest {

    @Test
    void selectionSort() {
        int[] arr = {64, 25, 12, 22, 11};
        int[] expected = {11, 12, 22, 25, 64};

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.selectionSort(arr);

        assertArrayEquals(expected, arr);
    }
}