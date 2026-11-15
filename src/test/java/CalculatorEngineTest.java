import org.example.calculator.CalculatorEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorEngineTest {

    // Addition Test
    @Test
    public void testAddition() {
        CalculatorEngine calculator = new CalculatorEngine();

        double result = calculator.add(10.0, 5.0);

        assertEquals(15.0, result);
    }

    // Subtraction Test
    @Test
    public void testSubtraction() {
        CalculatorEngine calculator = new CalculatorEngine();

        double result = calculator.subtract(10.0, 5.0);

        assertEquals(5.0, result);
    }

    // Multiplication Test
    @Test
    public void testMultiplication() {
        CalculatorEngine calculator = new CalculatorEngine();

        double result = calculator.multiply(10.0, 5.0);

        assertEquals(50.0, result);
    }

    // Division Test
    @Test
    public void testDivision() {
        CalculatorEngine calculator = new CalculatorEngine();

        double result = calculator.divide(10.0, 5.0);

        assertEquals(2.0, result);
    }

    // Division by Zero Test
    @Test
    public void testDivisionByZero() {
        CalculatorEngine calculator = new CalculatorEngine();

        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10.0, 0.0);
        });
    }
}
