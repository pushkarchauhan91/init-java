package com.company.sort.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BubbleSortTest {

    @Test
    void testBubbleSort() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);

        assertArrayEquals(expected, arr);
    }

}