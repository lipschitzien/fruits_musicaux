package ihm;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Fruits {

	private ImageIcon fruitIcon;
	private Image fruitImage;
	private String path;
	
	private int fruitX;
	
	private int fruitY;
	
	private int indiceColonne;
	
	private boolean dessinerFruit;
	
	private String note;
	
	private int jouerUneFois;
	
	private javax.sound.sampled.Clip clip;
	
	public Fruits(String path, int fruitX, int indiceColonne, String note) {
		
		this.path = "fruits/" + path + ".png";
		this.fruitX = fruitX;
		this.build();
		this.fruitY = -40;
		this.indiceColonne = indiceColonne;
		this.dessinerFruit = true;
		this.note = note;
		this.jouerUneFois = 0;
	}
	
	private void build() {
		
		this.fruitIcon = new ImageIcon(this.path);
		this.fruitImage = this.fruitIcon.getImage();
		
	}
	
	public Image getFruitImage() {
		return this.fruitImage;
	}
	
	public void setFruitX(int fruitX) {
		this.fruitX = fruitX;
	}
	
	public int getFruitX() {
		return this.fruitX;
	}
	
	public void setFruitY(int fruitY) {
		this.fruitY = fruitY;
	}
	
	public int getFruitY() {
		return this.fruitY;
	}
	
	public void setFruitIndiceColonne(int indiceColonne) {
		this.indiceColonne = indiceColonne;
	}
	
	public int getFruitIndiceColonne() {
		return this.indiceColonne;
	}
	
	
	public void setDessinerFruit(boolean b1) {
		this.dessinerFruit = b1;
	}
	
	public boolean getDessinerFruit() {
		return this.dessinerFruit;
	}
	
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setJouerUneFois(int nombre) {
		this.jouerUneFois = nombre;
	}
	
	public int getJouerUneFois() {
		return this.jouerUneFois;
	}
	
	public void jouerNote() {
		
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(new File("piano_wav/" + this.note + ".wav"));

			try {
				this.clip = AudioSystem.getClip();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			try {
				clip.open(audioStream);
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			clip.start();

		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	
}

