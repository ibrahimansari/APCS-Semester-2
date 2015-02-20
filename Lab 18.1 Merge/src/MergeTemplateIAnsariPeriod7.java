/*
Ibrahim Ansari
Period 7
1/13/2015

Lab 18.1 Merge

Time Spent 25 minutes

Reflection:
This lab took me a little bit longer than usual because I was absent teh day we did it in class,
so I learned it at home. I understand it now. I was stuck on a error for a long time. It was because I
was using the set method instead of the add method for ArrayLists. I realized that the ArrayList c was
not like a regular array so I couldn't set things based on index position, but I had to "add" them because
the List is not full of nulls. Overall though, this sort is way more efficient than the other sorts.
 */

import java.util.*;

public class MergeTemplateIAnsariPeriod7 {
	private Scanner in;

	/**
	*  Sorts any ArrayList of Comparable objects using Selection Sort.
	*
	* @param  list  reference to an array of integers to be sorted
	*/
	public void selectionSort(ArrayList <Comparable> list) {
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

	/**
	 *  Write a merge method to merge two sorted lists.
	 *
	 *  Preconditions: Lists A and B are sorted in nondecreasing order.
	 *  Action:        Lists A and B are merged into one list, C.
	 *  Postcondition: List C contains all the values from
	 *                 Lists A and B, in nondecreasing order.
	 */
	public void merge (ArrayList <Comparable> a, ArrayList <Comparable> b, ArrayList <Comparable> c) {
		int bIndex = 0;
		int aIndex = 0;
		int cIndex = 0;

		while (bIndex < b.size() && aIndex < a.size()) {
			if ((b.get(bIndex).compareTo(a.get(aIndex))) < 0) {
				c.add(cIndex, b.get(bIndex));
				bIndex++;
			} else {
				c.add(cIndex, a.get(aIndex));
				aIndex++;
			}
			cIndex++;
		}

		if (bIndex >= b.size()) {
			for (int i=aIndex; i<a.size(); i++) {
				c.add(cIndex, a.get(i));
				cIndex++;
			}
		} else {
			for (int i=bIndex; i<b.size(); i++) {
				c.add(cIndex, b.get(i));
				cIndex++;
			}
		}
	}

	/**
	*  Write a method to
	*    - Ask the user how many numbers to generate
	*    - Ask the user to enter the largest integer to generate
	*    - Initialize an ArrayList of random Integers from 1 to largestInt
	*	- Return the ArrayList
	*
	* @return  an ArrayList of size specified by the user filled
	*          with random numbers
	*/
	public ArrayList <Comparable> fillArray() {
		in = new Scanner(System.in);
		ArrayList<Comparable> myArray;

		System.out.print("How many numbers do you wish to generate? ");
		int numInts = in.nextInt();
		System.out.print("Largest integer to generate? ");
		int largestInt = in.nextInt();

		Random randGen = new Random();
		myArray = new ArrayList<Comparable>();

		for (int loop = 0; loop < numInts; loop++) {
			Integer x = new Integer(randGen.nextInt(largestInt) + 1);
			myArray.add(x);
		}

		return myArray;
	}

	/**
	*  Write a method to print out the contents of the ArrayList
	*  in tabular form, 20 columns.  You can use the \t escape character
	*  or use printf to format using fields.
	*/
	public void screenOutput(ArrayList <Comparable> temp) {
		for (int loop = 0; loop < temp.size(); loop++) {
			if (loop % 20 == 0) {
				System.out.println();
			}
			System.out.print(temp.get(loop) + "  ");
		}
		System.out.println();
	}
}