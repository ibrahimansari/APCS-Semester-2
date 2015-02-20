/*
Ibrahim Ansari
Period 7
1/30/2015

Lab 23.3 Knight's Tour 2

Time Spent: 55 minutes

Reflection:
This was a challenging lab and I had to think it through more thoroughly than I usually do. I rewrote
the entire code from my last lab because it was not compatible with what I was doing here. I ended up
writing a more efficient program and learned a lot from it. I used a lot of helper methods this time
and that really speed things up. My program also chooses a random start position. I also didn't have to use
 the file scanner to find the accessibility of a position. Overall a hard and time consuming lab.
 */

import java.io.File;
import java.util.*;

public class KnightsTour2IAnsariPeriod7 {
    private int[][] moves = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    private int move;
    private int[][] board, accessBoard;

    public static void main(String[] args) {
        KnightsTour2IAnsariPeriod7 chess = new KnightsTour2IAnsariPeriod7();
        chess.makeBoard();
        chess.readFile();
        int[] position = chess.initialPosition();

        for (int i = 1; i < 64; i++) {
            position = chess.nextMove(position);
        }

        chess.printBoard();

    }

    void makeBoard() {
        board = new int[8][8];
        move = 0;
    }

    void readFile() {
        try {
            Scanner in = new Scanner(new File("access.txt"));
            accessBoard = new int[8][8];

            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 8; col++) {
                    accessBoard[row][col] = in.nextInt();
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] initialPosition() {
        Random generator = new Random();
        int[] pos = new int[2];
        pos[0] = generator.nextInt(8);
        pos[1] = generator.nextInt(8);
        board[pos[0]][pos[1]] = ++move;
        return pos;
    }

    public int[] nextMove(int[] pos) {
        int xPos = pos[0];
        int yPos = pos[1];
        int access = 8;

        for (int i = 0; i < 8; i++) {
            int newX = xPos + moves[i][0];
            int newY = yPos + moves[i][1];
            int newAccess = getAccessibility(newX, newY);

            if (inRangeAndEmpty(newX, newY) && newAccess < access) {
                pos[0] = newX;
                pos[1] = newY;
                access = newAccess;
            }
        }

        board[pos[0]][pos[1]] = ++move;
        return pos;
    }

    private int getAccessibility(int x, int y) {
        int access = 0;
        for (int i = 0; i < 8; i++) {
            if (inRangeAndEmpty(x + moves[i][0], y + moves[i][1]))
                access++;
        }
        return access;
    }

    private boolean inRangeAndEmpty(int x, int y) {
        return (x < 8 && x >= 0 && y < 8 && y >= 0 && board[x][y] == 0);
    }

    public void printBoard() {
        System.out.println("    1    2    3    4    5    6    7    8");
        System.out.println();
        for (int i = 0; i < board.length; i ++){
            System.out.print(i + 1 + "   ");
            for (int j = 0; j < board[0].length; j++){
                System.out.printf("%-5d", board[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.println();
        System.out.println(move + " squares were visited");
    }
}
