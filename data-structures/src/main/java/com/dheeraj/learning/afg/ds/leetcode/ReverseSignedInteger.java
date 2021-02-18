package com.dheeraj.learning.afg.ds.leetcode;

public class ReverseSignedInteger {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483641));
        System.out.println(reverse2(-2147483641));
    }

    public static int reverse(int x) {
        int rev=0;
        int currentLastDigit = 0;
        while(x!=0) {
            currentLastDigit = x%10;
            x = x/10;
            if(checkOverFlown(rev, currentLastDigit))
                return 0;
            rev = rev*10+currentLastDigit;
        }
        return rev;
    }

    public static boolean checkOverFlown(int rev, int digit) {
        int temp = rev*10;
        if(rev != temp/10)
            return true;
        temp = rev*10+digit;
        if(rev<0 && digit <0 && temp >0)
            return true;
        if(rev>0 && digit >0 && temp < 0)
            return true;
        return false;
    }

    public static int reverse2(int x) {
        int num = 0;
        while(x!=0){
            int units = x%10;
            x = x/10;
            if(num>Integer.MAX_VALUE/10 || num<Integer.MIN_VALUE/10)
                return 0;
            num =num*10 + units;
        }
        return num;
    }

}
