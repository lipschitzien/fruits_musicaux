package ihm;

import javax.swing.JFrame;


public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private ZoneDessin z1;
	
	
	public MainFrame(String titre) {
		super(titre);
		this.init();
		this.build();
	}

	private void init() {
		this.setSize(914, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void build() {
		this.z1 = new ZoneDessin();
		this.getContentPane().add(z1);
	}
}
