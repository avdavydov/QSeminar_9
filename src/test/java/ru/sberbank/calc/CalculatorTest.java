package ru.sberbank.calc;

import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CalculatorTest {

    Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {
        CalculatorHelper calculatorHelper = mock(CalculatorHelper.class);
        when(calculatorHelper.do_calc(6, 3, '/')).thenReturn(2);
        when(calculatorHelper.do_calc(1, 2, '+')).thenReturn(3);
        when(calculatorHelper.do_calc(6, 3, '/')).thenThrow(IllegalArgumentException.class);

        when(calculatorHelper.convertRomanToInteger("I")).thenReturn(1);
        when(calculatorHelper.convertRomanToInteger("II")).thenReturn(2);
        when(calculatorHelper.convertRomanToInteger("III")).thenReturn(3);
        when(calculatorHelper.convertRomanToInteger("VI")).thenReturn(6);
        when(calculatorHelper.convertIntegerToRoman(2)).thenReturn("II");



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calc_roman_successfull() {

        String expectedResult = "II";
        Assertions.assertEquals(expectedResult, calculator.calc("VI/III"));

    }

    @Test
    void calc_roman_exception() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculator.calc("I-II");
                }
        );

    }


    @Test
    void calc_arabic_successfull() {

        String expectedResult = "3";
        Assertions.assertEquals(expectedResult, calculator.calc("1+2"));

    }

    @Test
    void calc_arabic_exception() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    calculator.calc("1+2+3");
                }
        );

    }

}