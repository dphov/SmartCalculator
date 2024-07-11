import java.util.Scanner;

class Main {
    public static boolean isTriplet(int[] arr, int indexToCheckFrom) {
        if (indexToCheckFrom + 2 >= arr.length || indexToCheckFrom + 1 >= arr.length) {
            return false;
        }
        boolean firstHalf = arr[indexToCheckFrom] == arr[indexToCheckFrom + 1] - 1;
        boolean secondHalf = arr[indexToCheckFrom] == arr[indexToCheckFrom + 2] - 2;

        return firstHalf && secondHalf;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int triplets = 0;
        int indexToCheckFrom = 0;

        while (indexToCheckFrom + 2 < arr.length || indexToCheckFrom + 1 < arr.length) {
            if (isTriplet(arr, indexToCheckFrom)) {
                triplets++;
            }

            indexToCheckFrom++;
        }
        System.out.println(triplets);
        scanner.close();
    }
}