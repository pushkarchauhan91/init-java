package com.company.string.easy;

public class VowelsInString {

    public int countVowels(String input) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < input.length(); i++) {
            if (vowels.indexOf(input.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    public String[] printVowels(String input) {
        String vowels = "aeiouAEIOU";
        StringBuilder vowelList = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (vowels.indexOf(input.charAt(i)) != -1) {
                vowelList.append(input.charAt(i)).append(" ");
            }
        }
        return vowelList.toString().trim().split(" ");
    }
}
