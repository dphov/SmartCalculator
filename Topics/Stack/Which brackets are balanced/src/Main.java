import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    static private final  Pattern OPENING_BRACKET = Pattern.compile("^(\\{)|(\\()|(\\[)");
    static private final Pattern CLOSING_BRACKET = Pattern.compile("^(\\})|(\\))|(\\])");

    public static Character getOpeningBracket(Character input) {
        if (input == '}') {
            return '{';
        }
        if (input == ')') {
            return '(';
        }
        if (input == ']') {
            return '[';
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Character> stack = new ArrayDeque<>();

        String input = scanner.nextLine();
        CharacterIterator it =  new StringCharacterIterator(input);

        while (it.current() != CharacterIterator.DONE) {
            if (OPENING_BRACKET.matcher(Character.toString(it.current())).matches()) {
                stack.push(it.current());
            } else if (CLOSING_BRACKET.matcher(Character.toString(it.current())).matches()) {
                if (stack.isEmpty()) {
                    System.out.println(false);
                    return;
                }
                Character poppedStartBracket = stack.pop();
                boolean areClosing = Character.compare(poppedStartBracket, getOpeningBracket(it.current())) == 0;
                if (!areClosing) {
                    System.out.println(areClosing);
                    return;
                }
            }
            it.next();
        }
        if (!stack.isEmpty()) {
            System.out.println(false);
        } else {
            System.out.println(true);
        }
        scanner.close();
    }
}