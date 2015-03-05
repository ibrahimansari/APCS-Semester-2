/*
Ibrahim Ansari
Period 7
2/20/2015

ClassworkGUI

Time Spent: 1 hour

Reflection:
This is incomplete. I had forgotten we had APCS homework. I am very sorry. I hurriedly finished how much I could.
I was given a lot of time to do this but made a bad decision and saved it for the last moment. Sorry :(
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClassworkGUIIAnsariPeriod7 {
    public static void main(String[] args) {
        ClassworkGUI gui = new ClassworkGUI();
    }
}

class ClassworkGUI implements MouseListener{
    RightPanel right;
    JSlider slider;

    ClassworkGUI() {
        // Create a basic Java window frame
        JFrame window = new JFrame("My Window Title");
        JPanel main = new JPanel(new GridLayout(1,3));
        right = new RightPanel();
        JPanel middle = new JPanel();
        JPanel left = new JPanel();
        JPanel topLeft = new JPanel();
        JPanel middleLeft = new JPanel();
        JPanel bottomLeft = new JPanel();
        JPanel center = new JPanel();
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
        LineBorder lineBorder = new LineBorder(Color.gray, 5, true);
        EtchedBorder etchedBorder = new EtchedBorder(EtchedBorder.RAISED);
        TitledBorder titledBorder = new TitledBorder("Blackboard");
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        slider = new JSlider(JSlider.VERTICAL);
        middleLeft.add(button1);
        middleLeft.add(button2);
        middleLeft.add(button3);
        bottomLeft.add(slider);

        // Decide what to do when the user closes the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the screen dimensions
        int screenWidth = (int)(window.getToolkit().getScreenSize().getWidth());
        int screenHeight = (int)(window.getToolkit().getScreenSize().getHeight());
        // Our window size
        int frameWidth = 800;
        int frameHeight = 600;
        // Centers the JFrame, regardless of screen resolution
        window.setBounds(screenWidth/2 - frameWidth/2, screenHeight/2 - frameHeight/2, frameWidth, frameHeight);

        // Decide whether to allow users to resize the window
        window.setResizable(true);

        // Define the overall layout
        left.setBackground(new Color(255, 100, 100));
        middle.setBackground(new Color(102, 255, 102));
        right.setBackground(new Color(108, 92, 147));
        center.setBackground(new Color(255, 252, 64));
        topLeft.setPreferredSize(new Dimension(200, 150));
        center.setPreferredSize(new Dimension(200, 300));
        topLeft.setBackground(Color.white);
        middleLeft.setBackground(null);
        bottomLeft.setBackground(null);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        right.addMouseListener(this);

        // Create GUI components
        left.setBorder(bevelBorder);
        topLeft.setBorder(lineBorder);
        middle.setBorder(etchedBorder);
        center.setBorder(titledBorder);

        // Add GUI components to JFrame (window)
        main.add(left);
        main.add(middle);
        main.add(right);
        window.add(main);
        left.add(topLeft);
        left.add(middleLeft);
        left.add(bottomLeft);
        middle.add(center);

        // Make the window visible
        window.setVisible(true);
    }

    private class RightPanel extends JPanel {
        public void setBackground(Color bg) {
            super.setBackground(new Color(108, 92, 147));
        }

        public void paintComponent(Graphics g) {
            g.setColor(Color.black);
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        Graphics g = right.getGraphics();
        int right = MouseEvent.BUTTON3_DOWN_MASK;
        int left = MouseEvent.BUTTON1_DOWN_MASK;

        if (e.getModifiersEx() == right) {
            g.drawRect(e.getX(), e.getY(), slider.getValue(), slider.getValue());
        } else if (e.getModifiersEx() == left) {
            g.drawOval(e.getX(), e.getY(), slider.getValue(), slider.getValue());
        }
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}