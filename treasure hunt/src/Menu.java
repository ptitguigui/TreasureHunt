


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel {
	private JButton bouton;

	public Menu(String titre,int x, int y, int largeur, int hauteur){
		
		JFrame frame = new JFrame("Menu");
        frame.setTitle("Treasure Hunt");
        frame.setSize(largeur, hauteur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setLocation(x, y);
        bouton  = new JButton("Jouer");
        bouton.setVerticalTextPosition(AbstractButton.CENTER);
        bouton.setHorizontalTextPosition(AbstractButton.CENTER);
       
        bouton.setActionCommand("disable");
        
        frame.getContentPane().add(bouton) ;
        JOptionPane jop = new JOptionPane();    	
       
       
	}
	
	      	
	        
	       
}
		

	

