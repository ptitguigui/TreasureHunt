public class Voleur extends Personnage {
 
	/**
	 * Constructeur créant un voleur.
	 * @param nom String.
	 * @param numEquipe int (1 ou 2).
	 */
	Voleur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.energie=100;
		super.valeur=10+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Voleur)";
			super.numEquipe=numEquipe;
			super.energie=0;
			super.valeur=0;
		}
		super.items[0]=false;
		super.items[1]=false;
	}
	
	/**
	 * Méthode permettant d'ajouter la clef aux items du voleur.
	 */
	public void setVoleClef(){
		items[0]=true;
	}

	/**
	 * Méthode permettant d'ajouter le trésor aux items du voleur.
	 */
	public void setVoleTresor(){
		items[1]=true;
	}
}
