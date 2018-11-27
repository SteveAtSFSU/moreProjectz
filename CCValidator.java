/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccvalidator;

import java.util.Scanner;

/**
 *
 * @author sstev
 */
public class CCValidator {
   //prompt and read user ibput.
    public static long enterCC() {                      
        Scanner input = new Scanner(System.in);
        long userInput;
        System.out.println("Enter a Credit Card Number between 13 and 16 digits: ");
        userInput = input.nextLong();
        return userInput;
    }
    
    //gets # of digits in cc entered if digit is greater than 10 it will add up the digits.
    public static int getDigit(int number) {             
        if (number <= 9) {
            return number;
        } else if (number > 9) {
            return (number % 10 + number / 10);
        }
        return number;
    }

    // Checks if cc nuumber is valid.
    public static boolean isValid(long total) {
        if (total % 10 == 0) {
            return true;
        }
        return false;
    }
    
    //Sums up all doubled even numbers in cc. 
    public static int sumOfDoubleEvens(long number) {
        int sum = 0;
        number /= 10;
        while (number != 0) {
            sum += getDigit((int) (number % 10) * 2);
            number = number / 100;
        }

        return sum;
    }

    //sums up all odd numbers. 
    public static int sumOfOdd(long number) {
        int sum = 0;
        while (number != 0) {
            sum += (int) (number % 10);
            number = number / 100;
        }

        return sum;
    }

    //get size of cc number entered. 
    public static int getSize(long number) {
        int cnt = 0;
        while (number > 0) {
            number /= 10;
            cnt++;
        }
        return cnt;
    }

    // get prefix of cc entered. 
    public static long getPrefix(long number, int numberOfDigits) {
        int size = getSize(number);
        if (numberOfDigits > size) {
            return number;
        }
        int div_cnt = size - numberOfDigits;
        while (div_cnt > 0) {
            number = number / 10;
            div_cnt--;
        }
        return number;
    }

    // Checks if the prefix of cc is matched to make it true. 
    public static boolean prefixMatched(long number, int d) {
        int numberOfDigits = getSize(number);
        long num = getPrefix(number, numberOfDigits);
        number = (long) (number / (Math.pow(10, numberOfDigits - 2)));
        if (number == 37) {
            return true;
        } else if (number != 37) {
            number /= 10;
            if (number == 4) {
                return true;
            } else if (number == 5) {
                return true;
            } else if (number == 6) {
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        // create a variable to hold total of evens and odds added together. 
        long total = 0;                
        //get user input. 
        long userInput = enterCC();
        //check size of user input.
        long size = userInput;
        size = getSize(userInput);
        //check if size is valid for a cc.
        if (size >= 13 || size <= 16) {
            //check if cc matches prefixes. 
            boolean verdict1 = prefixMatched(userInput, (int) size);
            if (verdict1 == true) {
                //if prefix is matched continue with adding up evens and odds.
                long sumOfdEvens = sumOfDoubleEvens(userInput);
                long sumOfOdds = sumOfOdd(userInput);
                total = sumOfOdds + sumOfdEvens;
                //check if cc sums are divisible by 10 to prove true.
                boolean verdict = isValid(total);
                if (verdict == true) {
                    System.out.println("Your Credit Card Number: " + userInput + " is valid cc. ");
                    

                } else {
                    System.out.println("Your Credit Card Number: " + userInput + " is invalid cc. ");
                }

            } else{
                System.out.println("Your Credit Card Number: " + userInput + " is invalid cc. ");
            }
        }
    }
}
