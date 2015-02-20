/*
Ibrahim Ansari
Period 7
2/9/2015

CircleDraw HW

Time Spent: 1 hour 30 minutes

Reflection:
This HW was not hard to understand conceptually, but was hard to debug and learn the new API.
I spent a lot of time time trying to get it to draw dynamically. I personally do not like the
Java GUI API. It is a little less user friendly and hard to use. This HW took longer than hard
labs took. Overall a time consuming HW.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CircleDrawIAnsariPeriod7 implements ActionListener, ChangeListener {
    public static void main(String[] args) {
        CircleDrawIAnsariPeriod7 gui = new CircleDrawIAnsariPeriod7();
        gui.createGUI();
    }
    
    JFrame frame;
    JPanel left, middle, right, buttonPanel, tButtonPanel, rButtonPanel, linePanel, textBoxPanel, sliderPanel;
    MyJPanel drawPanel;
    JTextField text1;
    JTextArea text2;
    JSlider slider;
    JButton drawButton;
    JToggleButton toggle;
    JRadioButton red, blue, green;
    TitledBorder color, lineThick, radius;
    Color circleColor;
    BasicStroke lineStroke;
    String colorName, lineName;
    int circleSize;

    void createGUI() {
        frame = new JFrame("Ibrahim's Circle Draw");
        int screenWidth = (int) (frame.getToolkit().getScreenSize().getWidth());
        int screenHeight = (int) (frame.getToolkit().getScreenSize().getHeight());
        int frameWidth = 900;
        int frameHeight = 380;
        frame.setBounds(screenWidth / 2 - frameWidth / 2, screenHeight / 2
                - frameHeight / 2, frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        left = new JPanel();
        left.setBackground(null);
        left.setLayout(new BoxLayout(left, BoxLayout.PAGE_AXIS));

        textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new BoxLayout(textBoxPanel, BoxLayout.Y_AXIS));
        text1 = new JTextField();
        text1.setPreferredSize(new Dimension(300, 30));
        text1.setBackground(Color.WHITE);
        text2 = new JTextArea();
        text2.setPreferredSize(new Dimension(180, 170));
        text2.setBackground(Color.WHITE);
        text2.setLineWrap(true);
        text2.setBorder(BorderFactory.createLineBorder(Color.gray));
        textBoxPanel.add(text1);
        textBoxPanel.add(text2);
        lineStroke = new BasicStroke(1);

        sliderPanel = new JPanel();
        sliderPanel.setBorder(new TitledBorder("Radius"));
        slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        slider.setMajorTickSpacing(20);
        slider.setMinimum(10);
        slider.setMaximum(90);
        slider.setValue(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        circleSize = slider.getValue();

        rButtonPanel = new JPanel();
        rButtonPanel.setBorder(new TitledBorder("Line Color"));
        red = new JRadioButton("Red");
        red.setSelected(true);
        blue = new JRadioButton("Blue");
        green = new JRadioButton("Green");

        tButtonPanel = new JPanel();
        tButtonPanel.setBorder(new TitledBorder("Line Thickness"));
        toggle = new JToggleButton("Thin");
        lineName = "Thin";
        tButtonPanel.add(toggle);

        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(blue);
        group.add(green);
        rButtonPanel.add(red);
        rButtonPanel.add(blue);
        rButtonPanel.add(green);

        right = new JPanel();
        right.add(textBoxPanel);
        sliderPanel.add(slider);

        left.add(tButtonPanel);
        left.add(rButtonPanel);
        left.add(sliderPanel);

        middle = new JPanel();
        drawPanel = new MyJPanel();
        drawPanel.setPreferredSize(new Dimension(260, 260));
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        middle.add(drawPanel);

        buttonPanel = new JPanel();
        drawButton = new JButton("Draw!");
        buttonPanel.setPreferredSize(new Dimension(280, 40));
        buttonPanel.setBackground(null);
        buttonPanel.add(drawButton);
        left.add(buttonPanel);

        frame.add(left, BorderLayout.LINE_START);
        frame.add(middle, BorderLayout.CENTER);
        frame.add(right, BorderLayout.LINE_END);

        red.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        drawButton.addActionListener(this);
        toggle.addActionListener(this);
        slider.addChangeListener(this);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == red) {
            circleColor = Color.red;
            colorName = "Red";
        } else if (e.getSource() == blue) {
            circleColor = Color.blue;
            colorName = "Blue";
        } else if (e.getSource() == green) {
            circleColor = Color.green;
            colorName = "Green";
        } else if (e.getSource() == toggle) {
            if (toggle.isSelected() == false) {
                toggle.setText("Thin");
                lineName = "Thin";
                lineStroke = new BasicStroke(1);
            }
            if (toggle.isSelected() == true) {
                toggle.setText("Thick");
                lineName = "Thick";
                lineStroke = new BasicStroke(5);
            }
        } else if (e.getSource() == drawButton) {
            drawPanel.repaint();
            text2.setText("Congratulations " + text1.getText() + " You drew a "
                    + lineName + " " + colorName + " circle of radius "
                    + circleSize + " !");

        }

    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider) {
            circleSize = slider.getValue();
            drawPanel.repaint();
        }
    }

    private class MyJPanel extends JPanel {

        public void paintComponent(Graphics g) {
            if (red.isSelected() == true) {
                circleColor = Color.red;
                colorName = "Red";
            }
            super.paintComponent(g);
            Graphics2D g3 = (Graphics2D) g;
            g3.setColor(circleColor);
            g3.setStroke(lineStroke);
            g3.drawOval((this.getWidth() / 2) - (circleSize / 2),
                    (this.getWidth() / 2) - (circleSize / 2), circleSize, circleSize);
        }
    }
}