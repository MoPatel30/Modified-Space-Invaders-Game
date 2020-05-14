import java.awt.Graphics2D;
import java.awt.Image;

public class PowerUp extends Alien {


	private Image image = ImageLoader.loadCompatibleImage("sprites/mysteryShip.png");
	private int updateCounter = 0;
	public boolean max = false ;
	public boolean movingRight = true ;
	public int value = 0;
	private int shoot = 240;
	public PowerUp(int x, int y, int w, int h) {
		super(x, y, w, h);

	}

	public void update() {

		//keep track of how many times this Alien has been updated
		updateCounter++;

		//every 120th update, move the bounds of the alien half it's width to the right
		if(updateCounter % 10 == 0)
			value = (int) getBounds().y;
		if(max==false) {
			getBounds().x +=3.5;	
			if(getBounds().x>=755) {
				max=true;
			}
		}
		if(max==true) {
			getBounds().x -=3.5;
			if(getBounds().x<=0) {
				max=false;
			}
		}
		if(value<20) {
			max=false;
			getBounds().y +=5;
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

