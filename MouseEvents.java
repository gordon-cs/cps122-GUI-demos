/*
 * MouseEvents.java
 *
 * This program creates a canvas that displays the word "Cheese", and then reports
 * each mouse event that takes place on that canvas.
 *
 * Copyright (c) 2000, 2004 - Russell C. Bjork
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MouseEvents extends JFrame {
  // Constructor for the GUI

  MouseEvents() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JComponent canvas = new JComponent() {
        public void paint(Graphics g) {
          g.setColor(Color.yellow);
          g.fillRect(getBounds().x, getBounds().y,
                     getBounds().width, getBounds().height);
          String text = "C h e e s e";
          int textWidth = g.getFontMetrics().stringWidth(text);
          g.setColor(Color.black);
          g.drawString(text, (getBounds().width - textWidth) / 2,
                       (getBounds().height - 20) / 2 + 10);
        }
    };

    canvas.setFont(new Font("SansSerif", Font.BOLD, 36));
    getContentPane().add(canvas, BorderLayout.CENTER);
    setSize(250, 200);

    canvas.addMouseListener(new MouseListener() {
        public void mouseClicked(MouseEvent event)
          { describeEvent("clicked ", event); }
        public void mouseEntered(MouseEvent event)
          { describeEvent("entered ", event); }
        public void mouseExited(MouseEvent event)
          { describeEvent("exited  ", event); }
        public void mousePressed(MouseEvent event)
          { describeEvent("pressed ", event); }
        public void mouseReleased(MouseEvent event)
          { describeEvent("released", event); }
    });
    canvas.addMouseMotionListener(new MouseMotionListener() {
        public void mouseDragged(MouseEvent event)
          { describeEvent("dragged ", event); }
        public void mouseMoved(MouseEvent event)
          { describeEvent("moved   ", event); }
    });
  }

  // Describe a mouse event to System.out
  private void describeEvent(String type, MouseEvent event) {
    System.out.print("Mouse " + type + " at " + event.getX() + " "
                     + event.getY());
    if (event.getClickCount() > 1) {
      System.out.println(" clicks: " + event.getClickCount());
    } else {
      System.out.println();
    }
  }

  // main program - create an instance of the GUI and let it do the work
  public static void main(String [] args) {
    new MouseEvents().setVisible(true);
  }
}
