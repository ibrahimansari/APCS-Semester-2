/*
Ibrahim Ansari
Period 7
2/13/2015

SimpleDraw HW 1

Time Spent: 25 minutes

Reflection:
This HW was fairly easy to do after seeing Mr. Ferrante do it in class. The only part I was
confused on was that the video showed squares drawn within the grid lines, but the assignment didn't
have it as a requirement. I tried to add them, but failed. I will ask someone how to do that tomorrow.
Overall a easy HW though. So I saw it in class today and I figured out how to draw in the grid.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleDrawIAnsariPeriod7 {
    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
    }
}

class MyGUI implements ActionListener, MouseListener, MouseMotionListener {
    Color color = Color.RED;
    MyDrawingPanel drawingPanel;

    MyGUI() {
        JFrame window = new JFrame("Ibrahim's SimpleDraw");
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

        radioButton1.setBounds(50, 75, 100, 20);
        radioButton2.setBounds(50, 100, 100, 20);
        radioButton3.setBounds(50, 125, 100, 20);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel colorPanel = new JPanel();
        colorPanel.setBorder(BorderFactory.createTitledBorder("Drawing Color"));

        colorPanel.setBounds(120, 425, 200, 70);
        colorPanel.add(radioButton1);
        colorPanel.add(radioButton2);
        colorPanel.add(radioButton3);

        drawingPanel.addMouseMotionListener(this);
        drawingPanel.addMouseListener(this);
        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);
        radioButton3.addActionListener(this);
        button.addActionListener(this);

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
            if (e.getActionCommand().equals("Clear")) {
                clearDraw();
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
        g.fillRect(x, y, 19, 19);
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