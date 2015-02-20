/*
Ibrahim Ansari
Period 7
2/1/2015

Lab 23.1 Life

Time Spent: 60 minutes

Reflection:
This was definitely one of the harder labs. It wasn't hard to understand what to do, but to actually implement it.
I spent more time thinking than writing code. I didn't have to debug at all because once the thought out code was
written, it performed well. I had to write 3 helper methods to aid me in this lab. One returned the number of neighbors.
Another one was for making sure the neighbor was in th world and not out of bounds. And the last one was for telling
whether the neighbors are live or not. I decided to use a 2d array of boolean, because it would simplify my logic,
and it did make it easier. Overall a time consuming but good lab.
 */

import java.io.File;
import java.util.Scanner;

public class LifeIAnsariPeriod7 {
    private boolean[][] world;

    public static void main(String[] args) {
        LifeIAnsariPeriod7 life = new LifeIAnsariPeriod7("life100.txt");
        life.runLife(5);
        life.printBoard();
    }

    public LifeIAnsariPeriod7(String fileName) {
        try {
            Scanner in = new Scanner(new File(fileName));
            world = new boolean[in.nextInt()][in.nextInt()];
            while (in.hasNext()) {
                int r = in.nextInt();
                int c = in.nextInt();
                world[r][c] = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runLife(int numGenerations) {
        for (int i = 0; i < numGenerations; i++) {
            nextGeneration();
        }
    }

    public int rowCount(int row) {
        int inc = 0;
        for (int i = 0; i < world[row].length; i++) {
            if (world[row][i])
                inc++;
        }
        return inc;
    }

    public int colCount(int col) {
        int inc = 0;
        for (int i = 0; i < world.length; i++) {
            if (world[i][col])
                inc++;
        }
        return inc;
    }

    public int totalCount() {
        int inc = 0;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j])
                    inc++;
            }
        }
        return inc;
    }

    public void printBoard(){
        String s = "";
        int r = 0;
        System.out.print("   ");
        for (int i = 0; i < world[0].length; i++) {
            if (i > 9)
                System.out.print(i - 10);
            else
                System.out.print(i);
        }
        System.out.println();
        for(boolean[] row : world){
            if (r < 10)
                s += " " + r + " ";
            else
                s += r + " ";
            for(boolean val : row) {
                if (val)
                    s += "*";
                else
                    s += " ";
            }
            s += "\n";
            r++;
        }
        System.out.println(s);
        System.out.println("Number of living cells in row 9 --> " + rowCount(9));
        System.out.println("Number of living cells in col 9 --> " + colCount(9));
        System.out.println("Number of living cells in total --> " + totalCount());
    }

    public void nextGeneration() {
        boolean[][] tempWorld = new boolean[world.length][world[0].length];
        int num;
        for(int r = 0; r < world.length; r++){
            for(int c = 0; c < world[0].length; c++){
                num = numNeighbors(world, r, c);
                if( occupiedNext(num, world[r][c]) )
                    tempWorld[r][c] = true;
            }
        }
        world = tempWorld;
    }

    public boolean occupiedNext(int numNeighbors, boolean occupied) {
        return occupied && (numNeighbors == 2 || numNeighbors == 3) || !occupied && numNeighbors == 3;
    }

    private int numNeighbors(boolean[][] world, int row, int col) {
        int num = world[row][col] ? -1 : 0;
        for(int r = row - 1; r <= row + 1; r++)
            for(int c = col - 1; c <= col + 1; c++)
                if( inbounds(world, r, c) && world[r][c] )
                    num++;

        return num;
    }

    private boolean inbounds(boolean[][] world, int r, int c) {
        return r >= 0 && r < world.length && c >= 0 && c < world[0].length;
    }
}