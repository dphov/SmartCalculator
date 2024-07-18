package calculator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SmartCalculator smartCalculator = new SmartCalculator();
        smartCalculator.inputReadingLoop(scanner);

        scanner.close();
    }
}
