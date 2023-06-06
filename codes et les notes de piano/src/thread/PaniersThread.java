package thread;

import ihm.Paniers;
import ihm.ZoneDessin;

public class PaniersThread implements Runnable{
	
	private boolean running;
	private Paniers panierInstance;
	
	public PaniersThread(ZoneDessin z1, Paniers panierInstance) {
		this.running = false;
		this.panierInstance = panierInstance;
	}
	
	public void setRunning(boolean r1) {
		this.running = r1;
	}
	
	public boolean getRunning() {
		return this.running;
	}
	
	
	
	@Override
	public void run() {
		
		while(this.running) {
			this.panierInstance.setImageIndex(1);
			this.panierInstance.setPanierOuvert(true);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			this.panierInstance.setImageIndex(0);
			this.panierInstance.setPanierOuvert(false);

			break;
			
		}
	}

}
