package Parcelles;
/**
 * Classe créant une parcelle correspondant à un piège.
 * @author vitsem
 *
 */
public class ParcellePiege extends Parcelle {

	/**
	 * Attribut correspondant au numéro de l'équipe ayant posé le piège.
	 */
	private int numEquipe;
	/**
	 * Attribut correspondant à la coordonnée x du navire.
	 */
	private int x;
	/**
	 *  Attribut correspondant à la coordonnée y du navire.
	 */
	private int y;
	/**
 	 * Constructeur initialisant le piege.
	 */

	/**
	 * Constructeur créant une parcelle avec un piège.
	 * @param numEquipe le numéro de l'équipe ayant posé le piège.
	 * @param x coordonnée x
	 * @param y coordonnée y
	 */
	public ParcellePiege(int numEquipe, int x, int y){
		super.valeur=12;
		this.numEquipe=numEquipe;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Méthode permettant de connaitre le numéro de l'équipe ayant posé le piège.
	 * @return le numéro de l'équipe ayant posé le piège.
	 */
	public int getNumEquipe(){
		return numEquipe;
	}
	
	/**
	 * Méthode retournant la coordonnée x du piège.
	 * @return la coordonnée x du piège.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Méthode retournant la coordonnée y du piège.
	 * @return la coordonnée y du piège.
	 */
	public int getY(){
		return y;
	}
}
