package calculator;

public class UnknownCommandException extends SmartCalculatorException {
    public UnknownCommandException() {
        super("Unknown command");
    }
}
