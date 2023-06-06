package thread;

import java.util.ArrayList;
import ihm.Melodie;
import ihm.ZoneDessin;

public class CreerFruitsThread implements Runnable{

	private ZoneDessin z1;
	private boolean creer;
	private ArrayList<Melodie> notesList;

	public CreerFruitsThread(ZoneDessin z1, ArrayList<Melodie> notesList) {
		this.z1 = z1;
		this.creer = false;
		this.notesList = notesList;
	}

	public void setCreer(boolean b1) {
		this.creer = b1;
	}

	public boolean getCreer() {
		return this.creer;
	}

	@Override
	public void run() {
		if(this.notesList != null) {
			while(this.creer) {

				for(Melodie ele : this.notesList){
					this.z1.fruitTomber(ele.getNote());
					if(this.z1.getAutomatoqie() == false) {

						try {
							Thread.sleep(ele.getTemps()+100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if(this.z1.getAutomatoqie()) {
						try {
							Thread.sleep(ele.getTemps());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				this.creer=false;
				break;

			}
		}
	}
}
