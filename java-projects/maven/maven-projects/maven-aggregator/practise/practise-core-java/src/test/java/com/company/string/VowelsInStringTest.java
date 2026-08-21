package com.company.string;

import com.company.string.easy.VowelsInString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VowelsInStringTest {

    @Test
    void countVowels() {
        VowelsInString vowelsInString = new VowelsInString();
        String input = "Hello, World!";
        int expectedCount = 3; // 'e', 'o', 'o'
        int actualCount = vowelsInString.countVowels(input);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void printVowels() {
        VowelsInString vowelsInString = new VowelsInString();
        String input = "Hello, World!";
        String[] expectedVowels = {"e", "o", "o"};
        String[] actualVowels = vowelsInString.printVowels(input);
        assertEquals(expectedVowels.length, actualVowels.length);
        for (int i = 0; i < expectedVowels.length; i++) {
            assertEquals(expectedVowels[i], actualVowels[i]);
        }
    }

}