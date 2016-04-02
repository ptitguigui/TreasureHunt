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
		super.valeur=6+numJoueur;
	}
	
	/**
	 * Constructeur initialisant le navire selon le numéro du joueur, avec une liste de personnages.
	 * @param numJoueur un entier.
	 * @param equipage une liste de personnages.
	 */
	ParcelleNavire(int numJoueur, ArrayList<Personnage> equipage){
		super.valeur=6+numJoueur;
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
	 * Méthode permettant de savoir si le navire est vide ou non.
	 * @return true si personne n'est dedans, false sinon.
	 */
	public boolean PersonneABord(){
		if (equipage.size()==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode permettant de savoir si un personnage peut monter dans le navire (s'il est le dernier de son équipe sur l'ile ou non).
	 * @param nbPersonnages le nombre de personnages du joueur.
	 */
	public boolean peutMonterABord(int nbPersonnages){
		if (equipage.size()>=nbPersonnages+1){
			return false;
		}
		return true;
	}

}
