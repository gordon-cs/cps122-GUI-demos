/*
 * GUIEventsDemo.java - demonstration of using GUI evemts
 *
 * This program asks the user to enter a number, the outputs its square root
 *
 * Copyright (c) 2000, 2004, 2010 - Russell C. Bjork
 *
 */
 
import java.awt.event.*;
import javax.swing.*;

public class GUIEventsDemo extends JFrame implements ActionListener {
  // Instance variables - components of the GUI
  private JLabel numberPrompt;
  private JTextField numberInput;
  private JButton computeButton;
  private JLabel result;

  // Sizing information
  private static final int WIDTH = 400;
  private static final int HEIGHT = 350;

  /** Constructor - create the GUI
   */
  public GUIEventsDemo() {
    // For simplicity, we'll use absolute positioning
    getContentPane().setLayout(null);
    setSize(WIDTH, HEIGHT);
    setResizable(false);
        
    numberPrompt = new JLabel("Please enter a positive number");
    getContentPane().add(numberPrompt);
    numberPrompt.setBounds(50, 50, 200, 20);
        
    numberInput = new JTextField(20);
    getContentPane().add(numberInput);
    numberInput.setBounds(270, 50, 80, 20);
        
    computeButton = new JButton("Compute square root");
    getContentPane().add(computeButton);
    computeButton.setBounds(100, 150, 200, 20);
        
    result = new JLabel("", SwingConstants.CENTER);
    getContentPane().add(result);
    result.setBounds(50, 250, 300, 20);
        
    // Prepare to handle mouse click on the compute button
    computeButton.addActionListener(this);
  }
      
  /** Method invoked when the user clicks the compute button
   */
  public void actionPerformed(ActionEvent e) {
    // Get the number the user typed:
    String numberTyped = numberInput.getText();
        
    // Convert it to a float - code borrowed from Wu
    Double doubleObj = new Double(numberTyped);
    double value = doubleObj.doubleValue();
        
    // Compute its square root and put it in the result label
    double sqrt = Math.sqrt(value);
    result.setText("Square root is " + sqrt);
  }
    
  /** Main method for the program
   */
  public static void main(String [] args) {
    new GUIEventsDemo().setVisible(true);
  }
}
