package com.company.string;

import com.company.string.easy.ReverseString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseStringTest {

    @Test
    void reverseStringUsingStringBuilder() {
        String input = "Hello, World!";
        String expectedOutput = "!dlroW ,olleH";
        ReverseString reverseString = new ReverseString();
        String actualOutput = reverseString.reverseStringUsingStringBuilder(input);
        assertEquals(expectedOutput, actualOutput);
    }

}