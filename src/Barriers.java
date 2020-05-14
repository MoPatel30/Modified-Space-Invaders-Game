import java.awt.Graphics2D;
import java.awt.Image;

public class Barriers extends GameObject {
	
	private Image image = ImageLoader.loadCompatibleImage("sprites/barrier.png");
	private int updateCounter = 0;
	public boolean max = false ;
	public boolean movingRight = true ;
	public int value = 0;
	private int shoot = 240;
	private int health = 5;
	public Barriers(int x, int y, int w, int h) {
		super(x, y, w, h);
		health = 5;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		updateCounter++;

		//every 120th update, move the bounds of the alien half it's width to the right
		if(updateCounter % 10 == 0) {
			value = (int) getBounds().x;
		if(max==false) {
			getBounds().x += 5;	
			if(value>790) {
				max=true;
			}
		}
		
		if(max==true) {
			getBounds().x = 0;
			
		}
		max = false;
		}
	}

	@Override
	public void render(Graphics2D g) {

		g.drawImage(image,
				(int)getBounds().x,
				(int)getBounds().y,
				(int)getBounds().width,
				(int)getBounds().height,
				null);

	}

}
