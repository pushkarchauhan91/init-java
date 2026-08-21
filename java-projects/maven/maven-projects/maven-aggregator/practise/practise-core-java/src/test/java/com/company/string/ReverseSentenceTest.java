package com.company.string;

import com.company.string.easy.ReverseSentence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseSentenceTest {

    @Test
    void reverseSentence() {
        ReverseSentence reverseSentence = new ReverseSentence();
        String input = "Hello World";
        String expected = "World Hello";
        String actual = reverseSentence.reverseSentence(input);
        assertEquals(expected, actual);
    }

}