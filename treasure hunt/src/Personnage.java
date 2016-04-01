/**
 * Classe permettant de créer un personnage.
 * @author bataillj
 *
 */
public class Personnage extends Parcelle{
	/**
	 * Attribut correspondant au nom du personnage.
	 */
	protected String nom;
	/**
	 * Attribut correspondant à l'énergie du personnage.
	 */
	protected int energie;
	/**
	 * Attribut correspondant au numéro d'équipe auquel le personnage appartient.
	 */
	protected int numEquipe;
	/**
	 * Attribut indicant la valeur du personnage, afin qu'il soit représenté par la bonne image sur le plateau de jeu.
	 */
	protected int valeur;
	/**
	 * Attribut créant un tableau de boolean correspondant aux items du personnage.
	 * items[0] => clef
	 * item[1] => trésor
	 */
	protected boolean[] items=new boolean[2];
	
	
	
	/**
	 * Constructeur créant un personnage en initialisant ses attributs à 0 ou la chaine vide.
	 */
	Personnage(){
		this.nom="Personnage non initialisé (classe Personnage)";
		this.numEquipe = 0;
		this.valeur=0;
		this.energie=0;
		items[0]=false;
		items[1]=false;
	}	
	
	
		
	/**
	 * Méthode retournant le numéro d'équipe du personnage.
	 * @return le numéro d'équipe du personnage.
	 */
	public int getNumEquipe(){
		return numEquipe;
	}
	
	/**
	 * Méthode retournant le nom du personnage.
	 * @return le nom du personnage.
	 */
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
	 * Méthode permettant de savoir si le personnage porte la clef ou non.
	 * @return vrai s'il possède la clef, faux sinon.
	 */
	public boolean porteClef(){
		return items[0];
	}
	
	/**
	 * Méthode permettant de savoir si le personnage porte le trésor ou non.
	 * @return vrai s'il possède le trésor, faux sinon.
	 */
	public boolean porteTresor() {
		return items[1];
	}
}
