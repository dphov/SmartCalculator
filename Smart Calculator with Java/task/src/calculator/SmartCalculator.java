package calculator;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;


public class SmartCalculator {

    static Map<String, String> mapStorage;
    static ArrayDeque<Object> executionStack;

    private static final Pattern VALID_VARIABLE_OR_INT_PATTERN = Pattern.compile("^(?:((\\+|\\-)?\\d+)|[a-zA-Z]+)$");
    private static final Pattern ABC_ONLY_PATTERN = Pattern.compile("[a-zA-Z]+");
    private static final Pattern HAS_OPERATORS_PATTERN = Pattern.compile("[\\+\\-\\*\\/\\^]");

    public static String normalizeOp(String op) {
        return Arrays.stream(op.split("")).reduce("", (a, b) -> {
            if (a.equals("-") && b.equals("-")) {
                return "+";
            } else if ((a.equals("-") && b.equals("+")) || (a.equals("+") && b.equals("-")) ) {
                return "-";
            } else if (a.equals(b)) {
                return a;
            }
            return b;
        });
    }

    static BigInteger getVariableValue (String element) throws UnknownVariableException {
        while (true) {
            try {
                BigInteger num = new BigInteger(element);
//                Integer num = Integer.parseInt(element);
                return num;
            } catch (NumberFormatException e1) {
                try {
                    String varValue = mapStorage.get(element);
//                    return Integer.parseInt(varValue);
                    return new BigInteger(varValue);
                } catch (NullPointerException e2) {
                    if (mapStorage.containsKey(element)) {
                        element = mapStorage.get(element);
                    } else {
                        throw new UnknownVariableException("Unknown variable: " + element);
                    }
                }
            }
        }
    }


    public static String infixToPostfix(String infix) throws InvalidExpressionException {
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/",2, "^", 3);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(' ');
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    throw new InvalidExpressionException("Invalid Expression");
                }
            } else if (c == ' ') {
                postfix.append(' ');
            } else {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.getOrDefault(String.valueOf(c), 0) <= precedence.getOrDefault(String.valueOf(stack.peek()), 0)) {
                    postfix.append(' ');
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new InvalidExpressionException("Invalid Expression");
            }
            postfix.append(' ');
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    public static BigInteger eval(String input) throws InvalidExpressionException, UnknownVariableException, InvalidIdentifierException {
        input = input.trim();
        String[] splitRes = input.split(" ");

        if (splitRes.length > 1 && !HAS_OPERATORS_PATTERN.matcher(input).find()) {
            throw new InvalidExpressionException("has no ops: " + input);
        } else if (splitRes.length == 1 && VALID_VARIABLE_OR_INT_PATTERN.matcher(splitRes[0]).matches()) {
            return getVariableValue(splitRes[0]);
        }

        String infixSimplified = simplify(input);
        String infixWithSpaces = addSpaceAroundOperators(infixSimplified);
        String postfix = infixToPostfix(infixWithSpaces);
        BigInteger result = evaluatePostfix(postfix);
        return result;
    }

    public static void inputReadingLoop(Scanner s) {
        mapStorage = new HashMap<>();
        out:
        for (; ; ) {
            for (; ; ) {
                String input = s.nextLine();
                input = input.trim();
                Pattern detectDeclareAssign = Pattern.compile("^\\w+\\s*[=]\\s*(?:-?\\d+|\\w+)+");
                if (input.isEmpty()) {
                    break;
                } else if (input.charAt(0) == '/') {
                    if (input.equals("/exit")) {
                        System.out.println("Bye!");
                        break out;
                    } else if (input.equals("/help")) {
                        System.out.println("The program calculates numbers");
                        break;
                    } else {
                        System.out.println("Unknown command");
                        break;
                    }
                } else if (detectDeclareAssign.matcher(input).find()) {
                    try {
                        String[] split = input.split("\\s*=\\s*");
                        split[0] = split[0].trim();
                        split[1] = split[1].trim();
                        if (!VALID_VARIABLE_OR_INT_PATTERN.matcher(split[0]).matches()) {
                            throw new InvalidIdentifierException();
                        }
                        try {
                              new BigInteger(split[1]);
//                            Integer.parseInt(split[1]);
                        } catch (NumberFormatException e) {
                            if (!ABC_ONLY_PATTERN.matcher(split[1]).find() || !mapStorage.containsKey(split[1])) {
                                throw new InvalidAssignmentException();
                            }
                        }
                        mapStorage.put(split[0], split[1]);
                    } catch (InvalidIdentifierException e) {
                        System.out.println("Invalid identifier");
                    } catch (InvalidAssignmentException e) {
                        System.out.println("Invalid assignment");
                    }
                    break;
                } else {
                    try {
                        BigInteger result = eval(input);
                        System.out.println(result);
                    } catch (InvalidExpressionException e) {
                        System.out.println("Invalid expression");
                    } catch (UnknownVariableException e) {
                        System.out.println("Unknown variable");
                    } catch (InvalidIdentifierException e) {
                        System.out.println("Invalid identifier");
                    }
                    break;
                }
            }
        }
    }

    static  BigInteger performOp (String operator, BigInteger a, BigInteger b) {
       if (operator.equals("+")) {
           return a.add(b);
       } else if (operator.equals("-")) {
           return a.subtract(b);
       } else if (operator.equals("*")) {
           return a.multiply(b);
       } else if (operator.equals("/")) {
           return a.divide(b);
       } else if (operator.equals("^")) {
           BigInteger pow = a.pow(b.intValue());
           return pow;
       }
       return new BigInteger(String.valueOf(0));
    }

    public static BigInteger evaluatePostfix(String postfix) throws UnknownVariableException {
        ArrayDeque<BigInteger> stack = new ArrayDeque<>();
        String[] elements = postfix.split("\\s+");
        for (String element : elements) {
            if (HAS_OPERATORS_PATTERN.matcher(element).matches()) {
                BigInteger a = stack.pop();
                BigInteger b = stack.pop();
                // RPN inverses so, we need to do pass b first and a second
                BigInteger result = performOp(element, b, a);
                stack.push(result);
            } else if (VALID_VARIABLE_OR_INT_PATTERN.matcher(element).matches()) {
                stack.push(getVariableValue(element));
            }
        }
        return stack.peek();
    }

    public static String addSpaceAroundOperators(String infix) {
        StringBuilder infixWithSpaces = new StringBuilder();
        for (char c : infix.toCharArray()) {
           if(Character.isLetterOrDigit(c)){
               infixWithSpaces.append(c);
           } else if (HAS_OPERATORS_PATTERN.matcher(String.valueOf(c)).matches()) {
               infixWithSpaces.append(" ").append(c).append(" ");
           } else {
               infixWithSpaces.append(c);
           }
        }
        return infixWithSpaces.toString();
    }

    public static String simplify(String infix ) throws InvalidExpressionException {
        Pattern manyMultiply = Pattern.compile("\\*{2,}");
        Pattern manyDiv = Pattern.compile("\\/{2,}");
        Pattern manyPow = Pattern.compile("\\^{2,}");
        if (manyMultiply.matcher(infix).find() || manyDiv.matcher(infix).find() || manyPow.matcher(infix).find()) {
            throw new InvalidExpressionException();
        }
        infix = infix.replaceAll("--", "+");
        infix = infix.replaceAll("\\+{2,}", "+"); // Replace 2 or more '+' with a single '+'

        return infix.replaceAll("(?<=.)-\\+|\\+-", "-")  // Replace "-+" or "+-" with "-"
                .replaceAll("\\+-|-\\+", "-");       // Replace "+-" or "-+" with "-"
    }
}
