public class Explorateur extends Personnage {

	/**
	 * Constructeur créant un voleur.
	 * @param nom String.
	 * @param numEquipe int (1 ou 2).
	 */
	Explorateur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.maxEnergie=100;
		super.energie=maxEnergie;
		super.valeur=11+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Explorateur)";
			super.numEquipe=numEquipe;
			super.maxEnergie=0;
			super.energie=maxEnergie;
			super.valeur=0;
		}
		super.items[0]=false;
		super.items[1]=false;
	}
	
	/**
	 * Méthode permettant d'ajouter la clef aux items de l'explorateur.
	 */
	public void setPorteClef(){
		items[0]=true;
	}

	/**
	 * Méthode permettant d'ajouter le trésor aux items de l'explorateur.
	 */
	public void setPorteTresor(){
		items[1]=true;
	}
}
