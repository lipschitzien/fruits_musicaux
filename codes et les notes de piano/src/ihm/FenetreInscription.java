package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;


public class FenetreInscription extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField prenomText;
	
	private JLabel titreLabel;
	private JLabel prenomLabel;
	
	private JButton ajouterButton;
	
	private JPanel mainPanel;
	private JPanel panelBouton;
	private JPanel conteneurCentral;
	
	private String sauvegarder;
	
	private String message;
	private ZoneDessin z1;
	
	
	public FenetreInscription(String titre, ZoneDessin z1) {
		super(titre);
		this.init();
		this.instancier();
		this.build();
		sauvegarder = "0";
		this.z1 = z1;
	}
	
	private void init() {
		this.setSize(400, 150);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void instancier() {
		this.message = "";
		this.titreLabel = new JLabel("Enregistrement d'un utilisateur: ", JLabel.CENTER);
		
		this.prenomLabel = new JLabel("Prénom: ");
		this.prenomText = new JTextField(20);

		this.ajouterButton = new JButton("Ajouter");
		this.ajouterButton.addActionListener(this);
		
		
	}
	
	private void build() {
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.panelBouton = new JPanel();
		this.conteneurCentral = new JPanel();
		this.conteneurCentral.setLayout(new SpringLayout());
		
		this.titreLabel.setFont(new Font("Arial", Font.BOLD, 25));
		
		this.mainPanel.add(this.titreLabel, BorderLayout.PAGE_START);
		
		this.panelBouton.add(this.ajouterButton);
		this.panelBouton.setBackground(Color.WHITE);
		
		this.mainPanel.add(panelBouton, BorderLayout.PAGE_END);
		
		this.conteneurCentral.add(this.prenomLabel);
		this.conteneurCentral.add(this.prenomText);
		
		this.conteneurCentral.setBackground(Color.WHITE);
		

		int nbLigne = 1;
		int nbColonne = 2;
		SpringUtilities.makeCompactGrid(conteneurCentral, nbLigne, nbColonne, 6, 6, 6, 6);
		
		
		JPanel panelIntermediaire = new JPanel();
		panelIntermediaire.add(conteneurCentral);
		panelIntermediaire.setBackground(Color.WHITE);
		
		mainPanel.add(panelIntermediaire, BorderLayout.CENTER);
		this.mainPanel.setBackground(Color.WHITE);
		
		this.setContentPane(this.mainPanel);
		this.getContentPane().setBackground(Color.WHITE);
		
		this.prenomText.setFocusable(true);		
		this.prenomText.requestFocusInWindow();	


		this.prenomText.addKeyListener(this);
	}
	
	public void setData(String s1) {
		this.sauvegarder = s1;
	}
	
	public String getData() {
		return this.sauvegarder;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.ajouterButton) {
			System.out.println("Enregistrer");
			
			message = "";
			message += this.prenomLabel.getText() + " " + this.prenomText.getText() +" ";

			JOptionPane.showMessageDialog(this, message);
			
			enregistrer();
			System.exit(0);
		
		}
	}
	
	public void enregistrer(){

		this.z1.getManque1().setText("0");
		this.z1.getScore1().setText("0");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String line = df.format(date)+";" + this.prenomText.getText() +";" + this.z1.getManques() +";" + this.z1.getScores();

		System.out.println(line); //affichier pour vérifier

		File file2 = new File("sauvegarder/data2.csv");

		boolean teteExiste = true;
		if(!file2.exists()){
			System.out.println("Le fichier " + file2 + " n'existe pas, on vient de le créé.");
			teteExiste = false;
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter fw;
		try {
			fw = new FileWriter(file2,true);
			BufferedWriter bw = new BufferedWriter(fw);

			//quand le fichier existe, il y a un entête
			if(teteExiste) {
				bw.append(line); //saisir que les données
			}
			else if(teteExiste == false) { //quand il n'y a pas d"entête
				bw.append("temps;prénom;attrapé;manqué"); //d'abord créer un entête
				bw.newLine();	
				bw.append(line);						  //puis saisir les données
			}
			bw.newLine();
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setVisible(false);
	
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.println("Enregistrer");
			
			message = "";
			message += this.prenomLabel.getText() + " " + this.prenomText.getText() +" ";

			JOptionPane.showMessageDialog(this, message);
			enregistrer();
			
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
