package calculator;

public class InvalidIdentifierException extends SmartCalculatorException {
    public InvalidIdentifierException() {
        super("Invalid identifier");
    }

    public InvalidIdentifierException(String msg) {
        super(msg);
    }
}

