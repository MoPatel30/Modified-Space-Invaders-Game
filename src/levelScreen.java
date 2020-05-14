/*
    Screen implementation that models a game
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class levelScreen extends Screen
{
	private int level = 1;
	public levelScreen(GameState s, int w, int h) {
		super(s, w, h);
	}
    
	public void render(Graphics2D g) {
	g.setColor(Color.YELLOW);
	g.setFont(new Font("Geneva" , Font.BOLD, 50));
	g.drawString("Congratulations! On to the next level!", 350, 200);
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