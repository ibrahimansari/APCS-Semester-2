/*
Ibrahim Ansari
Period 7
1/22/2015

Lab 19.3 CountWords

Time Spent: 1 hour

Reflection:
This lab was pretty scary looking at first glance but when you think about it, it becomes much easier. I
was first confused on how to store frequency relative to a word and was looking into using a HashTable. I
started off with HashTable and realized it could be done easily with a custom Word class. I then did a similar
thing as lab 19.2. The stripping was a little difficult because it was hard to handle the special cases. I just
stripped everything off that wasn't a apostrophe or a hyphen in a middle of a word. Hyphens separated by spaces
are their own String and return a empty String. I don't add empty strings. After adding and incrementing frequency
accordingly, I sort alphabetically first, then by frequency. Overall a easy but time consuming lab!
 */

import java.util.*;
import java.io.File;

public class CountWordsDriverIAnsariPeriod7 {
    public static void main(String[] args) {
        CountWords word = new CountWords();
        word.countAndPrint();
    }
}

class CountWords {
    ArrayList<Word> list = new ArrayList<Word>();
    Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    File file = new File("dream.txt");
    Scanner in;
    int total = 0, ind;
    private boolean used = false;

    public void countAndPrint() {
        try {
            in = new Scanner(file);
            while (in.hasNext()) {
                String val = strip(in.next());
                if (!val.equals("")) {
                    Word x = new Word();
                    x.setVal(val);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals(x)) {
                            used = true;
                            ind = i;
                            break;
                        }
                    }
                    if (!used) {
                        list.add(x);
                    } else {
                        list.get(ind).incFreq();
                    }
                    used = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sort();

        for (Word aList : list) {
            total += aList.getFreq();
        }

        for (int i = 1; i <= 30; i++) {
            System.out.printf("%2s %4s \n", i + " ", list.get(i - 1).toString());
        }

        System.out.println();
        System.out.println("Total number of unique words used in the file: " + list.size());
        System.out.println("Total number of words in a file: " + total);
    }

    String strip(String inputString) {
        String output = "";
        inputString = inputString.toLowerCase();

        if (inputString.length() < 1) {
            return "";
        } else if (inputString.equals("-")) {
            return "";
        }

        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isLetterOrDigit(inputString.charAt(i)) || inputString.charAt(i) == 39 || inputString.charAt(i) == 45) {
                output = output + inputString.charAt(i);
            }
        }

        return output;
    }

    public void sort() {
        mergeValSort(list, 0, list.size() - 1);
        mergeSort(list, 0, list.size() - 1);
    }

    private void merge(ArrayList<Word> a, int first, int mid, int last) {
        ArrayList<Word> temp = new ArrayList<Word>();
        int leftIndex = first;
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= last) {
            if (a.get(leftIndex).compareTo(a.get(rightIndex)) > 0) {
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

    private void mergeVal(ArrayList<Word> a, int first, int mid, int last) {
        ArrayList<Word> temp = new ArrayList<Word>();
        int leftIndex = first;
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= last) {
            if (a.get(leftIndex).compareValTo(a.get(rightIndex)) > 0) {
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

    public void mergeSort(ArrayList<Word> a, int first, int last) {
        if (first < last && (last - first) >= 1) {
            int mid = (last + first) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
    }

    public void mergeValSort(ArrayList<Word> a, int first, int last) {
        if (first < last && (last - first) >= 1) {
            int mid = (last + first) / 2;
            mergeValSort(a, first, mid);
            mergeValSort(a, mid + 1, last);
            mergeVal(a, first, mid, last);
        }
    }
}

class Word implements Comparable<Word>{
    private int freq = 1;
    private String val;

    public void setVal(String x) {
        val = x;
    }

    public String getVal() {
        return val;
    }

    public int getFreq() {
        return freq;
    }

    public void incFreq() {
        freq++;
    }

    public String toString() {
        return freq + "  " + val;
    }

    public boolean equals(Word other) {
        return compareValTo(other) == 0;
    }

    public int compareValTo(Word other) {
        return val.equals(other.getVal()) ? 0 : val.compareTo(other.getVal());
    }

    @Override
    public int compareTo(Word other) {
        return freq == other.freq ? 0 : freq - other.freq;
    }
}