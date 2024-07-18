package calculator;

public class InvalidExpressionException extends SmartCalculatorException {
    public InvalidExpressionException() {
        super("Invalid expression");
    }
    public InvalidExpressionException(String message) {
        super(message);
    }
}
