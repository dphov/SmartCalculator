import java.util.Scanner;
import java.util.Arrays;

class Main {
    private static void rotate(int[] arr, int steps) {
        int i = 0;
        while (i < steps) {
            int lastElement = arr[arr.length - 1];
            for (int j = 0; j < arr.length; j++) {
                int tmp = arr[j];
                arr[j] = lastElement;
                lastElement = tmp;
            }
            i++;
        }
    }

    // do not change code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int steps = Integer.parseInt(scanner.nextLine());

        rotate(arr, steps);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}