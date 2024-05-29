import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    public double calculate(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        // Výstup pro ladění
        System.out.println("Input expression: " + expression);

        // Kontrola platnosti vstupního výrazu
        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        // Vyhodnocení výrazu a vrácení výsledku
        return evaluateExpression(expression);
    }

    private boolean isValidExpression(String expression) {
        for (char character : expression.toCharArray()) {
            if (!isNumeric(character) && !isOperator(character) && !isParenthesis(character) && !Character.isWhitespace(character) && !Character.isLetter(character)) {
                System.out.println("Invalid character found: " + character); // Výstup pro ladění
                return false;
            }
        }
        return true;
    }

    private double evaluateExpression(String expression) {
        Stack<Double> stack = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String[] tokens = expression.split("\\s+"); // Rozdělení výrazu na jednotlivé tokeny
        System.out.println("Tokens: " + Arrays.toString(tokens)); // Výstup pro ladění

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            System.out.println("Processing token: " + token); // Výstup pro ladění

            if (isNumeric(token.charAt(0))) {
                // Pokud je token číslo, převedeme ho na double a vložíme na zásobník
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                // Pokud je token operátor, provedeme operaci na dvou vrchních hodnotách ze zásobníku
                while (!operators.isEmpty() && isHigherPrecedence(operators.peek(), token.charAt(0))) {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(performOperation(operand1, operand2, operators.pop()));
                }
                operators.push(token.charAt(0));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (operators.peek() != '(') {
                    double operand2 = stack.pop();
                    double operand1 = stack.pop();
                    stack.push(performOperation(operand1, operand2, operators.pop()));
                }
                operators.pop(); // Odebrat '(' ze zásobníku
            } else if (isScientificFunction(token)) {
                if (i + 1 < tokens.length && isNumeric(tokens[i + 1].charAt(0))) {
                    double operand = Double.parseDouble(tokens[++i]);
                    stack.push(performScientificFunction(operand, token));
                } else {
                    throw new IllegalArgumentException("Invalid argument for scientific function: " + token);
                }
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = stack.pop();
            double operand1 = stack.pop();
            stack.push(performOperation(operand1, operand2, operators.pop()));
        }

        // Výsledek je na vrcholu zásobníku
        return stack.pop();
    }

    private double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private double performScientificFunction(double operand, String function) {
        switch (function) {
            case "sin":
                return Math.sin(operand);
            case "cos":
                return Math.cos(operand);
            case "tan":
                return Math.tan(operand);
            case "log":
                return Math.log(operand);
            case "exp":
                return Math.exp(operand);
            case "sqrt":
                return Math.sqrt(operand);
            // Další vědecké funkce...
            default:
                throw new IllegalArgumentException("Invalid scientific function: " + function);
        }
    }

    private boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '*' || character == '/';
    }

    private boolean isHigherPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    private boolean isParenthesis(char character) {
        return character == '(' || character == ')';
    }

    private boolean isNumeric(char character) {
        return Character.isDigit(character) || character == '.';
    }

    private boolean isScientificFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("log") || token.equals("exp") || token.equals("sqrt");
    }
}
