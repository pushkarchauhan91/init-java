package com.company.array.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplicatesInArrayTest {

    @Test
    void hasDuplicates() {
        DuplicatesInArray duplicatesInArray = new DuplicatesInArray();
        int[] arr = {1, 2, 3, 4, 5, 1};
        boolean expected = true;
        boolean actual = duplicatesInArray.hasDuplicates(arr);
        assertEquals(expected, actual);
    }

    @Test
    void countDuplicates() {
        DuplicatesInArray duplicatesInArray = new DuplicatesInArray();
        int[] arr = {1, 2, 3, 4, 5, 1, 2};
        int expected = 2;
        int actual = duplicatesInArray.countDuplicates(arr);
        assertEquals(expected, actual);
    }

    @Test
    void findDuplicates() {
        DuplicatesInArray duplicatesInArray = new DuplicatesInArray();
        int[] arr = {1, 2, 3, 4, 5, 1, 2};
        int[] expected = {1, 2};
        int[] actual = duplicatesInArray.findDuplicates(arr);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

}