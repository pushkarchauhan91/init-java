package com.company.number.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberSumTest {

    @Test
    void sumOfDigits() {
        NumberSum numberSum = new NumberSum();
        int input = 123;
        int expected = 6;
        int actual = numberSum.sumOfDigits(input);
        assertEquals(expected, actual);
    }

}