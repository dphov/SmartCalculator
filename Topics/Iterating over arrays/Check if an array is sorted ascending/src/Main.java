import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        array[0] = scanner.nextInt();
        boolean isSortedDesc = true;
        for(int i = 1; i < array.length; i++) {
           array[i] = scanner.nextInt();
           if (array[i - 1] > array[i]) {
               isSortedDesc = false;
               break;
           }
        }
        System.out.println(isSortedDesc);
    }
}