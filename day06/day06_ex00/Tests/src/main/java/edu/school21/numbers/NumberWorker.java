package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalArgumentException("Number must be greater than 2");
        }

        for (int i = 2; i <= Math.sqrt(number); ++i)
            if (number % i == 0)
                return false;
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;
        for (int i = 0; i < String.valueOf(number).length(); ++i)
            result += (String.valueOf(number).charAt(i) - '0');
        return result;
    }

}