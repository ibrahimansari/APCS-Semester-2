/*
 Ibrahim Ansari
 Period 7
 1/15/2015

 Lab 18.2 MergeSort

 Time Spent: 30 minutes

 Reflection:
 This lab was a fairly easy lab but required some debugging. I ran inti a couple of
 errors, but they were minor fixes. The hardest part about this lab was to make the
 merge method. We had to modify our old one to work with indexes as parameters instead
 of two already sorted Lists. Overall a fun lab.
 */

import java.util.ArrayList;

public class MergeSort {

    public void merge(ArrayList<Comparable> a, int first, int mid, int last) {
        ArrayList<Comparable> temp = new ArrayList<Comparable>();
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

    public void mergeSort(ArrayList<Comparable> a, int first, int last) {
        if (first < last && (last - first) >= 1) {
            int mid = (last + first) / 2;
            mergeSort(a, first, mid);
            mergeSort(a, mid + 1, last);
            merge(a, first, mid, last);
        }
    }
}
