package com.company.number.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmstrongNumberTest {

    @Test
    void testIsArmstrongNumber() {
        ArmstrongNumber armstrongNumber = new ArmstrongNumber();
        assertTrue(armstrongNumber.isArmstrongNumber(153));
        assertFalse(armstrongNumber.isArmstrongNumber(123));
    }

}