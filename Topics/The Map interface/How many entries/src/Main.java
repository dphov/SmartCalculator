import java.util.*;

class Main {
    private static void log(Map<Long, String> map) {
        String msg = "";
        if (map.size() == 0) {
            msg = "There is no objects";
        } else if (map.size() == 1) {
            msg = "Something in the map";
        } else if (map.size() > 1) {
            msg = "Too many objects";
        }
        System.out.println(msg);
    }

    // do not change the code below
    public static void main(String[] args) {
        String valueBase = "value-";
        Scanner scanner = new Scanner(System.in);

        Map<Long, String> m = new HashMap<>();
        long size = scanner.nextLong();
        for (long i = 0; i < size; ++i) {
            Long key = i;
            String value = valueBase + i;
            m.put(key, value);
        }
        log(Map.copyOf(m));
    }
}