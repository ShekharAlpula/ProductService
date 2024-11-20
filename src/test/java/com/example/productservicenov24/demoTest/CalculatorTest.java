package com.example.productservicenov24.demoTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        //Arrange
        int a =20, b = 30;
        Calculator calculator = new Calculator();

        //Action
        int result  = calculator.add(a,b);

        //Assert
        assertEquals(result,50);
    }

    @Test
    void subtract() {
        //Arrange
        int a =50, b = 30;

        Calculator calculator = new Calculator();

        int result  = calculator.subtract(a,b);
        //Assertion
        assertEquals(result,20);
    }

    @Test
    void multiply() {
        int a =50, b = 30;
        Calculator calculator = new Calculator();
        int result  = calculator.multiply(a,b);
        assertEquals(result,1500);

    }

    @Test
    void divide() {
        int a =50, b = 0;

        //Action
        Calculator calculator = new Calculator();

        //Assertion
        assertThrows(ArithmeticException.class, ()->calculator.divide(a,b));
    }
}