import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int sumAllGreaterThatThis = scanner.nextInt();

        int sumHere = 0;

        for (int i: arr) {
            if (i > sumAllGreaterThatThis) {
                sumHere += i;
            }
        }

        System.out.println(sumHere);
    }
}