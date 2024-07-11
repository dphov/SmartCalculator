package calculator;

public class InvalidAssignmentException extends SmartCalculatorException {
    public InvalidAssignmentException() {
        super("Invalid identifier");
    }
}
