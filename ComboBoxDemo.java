/* ComboBoxDemo.java */
import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* This class demonstrates the use of a Combo box to control the speed of a
 * falling ball.  Adapted from example in Bruce et al.
 */
public class ComboBoxDemo extends WindowController implements ActionListener {
    
  // location of instructions
  private static final Location INSTR_LOCATION = new Location(100,25);
    
  private static final int SLOW_SPEED = 1,   // slow setting
    MEDIUM_SPEED = 3, // medium setting
    FAST_SPEED = 10;   // fast setting
    
  private FallingBall droppedBall; // the falling ball
    
  private JComboBox speedChoice;   // Combo box to select ball speed
  private int speed;               // Current speed setting
    
  // display instructions and combo box
  public void begin() {
    new Text( "Click to make a falling ball...", INSTR_LOCATION, canvas );

    speed = SLOW_SPEED;

    speedChoice = new JComboBox();         //construct combo box
        
    speedChoice.addItem ( "Slow" );        // Add 3 entries
    speedChoice.addItem ( "Medium" );
    speedChoice.addItem ( "Fast" );
    speedChoice.setSelectedItem( "Medium" ); // Display "Medium" initially

    speedChoice.addActionListener ( this );    // this class is listener
        
    Container contentPane = getContentPane(); // Add combo box to south
    contentPane.add( speedChoice, BorderLayout.SOUTH );
    contentPane.validate();
  }
    
  // make a new ball when the player clicks
  public void onMouseClick( Location point ) {
    droppedBall = new FallingBall( point, speed, canvas );
  }
    
  // reset ball speed from combo box setting
  public void actionPerformed( ActionEvent evt ) {
    Object newLevel = speedChoice.getSelectedItem ();
    if ( newLevel.equals( "Slow" )) {
      speed = SLOW_SPEED;
    } else if ( newLevel.equals( "Medium" )) {
      speed = MEDIUM_SPEED;
    } else if ( newLevel.equals( "Fast" )) {
      speed = FAST_SPEED;
    }
    if ( droppedBall != null ) {
      droppedBall.setSpeed( speed );
    }
  }
    
  // Main program for application
  public static void main(String [] args) {
    ComboBoxDemo theDemo = new ComboBoxDemo();
    theDemo.startController(500, 500);
  }
    
  /** Animate a falling ball
   */
  class FallingBall extends ActiveObject {
		
    private static final int BALLSIZE = 30;
    private static final int DELAY_TIME = 33;
		
    private DrawingCanvas canvas; // canvas to draw on
    private FilledOval ball;      // Image of ball as circle
    private int speed;            // current speed of ball
		
    // Draw ball at location and w/speed given in parameters
    public FallingBall( Location ballLocation, int initSpeed,
                        DrawingCanvas aCanvas ) {
      canvas = aCanvas;
      ball = new FilledOval( ballLocation, BALLSIZE, BALLSIZE, canvas );
      speed = initSpeed;
      start();
    }
		
    // Move ball down until off of canvas
    public void run() {
      while ( ball.getY() < canvas.getHeight() ) {
        ball.move( 0, speed );
        pause( DELAY_TIME );
      }
      ball.removeFromCanvas();
    }
		
    // reset speed of ball
    public void setSpeed( int newSpeed ) {
      speed = newSpeed;
    }
  }
}
