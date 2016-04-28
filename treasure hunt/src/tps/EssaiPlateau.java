

package tps;
import java.util.Random;
import java.awt.Color;
import java.awt.event.InputEvent ;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class EssaiPlateau {
	public static void main(String[] args) {
		Random r=new Random();
		int tailleX=23;
		int tailleY=25;
		String[] gifs={"images/un.gif","images/deux.gif","images/trois.gif","images/quatre.gif"};
		
		Plateau.setDefaultVisibility(false) ;
		Plateau[] grille = new Plateau[2] ;
		grille[0] = new Plateau(gifs,10, true);
		grille[1] = new Plateau(gifs,10, false);
		grille[0].setTitle("Alain") ;
		grille[1].setTitle("Bruno") ;
		int[][] jeu=new int[tailleX][tailleY];
		for (int e = 0 ; e < 2 ; e++) {
	  		for (int i=0;i<tailleX;i++)
				for (int j=0;j<tailleY;j++)
					if ((i == 0)||(j == 0)) {
						jeu[i][j] = 0 ;
					} else {
				  		jeu[i][j]=r.nextInt(gifs.length+1);
					}
			grille[e].setJeu(jeu);
			for (int i = 1 ; i < tailleX ; i++) {
				grille[e].setText(i, 0, String.valueOf(i)) ;
			}
			for (int j = 1 ; j < tailleY ; j++) {
				String msg = "" ;
				int y = j-1 ;
				if (y < 26) {
					msg += (char) (y + 65) ; // Convert 0 to A
				} else {
					msg += (char) ((y/26) + 65) ;  // Convert to AA..ZZ
					msg += (char) ((y%26) + 65) ;
				}
				grille[e].setText(0, j, msg) ;
			}
		}
		
    grille[0].println("Hello world !!") ;
    grille[0].println("This is a new day !") ;
    grille[0].println("And again ...") ;
    grille[0].println("And again ...") ;
    grille[0].println("And again ...") ;
    grille[0].println("And again ...") ;
    
	int equipe = 0 ;
	boolean finished = false ;
	Color currentColor = Color.BLUE ;
    do {
        InputEvent event ;
        grille[equipe].println("A vous (ESC pour finir)") ;
    	grille[equipe].affichage();
        event=  grille[equipe].waitEvent(20000);
        if (event instanceof MouseEvent) {
        	int x = grille[equipe].getX((MouseEvent) event) ;
        	int y = grille[equipe].getY((MouseEvent) event) ;
        	grille[equipe].println("ligne " + x + " colonne : " + y ) ;
        	if (grille[equipe].isHighlight(x,y)) {
            	grille[equipe].resetHighlight(x, y) ;        		
        	} else {
            	grille[equipe].setHighlight(x, y, currentColor) ;  
            	if (currentColor == Color.BLUE) {
            		currentColor = Color.GREEN ; 
            	} else if (currentColor == Color.GREEN) {
            		currentColor = Color.RED ;
            	} else if (currentColor == Color.RED) {
            		currentColor = Color.YELLOW ;
            	} else {
            		currentColor = Color.BLUE ;
            	}
        	}
        } else if (event instanceof KeyEvent) {
        	grille[equipe].println(event.toString()) ;
        	int keyCode = ((KeyEvent) event).getKeyCode() ;
        	if (keyCode == 27) { // test escape key
        		finished = true ;
        	}
        }
        event = grille[equipe].waitEvent(1000) ;	// Délai pour permettre la lecture.
    	grille[equipe].masquer();
    	equipe = 1-equipe ; // Passe à l'équipe suivante.
    } while (! finished) ;
    grille[0].close() ;
    grille[1].close() ;
    
}
}

