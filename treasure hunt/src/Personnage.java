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
	
	void actionJ1(InputEvent event){
		if (event instanceof MouseEvent) {
			 int x = jeuJ1b.getX((MouseEvent) event) ;
			 int y = jeuJ1b.getY((MouseEvent) event) ;
			 [equipe].println("ligne " + x + " colonne : " + y ) ;			 
		 }
	}
}
