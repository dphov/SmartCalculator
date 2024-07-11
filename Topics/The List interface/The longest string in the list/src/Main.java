import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        Integer length = list.get(0).length();
        String str = list.get(0);
        for (int i = 1; i < list.size(); i++) {
           if (list.get(i).length() > length) {
              length = list.get(i).length();
              str = list.get(i);
           }
        }
        for (int i = 0; i < list.size(); i++) {
            list.set(i, str);
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}