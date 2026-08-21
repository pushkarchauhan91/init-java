package com.company.number.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberTest {

    @Test
    void testIsPrime() {
        PrimeNumber primeNumber = new PrimeNumber();
        assertTrue(primeNumber.isPrime(7));
        assertFalse(primeNumber.isPrime(10));
    }

}