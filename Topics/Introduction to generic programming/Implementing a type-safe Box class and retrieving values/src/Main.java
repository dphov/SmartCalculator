import java.util.*;

public class Main {
    static class Box<T> {
        T t;
        T get() {
           return this.t;
        }
        void set(T t) {
            this.t = t;
        }

        Box(T t) {
            this.t = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        if(sc.hasNextInt()) {
            int num = sc.nextInt();
            Box<Integer> box = new Box<>(num);
            System.out.println(box.get());
        } else if (sc.hasNextFloat()) {
            float num = sc.nextFloat();
            Box<Float> box = new Box<>(num);
            System.out.println(box.get());
        } else {
            String str = sc.next();
            Box<String> box = new Box<>(str);
            System.out.println(box.get());
        }
    }
}