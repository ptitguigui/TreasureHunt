
/**
 * Classe créant une parcelle contenant une valeur int.
 * @author vitsem
 *
 */
public class Parcelle {
	
	/**
	 * Attribut correspondant à la valeur de la parcelle, initialié à 0.
	 * 0 = vide
	 * 1 = mer
	 * 2 = rocher
	 * 3 = navire1
	 * 4 = navire2
	 * 5 = clef
	 * 6 = tresor
	 * 7 = explorateur1
	 * 8= explorateur2
	 */
	private int valeur = 0;

	/**
	 * Méthode permettant de changer la valeur de la parcelle
	 * @param i un entier.
	 */
	public void setValeur(int i){
		valeur = i;
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
	 * Méthode vérifiant si la parcelle est vide ou non.
	 * @return vrai si la parcelle est vide, faux sinon.
	 */
	public boolean estVide() {
		return (getValeur()==0);
	}
}
