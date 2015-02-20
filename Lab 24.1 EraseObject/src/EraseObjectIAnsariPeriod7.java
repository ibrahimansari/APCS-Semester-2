/*
Ibrahim Ansari
Period 7
2/3/2015

Lab 24.1 ErasureObject

Time Spent: 30 minutes

Reflection:
This was a very easy  to understand lab. After playing around with the maze solving code, it was easy to
understand how to accomplish this lab with recursion. It made sense to to go in all four directions and
erase. I was stuck on small mistake in which I was using a temporary array and I remembered Mr. Ferrante
telling us in class that would cause a StackOverflow and it did. So I just deleted that portion and it
worked fine. Overall a easy lab!
 */

import java.util.Scanner;
import java.io.*;

public class EraseObjectIAnsariPeriod7 {
    char[][] picture;
    static Scanner key = new Scanner(System.in);

    public static void main(String[] args) {
        EraseObjectIAnsariPeriod7 erase = new EraseObjectIAnsariPeriod7();

        erase.loadFile("digital.txt");
        System.out.println("Image before an erasure\n");
        while (true) {
            erase.print();
            System.out.print("Enter y-coordinate (row) of point to erase: ");
            int r = key.nextInt();
            System.out.println();
            System.out.print("Enter x-coordinate (col) of point to erase: ");
            int c = key.nextInt();
            System.out.println();
            erase.eraseObject(r,c);
            System.out.println("Image after erasure\n");
            erase.print();
            System.out.print("Do you want to erase again? (y/n): ");
            String ans = key.next();
            System.out.println();
            if (ans.equals("n"))
                break;
            else if (ans.equals("y"))
                continue;
        }
    }
    
    public void loadFile(String fileName) {
        picture = new char[21][21];
        for (int r = 0; r < picture.length; r++)
            for (int c = 0; c < picture[0].length; c++)
                picture[r][c] = '-';
        try {
            Scanner in = new Scanner(new File(fileName));
            in.nextInt();
            while (in.hasNext()) {
                int r = in.nextInt();
                int c = in.nextInt();
                picture[r][c] = '@';
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.print("   ");
        for (int i = 1; i < picture[0].length; i++) {
            System.out.print(i % 10);
        }
        System.out.println();
        for (int row = 1; row <= 20; row++) {
            if (row < 10)
                System.out.print(" " + row + " ");
            else
                System.out.print(row + " ");
            for (int col = 1; col <= 20; col++)
                System.out.print("" + picture[row][col]);
            System.out.println();
        }
        System.out.println();
    }

    public void eraseObject(int row, int col) {
        if (picture[row][col] == '@' && !(row == 20 || row == 0)) {
            picture[row][col] = '-';
            eraseObject(row - 1, col);
            eraseObject(row, col + 1);
            eraseObject(row + 1, col);
            eraseObject(row, col - 1);
        }
    }
}