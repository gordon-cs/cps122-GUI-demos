/* MultipleEvents1.java
 *
 * This program illustrates one way to handle multiple events in a
 * single program - by having a single listener that checks the source
 * of the event to decide what to do.  The GUI consists of a window
 * that can be drawn in one of three colors, and three buttons that
 * can be used to change the color
 *
 * Copyright (c) 2000, 2004 - Russell C. Bjork
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultipleEvents1 extends JFrame implements ActionListener {
  // GUI Components
  private JButton redButton, greenButton, blueButton;

  // Constructor - create the GUI
  public MultipleEvents1() {
    super("MultipleEvents Demo #1");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add the buttons - in their own panel
    JPanel buttonPanel = new JPanel();
    redButton = new JButton("Red");
    redButton.addActionListener(this);
    buttonPanel.add(redButton);
    greenButton = new JButton("Green");
    greenButton.addActionListener(this);
    buttonPanel.add(greenButton);
    blueButton = new JButton("Blue");
    blueButton.addActionListener(this);
    buttonPanel.add(blueButton);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    setSize(300, 200);
  }

  // Action listener
  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    if (source == redButton)
      getContentPane().setBackground(Color.red);
    else if (source == greenButton)
      getContentPane().setBackground(Color.green);
    else if (source == blueButton)
      getContentPane().setBackground(Color.blue);
    repaint();
  }

  // Main program - Create the GUI and let it do the work
  public static void main(String [] args) {
    new MultipleEvents1().setVisible(true);
  }
}
