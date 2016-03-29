import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
/**
 * Classe permettant de créer un personnage.
 * @author bataillj
 *
 */
public class Personnage {
	protected String nom;
	protected int energie;
	protected int numEquipe;
	protected int valeur;
	
/**
 * Constructeur créant un personnage en initialisant ses attributs à 0 ou la chaine vide.
 */
	Personnage(){
		this.nom="";
		this.energie = 0;
		this.numEquipe = 0;
		this.valeur=0;
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
	
	/**
	 * Méthode permettant de déplacer un personnage.
	 */
	public void deplacer(){
		
	}
	
	public void echanger(){
		
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
