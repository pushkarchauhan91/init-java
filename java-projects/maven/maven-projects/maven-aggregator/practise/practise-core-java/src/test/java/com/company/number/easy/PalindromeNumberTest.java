package com.company.number.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalindromeNumberTest {

    @Test
    void checkPalindrome() {
        PalindromeNumber palindromeNumber = new PalindromeNumber();
        int input = 121;
        boolean expected = true;
        boolean actual = palindromeNumber.checkPalindrome(input);
        assertEquals(expected, actual);
    }

}