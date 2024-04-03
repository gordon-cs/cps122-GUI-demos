/*
 * JPanelDemo.java
 *
 * This program demonstrates using Panels to:
 * - Group Components into a single unit that can be
 *	 laid out together by a layout manager
 * - Protect a component from being stretched by a layout
 *	 manager.
 *
 * Copyright (c) 2000, 2004, 2011 - Russell C. Bjork
 *
 */

import java.awt.*;
import javax.swing.*;

public class JPanelDemo {
  public static void main(String [] args) {
    // Create a window that uses a border layout to display six
    // buttons labelled NORTH, SOUTH, EAST, WEST, UP, and DOWN.
    // The four compass directions are placed in the corresponding
    // positions using a BorderLayout; the Up and Down buttons are
    // put into a single panel that is then placed in the center of
    // the BorderLayout.
    JFrame frame1 = new JFrame("Panel Demo 1");

    // Since BorderLayout is the default for a Frame,
    // we don't need a setLayout()
    frame1.getContentPane().add(new JButton("NORTH"),
                                BorderLayout.NORTH);
    frame1.getContentPane().add(new JButton("SOUTH"),
                                BorderLayout.SOUTH);
    frame1.getContentPane().add(new JButton("EAST"),
                                BorderLayout.EAST);
    frame1.getContentPane().add(new JButton("WEST"),
                                BorderLayout.WEST);
    JPanel centerPanel1 = new JPanel();
    centerPanel1.setLayout(new GridLayout(2, 1));
    centerPanel1.add(new JButton("UP"));
    centerPanel1.add(new JButton("DOWN"));
    frame1.getContentPane().add(centerPanel1, BorderLayout.CENTER);

    frame1.pack();
    frame1.setVisible(true);

    // Create a second window the same as the above, but
    // protect each button from being stretched to fill
    // its space in the layout by using a private helper
    // method to wrap the button in a JPanel.
    JFrame frame2 = new JFrame("Panel Demo 2");

    // Since BorderLayout is the default for a Frame,
    // we don't need a setLayout()
    frame2.getContentPane().add(protectedJButton("NORTH"),
                                BorderLayout.NORTH);
    frame2.getContentPane().add(protectedJButton("SOUTH"),
                                BorderLayout.SOUTH);
    frame2.getContentPane().add(protectedJButton("EAST"),
                                BorderLayout.EAST);
    frame2.getContentPane().add(protectedJButton("WEST"),
                                BorderLayout.WEST);
    JPanel centerPanel2 = new JPanel();
    centerPanel2.setLayout(new GridLayout(2, 1));
    centerPanel2.add(protectedJButton("UP"));
    centerPanel2.add(protectedJButton("DOWN"));
    frame2.getContentPane().add(centerPanel2, BorderLayout.CENTER);

    frame2.pack();
    frame2.setVisible(true);
  }

  // Create a protected button by wrapping it in a JPanel
  private static JPanel protectedJButton(String label) {
    JPanel panel = new JPanel();
    // Since FlowLayout is the default for a JPanel,
    // we don't need a setLayout()
    panel.add(new JButton(label));
    return panel;
  }
}
