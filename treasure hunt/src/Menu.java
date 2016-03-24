import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	private JButton jouer;
	
	public Menu(){
		ImageIcon boutonJouer = new ImageIcon("image/play.png");
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if("disable".equals(e.getActionCommand())){
			jouer.setEnabled(false);
			
		}else{
			jouer.setEnabled(true);
			
		}
		
	}
}
	

