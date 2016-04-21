
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
	 * 8 = coffre
	 * 9 = piege
	 * 10 = tresor
	 * 11 = epee
	 * 12 = navire1
	 * 13 = navire2
	 * 14 = explorateur1
	 * 15 = explorateur2
	 * 16 = voleur1
	 * 17 = voleur2
	 * 18 = piegeur1
	 * 19 = piegeur2
	 * 20 = guerrier1
	 * 21 = guerrier2
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
		return (getValeur() == 1 || getValeur() == 9 || getValeur() == 11 || getValeur() == 7 || getValeur() == 10);
	}
}
