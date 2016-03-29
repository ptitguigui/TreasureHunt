import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
/**
 * classe permettant de creer les personnages.
 * @author bataillj
 *
 */
public class Personnage {
	String nom;
	int energie;
	int equipe;
	
/**
 * Methode permettant d'initialiser l'energie, le nom et l'equipe.
 * @param nom
 * @param equipe
 * @param energie
 */
	Personnage(String nom, int equipe, int energie){
		this.nom=nom;
		this.energie = energie;
		this.equipe = equipe;
	}
	
	/*
	void action(int id, InputEvent event){
		if(id = 1){
			if (event instanceof MouseEvent) {
				 int x = jeuJ1b.getX((MouseEvent) event) ;
				 int y = jeuJ1b.getY((MouseEvent) event) ;
				 [equipe].println("ligne " + x + " colonne : " + y ) ;			 
			 }
		}else{
			if (event instanceof MouseEvent) {
				 int x = jeuJ2b.getX((MouseEvent) event) ;
				 int y = jeuJ2b.getY((MouseEvent) event) ;
				 [equipe].println("ligne " + x + " colonne : " + y ) ;			 
			 }
		}
	}*/
}
