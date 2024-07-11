package calculator;

public class InvalidExpressionException extends SmartCalculatorException {
    public InvalidExpressionException(String message) {
        super(message);
    }
}
