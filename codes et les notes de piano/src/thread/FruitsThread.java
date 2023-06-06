package thread;

import ihm.Fruits;
import ihm.Paniers;
import ihm.ZoneDessin;


public class FruitsThread implements Runnable{

	private ZoneDessin z1;
	private boolean running;
	private Fruits fruitInstance;

	public int stockY;

	public FruitsThread(ZoneDessin z1, Fruits fruitInstance) {
		this.z1 = z1;
		this.running = false;
		this.fruitInstance = fruitInstance;
		this.stockY = 0;
	}

	public void setRunning(boolean b1) {
		this.running = b1;
	}

	public boolean getRunning() {
		return this.running;
	}
	public void setStockY(int y) {
		this.stockY = y;
	}

	public int getStockY() {
		return this.stockY;
	}

	

	@Override
	public void run() {

		int i = 0;
		int j = 0;

		while(running) {

			this.fruitInstance.setFruitY(this.fruitInstance.getFruitY()+1);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			for(Paniers ele: this.z1.getPanierList()) {
				if(this.z1.getPanierList().indexOf(ele) == this.fruitInstance.getFruitIndiceColonne()) {

					boolean attrape = (this.fruitInstance.getFruitY() + 40) >= 390 && (this.fruitInstance.getFruitY() <= 420) && ele.getPanierOuvert();

					boolean manque = (this.fruitInstance.getFruitY() + 40) >= 390 && (this.fruitInstance.getFruitY() <= 420) && ele.getPanierOuvert()== false;

					boolean auto = (this.fruitInstance.getFruitY() == 350) && this.z1.getAutomatoqie();
					
					if(attrape) {

						i++;
						this.fruitInstance.setJouerUneFois(i);

						if(this.fruitInstance.getJouerUneFois() == 1) {

							this.z1.setScores(this.z1.getScores()+1);
							this.z1.getScore1().setText(String.valueOf(this.z1.getScores()));

							this.fruitInstance.jouerNote();


						}
						ele.setAfficherDevant(true);

						this.fruitInstance.setDessinerFruit(false);
					}


					else if(auto) {
						i++;

						this.fruitInstance.setJouerUneFois(i);

						if(this.fruitInstance.getJouerUneFois() == 1) {

							this.z1.setScores(this.z1.getScores()+1);
							this.z1.getScore1().setText(String.valueOf(this.z1.getScores()));;

							this.fruitInstance.jouerNote();


						}

						PaniersThread r1 = new PaniersThread(this.z1, ele);
						Thread t1 = new Thread(r1);
						r1.setRunning(true);
						t1.start();
						ele.setAfficherDevant(true);
	
						this.fruitInstance.setDessinerFruit(false);

					}

					else if(manque){
						j++;
						if(j == 1) {
							
							this.z1.setManques(this.z1.getManques()+1);
							int m = this.z1.getManques()-this.z1.getScores();
							if(m>=0) {
								this.z1.getManque1().setText(String.valueOf(this.z1.getManques()-this.z1.getScores()));
							}
							else if(m<0) {
								this.z1.getManque1().setText("0");
							}

						}

					}
	
					if(this.fruitInstance.getFruitY()>=500) {
						this.running=false;
						this.z1.getFruitsList().remove(this.fruitInstance);
						this.fruitInstance=null;
						break;
					}


				}

			}


		}

	}

}
