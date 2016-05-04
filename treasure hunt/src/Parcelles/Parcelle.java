package Parcelles;

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
	 * 2 = herbeSec 
	 * 3 = herbe
	 * 4 = mer
	 * 5 = rocher1
	 * 6 = rocher2
	 * 7 = rocher3
	 * 8 = arbre
	 * 9 = buisson
	 * 10 = clef
	 * 11 = coffre
	 * 12 = piege
	 * 13 = tresor
	 * 14 = epee
	 * 15 = navire1
	 * 16 = navire2
	 * 17 = explorateur1
	 * 18 = explorateur2
	 * 19 = voleur1
	 * 20 = voleur2
	 * 21 = piegeur1
	 * 22 = piegeur2
	 * 23 = guerrier1
	 * 24 = guerrier2
	 */
	protected int valeur;
	
	/**
	 * Constructeur permettant de créer un terrain clair.
	 */
	public Parcelle(){
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
	 * Méthode vérifiant si la parcelle est un terrain clair ou non.
	 * @return vrai si la parcelle est un terrain clair, faux sinon.
	 */
	public boolean terrainClair(){
		return valeur == 1 || valeur == 9 || valeur == 11 || valeur == 7 || valeur == 10;
	}
	/**
	 * Méthode vérifiant si la parcelle est vide ou non (c'est à dire qu'il n'y a aucun élément dessus).
	 * @return vrai si la parcelle est vide, faux sinon.
	 */
	public boolean estVide() {
		return valeur== 1 || valeur==2 || valeur==3;
	}
}
