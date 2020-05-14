/*
    Screen implementation that models a game
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class GameScreen extends Screen
{
	//variables that represent the different GameObjects in the game
	private ArrayList<Alien> aliens;
	private ArrayList<laser> lasers;
	private mysteryAlien boss;
	private mysteryAlien boss2;
	private boolean alive2 = false;
	private int bossLives=3;
	private int bossLives2 =3;
	private PowerUp extralife ;
	private boolean powerup = false;
	private Barriers barrier1;
	private Barriers barrier2;
	private Barriers barrier3;
	private Barriers barrier4;
	private Player player;
	private int score;
	private boolean alive =false;
	private int level1 = 0;
	private int highScore;
	private PowerShooter spray ;
	private boolean sprayer = false;
	private boolean bAlive1 = true;
	private boolean bAlive2 = true;
	private boolean bAlive3 = true;
	private boolean bAlive4 = true;
	private static Image img2 = ImageLoader.loadCompatibleImage("sprites/player.png");
	//this class inherits the following final variables (so you can't change them!)
	//
	//  GameState state;    //variable that lets you switch to another screen
	//  int width, height;  //the width and height of this screen

	public GameScreen(GameState s, int w, int h) {
		super(s, w, h);
		highScore = java.util.prefs.Preferences.userRoot().getInt("Highscore ", 0);

		initGame();
	}

	//initialize our variables before the next game begins
	public void initGame() {

		score = 0;
		alive = false;
		alive2=false;
		bossLives=3;
		bossLives2=3;
		bAlive1 = true;
		bAlive2 = true;
		bAlive3 = true;
		bAlive4 = true;
		sprayer=false;
		aliens = new ArrayList<Alien>();
		level1 = 0;
		int level = 125;
		player = new Player(width/2 - 23, height - 24, 45, 24);
		for(int i =200;i>=0;i-=100) {

			for(int k = 500;k>=0;k-=100) {
				aliens.add(new Alien(k/4,200+level,25,25));
			}

			level-=25;
		}

		lasers = new ArrayList<laser>();
		score=0;
		extralife = new PowerUp(375,130, 20, 10);
		powerup = false;
		barrier1= new Barriers(125,450,75,25);
		barrier2 = new Barriers(275,450,75,25);
		barrier3 = new Barriers(425,450,75,25);
		barrier4 = new Barriers(575, 450,75,25);
		spray = new PowerShooter(350,160,25,25);


	}


	//render all the game objects in the game
	public void render(Graphics2D g) {
		if(bAlive1) {
			barrier1.render(g);
		}

		if(bAlive2) {
			barrier2.render(g);
		}
		if(bAlive3) {
			barrier3.render(g);
		}
		if(bAlive4) {
			barrier4.render(g);
		}

		for(Alien a: aliens) 
			a.render(g);
		for(laser l : lasers)
			l.render(g);
		if(alive) {
			boss.render(g);
		}
		if(alive2) {
			boss2.render(g);
		}
		if(sprayer) {
			spray.render(g);
		}
		if(powerup) {
			extralife.render(g);
		}
		player.render(g);
		int lives1 = player.getLives();
		for(int i=lives1;i>0;i--) {
			g.drawImage(img2,(img2.getWidth(null)+i*50)-45,img2.getHeight(null),null);

		}
		g.setFont(new Font("Geneva", Font.BOLD, 20));
		g.setColor(Color.GREEN);
		g.drawString("Score: " + score, 600, 50);
		g.drawString("Highscore: "+ highScore, 600, 25);
	}



	//update all the game objects in the game
	public void update() {

		if((score==1000 || score==7000 || score==12500) && !alive) {
			boss = new mysteryAlien(350,60, 75,75);
			boss.update();
			alive = true;
		}
		if((score==3500 || score==10000 ||score ==15000) && !alive) {
			boss = new mysteryAlien(755,65, 75,75);
			boss2 = new mysteryAlien(0,65,75,75);
			boss.update();
			boss2.update();
			alive = true;
			alive2 = true;
		}
		if(alive) {
			boss.update();
			laser k = boss.shoot();
			if(k != null) {
				lasers.add(k);
			}
		}
		if(alive2) {
			boss2.update();
			laser k = boss2.shoot();
			if(k != null) {
				lasers.add(k);
			}
		}
		for(int i = 0; i < aliens.size(); i++) {
			Alien a = aliens.get(i);
			a.update();
			Alien a1 = aliens.get(i%3);
			laser l= a1.shoot();
			if(l !=null) {
				lasers.add(l); 
			}


		}

		for(int j = 0;j<lasers.size();j++) {
			laser l = lasers.get(j);
			l.update();

		}


		Sound dead= new Sound("invaderkilled.wav");
		for( int k = lasers.size()-1;k>=0;k--) {
			for(int m = 0; m<aliens.size();m++) {
				if(aliens.get(m).intersects(lasers.get(k)) && lasers.get(k).getDirection() == -1 && !aliens.get(m).isMaster()) {
					dead.play();
					score+=100;
					aliens.remove(aliens.get(m));
					lasers.remove(lasers.get(k));
					break;
				}
			}


		}
		for( int k = lasers.size()-1;k>=0;k--) {
			if(alive) {
				if(boss.intersects(lasers.get(k)) && lasers.get(k).getDirection() == -1) {
					bossLives--;
					lasers.remove(lasers.get(k));
					if(bossLives==0) {
						dead.play();
						alive = false;
						score +=000;
						bossLives=3;
					}
				}


			}
		}
		for( int k = lasers.size()-1;k>=0;k--) {
			if(alive2) {
				if(boss2.intersects(lasers.get(k)) && lasers.get(k).getDirection() == -1) {
					bossLives2--;
					lasers.remove(lasers.get(k));
					if(bossLives2==0) {
						dead.play();
						alive2 = false;
						score +=000;
						bossLives2=3;
					}
				}


			}
		}

		player.update();
		for( int l = 0;l<aliens.size();l++) {
			if(aliens.get(l).intersects(player)) {
				player.loseLife();
				if(player.getLives()==0) {
					gameOver();
					initGame();
				}
			}
			/*	if(aliens.get(l).intersects(barrier1)) {
				bAlive1=false;
			}
			if(aliens.get(l).intersects(barrier2)) {
				bAlive2=false;
			}
			if(aliens.get(l).intersects(barrier3)) {
				bAlive3=false;
			}
			if(aliens.get(l).intersects(barrier4)) {
				bAlive4=false;
			}*/
		}
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(player) && lasers.get(k).getDirection() == 1 ){
				player.loseLife();
				lasers.remove(lasers.get(k));			
				break;
			}
		}
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(extralife) && lasers.get(k).getDirection() == -1 ){
				player.gainLife();
				powerup=false;
				lasers.remove(lasers.get(k));
			}

		}
		for( int k = lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(spray) && lasers.get(k).getDirection()==-1) {
			player.shootPower();
			}
		}
		if(100<=score && score!=0) {
			sprayer=true;
			spray.update();
		}
		powerup = true;
		extralife.update();
		if(player.getLives()>7) {
			powerup=false;
		}
		if(player.getLives()==0) {
			gameOver();
			initGame();
		}
		if(score==19800) {
			WinScreen();
			initGame();
		}
		/*		for(int k= lasers.size()-1;k>=0;k--) {
			for(int b = 0; b<barrier.size();b++) {
				if(lasers.get(k).intersects(barrier.get(0)) &&  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
					barrierLive1--;
					if(barrierLive1==0) {
						barrier.remove(b);

					}
				}
				if(lasers.get(k).intersects(barrier.get(1)) &&  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
					barrierLive2--;
					if(barrierLive2==0) {
						barrier.remove(b);

					}
				}
				if(lasers.get(k).intersects(barrier.get(2)) &&  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
					barrierLive3--;
					if(barrierLive3==0) {
						barrier.remove(b);

					}
				}
				if(lasers.get(k).intersects(barrier.get(3)) &&  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
					barrierLive4--;
					if(barrierLive4==0) {
						barrier.remove(b);

					}
				}
			}
		}*/
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(barrier2) &&  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
				//barrierLive2--;
				lasers.remove(lasers.get(k));
				/*if(barrierLive2==0) {
					bAlive2=false;

				}*/

			}
		}
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(barrier3) && (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
				//barrierLive3--;

				lasers.remove(lasers.get(k));
				/*if(barrierLive3==0) {
					bAlive3=false;

				}*/

			}

		}
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(barrier4) &&  (lasers.get(k).getDirection() == 1 ||  (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1))){
				//barrierLive4--;

				lasers.remove(lasers.get(k));
				/*if(barrierLive4==0) {
					bAlive4=false;

				}*/

			}

		}
		for(int k= lasers.size()-1;k>=0;k--) {
			if(lasers.get(k).intersects(barrier1) && (lasers.get(k).getDirection() == 1 || lasers.get(k).getDirection() == -1)){
				//	barrierLive1--;

				lasers.remove(lasers.get(k));
				/*if(barrierLive1==0) {
					bAlive1=false;

				}*/

			}

		}



		if(score>highScore) {
			highScore=score;
		}


		int level2=0;
		if(score==1800 && level1==0) {//1800

			level1++;
			for(int i = 200+(level1*100);i>=0;i-=100) {

				for(int k = 500;k>=0;k-=100) {
					aliens.add(new Alien(k/4,300+level2,25,25));
				}

				level2-=25;
			}
		}
		if(score==4200 && level1==1) {//4200

			level1++;
			for(int i = 200+(level1*100);i>=0;i-=100) {

				for(int k = 500;k>=0;k-=100) {
					aliens.add(new Alien(k/4,300+level2,25,25));
				}

				level2-=25;
			}
		}
		if(score==7200 && level1==2) {//7200
			level1++;
			for(int i = 200+(level1*100);i>=0;i-=100) {

				for(int k = 500;k>=0;k-=100) {
					aliens.add(new Alien(k/4,300+level2,25,25));
				}

				level2-=25;
			}
		}
		if(score==10800 && level1==3) { //10800
			level1++;
			for(int i = 200+(level1*100);i>=0;i-=100) {

				for(int k = 500;k>=0;k-=100) {
					aliens.add(new Alien(k/4,350+level2,25,25));
				}

				level2-=25;
			}
		}
		if(score==15000 && level1==4) { //15000
			level1++;
			for(int i = 200+(level1*100);i>=0;i-=100) {

				for(int k = 500;k>=0;k-=100) {
					aliens.add(new Alien(k/4,350+level2,25,25));
				}

				level2-=25;
			}
		}


	}



	//handles key press events
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_Q)
			state.switchToWelcomeScreen();

		else if(code == KeyEvent.VK_LEFT)
			player.setMovingLeft(true);

		else if(code == KeyEvent.VK_RIGHT)
			player.setMovingRight(true);

		if(code == KeyEvent.VK_SPACE) {
			Sound shoot = new Sound("shoot.wav");
			laser o = player.shoot();
			shoot.play();
			if(o!=null) {
				lasers.add(o);
			}
		}
	}

	//handles key released events
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_LEFT)
			player.setMovingLeft(false);
		if(code == KeyEvent.VK_RIGHT)
			player.setMovingRight(false);
	}




	//should be called when the game is over
	public void gameOver() {
		java.util.prefs.Preferences.userRoot().putInt("highScore", highScore);
		try {
			java.util.prefs.Preferences.userRoot().sync();
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		//sets up the next game
		initGame();

		//switch to the game over screen
		state.switchToGameOverScreen();
	}
	public void levelScreen() {
		state.switchTolevelScreen();
	}
	public void IntroGame() {
		state.switchToIntroGame();
	}
	public void WinScreen() {
		state.switchToWinScreen();
		score=0;
	}

	//implement these methods if your player can use the mouse
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