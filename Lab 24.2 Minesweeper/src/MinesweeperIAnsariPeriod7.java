import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperIAnsariPeriod7 {
    public static void main(String[] args) {
        MineGUI gui = new MineGUI();
    }
}

class MineGUI extends MouseAdapter implements ActionListener {
    MyMinePanel minePanel;
    Color[][] frontBoard;

    MineGUI() {
        JFrame window = new JFrame("Ibrahim's SimpleDraw");
        frontBoard = new Color[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                frontBoard[i][j] = Color.white;
        window.setBounds(100, 100, 445, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        minePanel = new MyMinePanel();
        minePanel.setBounds(20, 20, 400, 400);
        minePanel.setBorder(BorderFactory.createEtchedBorder());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu optionsMenu = new JMenu("Options");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem totalMines = new JMenuItem("Total Mines");
        JMenuItem howTo = new JMenuItem("How To Play");
        JMenuItem about = new JMenuItem("About");

        window.setJMenuBar(menuBar);
        menuBar.add(gameMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        gameMenu.add(newGame);
        gameMenu.add(exit);
        optionsMenu.add(totalMines);
        helpMenu.add(howTo);
        helpMenu.add(about);

        minePanel.addMouseMotionListener(this);
        minePanel.addMouseListener(this);
        newGame.addActionListener(this);
        totalMines.addActionListener(this);
        exit.addActionListener(this);
        howTo.addActionListener(this);
        about.addActionListener(this);

        mainPanel.add(minePanel);

        window.getContentPane().add(mainPanel);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private class MyMinePanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);

            g.setColor(Color.lightGray);
            for (int x = 0; x < this.getWidth(); x += 20)
                g.drawLine(x, 0, x, this.getHeight());

            for (int y = 0; y < this.getHeight(); y += 20)
                g.drawLine(0, y, this.getWidth(), y);
        }
    }
}