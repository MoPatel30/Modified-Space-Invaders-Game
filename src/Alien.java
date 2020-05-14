/*
    This class represents a basic alien in the game
 */

import java.awt.*;
import java.awt.geom.*;

public class Alien extends GameObject {

	private Image image = ImageLoader.loadCompatibleImage("sprites/alienC1.png");
	private int updateCounter = 0;
	public boolean max = false ;
	public boolean movingRight = true ;
	public int value = 0;
	private int shoot = 240;
	public Alien(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public void update() {

		//keep track of how many times this Alien has been updated
		updateCounter++;

		//every 120th update, move the bounds of the alien half it's width to the right
		if(updateCounter % 10 == 0)
			value = (int) getBounds().x;
		if(max==false) {
			getBounds().x += getBounds().width/10;	
		}
		if(value>755) {
			max=true;
			getBounds().y +=getBounds().width/15;
		}
		if(max==true) {
			getBounds().x -=getBounds().width/10;
		}
		if(value<2) {
			max=false;
			getBounds().y +=getBounds().width/15
					;
		}
		if(shoot>0) {
			shoot--;
		}

	}
	public boolean isMaster() {
		return false;
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
	public laser shoot() {
	
		
		if(shoot<=0) {
			shoot=240;
		
			return new laser((int)this.getBounds().x,(int)this.getBounds().y+5,10,20,1);
			
		}
		else {
			return null;
		}

	}
	public void setTheImage(Image i) {
		image = i;
	}

}