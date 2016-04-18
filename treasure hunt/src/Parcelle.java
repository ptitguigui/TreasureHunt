
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
	 * 3 = rocher1
	 * 4 = rocher2
	 * 5 = rocher3
	 * 6 = arbre
	 * 7 = clef
	 * 8 = tresor
	 * 9 = piege
	 * 10 = navire1
	 * 11 = navire2
	 * 12 = explorateur1
	 * 13 = explorateur2
	 * 14 = voleur1
	 * 15 = voleur2
	 * 16 = piegeur1
	 * 17 = piegeur2
	 * 18 = guerrier1
	 * 19 = guerrier2
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
