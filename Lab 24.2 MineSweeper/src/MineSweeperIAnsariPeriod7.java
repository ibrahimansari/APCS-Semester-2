/*
Ibrahim Ansari
Period 7
3/8/2015

Minesweeper Version 1.0

Time Spent: 2 hours

Reflection:
This was a fun lab. It was fun making a classic game in a Swing implementation. I enjoyed this lab. It wasn't even
that hard. It was a mash-up of LifeGUI and EraseObject with some new things involved. I was stuck for a while on how
to make it work with a single JPanel, but decided to instead to fill a JPanel up with JToggleButtons as that seemed
like the best way for me. I had to make a Cell Class where I defined each cell to have Icons and other information.
The recursive algorithm was a modified version of my EraseObject Lab. I also made the JFrame resize according to how
big the user wanted the gam to be. My game can have any size and any number of mines. The user wins when they click
all the non-mine cells. I plan to add auto-play and other graphical improvements.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import javax.swing.*;

public class MineSweeperIAnsariPeriod7 extends JFrame {
    private static final int EMPTY_CELL = 0, MINE_CELL = 9;

    private MineCell mineField[][];
    private JLabel mineLeft, timeLabel;
    private Timer timer;
    private int gameSize, mineAmount, flagAmount, cellTotal, timeElapsed, unclickedCells = 0;

    public static void main(String args[]) {
        MineSweeperIAnsariPeriod7 minesweeper = new MineSweeperIAnsariPeriod7();
    }

    public MineSweeperIAnsariPeriod7() {
        super("Ibrahim's MineSweeper");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameSize = Integer.parseInt(JOptionPane.showInputDialog("Enter size of board", 20));
        mineAmount  = Integer.parseInt(JOptionPane.showInputDialog("Enter amount of Mines", 20));

        mineField = new MineCell[gameSize][gameSize];
        // GUI stuff
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel mineFieldPanel = new JPanel();
        mineFieldPanel.setLayout(new GridLayout(gameSize, gameSize));

        CellMouseAdapter adapter = new CellMouseAdapter();
        for (int row = 0; row < gameSize; row++) {
            for (int col = 0; col < gameSize; col++) {
                mineField[row][col] = new MineCell(row, col);
                mineField[row][col].addMouseListener(adapter);
                mineFieldPanel.add(mineField[row][col]);
            }
        }

        createMenu();
        JPanel top = new JPanel(new FlowLayout());
        mineLeft = new JLabel("  Mines Remaining: " + flagAmount);
        timeLabel = new JLabel("  Time Elapsed: " + timeElapsed);
        top.add(mineLeft);
        top.add(timeLabel);
        newGame();

        contentPane.add(top, BorderLayout.NORTH);
        contentPane.add(mineFieldPanel, BorderLayout.CENTER);

        setSize((gameSize * 20) - 20, (gameSize * 20) + 20);
        // Cool way to center
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void newGame() {
        timeElapsed = 0;
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timeLabel.setText("  Time Elapsed: " + timeElapsed);
            }
        });
        timer.start();

        for (int row = 0; row < gameSize; row++) {
            for (int col = 0; col < gameSize; col++) {
                mineField[row][col].clear();
            }
        }

        placeMines();
        assignClues();
        unclickedCells = 0;
        cellTotal = gameSize * gameSize;
        flagAmount = mineAmount;
        mineLeft.setText("  Mines Remaining: " + flagAmount);
    }

    private void createMenu() {
        JMenuBar menuBar;
        JMenu gameMenu, optionsMenu, helpMenu;
        JMenuItem newItem, exitItem, aboutItem, howItem, totalItem;

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        gameMenu = new JMenu("Game");
        optionsMenu = new JMenu("Options");
        helpMenu = new JMenu("Help");
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        newItem = new JMenuItem("New Game");
        newItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newGame();
                    }
                }
        );
        gameMenu.add(newItem);

        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        gameMenu.add(exitItem);

        totalItem = new JMenuItem("Total Mines");
        totalItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        mineAmount  = Integer.parseInt(JOptionPane.showInputDialog("Enter amount of Mines for New Game", 20));
                        newGame();
                    }
                }
        );
        optionsMenu.add(totalItem);

        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JEditorPane aboutContent = new JEditorPane(new URL("file:about.html"));
                            JOptionPane.showMessageDialog(null, aboutContent, "About", JOptionPane.PLAIN_MESSAGE, null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        helpMenu.add(aboutItem);

        howItem = new JMenuItem("How To Play");
        howItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            JEditorPane helpContent = new JEditorPane(new URL("file:how.html"));
                            JScrollPane helpPane = new JScrollPane(helpContent);
                            JOptionPane.showMessageDialog(null, helpPane, "How To Play", JOptionPane.PLAIN_MESSAGE, null);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        helpMenu.add(howItem);
    }

    private void revealBoard() {
        for (int row = 0; row < gameSize; row++) {
            for (int col = 0; col < gameSize; col++) {
                mineField[row][col].setSelected(true);
                if (mineField[row][col].clue() > EMPTY_CELL && mineField[row][col].clue() < MINE_CELL) {
                    mineField[row][col].setText("" + mineField[row][col].clue());
                }
            }
        }
    }

    private void placeMines() {
        int xPos, yPos;

        for (int x = 0; x < mineAmount; ++x) {
            xPos = (int) (Math.random() * gameSize);
            yPos = (int) (Math.random() * gameSize);
            // Check mine on a mine
            while (mineField[yPos][xPos].clue() != 0) {
                xPos = (int) (Math.random() * gameSize);
                yPos = (int) (Math.random() * gameSize);
            }
            // Place mine
            mineField[yPos][xPos].setClue(MINE_CELL);
            mineField[yPos][xPos].setMine();
        }
    }

    private void assignClues() {
        for (int y = 0; y < gameSize; ++y) {
            for (int x = 0; x < gameSize; ++x) {
                // Ignore mine
                if (mineField[y][x].clue() != MINE_CELL) {
                    int clueCount = EMPTY_CELL;
                    // check neighbors
                    if ((x - 1 >= 0) &&
                            (mineField[y][x - 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((x + 1 < gameSize) &&
                            (mineField[y][x + 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y - 1 >= 0) &&
                            (mineField[y - 1][x].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y + 1 < gameSize) &&
                            (mineField[y + 1][x].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y - 1 >= 0) && (x - 1 >= 0) &&
                            (mineField[y - 1][x - 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y - 1 >= 0) && (x + 1 < gameSize) &&
                            (mineField[y - 1][x + 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y + 1 < gameSize) && (x - 1 >= 0) &&
                            (mineField[y + 1][x - 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }
                    if ((y + 1 < gameSize) &&
                            (x + 1 < gameSize) &&
                            (mineField[y + 1][x + 1].clue() == MINE_CELL)) {
                        ++clueCount;
                    }

                    mineField[y][x].setClue(clueCount);
                }
            }
        }
    }

    private boolean outOfBounds(int yPos, int xPos) {
        return ((xPos < 0) || (yPos < 0) || (xPos >= gameSize) || (yPos >= gameSize));
    }

    private void cellClick(int row, int col) {
        if (outOfBounds(col, row)) {
            return;
        }

        MineCell cell = mineField[row][col];
        int clue = cell.clue();

        if ((clue == MINE_CELL) || !cell.isCovered()) {
            return;
        }

        cell.makeUncovered();
        unclickedCells++;

        if (clue != EMPTY_CELL) {
            mineField[row][col].setText("" + clue);
            this.repaint();
            return;
        }

        this.repaint();

        cellClick(row - 1, col);
        cellClick(row - 1, col + 1);
        cellClick(row, col + 1);
        cellClick(row + 1, col + 1);
        cellClick(row + 1, col);
        cellClick(row + 1, col - 1);
        cellClick(row, col - 1);
        cellClick(row - 1, col - 1);
    }

    class CellMouseAdapter extends MouseAdapter {
        public void mouseReleased(MouseEvent e) {
            MineCell cell = (MineCell) e.getSource();
            if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
                if (cell.isCovered()) {
                    if (!cell.isFlagged() && (flagAmount > 0)) {
                        cell.makeFlagged(true);
                        flagAmount--;
                        mineLeft.setText("  Mines Remaining: " + flagAmount);
                    } else {
                        cell.makeFlagged(false);
                        if (flagAmount < 20) {
                            flagAmount++;
                        }
                        mineLeft.setText("  Mines Remaining: " + flagAmount);
                    }
                }
            } else {
                if (cell.isCovered()) {
                    if (cell.clue() != EMPTY_CELL && cell.clue() != MINE_CELL) {
                        cell.setText("" + cell.clue());
                        cell.makeUncovered();
                        unclickedCells++;
                    } else
                    if (cell.clue() == EMPTY_CELL) {
                        cellClick(cell.row(), cell.col());
                    } else {
                        revealBoard();
                        unclickedCells = -100;
                        mineLeft.setText("  Y O U    L O S E !");
                        timer.stop();
                    }
                }
            }
            if (mineAmount == cellTotal - unclickedCells) {
                mineLeft.setText("  Y O U    W I N !");
                timer.stop();

            }
        }
    }
}

class MineCell extends JToggleButton {
    static protected ImageIcon mineIcon, notSelectedIcon, selectedIcon, flagIcon;
    static protected Font font;
    private int row, col, clue = 0;
    private boolean flagged, covered;

    public MineCell(int r, int c) {
        this.row = r;
        this.col = c;

        setHorizontalTextPosition(AbstractButton.CENTER);

        setBorderPainted(false);
        setMargin(new Insets(0, 0, 0, 0));

        if (font == null) {
            font = new Font("serif", Font.BOLD, 12);
        }
        setFont(font);

        if (mineIcon == null) {
            try {
                mineIcon = new ImageIcon(new URL("file:mine.gif"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (selectedIcon == null) {
            try {
                selectedIcon = new ImageIcon(new URL("file:selected.jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (notSelectedIcon == null) {
            try {
                notSelectedIcon = new ImageIcon(new URL("file:notSelected.png"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (flagIcon == null) {
            try {
                flagIcon = new ImageIcon(new URL("file:flag.png"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        setIcon(notSelectedIcon);
        setSelectedIcon(selectedIcon);
        this.flagged = false;
        this.covered = true;
    }

    void setClue(int clueVal) {
        this.clue = clueVal;
    }

    int clue() {
        return this.clue;
    }

    void setMine() {
        setSelectedIcon(mineIcon);
    }

    public boolean isCovered() {
        return this.covered;
    }

    void makeUncovered() {
        this.covered = false;
        this.setIcon(selectedIcon);
    }

    void makeFlagged(boolean flag) {
        this.flagged = flag;
        if (flag) {
            this.setIcon(flagIcon);
        } else {
            this.setIcon(notSelectedIcon);
        }
    }

    boolean isFlagged() {
        return flagged;
    }

    int row() {
        return row;
    }

    int col() {
        return col;
    }

    void clear() {
        covered = true;
        flagged = false;
        setText("");
        setClue(0);
        setSelected(false);
        setIcon(notSelectedIcon);
        setSelectedIcon(selectedIcon);
    }
}