package org.example.app

import org.example.calculator.CalculatorEngine

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    CalculatorWindow().show()
    val calculator = CalculatorEngine()

    println("Addition: ${calculator.add(10.0, 5.0)}")
    println("Subtraction: ${calculator.subtract(10.0, 5.0)}")
    println("Multiplication: ${calculator.multiply(10.0, 5.0)}")

    try {
        println("Division: ${calculator.divide(10.0, 2.0)}")
    } catch (e: ArithmeticException) {
        println("Error: ${e.message}")
    }
}