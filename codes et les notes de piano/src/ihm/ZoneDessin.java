package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;

import thread.FpsThread;
import thread.FruitsThread;
import thread.PaniersThread;
import thread.CreerFruitsThread;

public class ZoneDessin extends JPanel implements KeyListener{

	private static final long serialVersionUID = 1L;
	private FpsThread fps;
	private PaniersThread p1;

	private Paniers paniersListQ;
	private Paniers paniersListS;
	private Paniers paniersListD;
	private Paniers paniersListF;
	private Paniers paniersListJ;
	private Paniers paniersListK;
	private Paniers paniersListL;
	private Paniers paniersListM;

	private PaniersThread p2;
	private PaniersThread p3;
	private PaniersThread p4;
	private PaniersThread p5;
	private PaniersThread p6;
	private PaniersThread p7;
	private PaniersThread p8;

	private int fruitY;
	private int panierY;


	private ArrayList<Fruits> fruitsList;

	private ArrayList<String> nomFruits;

	private ArrayList<Paniers> paniersList;

	private boolean demarrerUnefois;

	private String[] tokens;

	private JPanel droitePanel;
	private JLabel lblScores;
	private JLabel lblTemps;

	private JLabel score1;
	private JLabel manque1;

	private JPanel panel1;
	private JPanel panel2;

	private int scores;
	private int manques;

	private JPanel panel3;


	private ArrayList<FruitsThread> fruitsThreadList;

	private boolean creerFruits;

	private FenetreInscription f3;

	private String[] ligne;

	private boolean automatique;

	private JLabel lblModeAutomatique;
	private JLabel lblModeJeu;

	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl5;
	private JLabel lblm1;
	private JLabel lblm2;

	private javax.sound.sampled.Clip clip;
	
	private ArrayList<Melodie> notesList;
	
	private int numeroFruit;


	public ZoneDessin() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.build();

		this.fps = null;
		this.fruitY = 0;
		this.panierY = 360;

		this.demarrerUnefois = false;

		//instancier les 8 paniers

		this.paniersListQ = new Paniers(0);
		this.paniersListS = new Paniers(1);
		this.paniersListD = new Paniers(2);
		this.paniersListF = new Paniers(3);
		this.paniersListJ = new Paniers(4);
		this.paniersListK = new Paniers(5);
		this.paniersListL = new Paniers(6);
		this.paniersListM = new Paniers(7);


		this.paniersList = new ArrayList<>();
		this.paniersList.add(this.paniersListQ);
		this.paniersList.add(this.paniersListS);
		this.paniersList.add(this.paniersListD);
		this.paniersList.add(this.paniersListF);
		this.paniersList.add(this.paniersListJ);
		this.paniersList.add(this.paniersListK);
		this.paniersList.add(this.paniersListL);
		this.paniersList.add(this.paniersListM);

		this.fruitsList = new ArrayList<>();

		this.nomFruits = new ArrayList<>();
		this.nomFruits.add("pomme");
		this.nomFruits.add("orange");
		this.nomFruits.add("banana");
		this.nomFruits.add("cerise");
		this.nomFruits.add("citron");

		this.tokens = null;
		this.scores = 0;
		this.manques = 0;

		this.fruitsThreadList = new ArrayList<>();	
		this.creerFruits = true;


		this.f3 = new FenetreInscription("Sauvegarder les scores", this);

		this.automatique = false;
		
		this.notesList = new ArrayList<>();
		this.numeroFruit = 0;

	}

	private void build() {

		this.droitePanel = new JPanel();
		this.panel1 = new JPanel(new BorderLayout());
		this.panel1.setBackground(Color.white);

		this.panel2 = new JPanel(new BorderLayout());
		this.panel2.setBackground(Color.white);

		this.score1 = new JLabel(String.valueOf(this.getScores()), JLabel.CENTER);
		this.manque1 = new JLabel(String.valueOf(this.getManques()), JLabel.CENTER);

		this.lblScores = new JLabel("attrapé:", JLabel.CENTER);
		this.lblTemps = new JLabel("manqué:", JLabel.CENTER);

		this.setLayout(new BorderLayout());

		this.droitePanel.setPreferredSize(new Dimension(99, 500));
		this.droitePanel.setBackground(Color.white);

		this.panel1.setPreferredSize(new Dimension(80, 80));
		this.panel2.setPreferredSize(new Dimension(80, 80));


		this.panel1.add(lblScores, BorderLayout.NORTH);
		this.panel1.add(score1);
		this.panel2.add(lblTemps, BorderLayout.NORTH);
		this.panel2.add(manque1);

		this.panel3 = new JPanel(new GridLayout(17, 1));
		
		//JLabel lbl11 = new JLabel("  ");
		JLabel lbl0 = new JLabel("X: Démarrer");
		
		JLabel lbl6 = new JLabel("  ");
		this.lbl1 = new JLabel("A: Mélodie1 ");
		
		JLabel lbl7 = new JLabel("  ");
		this.lbl2 = new JLabel("Z: Mélodie2 ");
		
		JLabel lblEspace1 = new JLabel("  ");
		this.lblm1 = new JLabel("E: Mélodie3 ");
		
		JLabel lblEspace2 = new JLabel("  ");
		this.lblm2 = new JLabel("R: Mélodie4 ");
		
		JLabel lbl8 = new JLabel("  ");
		this.lblModeAutomatique = new JLabel("O: Automatique ");
		
		JLabel lbl9 = new JLabel("  ");
		this.lblModeJeu = new JLabel("P: ModeJeu ");
		
		
		JLabel lbl10 = new JLabel("  ");
		this.lbl5 = new JLabel("0: Aléatoire ");
		
		JLabel lbl13 = new JLabel("  ");
		JLabel lbl12 = new JLabel("Y: Arrêter ");
			

		//this.panel3.add(lbl11);
		this.panel3.add(lbl0);
		
		this.panel3.add(lbl6);
		this.panel3.add(lbl1);
		
		this.panel3.add(lbl7);
		this.panel3.add(lbl2);
		
		this.panel3.add(lblEspace1);
		this.panel3.add(this.lblm1);
		
		this.panel3.add(lblEspace2);
		this.panel3.add(this.lblm2);
		
		this.panel3.add(lbl8);
		this.panel3.add(this.lblModeAutomatique);
		
		this.panel3.add(lbl9);
		this.panel3.add(this.lblModeJeu);
		
		this.panel3.add(lbl10);
		this.panel3.add(this.lbl5);
		
		this.panel3.add(lbl13);
		this.panel3.add(lbl12);

		this.panel3.setBackground(Color.white);


		this.lblModeJeu.setOpaque(true);
		this.lblModeJeu.setBackground(Color.WHITE);


		this.lblModeAutomatique.setOpaque(true);
		this.lblModeAutomatique.setBackground(Color.WHITE);

		
		this.lblm1.setOpaque(true);
		this.lblm1.setBackground(Color.WHITE);
		
		this.lblm2.setOpaque(true);
		this.lblm2.setBackground(Color.WHITE);
		
		
		this.lbl1.setOpaque(true);
		this.lbl1.setBackground(Color.WHITE);

		this.lbl2.setOpaque(true);
		this.lbl2.setBackground(Color.WHITE);

		this.lbl5.setOpaque(true);
		this.lbl5.setBackground(Color.WHITE);


		this.droitePanel.add(panel1);
		this.droitePanel.add(panel2);
		this.droitePanel.add(panel3);
		
		this.add(droitePanel, BorderLayout.EAST);

		this.setFocusable(true);		
		this.requestFocusInWindow();	


		this.addKeyListener(this);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;



		int longeur = this.getWidth();
		int largeur = this.getHeight();

		int longeurCol = 800 / 8;

		//les 8 colonnes
		for(int i=1;i<9;i++) {
			g2.drawLine(i*longeurCol, 0, i*longeurCol, largeur);
		}

		//une ligne pour le panier
		g2.drawLine(0,largeur-70, longeur, largeur-70); //environ 350

		//clavier
		ArrayList<String> clavier1 = new ArrayList<>();
		clavier1.add("Q"); //50 1 0
		clavier1.add("S"); //150 3 1
		clavier1.add("D"); //250 5 2
		clavier1.add("F"); //350 7 3
		clavier1.add("J");
		clavier1.add("K");
		clavier1.add("L");
		clavier1.add("M");

		for(String ele: clavier1) {
			g2.drawString(ele, (2*(clavier1.indexOf(ele))+1)*50, largeur-5);
		}

		//déssiner les 8 paniers

		if(this.paniersListQ.getImageList().size()>0) { //si ArrayList<ImageIcon> taille > 0

			if (this.paniersListQ.getImageList().get(this.paniersListQ.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListQ.getImageList().get(this.paniersListQ.getImageIndex()).paintIcon(this, g, this.paniersListQ.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListS.getImageList().size()>0) {
			if (this.paniersListS.getImageList().get(this.paniersListS.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListS.getImageList().get(this.paniersListS.getImageIndex()).paintIcon(this, g, this.paniersListS.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListD.getImageList().size()>0) {
			if (this.paniersListD.getImageList().get(this.paniersListD.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListD.getImageList().get(this.paniersListD.getImageIndex()).paintIcon(this, g, this.paniersListD.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListF.getImageList().size()>0) { 
			if (this.paniersListF.getImageList().get(this.paniersListF.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListF.getImageList().get(this.paniersListF.getImageIndex()).paintIcon(this, g, this.paniersListF.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListJ.getImageList().size()>0) {
			if (this.paniersListJ.getImageList().get(this.paniersListJ.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListJ.getImageList().get(this.paniersListJ.getImageIndex()).paintIcon(this, g, this.paniersListJ.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListK.getImageList().size()>0) {
			if (this.paniersListK.getImageList().get(this.paniersListK.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListK.getImageList().get(this.paniersListK.getImageIndex()).paintIcon(this, g, this.paniersListK.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListL.getImageList().size()>0) {
			if (this.paniersListL.getImageList().get(this.paniersListL.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListL.getImageList().get(this.paniersListL.getImageIndex()).paintIcon(this, g, this.paniersListL.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.paniersListM.getImageList().size()>0) {
			if (this.paniersListM.getImageList().get(this.paniersListM.getImageIndex()).getImageLoadStatus() == MediaTracker.COMPLETE) {
				this.paniersListM.getImageList().get(this.paniersListM.getImageIndex()).paintIcon(this, g, this.paniersListM.getIndexPanier()*100+32, this.panierY);

			}
		}

		if(this.fruitsList!=null) {
			if(this.fruitsList.size() !=0 ) {
				try { //pour l'exception
					for(Fruits ele : this.fruitsList) {
						if(ele.getDessinerFruit()) {
							g.drawImage(ele.getFruitImage(), ele.getFruitX(), ele.getFruitY(), 40, 40, null);
					
						}
					}
				}catch(ConcurrentModificationException e) {

				}

			}
		}



		//pour déssiner le panier devant
		if(this.paniersListQ.getAfficherDevant()) {//Q
			g.drawImage(this.paniersListQ.getImageDevant(), 32, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListQ.setAfficherDevant(false);
		}

		if(this.paniersListS.getAfficherDevant()) {//S
			g.drawImage(this.paniersListS.getImageDevant(), 132, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListS.setAfficherDevant(false);
		}

		if(this.paniersListD.getAfficherDevant()) {//D
			g.drawImage(this.paniersListD.getImageDevant(), 232, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListD.setAfficherDevant(false);
		}
		if(this.paniersListF.getAfficherDevant()) {//F
			g.drawImage(this.paniersListF.getImageDevant(), 332, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListF.setAfficherDevant(false);
		}
		if(this.paniersListJ.getAfficherDevant()) {//J
			g.drawImage(this.paniersListJ.getImageDevant(), 432, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListJ.setAfficherDevant(false);
		}
		if(this.paniersListK.getAfficherDevant()) {//K
			g.drawImage(this.paniersListK.getImageDevant(), 532, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListK.setAfficherDevant(false);
		}
		if(this.paniersListL.getAfficherDevant()) {//L
			g.drawImage(this.paniersListL.getImageDevant(), 632, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListL.setAfficherDevant(false);
		}
		if(this.paniersListM.getAfficherDevant()) {//M
			g.drawImage(this.paniersListM.getImageDevant(), 732, 360, 40, 70, null); //déssiner un panier_devant si on a attrapé le fruit
			this.paniersListM.setAfficherDevant(false);
		}



	}

	public void setFruitY(int y) {
		this.fruitY = y;
	}

	public int getFruitY() {
		return this.fruitY;
	}


	public int getPanierY() {
		return this.panierY;
	}

	public void setPanierY(int nouveauY) {
		this.panierY=nouveauY;
	}

	public void setScores(int score) {
		this.scores = score;
	}

	public int getScores() {
		return this.scores;
	}

	public JLabel getScore1() {
		return this.score1;
	}

	public void setManques(int manque) {
		this.manques = manque;
	}

	public int getManques() {
		return this.manques;
	}

	public JLabel getManque1() {
		return this.manque1;
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_X && this.demarrerUnefois == false) {
			System.out.println("X: Démarrer");

			chargerMusiqueCourte("piano_wav/demarrer.wav");

			this.demarrerUnefois = true; //une fois on démarre, on bloque cette touche
			
			this.fps = new FpsThread(this); //appliquer FPS 60
			fps.setGameRunning(true);
			Thread t1 = new Thread(fps);
			t1.start();

			this.lblModeJeu.setBackground(Color.pink);

		}

		else if (e.getKeyCode() == KeyEvent.VK_Q) { //1
			//System.out.println("Q");
			this.p1 = new PaniersThread(this, this.paniersListQ);
			p1.setRunning(true);
			Thread t1 = new Thread(p1);
			t1.start();


		}


		else if (e.getKeyCode() == KeyEvent.VK_S) {//2
			//System.out.println("S");
			this.p2 = new PaniersThread(this, this.paniersListS);
			p2.setRunning(true);
			Thread t1 = new Thread(p2);
			t1.start();
		}

		else if (e.getKeyCode() == KeyEvent.VK_D) {//3
			//System.out.println("D");
			this.p3 = new PaniersThread(this, this.paniersListD);
			p3.setRunning(true);
			Thread t1 = new Thread(p3);
			t1.start();

		}

		else if (e.getKeyCode() == KeyEvent.VK_F) {//4
			//System.out.println("F");
			this.p4 = new PaniersThread(this, this.paniersListF);
			p4.setRunning(true);
			Thread t1 = new Thread(p4);
			t1.start();

		}

		else if (e.getKeyCode() == KeyEvent.VK_J) {//5
			//System.out.println("J");
			this.p5 = new PaniersThread(this, this.paniersListJ);
			p5.setRunning(true);
			Thread t1 = new Thread(p5);
			t1.start();
		}

		else if (e.getKeyCode() == KeyEvent.VK_K) {//6
			//System.out.println("K");
			this.p6 = new PaniersThread(this, this.paniersListK);
			p6.setRunning(true);
			Thread t1 = new Thread(p6);
			t1.start();

		}

		else if (e.getKeyCode() == KeyEvent.VK_L) {//7
			//System.out.println("L");
			this.p7 = new PaniersThread(this, this.paniersListL);
			p7.setRunning(true);
			Thread t1 = new Thread(p7);
			t1.start();

		}

		else if (e.getKeyCode() == KeyEvent.VK_M) {//8
			//System.out.println("M");
			this.p8 = new PaniersThread(this, this.paniersListM);
			p8.setRunning(true);
			Thread t1 = new Thread(p8);
			t1.start();

		}

		//produit un fruit aléatoire dans une colonne aléatoire

		else if (e.getKeyCode() == KeyEvent.VK_0) { //cliquer sur 0
			//System.out.println("aléatoire");

			int index = (int)(Math.random()*this.nomFruits.size());
			int nombreAleatoire = (int) (Math.random() * 8);
			creerFruit(this.nomFruits.get(index), nombreAleatoire, "C4");

			this.lbl1.setForeground(Color.BLACK);
			this.lbl2.setForeground(Color.BLACK);
			this.lblm1.setForeground(Color.BLACK);
			this.lblm2.setForeground(Color.BLACK);
			this.lbl5.setForeground(Color.pink);


		}

		else if (e.getKeyCode() == KeyEvent.VK_A && this.demarrerUnefois) { //cliquer sur A
			System.out.println("Charger la mélodie 1.");
			chargerMusiqueCourte("piano_wav/demarrer.wav");
			chargerMelodie("melodies/melodie1.txt");
			
			this.lbl1.setForeground(Color.pink);
			this.lbl2.setForeground(Color.BLACK);
			this.lblm1.setForeground(Color.BLACK);
			this.lblm2.setForeground(Color.BLACK);
			this.lbl5.setForeground(Color.BLACK);
			
			this.notesList= new ArrayList<>();
		}

		else if (e.getKeyCode() == KeyEvent.VK_Z && this.demarrerUnefois) { //cliquer sur Z
			System.out.println("Charger la mélodie 2.");
			chargerMusiqueCourte("piano_wav/demarrer.wav");
			chargerMelodie("melodies/melodie2.txt");
			
			this.lbl1.setForeground(Color.BLACK);
			this.lbl2.setForeground(Color.pink);
			this.lblm1.setForeground(Color.BLACK);
			this.lblm2.setForeground(Color.BLACK);
			this.lbl5.setForeground(Color.BLACK);
			this.notesList= new ArrayList<>();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_E && this.demarrerUnefois) { //cliquer sur E
			System.out.println("Charger la mélodie 3.");
			chargerMusiqueCourte("piano_wav/demarrer.wav");
			chargerMelodie("melodies/melodie3.txt");
			
			this.lbl1.setForeground(Color.BLACK);
			this.lbl2.setForeground(Color.BLACK);
			this.lblm1.setForeground(Color.pink);
			this.lblm2.setForeground(Color.BLACK);
			this.lbl5.setForeground(Color.BLACK);
			this.notesList= new ArrayList<>();
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_R && this.demarrerUnefois) { //cliquer sur r
			System.out.println("Charger la mélodie 4.");
			chargerMusiqueCourte("piano_wav/demarrer.wav");
			chargerMelodie("melodies/melodie4.txt");
			
			this.lbl1.setForeground(Color.BLACK);
			this.lbl2.setForeground(Color.BLACK);
			this.lblm1.setForeground(Color.BLACK);
			this.lblm2.setForeground(Color.pink);
			this.lbl5.setForeground(Color.BLACK);
			this.notesList= new ArrayList<>();
		}



		else if (e.getKeyCode() == KeyEvent.VK_Y) { //cliquer sur Y
			//System.out.println("Arrêter");
			arreter();
			this.creerFruits = false;
			this.f3.setVisible(true);

		}


		else if (e.getKeyCode() == KeyEvent.VK_O) { //cliquer sur O
			//System.out.println("mode automatique");
			this.automatique = true;
			this.lblModeAutomatique.setBackground(Color.yellow);
			this.lblModeJeu.setBackground(Color.WHITE);

		}

		else if (e.getKeyCode() == KeyEvent.VK_P) { //cliquer sur P
			//System.out.println("mode de jeu");
			this.automatique = false;
			this.lblModeAutomatique.setBackground(Color.WHITE);
			this.lblModeJeu.setBackground(Color.PINK);

		}



	}

	public boolean getAutomatoqie() {
		return this.automatique;
	}


	//pour charger une musique courte
	public void chargerMusiqueCourte(String nomMusique) {
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(nomMusique));

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


	//pour charger une mélodie
	public void chargerMelodie(String nomMelodie) {

		File melodieTxt = new File(nomMelodie);

		if(melodieTxt.exists() && melodieTxt.isFile()) {
			FileReader fr;
			try {
				fr = new FileReader(melodieTxt);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();

				while(line!=null) {

					if(line!=null) {
						String[] ligne = line.split(" ");
						this.notesList.add(new Melodie(Integer.valueOf(ligne[0]), ligne[1]));
						
						line = br.readLine();

					}

				}
				br.close();
				fr.close();

			} catch (FileNotFoundException e) {
				System.out.println("Fichier introuvable.");
			} catch (IOException ioe) {
				System.out.println("Problème dans la lecture du fichier.");
			}


		}
		
		CreerFruitsThread r1 = new CreerFruitsThread(this, this.notesList);
		r1.setCreer(true);
		Thread t1 = new Thread(r1);
		t1.start();
		
		
	}

	
	public void arreter() {

		for(FruitsThread ele: this.fruitsThreadList) {
			ele.setRunning(false);
			ele=null;
		}

		
		for(Fruits ele: this.fruitsList) {
			ele.setDessinerFruit(false);
			ele=null; 
		}
		

	}

	public ArrayList<Fruits> getFruitsList(){
		return this.fruitsList;
	}


	public String[] getString1() {
		return this.tokens;
	}

	public String[] getLigne() {
		return this.ligne;
	}


	//créer les fruits par l'ordre de type, dans un colonne aléatoire
	public void fruitTomber(String note) {

		numeroFruit++;
		int nombreAleatoire = (int) (Math.random() * 8);
		creerFruit(this.nomFruits.get(numeroFruit%5), nombreAleatoire, note);
		
	}

	//instancier un fruit er l'ajouter dans un ArrayList<Fruits>
	public void creerFruit(String name, int indexColonne, String note) {

		if(this.creerFruits) {
			Fruits f01 = new Fruits(name, indexColonne*100+32, indexColonne, note);
			FruitsThread fThread1 = new FruitsThread(this, f01);
			fThread1.setRunning(true);
			Thread t1 = new Thread(fThread1);
			t1.start();

			this.fruitsList.add(f01);

			this.fruitsThreadList.add(fThread1);


		}
	}


	public ArrayList<Paniers> getPanierList(){
		return this.paniersList;
	}



	@Override
	public void keyReleased(KeyEvent e) {

	}

}
