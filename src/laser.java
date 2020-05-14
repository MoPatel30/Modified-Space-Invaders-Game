/*
    This class represents a basic alien in the game
*/

import java.awt.*;
import java.awt.geom.*;

public class laser extends GameObject {

    private Image image = ImageLoader.loadCompatibleImage("sprites/playerLaser.png");
    private int updateCounter = 0;
	public boolean max = false ;
	public boolean movingRight = true ;
	public int value = 0;
	public int dir = 0;
	public laser(int x, int y, int w, int h, int d) {
		super(x,y,w,h);
		dir = d;		
	}

	public void update() {
	
        //keep track of how many times this Alien has been updated
        updateCounter++;
        if(updateCounter % 0.25 == 0) {
        	if(dir >0) {
        		this.getBounds().y+=2;
        	}
        	if(dir<0) {
        		this.getBounds().y-=4;
        	}
        }
        //every 120th update, move the bounds of the alien half it's width to the right
        if(updateCounter % 1000 == 0) {
        	 value = (int) getBounds().x;
        	if(max==false) {
        		getBounds().x += getBounds().width/2;	
        	}
            if(value>755) {
            	max=true;
            	getBounds().y +=getBounds().width/3;
            }
            if(max==true) {
            	getBounds().x -=getBounds().width/2;
            }
            if(value<2) {
            	max=false;
            	getBounds().y +=getBounds().width/3;
            }
            
        }  
	}
    
    //draw the image represented by the alien
	public void render(Graphics2D g) {

		g.drawImage(image,
                    (int)getBounds().x,
                    (int)getBounds().y,
                    (int)getBounds().width,
                    (int)getBounds().height,
                    null);

	}
	public int getDirection() {
		return dir;
	}
}