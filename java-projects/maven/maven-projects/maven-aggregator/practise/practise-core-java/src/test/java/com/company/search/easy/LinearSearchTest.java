package com.company.search.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearSearchTest {

    @Test
    void linearSearch() {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 3;
        int expectedIndex = 2;
        int actualIndex = LinearSearch.linearSearch(arr, target);
        assertEquals(expectedIndex, actualIndex);
    }

}