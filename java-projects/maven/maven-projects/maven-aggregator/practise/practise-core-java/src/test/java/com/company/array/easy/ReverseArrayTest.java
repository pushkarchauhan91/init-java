package com.company.array.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayTest {

    @Test
    void reverseArray() {
        ReverseArray reverseArray = new ReverseArray();
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        int[] actual = reverseArray.reverseArray(input);
        assertArrayEquals(expected, actual);
    }

}