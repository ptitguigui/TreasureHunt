
/**
 * Classe créant une parcelle contenant une valeur int.
 * @author vitsem
 *
 */
public class Parcelle {
	
	/**
	 * Attribut correspondant à la valeur de la parcelle, initialié à 1.
	 * 0 = vide
	 * 1 = sable
	 * 2 = mer
	 * 3 = rocher
	 * 4 = clef
	 * 5 = trésor
	 * 6 = arbre
	 * 7 = navire1
	 * 8 = navire2
	 * 9 = explorateur1
	 * 10 = explorateur2
	 * 11 = voleur1
	 * 12 = voleur2
	 */
	protected int valeur;
	/**
	 * Attribut correspondant à un personnage, initialisé à null, avec la possibilité de le modifier via un setter.
	 */
	protected Personnage personnage;

	/**
	 * Constructeur permettant de créer un terrain clair avec un personnage par dessus.
	 * @param p un personnage
	 */
	Parcelle(Personnage p){
		personnage=p;
		valeur=p.getValeur();
	}
	
	Parcelle(){
		valeur=1;
		personnage=null;
	}
	
	
	/**
	 * Méthode permettant de changer la valeur de la parcelle.
	 * @param v un entier.
	 */
	public void setValeur(int v){
		valeur=v;
	}
	
	/**
	 * Méthode permettant de changer le personnage dans la parcelle.
	 * @param p un Personnage.
	 */
	public void setPersonnage(Personnage p){
		personnage=p;
		if (p!=null){
			valeur=p.getValeur();
		} else {
			valeur=1;
		}
	}
	
	/**
	 * Méthode retournant le personnage dans la parcelle.
	 * @return le personnage dans la parcelle.
	 */
	public Personnage getPersonnage(){
		return personnage;
	}
	
	/**
	 * Méthode retournant la valeur de la parcelle.
	 * @return la valeur int de la parcelle.
	 */
	public int getValeur(){
		return valeur;
	}
	
	/**
	 * Méthode transformant la parcelle en un String, afin de permettre son affichage.
	 */
	public String toString(){
		return ""+valeur;
	}
	/**
	 * Méthode vérifiant si la parcelle est vide ou non (c'est à dire s'il s'agit d'un terrain clair et n'ayant aucun personnage dessus).
	 * @return vrai si la parcelle est vide, faux sinon.
	 */
	public boolean estVide() {
		return (getValeur() == 1 && personnage==null);
	}
}
