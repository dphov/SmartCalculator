import java.util.List;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        // implement the method
        // there is no need to input data from the command line
        // instead, use arguments elem, list1 and list2
        int counter1 = 0;
        int counter2 = 0;
        for(Integer i : list1) {
            if(i.equals(elem)) {
                counter1 += 1;
            }
        }
        for(Integer i : list2) {
            if(i.equals(elem)) {
                counter2 += 1;
            }
        }
        return counter1 == counter2;
    }
}