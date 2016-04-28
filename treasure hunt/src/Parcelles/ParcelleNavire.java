package Parcelles;
import java.util.ArrayList;

import Personnages.Personnage;

/**
 * Classe créant une parcelle correspondant à un navire.
 * @author vitsem
 *
 */
public class ParcelleNavire extends Parcelle {
	
	/**
	 * Attribut correspondant à une liste de personnages, c'est à dire à l'équipage présent dans le navire.
	 */
	private ArrayList<Personnage> equipage=new ArrayList<>();
	/**
	 * Attribut correspondant à la coordonnée x du navire.
	 */
	private int x;
	/**
	 *  Attribut correspondant à la coordonnée y du navire.
	 */
	private int y;
	
	/**
	 * Constructeur initialisant le navire selon le numéro du joueur.
	 * @param numJoueur un entier.
	 * @param x coordonnée x
	 * @param y coordonnée y
	 */
	public ParcelleNavire(int numJoueur, int x, int y){
		super.valeur=11+numJoueur;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Constructeur initialisant le navire selon le numéro du joueur, avec une liste de personnages.
	 * @param numJoueur un entier.
	 * @param x coordonnée x
	 * @param y coordonnée y
	 * @param equipage une liste de personnages.
	 */
	ParcelleNavire(int numJoueur, int x, int y, ArrayList<Personnage> equipage){
		super.valeur=9+numJoueur;
		this.equipage=equipage;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Méthode permettant d'ajouter un personnage sur le navire.
	 * @param p un personnage.
	 */
	public void addPersonnage(Personnage p){
		equipage.add(p);
	}
	
	/**
	 * Méthode permettant de retirer un personnage du navire.
	 * @param p un personnage.
	 */
	public void removePersonnage(Personnage p){
		equipage.remove(p);
	}
	
	/**
	 * Méthode retournant un personnage dans le navire.
	 * @param idx l'indice du personnage dans la liste
	 * @return le personnage d'indice idx.
	 */
	public Personnage getPersonnage(int idx){
		return equipage.get(idx);
	}
	
	/**
	 * Méthode retournant un personnage dans le navire.
	 * @param nom le nom d'un personnage
	 * @return le personnage ayant pour nom celui passé en paramètre, s'il y en a plusieurs alors il renvoit la première occurence.
	 */
	public Personnage getPersonnage(String nom){
		for(int i=0; i<equipage.size(); i++){
			if(equipage.get(i).getNom()==nom){
				return equipage.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Méthode retournant le nombre de personne à bord du navire.
	 * @return le nombre de personne à bord du navire.
	 */
	public int getNbPersonnage(){
		return equipage.size();
	}
	
	/**
	 * Méthode permettant de savoir si un personnage peut monter dans le navire (s'il est le dernier de son équipe sur l'ile ou non).
	 * @param nbPersonnages le nombre de personnages du joueur.
	 * @return le nombre de personnage sur le navire.
	 */
	public boolean peutMonterABord(int nbPersonnages){
		if (equipage.size()+1>=nbPersonnages && nbPersonnages!=1){
			return false;
		}
		return true;
	}

	/**
	 * Méthode retournant la coordonnée x du navire.
	 * @return la coordonnée x du navire.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Méthode retournant la coordonnée y du navire.
	 * @return la coordonnée y du navire.
	 */
	public int getY(){
		return y;
	}
}
