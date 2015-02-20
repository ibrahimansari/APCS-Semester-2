/*
Ibrahim Ansari
Period 7
1/19/2015

Lab 19.2 Search

Time Spent 20 minutes

Reflection:
This was a very easy lab. I get the concept of Binary Search. One problem I
encountered during this lab was that I wasn't using recursion correctly. I spent
a long time debugging until I realized I forgot my return statements. After this
small fix, my code worked perfectly. Overall a easy lab.
 */

import java.io.File;
import java.util.*;

public class SearchDriverIAnsariPeriod7 {

    public static void main(String[] args) {
        Store s = new Store("file50.txt");
        s.sort();
        s.displayStore();
        s.testSearch();
    }
}


class Item implements Comparable<Item> {

    private int myId;
    private int myInv;

    /**
     * Constructor for the Item object
     *
     * @param id  id value
     * @param inv inventory value
     */
    public Item(int id, int inv) {
        myId = id;
        myInv = inv;
    }

    /**
     * Gets the id attribute of the Item object
     *
     * @return The id value
     */
    public int getId() {
        return myId;
    }

    /**
     * Gets the inv attribute of the Item object
     *
     * @return The inv value
     */
    public int getInv() {
        return myInv;
    }

    /**
     * Compares this item to another item based on id number. Returns the
     * difference between this item's id and the other item's id. A
     * difference of zero means the items' ids are equal in value.
     *
     * @param other Item object to compare to
     * @return positive int if myId > other.myId
     * 0 if myId == other.myId
     * negative int if myId < other.myId
     */
    public int compareTo(Item other) {
        return myId == other.myId ? 0 : myId - other.myId;
    }

    /**
     * Compares the Item to the specified object
     *
     * @param other Item object to compare to
     * @return true if equal, false otherwise
     */
    public boolean equals(Item other) {
        return compareTo(other) == 0;
    }

    /**
     * Overrides the default toString() of Object.
     * Returns a String representation of this object. It's up to you
     * exactly what this looks like.
     */
    public String toString() {
        return "ID: " + myId + " Inventory: " + myInv;
    }
}


class Store {

    private ArrayList<Item> myStore = new ArrayList<Item>();

    /**
     * Creates a Store object from data stored in the given file name
     *
     * @param fName name of the file containing id/inv pairs of data
     */
    public Store(String fName) {
        loadFile(fName);
    }

    public void testSearch(){
        int idToFind;
        int invReturn;
        int index;
        Scanner in = new Scanner(System.in);

        System.out.println("Testing search algorithm\n");
        do{
            System.out.println();
            System.out.print("Enter Id value to search for (-1 to quit) ---> ");
            idToFind = in.nextInt();
            //index = bsearch(new Item(idToFind, 0));
            //recursive version call
            index = bsearch (new Item(idToFind, 0), 0, myStore.size()-1);
            System.out.print("Id # " + idToFind);
            if (index == -1){
                System.out.println(" No such part in stock");
            }else{
                System.out.println(" Inventory = " + myStore.get(index).getInv());
            }
        } while (idToFind >= 0);
    }

    /**
     * Searches the myStore ArrayList of Item Objects for the specified
     * item object using a iterative binary search algorithm
     *
     * @param idToSearch Item object containing id value being searched for
     * @return index of Item if found, -1 if not found
     */

    private int bsearch(Item idToSearch){
        int val = idToSearch.getId();
        int f = 0;
        int l = myStore.size() - 1;
        int mid = (f + l) / 2;

        while (true) {
            if (mid == 0) {
                return -1;
            } else if (myStore.get(mid).getId() < val) {
                f = mid + 1;
                mid = (f + l) / 2;
            } else if (myStore.get(mid).getId() > val) {
                l = mid;
                mid = (f + l) / 2;
            } else if (myStore.get(mid).getId() == val) {
                return mid;
            }
        }
    }

    /**
     * Searches the specified ArrayList of Item Objects for the specified
     * id using a recursive binary search algorithm
     *
     * @param idToSearch Id value being search for
     * @param first Starting index of search range
     * @param last Ending index of search range
     * @return index of Item if found, -1 if not found
     */

    private int bsearch(Item idToSearch, int first, int last){
        int val = idToSearch.getId();
        int mid = (first + last) / 2;

        if (first > last) {
            return -1;
        } else if (myStore.get(mid).getId() == val) {
            return mid;
        } else if (myStore.get(mid).getId() < val) {
            return bsearch(idToSearch, mid + 1, last);
        } else {
            return bsearch(idToSearch, first, mid - 1);
        }
    }

    /**
     * Reads a file containing id/inv data pairs one pair per line.
     *
     * @param inFileName name of file containing id/inv pairs of data
     */
    private void loadFile(String inFileName) {
        try {
            File inFile = new File(inFileName);
            Scanner in = new Scanner(inFile);

            while (in.hasNext()) {
                Item item = new Item(in.nextInt(), in.nextInt());
                myStore.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the store contents in the format shown below
     * Line #   	Id	     	Inv
     * 1	       	184	    	14
     * 2	       	196	    	60
     */
    public void displayStore() {
        System.out.printf("%1s  %3s %9s", "Line #", "ID", "Inv");
        System.out.println();
        for (int i = 0; i < myStore.size(); i++) {
            System.out.printf("%1d  %9d  %6d", i + 1, myStore.get(i).getId(), myStore.get(i).getInv());
            System.out.println();
        }
    }

    /**
     * Sorts the store ArrayList using recursive mergesort
     */
    public void sort() {
        mergeSort(myStore, 0, myStore.size() - 1);
    }

    private void merge(ArrayList<Item> a, int first, int mid, int last) {
        ArrayList<Item> temp = new ArrayList<Item>();
        int leftIndex = first;
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= last) {
            if (a.get(leftIndex).compareTo(a.get(rightIndex)) < 0) {
                temp.add(a.get(leftIndex));
                leftIndex++;
            } else {
                temp.add(a.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex <= mid) {
            temp.add(a.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= last) {
            temp.add(a.get(rightIndex));
            rightIndex++;
        }
        int i = 0;
        int j = first;
        while (i < temp.size()) {
            a.set(j, temp.get(i++));
            j++;
        }
    }

    /**
     * Recursive mergesort of an ArrayList of Items
     *
     * @param a     reference to an ArrayList of Items to be sorted
     * @param first starting index of range of values to be sorted
     * @param last  ending index of range of values to be sorted
     */
    public void mergeSort(ArrayList<Item> a, int first, int last) {
        if (first < last && (last - first) >= 1) {
            int mid = (last + first) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
    }
}