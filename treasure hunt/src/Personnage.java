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
	 * Attribut correspondant à l'énergie maximum du personnage.
	 */
	protected int maxEnergie;
	/**
	 * Attribut correspondant au numéro d'équipe auquel le personnage appartient.
	 */
	protected int numEquipe;
	/**
	 * Attribut créant un tableau de boolean correspondant aux items du personnage.
	 * items[0] = clef
	 * item[1] = trésor
	 */
	protected boolean[] items=new boolean[2];
	
	
	
	/**
	 * Constructeur créant un personnage en initialisant ses attributs à 0 ou la chaine vide.
	 */
	Personnage(){
		this.nom="Personnage non initialisé (classe Personnage)";
		this.numEquipe = 0;
		super.valeur=0;
		this.energie=0;
		maxEnergie=0;
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
	 * Méthode retournant l'energie du personnage.
	 * @return l'energie du personnage.
	 */
	public int getEnergie(){
		return energie;
	}
	
	/**
	 * Méthode permettant de savoir si le personnage est mort ou non.
	 * @return vrai s'il est mort, faux sinon.
	 */
	public boolean estMort(){
		if (energie<=0){
			return true;
		}
		return false; 
	}
	
	/**
	 * Méthode permettant de modifier l'energie du personnage.
	 * @param nrj la nouvelle valeur de l'energie.
	 */
	public void setEnergie(int nrj){
		if(nrj<maxEnergie){
			energie=nrj;
		} else {
			energie=maxEnergie;
		}
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
	
	/**
	 * Méthode permettant de donner un objet d'un personnage à un autre.
	 * @param p l'autre personnage.
	 * @param i l'objet (0 pour la clef, 1 pour le trésor)
	 */
	public void donneItem(Personnage p, int i){
		if(items[i]==true){
			p.items[i]=items[i];
			items[i]=false;
		}
	}
}
