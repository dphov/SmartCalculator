import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] names = input.split(",");
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(String name : names) {
            set.add(name);
        }
        String[] newarr = set.toArray(new String[0]);
        System.out.println(String.join(",", newarr));
    }
}