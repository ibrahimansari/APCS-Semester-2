/*
Ibrahim Ansari
Period 7
1/8/2105

Lab 17.0 QuadSorts

Time Spent: 35 minutes

Reflection:
This was a pretty straight-forward lab and easy to get hold on. I like the concept of
different sorting algorithm anf how they have different efficiencies. Like for example Bubble
sort is probably not the best sort to use if not for a conceptual purpose, i.e. it is only
good for people learning how sorting is done in a simple way. I think I understand all the
sorting algorithms so far. I can't wait until we get into more practical ones though, because
those are more complex. I also learned to use Comparable and teh method .compareTo() in this
lab. This was a good lab overall.
 */

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Lab17_0_IAnsariPeriod7 {

    public static void main(String[] args) {

        Lab17_0_IAnsariPeriod7 lab = new Lab17_0_IAnsariPeriod7();

        ArrayList <Comparable> list = lab.initializeList();
        ArrayList <Comparable> copy = lab.duplicate(list);

        System.out.println("Before Bubble Sort:");
        System.out.println(list);

        lab.bubbleSort(list);	// runs your Bubble Sort code
        Collections.sort(copy);	// runs built-in sorting code
        Collections.reverse(copy);

        System.out.println("After Bubble Sort:");
        System.out.println(list);
        System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");

        list = lab.initializeList();
        copy = lab.duplicate(list);
        System.out.println("\nBefore Selection Sort:");
        System.out.println(list);

        lab.selectionSort(list);	// runs your Selection Sort code
        Collections.sort(copy);		// runs built-in sorting code

        System.out.println("After Selection Sort:");
        System.out.println(list);
        System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");

        list = lab.initializeList();
        copy = lab.duplicate(list);
        System.out.println("\nBefore Insertion Sort:");
        System.out.println(list);

        lab.insertionSort(list);	// runs your Insertion Sort code
        Collections.sort(copy);		// runs built-in sorting code
        Collections.reverse(copy);

        System.out.println("After Insertion Sort:");
        System.out.println(list);
        System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");
    }

    /* Write code for list Bubble Sort algorithm that starts at the right side of
     * of ArrayList of Comparable objects and "bubbles" the largest item to the
     * left of the list.  The result should be an ArrayList arranged in descending
     * order.
    */
    void bubbleSort(ArrayList <Comparable> list) {
        for(int i = 0; i <= list.size() - 1; i++) {
            for(int j = list.size() - 1; j > i; j--) {
                if(list.get(j).compareTo(list.get(j - 1)) > -1) {
                    Comparable temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                }
            }
        }
    }

    /* Write code for a Selection Sort algorithm that starts at the left side
     * of an ArrayList of Comparable objects and searches through the list for
     * the largest item and then swaps it with the last item in the list.  The
     * "last item" then becomes the item to its left. The result should be
     * an ArrayList arranged in ascending order.
    */
    void selectionSort(ArrayList <Comparable> list) {
        int min;
        Comparable temp;

        for (int i = 0; i < list.size()-1; i++) {
            min = i;

            for (int j = i+1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(min)) < 0) {
                    min = j;
                }
            }
            temp = list.get(min);
            list.set(min, list.get(i));
            list.set(i, temp);
        }
    }

    /* Write code for an Insertion Sort algorithm that starts at the left side
     * of an ArrayList of Comparable objects and inserts the first item (in
     * position 1) into it's correct place within the first two items...then
     * inserts the third item into its correct place on the left, then the fourth
     * item into its correct place on the left, etc, until the last item is
     * inserted into the list.  Insert items so the result is an ArrayList arranged
     * in descending order.
    */
    void insertionSort(ArrayList <Comparable> list) {
        for (int i = 1; i < list.size(); i++){
            int pos = i;
            Comparable key = list.get(pos);
            
            while (pos > 0 && key.compareTo(list.get(pos-1)) > 0){
                list.set(pos, list.get(pos - 1));
                pos--;
            }
            list.set(pos, key);
        }
    }

    ArrayList <Comparable> initializeList() {

        String[] words = {"apple", "orange", "banana", "pear", "peach", "plum",
                "grape", "cherry", "apricot", "strawberry"};

        ArrayList <Comparable> temp = new ArrayList<Comparable>();
        ArrayList <Comparable> list = new ArrayList<Comparable>();

        for (int i = 0; i < words.length; i++)
            temp.add(words[i]);

        list.clear(); // clear the list before adding to it

        while (temp.size() > 0) {
            list.add(temp.remove((int)(Math.random()*temp.size())));
        }

        return list;
    }

    ArrayList <Comparable> duplicate(ArrayList<Comparable> list) {

        ArrayList<Comparable> listCopy = new ArrayList<Comparable>();

        Iterator<Comparable> iter = list.iterator();

        while(iter.hasNext()){
            listCopy.add(iter.next());
        }

        return listCopy;
    }
}