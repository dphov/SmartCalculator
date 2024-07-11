package calculator;

public class UnknownVariableException extends SmartCalculatorException {
    public UnknownVariableException() {
        super("Unknown variable");
    }

    public UnknownVariableException(String msg) {
        super(msg);
    }
}
