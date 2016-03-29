import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
/**
 * Classe permettant de créer un personnage.
 * @author bataillj
 *
 */
public class Personnage {
	private String nom;
	private int energie;
	private int numEquipe;
	private int valeur;
	
/**
 * Constructeur créant un personnage avec un nom, un numéro d'équipe et de l'énergie.
 * @param nom String.
 * @param numEquipe int.
 * @param energie int.
 * @param valeur int.
 */
	Personnage(String nom, int numEquipe, int energie, int valeur){
		this.nom=nom;
		this.energie = energie;
		this.numEquipe = numEquipe;
		this.valeur=valeur;
	}
	
	/**
	 * Méthode retournant le numéro d'équipe du personnage.
	 * @return le numéro d'équipe du personnage.
	 */
	public int getNumEquipe(){
		return numEquipe;
	}
	
	/**
	 * Méthode retournant la valeur du personnage.
	 * @return la valeur du personnage.
	 */
	public int getValeur(){
		return valeur;
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
