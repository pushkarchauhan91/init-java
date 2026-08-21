package com.company.set.easy;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetTesterTest {

    @Test
    void findDuplicates() {
        SetTester setTester = new SetTester();
        int[] arr = {1, 2, 3, 4, 5, 1, 2};
        Set<Integer> expected = Set.of(1, 2);
        Set<Integer> actual = setTester.findDuplicates(arr);
        assertEquals(expected, actual);
    }

}