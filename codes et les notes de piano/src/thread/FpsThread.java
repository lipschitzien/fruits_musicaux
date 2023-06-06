package thread;

import javax.swing.JPanel;

import ihm.ZoneDessin;

public class FpsThread extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	private int fps;
	private boolean gameRunning;
	private ZoneDessin z1;

	public FpsThread(ZoneDessin z1) {
		this.fps = 60;
		this.gameRunning = false;
		this.z1 = z1;
	}

	public void setGameRunning(boolean b1) {
		this.gameRunning = b1;
	}

	public boolean getGameRunning() {
		return this.gameRunning;
	}


	@Override
	public void run() {

		double drawInterval = 1000000000/fps; //0.01666s, 1 seconde 60 photos
		double delta = 0;
		long lastTime = System.nanoTime();

		long currentTime;

		long timer = 0;
	

		while(this.gameRunning) {

			currentTime = System.nanoTime();
			delta = delta + (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if(delta >=1) {  //while marche aussi, mais "if" est suiffisant normalement
				this.z1.repaint();
				delta--;
			}

			if(timer > 1000000000) {
				timer = 0;

			}



		}
	}
}
