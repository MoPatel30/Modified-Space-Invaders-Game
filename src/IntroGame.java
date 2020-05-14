import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class IntroGame extends Screen{
	private Image image = ImageLoader.loadCompatibleImage("sprites/alienC1.png");
	private Image image2 = ImageLoader.loadCompatibleImage("sprites/mysteryShip.png");
	private Image image3 = ImageLoader.loadCompatibleImage("sprites/barrier.png");
	private Image image4 = ImageLoader.loadCompatibleImage("sprites/arrows.jpg");
	private Image image5 = ImageLoader.loadCompatibleImage("sprites/spacebar3.gif");
	IntroGame(GameState s, int w, int h) {
		super(s, w, h);
	}

	public void render(Graphics2D g) {
		g.setFont(new Font("Geneva", Font.BOLD, 20));
		g.setColor(Color.WHITE);
		g.drawString("Use left and right arrows to move", 250, 75);
		g.drawImage(image4, 20,20,null);
		g.drawString("Use Spacebar to shoot", 250, 175);
		g.drawImage(image5, 550,145,null);
		g.drawString("kill all aliens on screen to move to next nevel", 250, 275);
		g.drawImage(image, 100, 275, null);
		g.drawString("This Game is Hard, so your barriers have infinite life!", 250, 375);
		g.drawImage(image3, 80, 345, null);
		g.drawString("Shoot the mystery ship to gain an extra life!", 250, 475);
		g.drawImage(image2, 90,475,null);
		g.drawString("Good luck and go save the world!", 250, 575);
		g.drawString("Press Enter to Play", 250, 675);
	}

	public void update() {

	}

	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER)
			state.switchToGameScreen();
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void mousePressed(Point2D p)
	{
	}
	public void mouseReleased(Point2D p)
	{
	}
	public void mouseMoved(Point2D p)
	{
	}
	public void mouseDragged(Point2D p)
	{
	}
}
