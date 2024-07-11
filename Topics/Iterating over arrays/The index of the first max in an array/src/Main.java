import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        int[] arr = new int[size];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        OptionalInt elemToSearch = Arrays.stream(arr).max();
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elemToSearch.getAsInt()) {
                index = i;
                break;
            }
        }

        System.out.println(index);
        scanner.close();
    }
}