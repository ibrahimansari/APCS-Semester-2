/*
Ibrahim Ansari
Period 7
2/24/2015

SimpleDraw HW #2

Time Spent: 40 minutes

Reflection:
This was a fun lab. It was a good experience to learn how files are formatted in general.
The hardest part of this lab was trying to save the file. After saving it, reading it was
easy. Overall a good lab.

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class SimpleDrawIAnsariPeriod7 {
    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
    }
}

class MyGUI implements ActionListener, MouseListener, MouseMotionListener {
    Color color = Color.RED;
    MyDrawingPanel drawingPanel;
    Color[][] image;

    MyGUI() {
        JFrame window = new JFrame("Ibrahim's SimpleDraw");
        image = new Color[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                image[i][j] = Color.white;
        window.setBounds(100, 100, 445, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new MyDrawingPanel();
        drawingPanel.setBounds(20, 20, 400, 400);
        drawingPanel.setBorder(BorderFactory.createEtchedBorder());

        JButton button = new JButton("Clear");
        button.setBounds(190, 510, 75, 20);

        JRadioButton radioButton1 = new JRadioButton("Red", true);
        JRadioButton radioButton2 = new JRadioButton("Green");
        JRadioButton radioButton3 = new JRadioButton("Blue");
        JRadioButton radioButton4 = new JRadioButton("Custom");

        radioButton1.setBounds(50, 75, 100, 20);
        radioButton2.setBounds(50, 100, 100, 20);
        radioButton3.setBounds(50, 125, 100, 20);
        radioButton4.setBounds(50, 150, 100, 20);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);
        radioGroup.add(radioButton4);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

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

        colorPanel.setBounds(120, 425, 200, 80);
        colorPanel.add(radioButton1);
        colorPanel.add(radioButton2);
        colorPanel.add(radioButton3);
        colorPanel.add(radioButton4);

        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);
        radioButton4.addActionListener(this);
        button.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        clearItem.addActionListener(this);

        mainPanel.add(drawingPanel);

        mainPanel.add(colorPanel);
        mainPanel.add(button);

        window.getContentPane().add(mainPanel);

        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (e.getActionCommand().equals("Red"))
                color = Color.RED;
            else if (e.getActionCommand().equals("Green"))
                color = Color.GREEN;
            else if (e.getActionCommand().equals("Blue"))
                color = Color.BLUE;
            else if (e.getActionCommand().equals("Custom")){
                color = JColorChooser.showDialog(null, "Choose a color", Color.red);
            } else if (e.getActionCommand().equals("Clear")) {
                clearDraw();
            } else {
                if (e.getActionCommand().equals("Open")) {
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
                                    image[i][j] = c;
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
                            g.setColor(image[j][i]);
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
                            int r = image[i][j].getRed();
                            int g = image[i][j].getGreen();
                            int b = image[i][j].getBlue();
                            bw.write("" + r + " " + g + " " + b);
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
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = drawingPanel.getGraphics();
        int m = e.getModifiersEx();
        if (m == MouseEvent.BUTTON3_DOWN_MASK) {
            g.setColor(Color.white);
        } else {
            g.setColor(color);
        }
        int x = e.getX(), y = e.getY();
        x /= 20;
        x *= 20;
        y /= 20;
        y *= 20;
        image[y / 20][x / 20] = g.getColor();
        g.fillRect(x + 1, y + 1, 19, 19);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        Graphics g = drawingPanel.getGraphics();
        int m = e.getModifiersEx();
        if (m == MouseEvent.BUTTON3_DOWN_MASK) {
            g.setColor(Color.white);
        } else {
            g.setColor(color);
        }
        int x = e.getX(), y = e.getY();
        x /= 20;
        x *= 20;
        y /= 20;
        y *= 20;
        image[y / 20][x / 20] = g.getColor();
        g.fillRect(x + 1, y + 1, 19, 19);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void clearDraw() {
        image = new Color[20][20];
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                image[i][j] = Color.white;
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
        }
    }
}