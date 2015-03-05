/*
Ibrahim Ansari
Period 7

3/1/2015

LifeGUI

Time Spent: 1 hour 30 minutes

Reflection:
This was a not too bad lab. I I spent a lot of time changing my Life code and then I realized I didn't
have too. I just had to adjust my SimpleDraw code. After that, it pretty smooth sailing. except for
speed control. I was confused on how to use timers and I will ask in class tomorrow. Overall a decent lab
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LifeGUIIAnsariPeriod7 {
    public static void main(String[] args) {
        final LifeGUI gui = new LifeGUI();
    }
}

class LifeGUI extends MouseAdapter implements ActionListener, ChangeListener, KeyListener {
    Color color = Color.RED;
    MyDrawingPanel drawingPanel;
    JSlider slider;
    JFrame window;
    Timer timer;
    boolean[][] image;
    int speed;
    int numGen = 0;

    LifeGUI() {
        window = new JFrame();
        window.setTitle("Ibrahim's LifeGUI -- Generation: " + numGen);
        image = new boolean[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                image[i][j] = false;
        window.setBounds(100, 100, 445, 640);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new MyDrawingPanel();
        drawingPanel.setBounds(20, 20, 400, 400);
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        JButton button1 = new JButton("Clear");
        JButton button2 = new JButton("Step");
        JButton button3 = new JButton("Run");
        slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 5);

        button1.setBounds(50, 60, 100, 20);
        button2.setBounds(50, 90, 100, 20);
        button3.setBounds(50, 110, 100, 20);
        slider.setBounds(60, 70, 200, 20);
        slider.setMajorTickSpacing(500);
        slider.setSnapToTicks(false);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder("Speed"));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem clearItem = new JMenuItem("Clear");

        window.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        editMenu.add(clearItem);

        colorPanel.setBounds(120, 425, 200, 170);
        colorPanel.add(button1);
        colorPanel.add(button2);
        colorPanel.add(button3);
        colorPanel.add(slider);

        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        drawingPanel.addKeyListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        slider.addChangeListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        clearItem.addActionListener(this);

        mainPanel.add(drawingPanel);
        mainPanel.add(colorPanel);

        window.getContentPane().add(mainPanel);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (e.getActionCommand().equals("Clear")) {
                if (timer != null)
                    timer.stop();
                clearDraw();
            } else if (e.getActionCommand().equals("Step")) {
                Graphics g = drawingPanel.getGraphics();
                Life life = new Life();
                image = life.nextGeneration();
                numGen++;
                window.setTitle("Ibrahim's LifeGUI -- Generation: " + numGen);
                for (int i = 0; i < 20; i++)
                    for (int j = 0; j < 20; j++) {
                        if (!image[j][i])
                            g.setColor(Color.white);
                        else if (image[j][i])
                            g.setColor(Color.black);
                        g.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                    }
            } else if (e.getActionCommand().equals("Run")) {
                timer = new Timer(5, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Graphics g = drawingPanel.getGraphics();
                        Life life = new Life();
                        image = life.nextGeneration();
                        numGen++;
                        window.setTitle("Ibrahim's LifeGUI -- Generation: " + numGen);
                        for (int i = 0; i < 20; i++)
                            for (int j = 0; j < 20; j++) {
                                if (!image[j][i])
                                    g.setColor(Color.white);
                                else if (image[j][i])
                                    g.setColor(Color.black);
                                g.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                            }
                    }
                });

                timer.start();
            } else if (e.getActionCommand().equals("Open")) {
                JFileChooser jf = new JFileChooser();
                jf.showDialog(null, "Open");
                File file = jf.getSelectedFile();
                Color c;
                try {
                    Scanner in = new Scanner(file);
                    if (in.next().equals("P3")) {
                        in.next();
                        in.next();
                        in.next();
                        int i = 0;
                        while (in.hasNext()) {
                            for (int j = 0; j < 20; j++) {
                                int r = in.nextInt();
                                int g = in.nextInt();
                                int b = in.nextInt();
                                c = new Color(r, g, b);
                                if (c == Color.black)
                                    image[j][i] = true;
                                else if (c == Color.white)
                                    image[j][i] = false;
                            }
                            i++;
                        }
                    } else {
                        throw new IOException("The file you are attempting to open is not a valid PPM file.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Graphics g = drawingPanel.getGraphics();
                for (int i = 0; i < 20; i++)
                    for (int j = 0; j < 20; j++) {
                        if (!image[j][i])
                            g.setColor(Color.white);
                        else if (image[j][i])
                            g.setColor(Color.black);
                        g.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                    }
            } else if (e.getActionCommand().equals("Save")) {
                JFileChooser jf = new JFileChooser();
                jf.showDialog(null, "Save");
                File file = jf.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write("P3");
                    bw.newLine();
                    bw.write(image[0].length + " " + image.length + " 255");
                    bw.newLine();
                    int i = 0, j = 0;

                    while (true) {
                        if (!image[i][j])
                            bw.write("" + 255 + " " + 255 + " " + 255);
                        else if (image[i][j])
                            bw.write("" + 0 + " " + 0 + " " + 0);
                        bw.newLine();
                        j++;
                        if (j >= image[0].length) {
                            i++;
                            j = 0;
                        }
                        if (i >= image.length)
                            break;
                    }
                    bw.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = drawingPanel.getGraphics();
        int m = e.getModifiersEx();
        if (m == MouseEvent.BUTTON3_DOWN_MASK) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }
        int x = e.getX(), y = e.getY();
        x /= 20;
        x *= 20;
        y /= 20;
        y *= 20;
        if (g.getColor() == Color.black)
            image[y / 20][x / 20] = true;
        else
            image[y / 20][x / 20] = false;
        g.fillRect(x + 1, y + 1, 19, 19);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics g = drawingPanel.getGraphics();
        int m = e.getModifiersEx();
        if (m == MouseEvent.BUTTON3_DOWN_MASK) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }
        int x = e.getX(), y = e.getY();
        x /= 20;
        x *= 20;
        y /= 20;
        y *= 20;
        if (g.getColor() == Color.black)
            image[y / 20][x / 20] = true;
        else
            image[y / 20][x / 20] = false;
        g.fillRect(x + 1, y + 1, 19, 19);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == slider){
            speed = slider.getValue();
            timer.setDelay(speed);
        }
    }

    public void clearDraw() {
        image = new boolean[20][20];
        numGen = 0;
        window.setTitle("Ibrahim's LifeGUI -- Generation: " + numGen);
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                image[i][j] = false;
        drawingPanel.repaint();
        drawingPanel.paintComponent(drawingPanel.getGraphics());
    }

    private class MyDrawingPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(2, 2, this.getWidth() - 2, this.getHeight() - 2);

            g.setColor(Color.lightGray);
            for (int x = 0; x < this.getWidth(); x += 20)
                g.drawLine(x, 0, x, this.getHeight());

            for (int y = 0; y < this.getHeight(); y += 20)
                g.drawLine(0, y, this.getWidth(), y);

            for (int i = 0; i < 20; i++)
                for (int j = 0; j < 20; j++) {
                    if (!image[j][i])
                        g.setColor(Color.white);
                    else if (image[j][i])
                        g.setColor(Color.black);
                    g.fillRect(i * 20 + 1, j * 20 + 1, 19, 19);
                }
        }
    }

    class Life {

//        public void runLife(int numGenerations) {
//            for (int i = 0; i < numGenerations; i++) {
//                nextGeneration();
//            }
//        }

        public boolean[][] nextGeneration() {
            boolean[][] tempWorld = new boolean[image.length][image[0].length];
            int num;
            for (int r = 0; r < image.length; r++) {
                for (int c = 0; c < image[0].length; c++) {
                    num = numNeighbors(image, r, c);
                    if (occupiedNext(num, image[r][c]))
                        tempWorld[r][c] = true;
                }
            }
            return tempWorld;
        }

        public boolean occupiedNext(int numNeighbors, boolean occupied) {
            return occupied && (numNeighbors == 2 || numNeighbors == 3) || !occupied && numNeighbors == 3;
        }

        private int numNeighbors(boolean[][] world, int row, int col) {
            int num = world[row][col] ? -1 : 0;
            for (int r = row - 1; r <= row + 1; r++)
                for (int c = col - 1; c <= col + 1; c++)
                    if (inbounds(world, r, c) && world[r][c])
                        num++;

            return num;
        }

        private boolean inbounds(boolean[][] world, int r, int c) {
            return r >= 0 && r < world.length && c >= 0 && c < world[0].length;
        }

    }
}