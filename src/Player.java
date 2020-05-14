/*
    Class that represents a player in the game
 */

import java.awt.*;
import java.awt.geom.*;

public class Player extends GameObject {

	//the image used for the player
	private Image image = ImageLoader.loadCompatibleImage("sprites/player.png");
	
	//keeps track of whether the player is moving to the left
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private int shootCountdown = 0;
	private int lives = 3;
	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	Sound effect= new Sound("shoot.wav");
	public void update() {
		//if we're moving to the left, decrease the player's boundary x by 1
		if(movingLeft) {
			getBounds().x-=3;
			if(getBounds().x<=-1) {
				getBounds().x+=3;
			}
		}
		if(movingRight) {
			getBounds().x+=3;
			if(getBounds().x > 755){ // switch later
				getBounds().x-=3;
			}
		}
		if(shootCountdown>0) {
			shootCountdown--;
		}

	}
	public laser shoot() {
		if(shootCountdown<=0) {
			shootCountdown=80;
			//effect.play();
			return new laser((int)getBounds().x+18,(int)getBounds().y,10,20,-1);
			
		}
		return null;
	}
	public void loseLife() {
		lives--;
	}
	public int getLives() {
		return lives;
	}
	public void gainLife() {
		lives++;
	}
	public void shootPower() {
		shootCountdown=5;
	}
	//tell the player if they should be moving left or right
	public void setMovingLeft(boolean ml) {
		movingLeft = ml;
	}
	public void setMovingRight(boolean m1) {
		movingRight = m1;
	}
	//draw the player with the player's image
	public void render(Graphics2D g) {

		g.drawImage(image,
				(int)getBounds().x,
				(int)getBounds().y,
				(int)getBounds().width,
				(int)getBounds().height,
				null);

	}

}