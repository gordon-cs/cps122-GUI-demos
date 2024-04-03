/* MultipleEvents42.java
 *
 * This program illustrates one way to handle multiple events in a
 * single program - by having a different listener for each event
 * source.  Each listeners is an instance of an anonymous inner class
 * declared a the point where the listener is created, whose
 * actionPerformed() method changes to the appropriate color.  The GUI
 * consists of a window that can be drawn in one of three colors, and
 * three buttons that can be used to change the color
 *
 * Copyright (c) 2000, 2004, 2010 - Russell C. Bjork
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultipleEvents2 extends JFrame {
  // Constructor - create the GUI
  public MultipleEvents2() {
    super("MultipleEvents Demo #2");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add the buttons - in their own panel

    JPanel buttonPanel = new JPanel();
    JButton redButton = new JButton("Red");
    redButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          getContentPane().setBackground(Color.red);
          repaint();
        }
      });
    buttonPanel.add(redButton);

    JButton greenButton = new JButton("Green");
    greenButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          getContentPane().setBackground(Color.green);
          repaint();
        }
      });
    buttonPanel.add(greenButton);

    JButton blueButton = new JButton("Blue");
    blueButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
          getContentPane().setBackground(Color.blue);
          repaint();
        }
      });
    buttonPanel.add(blueButton);

    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    setSize(300, 200);
  }

  // Main program - Create the GUI and let it do the work
  public static void main(String [] args) {
    new MultipleEvents2().setVisible(true);
  }
}
