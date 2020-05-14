/*
    Abstract class that represents a general screen
    
    Screens support being rendered (drawn) and updated
    They also support handling key events and mouse events
    
    The GameState object controls which Screen is currently "active"
    The width and height variables control the height of the screen
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public abstract class Screen
{
	protected final GameState state;
	protected final int width, height;
	
	public Screen(GameState s, int w, int h) {
		state = s;
		width = w;
		height = h;
	}
	
	public abstract void render(Graphics2D g);
	public abstract void update();
	
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void mousePressed(Point2D p);
	public abstract void mouseReleased(Point2D p);
	public abstract void mouseMoved(Point2D p);
	public abstract void mouseDragged(Point2D p);
}