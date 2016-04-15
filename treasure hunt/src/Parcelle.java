
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
	 * 11 = piegeur1
	 * 12 = piegeur2
	 * 13 = voleur1
	 * 14 = voleur2
	 * 15 = guerrier1
	 * 16 = guerrier2
	 */
	protected int valeur;
	
	/**
	 * Constructeur permettant de créer un terrain clair.
	 */
	Parcelle(){
		valeur=1;
	}
	
	
	/**
	 * Méthode permettant de changer la valeur de la parcelle.
	 * @param v un entier.
	 */
	public void setValeur(int v){
		valeur=v;
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
	 * Méthode vérifiant si la parcelle est vide ou non (c'est à dire s'il s'agit d'un terrain clair ou non).
	 * @return vrai si la parcelle est vide, faux sinon.
	 */
	public boolean estVide() {
		return (getValeur() == 1);
	}
}
