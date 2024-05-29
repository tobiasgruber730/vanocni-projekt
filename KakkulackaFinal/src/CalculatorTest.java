public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Testy jednoduchých aritmetických operací
        testCalculation(calculator, "2 + 3 * 4", 14.0);
        testCalculation(calculator, "10 / 2 - 3", 2.0);
        testCalculation(calculator, "5 + 2 * 3 - 1", 10.0);

        // Testy vědeckých funkcí
        testCalculation(calculator, "sin 0.5 + cos 0.5", Math.sin(0.5) + Math.cos(0.5));
        testCalculation(calculator, "log 1", Math.log(1));
        testCalculation(calculator, "sqrt 4 + exp 1", Math.sqrt(4) + Math.exp(1));

        // Testy se závorkami
        testCalculation(calculator, "( 2 + 3 ) * 4", 20.0);
        testCalculation(calculator, "10 / ( 2 - 3 )", -10.0);
        testCalculation(calculator, "( 5 + 2 ) * ( 3 - 1 )", 14.0);
    }

    private static void testCalculation(Calculator calculator, String expression, double expected) {
        try {
            double result = calculator.calculate(expression);
            System.out.println("Expression: " + expression);
            System.out.println("Expected: " + expected + ", Got: " + result);
            if (Math.abs(result - expected) < 1e-9) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test failed.");
            }
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
    }
}
