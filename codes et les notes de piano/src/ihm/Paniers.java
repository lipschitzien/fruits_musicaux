package ihm;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Paniers {
	private ImageIcon imageicon1;
	private ImageIcon imageicon2;
	private ArrayList<ImageIcon> images;
	
	private ImageIcon imageiconDevant;
	private Image imageDevant;
	
	private int imageIndex;
	
	private int indexPanier;
	
	private boolean panierOuvert;
	
	private boolean afficherDevant;
	
	
	
	public Paniers(int indexPanier) {
		this.build();
		this.indexPanier = indexPanier;
		this.imageIndex = 0;
		this.panierOuvert = false;
		this.afficherDevant = false;

	}
	
	private void build() {
		this.imageicon1 = new ImageIcon("paniers/panier1.png");
		this.imageicon2 = new ImageIcon("paniers/panier3.png");
		
		this.images = new ArrayList<>();
		this.images.add(imageicon1);
		this.images.add(imageicon2);
		
		this.imageiconDevant = new ImageIcon("paniers/panier_devant.png");
		this.imageDevant = this.imageiconDevant.getImage();

		
	}
	
	public ArrayList<ImageIcon> getImageList(){
		return this.images;
	}
	
	public Image getImageDevant() {
		return this.imageDevant;
	}
	
	public void setIndexPanier(int indexPanier) {
		this.indexPanier = indexPanier;
	}
	
	public int getIndexPanier() {
		return this.indexPanier;
	}
	
	
	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}
	
	public int getImageIndex() {
		return this.imageIndex;
	}
	
	public void setPanierOuvert(boolean b1) {
		this.panierOuvert = b1;
	}
	
	public boolean getPanierOuvert() {
		return this.panierOuvert;
	}
	
	public void setAfficherDevant(boolean b1) {
		this.afficherDevant = b1;
	}
	
	public boolean getAfficherDevant() {
		return this.afficherDevant;
	}
	
	
}
