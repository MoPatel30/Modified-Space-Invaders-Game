/*
    Abstract class that represents a generic object that can appear on a Screen.
    
    A GameObject is defined by a bounds variable of type Rectangle2D.Double (http://docs.oracle.com/javase/7/docs/api/java/awt/geom/Rectangle2D.Double.html)
    The bounds variable has four properties: an x, y, width, and height, where (x, y) represents the upper left hand corner of the object
 
    GameObject's have two main actions: update() and render().  Both methods are called ~60 times a second.
    The update() method is responsible for moving the GameObject.  
    The render() method is responsible for drawing the GameObject.
    
    The getBounds() method is a public accessor to the private bounds variable
    
    The intersects methods can be used to see if two GameObjects are currently intersecting with each other
*/

import java.awt.*;
import java.awt.geom.*;

public abstract class GameObject {

    //the boundary of the GameObject with four properties:
    //boubnds.x, bounds.y, bounds.width, bounds.height
	private Rectangle2D.Double bounds;

    //Constructor of a GameObject instance
    //(x, y) = the upper left hand corner
    //w = width of the object
    //h = height of the object
	public GameObject(int x, int y, int w, int h) {
		bounds = new Rectangle2D.Double(x, y, w, h);
	}

    //method to be implemented by sublcasses to define the actions (usually movement) of the GameObject
	public abstract void update();
    
    //method to be implemented by sublcasses to draw the GameObject
	public abstract void render(Graphics2D g);

    //accessor method to retreive the bounds of the GameObject
	public Rectangle2D.Double getBounds() {
		return bounds;
	}

    //returns true if this GameObject's bounds intersects the other GameObject's bounds
	public boolean intersects(GameObject other) {
		return bounds.intersects(other.getBounds());
	}

}