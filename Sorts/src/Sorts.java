import java.util.*;

public class Sorts {
    private long steps;

    public Sorts() {
        steps = 0;
    }

    public void bubbleSort(ArrayList<Comparable> list) {
        steps++; // init list
        steps += 4; // for loop init
        for (int i = 0; i <= list.size() - 1; i++) {
            steps += 4; // for loop init
            for (int j = list.size() - 1; j > i; j--) {
                steps += 5; // if condition check
                if (list.get(j).compareTo(list.get(j - 1)) > -1) {
                    steps++; // swap method called
                    swap(list, j, j - 1);
                }
                steps += 2; // decrement j and check condition
            }
            steps += 2; // increment i and check condition
        }
    }

    public void selectionSort(ArrayList<Comparable> list) {
        steps++; // init list
        int min;
        Comparable temp;
        steps += 2; // variable declaration
        steps += 5; // for loop init
        for (int i = 0; i < list.size() - 1; i++) {
            steps++; // assignment operator
            min = i;
            steps += 5; // for loop init
            for (int j = i + 1; j < list.size(); j++) {
                steps += 4; // if conditional
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    steps++; // assignment operator
                    min = j;
                }
                steps += 2; // increment j and check condition
            }
            steps++; // swap method called
            swap(list, min, i);
            steps += 2; // increment i and check condition
        }
    }

    public void insertionSort(ArrayList<Comparable> list) {
        steps++; // init list
        steps += 4; // init for loop
        for (int i = 1; i < list.size(); i++) {
            steps += 5; // assignment operator
            int pos = i;
            Comparable key = list.get(pos);
            steps += 6; // while loop init
            while (pos > 0 && key.compareTo(list.get(pos - 1)) > 0) {
                list.set(pos, list.get(pos - 1));
                steps += 3; // set value
                pos--;
                steps++; // decrement pos
            }
            steps++; // set value
            list.set(pos, key);
            steps += 2; // increment i and check condition
        }
    }

    public void merge(ArrayList<Comparable> a, int first, int mid, int last) {
        steps += 4; // init parameters
        ArrayList<Comparable> temp = new ArrayList<Comparable>();
        steps += 2; // init temp
        int leftIndex = first;
        steps += 2; // init leftIndex
        int rightIndex = mid + 1;
        steps += 3; // init rightIndex
        steps += 3; // while loop comparisons
        while (leftIndex <= mid && rightIndex <= last) {
            steps += 4; // if statement
            if (a.get(leftIndex).compareTo(a.get(rightIndex)) < 0) {
                steps += 2; // add and get method
                temp.add(a.get(leftIndex));
                steps++; // increment leftIndex
                leftIndex++;
            } else {
                steps += 2; // add and get method
                temp.add(a.get(rightIndex));
                steps++; // increment rightIndex
                rightIndex++;
            }
            steps += 3; // check conditionals again
        }
        steps++; // while loop comparison
        while (leftIndex <= mid) {
            steps += 2; // add and get method
            temp.add(a.get(leftIndex));
            steps++; // increment leftIndex
            leftIndex++;
            steps++; // check loop conditionals
        }
        steps++; // while loop conditions
        while (rightIndex <= last) {
            steps += 2; // add and get methods
            temp.add(a.get(rightIndex));
            steps++; // increment rightIndex
            rightIndex++;
            steps++; // check loop conditionals
        }
        steps += 2; // variable declarations
        int i = 0;
        int j = first;
        steps += 2; // while loop comparisons
        while (i < temp.size()) {
            steps += 3; // set, get, and increment i
            a.set(j, temp.get(i++));
            steps++; // increment j
            j++;
            steps += 2; // check loop conditionals
        }
    }

    public void mergeSort(ArrayList<Comparable> a, int first, int last) {
        steps += 3; // init parameters
        steps += 4; // if statement conditionals
        if (first < last && (last - first) >= 1) {
            steps += 3; // variable declaration
            int mid = (last + first) / 2;
            steps++; // merge sort recursive call
            mergeSort(a, first, mid);
            steps += 2; // merge sort recursive call
            mergeSort(a, mid + 1, last);
            steps++; // merge method
            merge(a, first, mid, last);
        }
    }

    public long getStepCount() {
        return steps;
    }

    public void setStepCount(long stepCount) {
        steps = stepCount;
    }

    public void swap(ArrayList<Comparable> list, int a, int b) {
        steps += 3;
        Comparable temp = list.get(a);
        steps += 2;
        list.set(a, list.get(b));
        steps += 2;
        list.set(b, temp);
        steps++;
    }
}
