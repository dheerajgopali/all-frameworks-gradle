package com.dheeraj.learning.afg.ds.leetcode;

public class LetterAndDigitLogs {
    public static void main(String[] args) {
        //String[] input = {"27 85717 7", "2 y xyr fc", "52 314 99", "d 046099 0", "m azv x f", "7e apw c y", "8 hyyq z p", "6 3272401", "c otdk cl", "8 ksif m u"};
        String[] input = {"1 f", "1 b", "1 a", "1 c", "1 e", "1 d"};
        LetterAndDigitLogs letterAndDigitLogs = new LetterAndDigitLogs();
        String[] output = letterAndDigitLogs.reorderLogFiles(input);
        for (String token:
             output) {
            System.out.print(token+",");
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        int nonDigitIndex =logs.length-1;
        int digitIndex=logs.length-1;
        while(nonDigitIndex >0 && digitIndex >0 ) {
            while(nonDigitIndex >0 && isDigitLog(logs[nonDigitIndex]))
                nonDigitIndex--;
            digitIndex = nonDigitIndex-1;
            while(digitIndex >=0 && !isDigitLog(logs[digitIndex]))
                digitIndex--;
            if(digitIndex<0)
                break;
            String temp = logs[nonDigitIndex];
            logs[nonDigitIndex] = logs[digitIndex];
            logs[digitIndex] = temp;
            nonDigitIndex = nonDigitIndex-1;
        }
        //Remaining will be letter logs from 0 till nonDigitIndex;
        quicksort(logs, 0, nonDigitIndex);
        return logs;
    }

    public boolean isDigitLog(String str){
        String[] tokens = str.split(" ");
        if(tokens[1].charAt(0) > 47 && tokens[1].charAt(0) < 58)
            return true;
        return false;
    }

    public void quicksort(String[]  logs, int startIndex, int endIndex) {
        if(startIndex >= endIndex)
            return;
        int left = startIndex;
        int right = endIndex;
        String pivot = logs[(startIndex+endIndex)/2];

        while (left <= right) {
            while (isLessThan(logs[left], pivot))
                left++;
            while (isGreaterThan(logs[right], pivot))
                right--;
            if (left <= right) {
                swap(left, right, logs);
                left++;
                right--;
            }
        }

        quicksort(logs, startIndex, right);
        quicksort(logs, left, endIndex);
    }

    public boolean isLessThan(String left, String right){
        String leftStringToken = left.substring(left.indexOf(' ')+1);
        String rightStringToken = right.substring(right.indexOf(' ')+1);
        if(leftStringToken.equals(rightStringToken)) {
            String leftIdentifier = left.substring(0, left.indexOf(' '));
            String rightIdentifier = right.substring(0, right.indexOf(' '));

            return leftIdentifier.compareTo(rightIdentifier)<0;
        } else
            return leftStringToken.compareTo(rightStringToken)<0;
    }

    public boolean isGreaterThan(String left, String right){
        String leftStringToken = left.substring(left.indexOf(' ')+1);
        String rightStringToken = right.substring(right.indexOf(' ')+1);
        if(leftStringToken.equals(rightStringToken)) {
            String leftIdentifier = left.substring(0, left.indexOf(' '));
            String rightIdentifier = right.substring(0, right.indexOf(' '));

            return leftIdentifier.compareTo(rightIdentifier)>0;
        } else
            return leftStringToken.compareTo(rightStringToken)>0;
    }

    private void swap(int left, int right, String[]  logs) {
        String temp = logs[left];
        logs[left] = logs[right];
        logs[right] = temp;
    }

}
