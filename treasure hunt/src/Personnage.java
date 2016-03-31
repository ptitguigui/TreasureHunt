import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
/**
 * Classe permettant de créer un personnage.
 * @author bataillj
 *
 */
public class Personnage extends Parcelle{
	protected String nom;
	protected int energie = 100;
	protected int numEquipe;
	protected int valeur;
	protected boolean aClef = false;
	protected boolean aTresor = false;
	
	/**
	 * Constructeur créant un personnage en initialisant ses attributs à 0 ou la chaine vide.
	 */
	Personnage(){
		this.nom="test";
		this.numEquipe = 0;
		this.valeur=0;
	}
	
	Personnage(int numEquipe){
		this.numEquipe = numEquipe;
	}
	
		
	/**
	 * Méthode retournant le numéro d'équipe du personnage.
	 * @return le numéro d'équipe du personnage.
	 */
	public int getNumEquipe(){
		return numEquipe;
	}
	public String getNom(){
		return nom;
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
	/**
	 * Méthode permettant de soulever un rocher si cela est possible
	 */
	public void soulever(){
		
	}
	/**
	 * Méthode permettant d'échanger les objets entre les personnages
	 */
	public void echanger(){
		
	}
}
