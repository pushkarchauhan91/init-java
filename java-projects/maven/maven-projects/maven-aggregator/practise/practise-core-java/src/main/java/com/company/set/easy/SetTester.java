package com.company.set.easy;

import java.util.HashSet;
import java.util.Set;

public class SetTester {

    public Set<Integer> findDuplicates(int[] arr) {
        Set<Integer> duplicates = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }
        return duplicates;
    }
}
