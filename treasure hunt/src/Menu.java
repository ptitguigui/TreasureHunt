


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener{
	private JButton jouer;

	public Menu(){
		
		ImageIcon boutonJouer = new ImageIcon("treasure hunt/images/play.png");
		jouer = new JButton("jouer", boutonJouer);
		jouer.setVerticalTextPosition(AbstractButton.CENTER);
		jouer.setHorizontalTextPosition(AbstractButton.CENTER);
		jouer.addActionListener(this);
		jouer.setToolTipText("Clic sur jouer");
		jouer.setActionCommand("disable");
		add(jouer);

	}
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Pas de fichier: " + path);
            return null;
        }
    }
	private static void createAndShowGUI() {
		 
       
        JFrame frame = new JFrame("Menu");
        frame.setTitle("Treasure Hunt");
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
       


        
        
        Menu newContentPane = new Menu();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
 
       
        frame.pack();
        frame.setVisible(true);
	}
	  public static void main(String[] args) {
	        
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI(); 
	            }
	        });
	    }
	      public void actionPerformed(ActionEvent e) {    	
	        JOptionPane jop = new JOptionPane();    	
	        int option = jop.showConfirmDialog(null,  "Voulez-vous lancer le jeu ?", "Lancement du jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

	        if(option == JOptionPane.OK_OPTION){
	        	jouer.setEnabled(false);
				jouer.setEnabled(true);    	
	        }
	      }    
	    }
		

	

