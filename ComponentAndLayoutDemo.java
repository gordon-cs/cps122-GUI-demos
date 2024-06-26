/*
 * ComponentAndLayoutDemo.java
 *
 * This program demonstrates various GUI components and layouts.  A special
 * kind of frame (ShrinkableFrame) is used to allow demonstrating what happens
 * when a frame is shrunk to less than what would otherwise be its minimum size.
 *
 * Copyright (c) 2001, 2004, 2011 - Russell C. Bjork
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComponentAndLayoutDemo {
  public static void main(String [] args) {
    // Create a listener for window events - using a local class

    WindowListener listener = new WindowAdapter() {
        public void windowOpened(WindowEvent event) {
          openWindows ++;
        }
        public void windowClosing(WindowEvent event) {
          Window window = event.getWindow();
          window.setVisible(false);
          if (-- openWindows == 0)
            System.exit(0);
        }
        private int openWindows = 0;
      };

    // Create and show a frame with a FlowLayout.

    JFrame flowLayoutFrame = new ShrinkableFrame("FlowLayout");
    flowLayoutFrame.getContentPane().setLayout(new FlowLayout());
    Component [] components = createComponents();
    for (int i = 0; i < components.length; i ++) {
      flowLayoutFrame.getContentPane().add(components[i]);
    }
    flowLayoutFrame.addWindowListener(listener);
    flowLayoutFrame.pack();
    flowLayoutFrame.setLocation(50, 50);
    flowLayoutFrame.setVisible(true);

    // Create and show a frame with a BorderLayout.
    // We can only show five components in this one

    JFrame borderLayoutFrame = new ShrinkableFrame("BorderLayout");
    borderLayoutFrame.getContentPane().setLayout(
      new BorderLayout(5, 5));
    components = createComponents();
    borderLayoutFrame.getContentPane().add(components[0],
                                           BorderLayout.NORTH);
    borderLayoutFrame.getContentPane().add(components[1],
                                           BorderLayout.EAST);
    borderLayoutFrame.getContentPane().add(components[2],
                                           BorderLayout.SOUTH);
    borderLayoutFrame.getContentPane().add(components[3],
                                           BorderLayout.WEST);
    borderLayoutFrame.getContentPane().add(components[4],
                                           BorderLayout.CENTER);
    borderLayoutFrame.addWindowListener(listener);
    borderLayoutFrame.pack();
    borderLayoutFrame.setLocation(100, 100);
    borderLayoutFrame.setVisible(true);

    // Create and show a frame with a GridLayout.

    JFrame gridLayoutFrame = new ShrinkableFrame("GridLayout");
    gridLayoutFrame.getContentPane().setLayout(
      new GridLayout(3, 3, 5, 5));
    components = createComponents();
    for (int i = 0; i < components.length; i ++) {
      gridLayoutFrame.getContentPane().add(components[i]);
    }
    gridLayoutFrame.addWindowListener(listener);
    gridLayoutFrame.pack();
    gridLayoutFrame.setLocation(150, 150);
    gridLayoutFrame.setVisible(true);

    // Create and show a frame with a BoxLayout

    JFrame boxLayoutFrame = new ShrinkableFrame("BoxLayout");
    boxLayoutFrame.getContentPane().setLayout(
      new BoxLayout(boxLayoutFrame.getContentPane(),
                    BoxLayout.Y_AXIS));
    components = createComponents();
    for (int i = 0; i < components.length; i ++) {
      boxLayoutFrame.getContentPane().add(components[i]);
    }
    boxLayoutFrame.addWindowListener(listener);
    boxLayoutFrame.pack();
    boxLayoutFrame.setLocation(200, 200);
    boxLayoutFrame.setVisible(true);

    // Create and show a frame with a GridBagLayout

    JFrame gridBagLayoutFrame = new ShrinkableFrame("GridBagLayout");
    gridBagLayoutFrame.getContentPane().setLayout(
      new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10, 10, 10, 10);
    components = createComponents();

    int[] componentColumns = { 0, 1, 3, 4, 0, 0, 0, 4, 1, 2, 4, 4 };
    int[] componentRows =    { 0, 0, 0, 0, 1, 5, 3, 1, 1, 5, 3, 4 };
    int[] componentWidths =	 { 1, 2, 1, 1, 1, 2, 1, 1, 3, 2, 1, 1 };
    int[] componentHeights = { 1, 1, 1, 1, 2, 1, 2, 2, 4, 1, 1, 2 };
    int[] componentAnchors = {
      GridBagConstraints.NORTHWEST, GridBagConstraints.NORTH,
      GridBagConstraints.NORTH, GridBagConstraints.NORTHEAST,
      GridBagConstraints.SOUTHWEST, GridBagConstraints.SOUTH,
      GridBagConstraints.WEST, GridBagConstraints.EAST,
      GridBagConstraints.CENTER, GridBagConstraints.SOUTH,
      GridBagConstraints.EAST, GridBagConstraints.SOUTHEAST };
    int[] componentFills = {
      GridBagConstraints.NONE, GridBagConstraints.NONE,
      GridBagConstraints.NONE, GridBagConstraints.NONE,
      GridBagConstraints.NONE, GridBagConstraints.HORIZONTAL,
      GridBagConstraints.NONE, GridBagConstraints.VERTICAL,
      GridBagConstraints.BOTH, GridBagConstraints.HORIZONTAL,
      GridBagConstraints.NONE, GridBagConstraints.NONE };

    for (int i = 0; i < components.length; i ++) {
      constraints.gridx = componentColumns[i];
      constraints.gridy = componentRows[i];
      constraints.gridwidth = componentWidths[i];
      constraints.gridheight = componentHeights[i];
      constraints.anchor = componentAnchors[i];
      constraints.fill = componentFills[i];
      gridBagLayoutFrame.getContentPane().add(components[i], constraints);
    }
    gridBagLayoutFrame.addWindowListener(listener);
    gridBagLayoutFrame.pack();
    gridBagLayoutFrame.setLocation(250, 250);
    gridBagLayoutFrame.setVisible(true);

    // Create and show a frame with a CardLayout.  Only one component will
    // be visible at a time

    JFrame cardLayoutFrame = new ShrinkableFrame("CardLayout");
    CardLayout cardLayout = new CardLayout(5, 5);
    cardLayoutFrame.getContentPane().setLayout(cardLayout);
    components = createComponents();
    for (int i = 0; i < components.length; i ++) {
      cardLayoutFrame.getContentPane().add(components[i], "Card" + i);
    }
    cardLayoutFrame.addWindowListener(listener);
    cardLayoutFrame.pack();
    cardLayoutFrame.setLocation(300, 300);
    cardLayoutFrame.setVisible(true);

    // Cycle continuously through the cards in the frame with the CardLayout

    while (true) {
      synchronized(cardLayoutFrame) {
        try {
          cardLayoutFrame.wait(5000); /* Delay
                                       * five
                                       seconds */
        } catch (InterruptedException e) {
        }
        cardLayout.next(cardLayoutFrame.getContentPane());
      }
    }
  }

  // Create one component of each type, and return them in an array

  private static JComponent [] createComponents() {
    JButton myButton = new JButton("Button");

    JCheckBox myCheckBox = new JCheckBox("I did my homework", true);

    JComboBox myComboBox = new JComboBox();
    myComboBox.addItem("Ice Cream");
    myComboBox.addItem("Cake");
    myComboBox.addItem("Pie");

    JLabel myLabel = new JLabel("This says something");

    DefaultListModel listModel = new DefaultListModel();
    JList myList = new JList(listModel);
    listModel.addElement("Aardvark");
    listModel.addElement("Buffalo");
    listModel.addElement("Cat");
    listModel.addElement("Dog");
    listModel.addElement("Elephant");

    JProgressBar myProgressBar = new JProgressBar(0, 100);
    myProgressBar.setValue(50);

    JRadioButton iceCreamButton = new JRadioButton("Ice Cream", true);
    JRadioButton cakeButton = new JRadioButton("Cake");
    JRadioButton pieButton = new JRadioButton("Pie");
    ButtonGroup dessertGroup = new ButtonGroup();
    dessertGroup.add(iceCreamButton);
    dessertGroup.add(cakeButton);
    dessertGroup.add(pieButton);
    JPanel myRadioButtonsPanel = new JPanel();
    myRadioButtonsPanel.setLayout(new GridLayout(3, 1));
    myRadioButtonsPanel.add(iceCreamButton);
    myRadioButtonsPanel.add(cakeButton);
    myRadioButtonsPanel.add(pieButton);

    JScrollBar myScrollBar = new JScrollBar();

    // Illustration of how a JComponent can be used as a blank canvas for
    // arbitrary drawing by defining one's own paint() method and setting
    // a preferred size

    JComponent myCanvas = new JComponent() {
      public void paint(Graphics g)  {
        // Draw two blue rectangular boxes - one at the outside edges,
        // one 1/4 of the way in from sides, top, bottom

        g.setColor(Color.blue);
        g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
        g.drawRect(getSize().width / 4, getSize().height / 4,
                   getSize().width / 2, getSize().height / 2);

        // Draw a text message centered horizontally in the inner box

        g.setColor(Color.red);
        String line1 = "A JComponent used";
        String line2 = "as a blank canvas";
        String line3 = "for drawing";
        int boxWidth = getSize().width / 2;
        int lineHeight = 20;
        int leftBorder = getSize().width / 4;
        int topBorder = getSize().height / 4;
        int line1Width = g.getFontMetrics().stringWidth(line1);
        int line2Width = g.getFontMetrics().stringWidth(line2);
        int line3Width = g.getFontMetrics().stringWidth(line3);

        g.drawString(line1, leftBorder + (boxWidth - line1Width) / 2,
                     topBorder + lineHeight);
        g.drawString(line2, leftBorder + (boxWidth - line2Width) / 2,
                     topBorder + 2 * lineHeight);
        g.drawString(line3, leftBorder + (boxWidth - line3Width) / 2,
                     topBorder + 3 * lineHeight);

      }
    };

    myCanvas.setPreferredSize(new Dimension(400, 400));

    // A scroll pane shows a scrollable view of some other object - in this
    // case, the canvas created above

    JScrollPane myScrollPane = new JScrollPane(myCanvas);
    myScrollPane.setPreferredSize(new Dimension(100, 100));

    JSlider mySlider = new JSlider(0, 100);

    JTextField myTextField = new JTextField("Some text");

    JTextArea myTextArea = new JTextArea(
      "Even more text\nin fact -\n3 lines of it", 3, 10);


    JComponent [] allComponents =
      {
        myButton, myCheckBox, myComboBox, myLabel,
        myList, myProgressBar, myRadioButtonsPanel, myScrollBar,
        myScrollPane, mySlider, myTextField, myTextArea
      };

    return allComponents;
  }

  /**
   * This subclass of JFrame can be shrunk below the minimum size that would
   * otherwise be enforced to show how a LayoutManager deals with inadequate
   * space - which allows frames created by ComponentAndLayoutDemo to be
   * made arbitrarily small.  (This is needed on MacOSX because a frame
   * ordinarily cannot be resized below its minimum size)
   */
  private static class ShrinkableFrame extends JFrame {
    public ShrinkableFrame(String title) {
      super(title);
    }

    public Dimension getMinimumSize() {
      return new Dimension(1, 1);
    }
  }
}
