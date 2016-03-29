import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class Personnage {
	String nom;
	int energie;
	int equipe;
	
	
	Personnage(String nom, int equipe, int energie){
		this.nom=nom;
		this.energie = energie;
		this.equipe = equipe;
	}
	
	void action(InputEvent event){
		 if (event instanceof MouseEvent) {
			 int x = grille[equipe].getX((MouseEvent) event) ;
			 int y = grille[equipe].getY((MouseEvent) event) ;
			 grille[equipe].println("ligne " + x + " colonne : " + y ) ;			 
		 }
	}
}
