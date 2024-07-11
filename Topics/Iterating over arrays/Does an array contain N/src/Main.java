import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        int intForLookup = scanner.nextInt();

        boolean isFound = false;
        for(int i: arr) {
            if(i == intForLookup) {
                isFound = true;
                break;
            }
        }
        System.out.println(isFound);
    }
}