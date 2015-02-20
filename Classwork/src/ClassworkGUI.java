import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

class ClassworkGUI {

    public static void main(String[] args) {
        ClassworkGUI gui = new ClassworkGUI();
        gui.createGUI();
    }

    public void createGUI() {
        // Create a basic Java window frame
        JFrame window = new JFrame("My Window Title");
        JPanel main = new JPanel(new GridLayout(1,3));
        JPanel right = new JPanel();
        JPanel middle = new JPanel();
        JPanel left = new JPanel();

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
        window.setResizable(false);

        // Define the overall layout
        left.setBackground(new Color(255,100,100));
        middle.setBackground(new Color(102, 255, 102));
        right.setBackground(new Color(79, 53, 100));

        // Create GUI components

        // Add GUI components to JFrame (window)
        main.add(left);
        main.add(middle);
        main.add(right);
        window.add(main);

        // Make the window visible
        window.setVisible(true);
    }
}