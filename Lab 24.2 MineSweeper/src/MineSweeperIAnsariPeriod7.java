/*
Ibrahim Ansari
Period 7
3/5/2015

Minesweeper Version 1.0

Time Spent:

Reflection:

 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class MineSweeperIAnsariPeriod7 {
    private static final int NUM_MINES = 20;
    private static final int SIZE = 20;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mine Sweeper | # of mines left: " + NUM_MINES);
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu optionsMenu = new JMenu("Options");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem totalMines = new JMenuItem("Total Mines");
        JMenuItem howTo = new JMenuItem("How To Play");
        JMenuItem about = new JMenuItem("About");

        frame.setJMenuBar(menuBar);
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        gameMenu.add(newGame);
        gameMenu.add(exit);
        optionsMenu.add(totalMines);
        helpMenu.add(howTo);
        helpMenu.add(about);
        frame.add(new MineSweeperGUI(SIZE, SIZE, NUM_MINES));
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}

class MineSweeperGUI extends JPanel {
    private MineGrid grid;

    public MineSweeperGUI(int numRows, int numCols, int numMines) {
        grid = new MineGrid(numRows, numCols, numMines);

        setLayout(new GridLayout(numRows, numCols));
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                JButton button = new JButton();
                add(button);
                button.addActionListener(new ButtonListener(i,j, grid));
            }
        }
    }
}

class ButtonListener implements ActionListener {
    private int row, col;
    private MineGrid grid;

    public ButtonListener(int x, int y, MineGrid g) {
        row = x;
        col = y;
        grid = g;
    }

    public void actionPerformed(ActionEvent event) {
        if(grid.mineAt(row, col)) {
            JOptionPane.showMessageDialog(null, "Game Over!!");
            System.exit(0);
        } else {
            JButton button = (JButton)event.getSource();
            button.setText(String.valueOf(grid.getInfo(row, col)));
        }
    }
}

class MineGrid {
    public static final int MINE = -1;
    protected int[][] mineInfo;

    public MineGrid(int numRows, int numCols, int numMines) {
        mineInfo = new int[numRows][numCols];

        initializeCells();
        placeMines(numMines);
        setMineInformation();
    }

    private void initializeCells() {
        for(int i = 0; i < mineInfo.length; i++) {
            for(int j = 0; j < mineInfo[0].length; j++) {
                mineInfo[i][j] = 0;
            }
        }
    }

    private void placeMines(int numMines) {
        Random random = new Random();
        for(int i = 0; i < numMines; i++) {
            int r = random.nextInt(mineInfo.length);
            int c = random.nextInt(mineInfo[0].length);
            mineInfo[r][c] = MINE;
        }
    }

    private void setMineInformation() {
        for(int i = 0; i < mineInfo.length; i++) {
            for(int j = 0; j < mineInfo[0].length; j++) {
                if(mineInfo[i][j] == MINE) {
                    // previous row
                    incrementMine(i-1, j-1);
                    incrementMine(i-1, j);
                    incrementMine(i-1, j+1);

                    // left and right cells
                    incrementMine(i, j-1);
                    incrementMine(i, j+1);

                    // next row
                    incrementMine(i+1, j-1);
                    incrementMine(i+1, j);
                    incrementMine(i+1, j+1);
                }
            }
        }
    }

    private void incrementMine(int i, int j) {
        if(inBounds(i, j) && mineInfo[i][j] != MINE) {
            mineInfo[i][j]++;
        }
    }

    private boolean inBounds(int i, int j) {
        return (i >= 0 && i < mineInfo.length) &&
                (j >= 0 && j < mineInfo[0].length);
    }

    public int getInfo(int i, int j) {
        return mineInfo[i][j];
    }

    public boolean mineAt(int i, int j) {
        return getInfo(i, j) == MINE;
    }
}

