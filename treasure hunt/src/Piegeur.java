/**
 * Classe définissant un piegeur.
 * @author vitsem
 *
 */
public class Piegeur extends Personnage {
	
	/**
	 * Attribut permettant de connaitre le nombre de mines que porte le piegeur.
	 */
	private int mines;
	private final int MAXMINES=5;
	
	/**
	 * Constructeur créant un piegeur.
	 * @param nom String.
	 * @param numEquipe un entier (1 ou 2).
	 */
	Piegeur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
			super.nom=nom;
			super.numEquipe=numEquipe;
			super.maxEnergie=100;
			super.energie=maxEnergie;
			super.valeur=17+numEquipe;
			mines=MAXMINES;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Piegeur)";
			super.numEquipe=numEquipe;
			super.maxEnergie=0;
			super.energie=maxEnergie;
			super.valeur=0;
			mines=0;
		}
	}
	
	/**
	 * Méthode permettant de retirer une mine au nombre de mine que le piègeur a en stock.
	 * @return vrai si l'on a pu retirer, faux sinon.
	 */
	public boolean retirerMine(){
		if(mines>0){
			mines=mines-1;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Méthode permettant de remettre le compteur de mines au maximum.
	 */
	public void setMinesFull(){
		mines=MAXMINES;
	}
	
	/**
	 * Méthode permettant de connaitre le nombre de mine qu'il reste au piegeur.
	 * @return le nombre de mines restant.
	 */
	public int getMines(){
		return mines;
	}
		
	
}
