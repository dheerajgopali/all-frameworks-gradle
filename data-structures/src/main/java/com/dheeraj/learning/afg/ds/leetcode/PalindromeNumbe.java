package com.dheeraj.learning.afg.ds.leetcode;

public class PalindromeNumbe {
    public static void main(String[] args) {
        System.out.println(isPalindromeAccurate(121));
    }
    public static boolean isPalindrome(int x) {
        int temp = x;
        if(temp<0)
            return false;
        int lastDigit = 0;
        int rev = 0;
        while(temp>0){
            lastDigit = temp%10;
            temp=temp/10;
            rev = rev*10 + lastDigit;
        }
        if(x == rev)
            return true;
        return false;
    }

    public static boolean isPalindromeAccurate(int x) {
        int temp = x;
        if(temp<0 || (temp%10==0 && temp!=0))
            return false;
        int lastDigit = 0;
        int rev = 0;
        while(temp>rev){
            lastDigit = temp%10;
            temp=temp/10;
            rev = rev*10 + lastDigit;
            if(temp == rev || temp ==rev/10)
                return true;
        }
        return false;
    }
}
