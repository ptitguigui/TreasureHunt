import java.util.ArrayList;

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
	 * Constructeur initialisant le navire selon le numéro du joueur.
	 * @param numJoueur un entier.
	 */
	ParcelleNavire(int numJoueur){
		super.valeur=11+numJoueur;
	}
	
	/**
	 * Constructeur initialisant le navire selon le numéro du joueur, avec une liste de personnages.
	 * @param numJoueur un entier.
	 * @param equipage une liste de personnages.
	 */
	ParcelleNavire(int numJoueur, ArrayList<Personnage> equipage){
		super.valeur=9+numJoueur;
		this.equipage=equipage;
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

}
