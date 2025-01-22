package edu.school21.numbers.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


class NumberWorkerTest {
    private NumberWorker numberWorker;

    @BeforeEach
    void setUp() {
        numberWorker = new NumberWorker();
    }

    @DisplayName("Test_01")
    @ParameterizedTest
    @ValueSource(ints = {3, 7, 13})
    public void isPrimeForPrimes(int inits) {
        Assertions.assertTrue(numberWorker.isPrime(inits));
    }

    @DisplayName("Test_02")
    @ParameterizedTest
    @ValueSource(ints = {4, 8, 14})
    public void isPrimeForNotPrimes(int inits) {
        Assertions.assertFalse(numberWorker.isPrime(inits));
    }

    @DisplayName("Test_03")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void isPrimeForIncorrectNumbers(int inits) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> numberWorker.isPrime(inits));
    }

    @DisplayName("Test_04")
    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv")
    public void testDigitsSum(int number, int result) {
        Assertions.assertEquals(numberWorker.digitsSum(number), result);
    }
}
