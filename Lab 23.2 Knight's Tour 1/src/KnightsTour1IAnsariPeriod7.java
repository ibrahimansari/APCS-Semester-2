/*
Ibrahim Ansari
Period 7
1/27/2015

Lab 23.2 Knight's Tour 1

Time Spent: 55 minutes

Reflection:
This lab took me forever because I used the wrong approach more than 3 times! I thought it was
easy and fount out the contrary. I then thought about it for a while and after trial and error,
got it to work! Overall a time consuming lab.
 */

import java.util.ArrayList;

public class KnightsTour1IAnsariPeriod7 {
    int [][] board;
    int [] hrz = {0, 1, 2, 2, 1, -1, -2, -2, -1};
    int [] vert = {0, -2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) {
        KnightsTour1IAnsariPeriod7 chess = new KnightsTour1IAnsariPeriod7();
        chess.play();
    }

    void play() {
        board = new int[9][9];
        int move = 1, col = 1, row = 1, n;
        board[row][col] = move;

        do {
            int tempRow = row, tempCol = col;
            n = 1 + (int)(Math.random() * ((8 - 1) + 1));
            tempRow = row + vert[n];
            tempCol = col + hrz[n];
            if (((tempRow > 0 && tempCol > 0) && (tempRow < 9 && tempCol < 9)) && board[tempRow][tempCol] == 0) {
                if (stuck(row, col)){
                    break;
                } else {
                    move++;
                    row = tempRow;
                    col = tempCol;
                    board[row][col] = move;
                }
            }
        } while (move <= 63 && !stuck(row, col));

        System.out.println("    1    2    3    4    5    6    7    8");
        System.out.println();
        for (int i = 1; i < board.length; i ++){
            System.out.print(i + "   ");
            for (int j = 1; j < board[0].length; j++){
                System.out.printf("%-5d", board[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.println();
        System.out.println(move + " squares were visited");
    }

    boolean stuck(int row, int col) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            if (((row + vert[i] > 0 && col + hrz[i] > 0) && (row + vert[i] < 9 && col + hrz[i] < 9))) {
                list.add(board[row + vert[i]][col + hrz[i]]);
            }
        }
        return !list.contains(0);
    }
}