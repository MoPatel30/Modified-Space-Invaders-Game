import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class WinScreen extends Screen
{
	private static Image img2 = ImageLoader.loadCompatibleImage("sprites/winner.jpg");
	public WinScreen(GameState s, int w, int h) {
		super(s, w, h);
	}
    
	public void render(Graphics2D g) {
		g.setFont(new Font("Geneva", Font.BOLD, 40));
        g.setColor(Color.RED);
        g.drawString("Congratulations!", 250, 350);
        g.drawString("Score: 19800", 250, 430);
        g.drawImage(img2,250,100,null);
	}
	
	public void update() {

	}
	
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER)
			state.switchToWelcomeScreen();
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