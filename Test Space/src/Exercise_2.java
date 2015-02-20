// import libraries
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Exercise_2 {

	public static void main(String[] args) {

		// Create a basic Java window frame
		JFrame window = new JFrame("My Window Title");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the window size (see API)
		window.setBounds(200, 200, 640, 480);

		// Prevent users from resizing the window
		window.setResizable(false);

		// Create GUI components.
		// For us, create a custom JPanel to draw on.
		MySketchPad panel = new MySketchPad();

		// Add GUI components to the JFrame (window)
		window.add(panel);

		// Make the window visible
		window.setVisible(true);
	}
}

/*
   A JPanel is like a SketchPad in the sense that you can
   draw on it.  It's more powerful though, because it has
   more capabilities than a SketchPad, such as the ability
   to add buttons and GUI elements.  And you can add
   JPanels to JPanels.
*/
class MySketchPad extends JPanel {

	public void paintComponent(Graphics g) {

		// Set the background color to white (do this yourself)
        g.setColor(Color.white);
		// Ask our parent to paint itself
		super.paintComponent(g);

		// Next, cast the Graphics parameter back into what
		// it really is - a more powerful Graphics2D object.
		// Or, if you want, you can leave it as a Graphics
		// and only use Graphics class methods.
		Graphics2D g2 = (Graphics2D)g;

		// Finally, draw stuff
		g2.drawString("This is a rectangle", 100, 50);
		g2.draw3DRect(10, 50, 300, 100, true);
	}
}