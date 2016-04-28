package Jeu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu {
	public Menu(){ 
		JFrame fenetre = new JFrame("Treasure Hunt");
		
		JPanel panel = new JPanel();
		JButton jouer = new JButton("Jouer");
		JButton options =new JButton("Options");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(jouer);
		panel.add(options);
		fenetre.getContentPane().add(panel, BorderLayout.CENTER);
		fenetre.setPreferredSize(new Dimension(300, 200));
		fenetre.setResizable(true);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		
		}
		public static void main(String[]args){
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        public void run() {
		          new Menu();
		        }
		    });
		}
}
