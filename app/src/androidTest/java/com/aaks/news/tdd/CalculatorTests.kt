package com.aaks.news.tdd

import org.junit.Assert.*
import org.junit.Test

class CalculatorTests {

    @Test
    fun add_AddsOneAndOne_ShouldReturnTwo(){

        var calculator = Calculator();
        val result = calculator.add(1,1);

        assertEquals(2, result)
    }

    @Test
    fun add_AddesOneAndTwo_ShouldReturnThree(){
        var calculator = Calculator();
        val result = calculator.add(1,2);

        assertEquals(3, result)
    }

    @Test
    fun  add_AddsOneTwoAndThree_ShouldReturnSix(){
        var calculator = Calculator();
        val result = calculator.add(1,2,3);

        assertEquals(6, result)
    }
}