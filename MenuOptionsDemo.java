/* MenuOptionsDemo.java
 *
 * This program illustrates both options for creating menus in a single program:
 * a "native awt" menu, and a "swing" menu.  ONE WOULD NOT ORDINARILY DO THIS IN
 * A SINGLE APPLICATION - IT IS BEING DONE HERE ONLY TO ILLUSTRATE THE DIFFERENCE
 * BETWEEN THE TWO APPROACHES TO MENUING
 *
 * Copyright (c) 2004 - Russell C. Bjork
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuOptionsDemo extends JFrame implements ActionListener {
  // Constructor - create the GUI

  public MenuOptionsDemo() {
    super("Menu Options Demo");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add a label indicating which menu we used

    whatWeUsedLabel = new JLabel(" ", JLabel.CENTER);
    getContentPane().add(whatWeUsedLabel, BorderLayout.CENTER);

    // Add the AWT menu.

    MenuBar menuBar = new MenuBar();

    Menu awtColorMenu = new Menu("Color (awt version)");
    menuBar.add(awtColorMenu);

    MenuItem awtRedItem = new MenuItem("Red", new MenuShortcut('R'));
    awtRedItem.addActionListener(this);
    awtColorMenu.add(awtRedItem);

    MenuItem awtGreenItem = new MenuItem("Green", new MenuShortcut('G'));
    awtGreenItem.addActionListener(this);
    awtColorMenu.add(awtGreenItem);

    MenuItem awtBlueItem = new MenuItem("Blue", new MenuShortcut('B'));
    awtBlueItem.addActionListener(this);
    awtColorMenu.add(awtBlueItem);

    setMenuBar(menuBar);

    // Add the swing menu.

    JMenuBar jMenuBar = new JMenuBar();

    JMenu swingColorMenu = new JMenu("Color (swing version)");
    jMenuBar.add(swingColorMenu);

    JMenuItem swingRedItem = new JMenuItem("Red", 'R');
    swingRedItem.addActionListener(this);
    swingColorMenu.add(swingRedItem);

    JMenuItem swingGreenItem = new JMenuItem("Green", 'G');
    swingGreenItem.addActionListener(this);
    swingColorMenu.add(swingGreenItem);

    JMenuItem swingBlueItem = new JMenuItem("Blue", 'B');
    swingBlueItem.addActionListener(this);
    swingColorMenu.add(swingBlueItem);

    setJMenuBar(jMenuBar);

    setSize(300, 200);

  }

  // Action listener

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    if (source instanceof MenuItem) {
      whatWeUsedLabel.setText("We did it AWT's way");
    } else if (source instanceof JMenuItem) {
      whatWeUsedLabel.setText("We did it Swing's way");
    }

    String command = event.getActionCommand();
    if (command.equals("Red")) {
      getContentPane().setBackground(Color.red);
      whatWeUsedLabel.setForeground(Color.black);
    } else if (command.equals("Green")) {
      getContentPane().setBackground(Color.green);
      whatWeUsedLabel.setForeground(Color.black);
    } else if (command.equals("Blue")) {
      getContentPane().setBackground(Color.blue);
      whatWeUsedLabel.setForeground(Color.white);
    }
    repaint();
  }

  // Main program - Create the GUI and let it do the work

  public static void main(String [] args) {
    new MenuOptionsDemo().setVisible(true);
  }

  private JLabel whatWeUsedLabel;
}
