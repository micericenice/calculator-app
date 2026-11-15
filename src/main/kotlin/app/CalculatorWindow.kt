package org.example.app

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JTextField
import javax.swing.SwingUtilities
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel
import org.example.calculator.CalculatorEngine

class CalculatorWindow {

    private val calculator = CalculatorEngine()

    fun show() {
        SwingUtilities.invokeLater {
            val frame = JFrame("Calculator")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.setSize(360,480)
            frame.layout = BorderLayout()

            val display = JTextField(0)
            display.isEditable = false
            display.horizontalAlignment = JTextField.RIGHT

            frame.add(display, BorderLayout.NORTH)

            val buttonPanel = JPanel()
            buttonPanel.layout = GridLayout(4,4,5,5)

            val buttons = listOf(
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
            )

            var firstOperand = 0.0
            var pendingOperator: String? = null
            var startNewNumber = true

            for (text in buttons) {
                val button = JButton(text)

                button.addActionListener {
                    when {
                        text == "C" -> {
                            display.text = "0"
                            firstOperand = 0.0
                            pendingOperator = null
                            startNewNumber = true
                        }

                        text in "0".."9" -> {
                            if (startNewNumber || display.text == "0") {
                                display.text = text
                                startNewNumber = false
                            } else {
                                display.text += text
                            }
                        }

                        text in listOf("+", "-", "×","÷") -> {
                            if (pendingOperator != null && !startNewNumber) {
                                val secondOperand =
                                    display.text.toDouble()

                                try {
                                    val result = calculate(
                                        firstOperand,
                                        secondOperand,
                                        pendingOperator!!
                                    )

                                    display.text = formatResult(result)
                                } catch (e: ArithmeticException) {
                                    display.text = "Error"
                                    pendingOperator = null
                                    startNewNumber = true
                                    return@addActionListener
                                }
                            }

                            firstOperand =
                                display.text.toDoubleOrNull() ?: 0.0

                            pendingOperator = text
                            startNewNumber = true
                        }

                        text == "=" -> {
                            val operation = pendingOperator

                            if (operation != null && !startNewNumber) {
                                val secondOperand =
                                    display.text.toDouble()

                                try {
                                    val result = calculate(
                                        firstOperand,
                                        secondOperand,
                                        operation
                                    )

                                    display.text = formatResult(result)
                                } catch (e: ArithmeticException) {
                                    display.text = "Error"
                                }

                                pendingOperator = null
                                startNewNumber = true
                            }
                        }
                    }
                }

                buttonPanel.add(button)
            }

            frame.add(buttonPanel, BorderLayout.CENTER)
            frame.setLocationRelativeTo(null)
            frame.isVisible = true
        }
    }

    private fun calculate(
        a: Double,
        b: Double,
        operation: String
    ): Double {
        return when (operation) {
            "+" -> calculator.add(a, b)
            "-" -> calculator.subtract(a, b)
            "×" -> calculator.multiply(a, b)
            "÷" -> calculator.divide(a, b)
            else -> throw IllegalArgumentException("Invalid operation")
        }
    }

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}