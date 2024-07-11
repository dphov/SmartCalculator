package calculator;

import java.util.*;
import java.util.regex.Pattern;


public class Main {
    private static final Pattern VALID_IDENTIFIER_PATTERN = Pattern.compile("^(?:\\d+|[a-zA-Z]+)$");
    private static final Pattern ABC_ONLY_PATTERN = Pattern.compile("[a-zA-Z]+");
    public static boolean handleCalcInput(List<Object> input) {
        if(input.isEmpty()) {
            return false;
        }

        if(input.size() == 1) {
            if (input.get(0).equals("")) {
                return false;
            }
            System.out.println(input.get(0));
            return false;
        }
        return true;
    }

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

    static List<Object> normalize(List<Object> elements) throws InvalidExpressionException, UnknownVariableException, InvalidIdentifierException {
        List<Object> newElements = new ArrayList<>(elements.size()) ;
        Pattern hasOpAfter = Pattern.compile("^\\d+[+-]");
        Pattern hasAbc = Pattern.compile("[a-zA-Z]+");
        for (int i = 0; i < elements.size(); i++) {
            Object elem = elements.get(i);
            if (elem instanceof String str) {
                if (str.length() > 1 ) {
                    if( (str.charAt(0) == '-' || str.charAt(0) == '+' ) && (str.charAt(1) == '-' || str.charAt(1) == '+')) {
                        newElements.add(normalizeOp(str));
                    } else if (!VALID_IDENTIFIER_PATTERN.matcher(str).matches()) {

                        throw new InvalidIdentifierException("Invalid identifier: " + str );
                    } else if (hasOpAfter.matcher(str).find())  {
                        throw new InvalidExpressionException("has Op after number: " + str);
                    } else if (hasAbc.matcher(str).find()) {
                        throw new InvalidExpressionException("has letters: " + str);
                    } else {
                        newElements.add(Integer.parseInt(str));
                    }
                } else {
                    if( str.charAt(0) == '-' || str.charAt(0) == '+') {
                        newElements.add(normalizeOp(str));
                    } else {
                        while (true) {
                            try {
                                Integer num = Integer.parseInt(str);
                                newElements.add(num);
                                break;
                            } catch (NumberFormatException e1) {
                                try {
                                    String varValue = map.get(str);
                                    Integer numFromVariable = Integer.parseInt(varValue);
                                    newElements.add(numFromVariable);
                                    break;
                                } catch (NumberFormatException e2) {
                                    if (map.containsKey(str)) {
                                        str = map.get(str);
                                    } else {
                                        throw new UnknownVariableException("Unknown variable: " + str);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return newElements;
    }
    public static void eval(String input) throws InvalidExpressionException, UnknownVariableException, InvalidIdentifierException {

        String[] splitRes = input.split(" ");
        List<Object> elements = Arrays.asList(splitRes);

        Pattern hasOps = Pattern.compile("[+-]");
        if (elements.size() > 1 && !hasOps.matcher(input).find()) {
            throw new InvalidExpressionException("has no ops: " + input);
        }

        List<Object> readyToCalcElements = normalize(elements);

        if (readyToCalcElements.size() == 1) {
            System.out.println(readyToCalcElements.get(0));
            return;
        }

        int acc = 0;
        int tmpX = 0;
        String tmpOp = "";
        for (Object elem : readyToCalcElements) {
            if (elem instanceof String) {
                tmpOp = (String) elem;
            } else if (elem instanceof Integer) {
                if (tmpOp.equals("+")) {
                    acc += tmpX + (int) elem;
                    tmpX = 0;
                    tmpOp = "";
                } else if (tmpOp.equals("-")) {
                    acc += tmpX - (int) elem;
                    tmpX = 0;
                    tmpOp = "";
                } else {
                    tmpX = (int) elem;
                }
            }
        }

        System.out.println(acc);
    }


    static Map<String, String> map;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        map = new HashMap<>();
        out:
        for (;;) {
            for (;;) {
                String input = scanner.nextLine();
                Pattern detectDeclareAssign = Pattern.compile("^\\w+\\s*[=]\\s*(?:\\d+|\\w+)+");
                if (input.isEmpty()) {
                    break;
                } else if (input.charAt(0) == '/') {
                    // command flow
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
                        if (!VALID_IDENTIFIER_PATTERN.matcher(split[0]).matches()) {
                            throw new InvalidIdentifierException();
                        }
                        try {
                           Integer.parseInt(split[1]) ;
                        } catch (NumberFormatException e) {
                            if (!ABC_ONLY_PATTERN.matcher(split[1]).find() || !map.containsKey(split[1])) {
                                throw new InvalidAssignmentException();
                            }
                        }
                        map.put(split[0], split[1]);
//                        map.forEach((k, v) -> {
//                            System.out.println("Key: " + k + " Value: " + v);
//                        });
                    } catch (InvalidIdentifierException e) {
                        System.out.println("Invalid identifier");
                    } catch (InvalidAssignmentException e) {
                        System.out.println("Invalid assignment");
                    }
                    break;
                } else {
                    try {
                        eval(input);
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

        scanner.close();
    }
}
