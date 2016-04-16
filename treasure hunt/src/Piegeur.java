public class Piegeur extends Personnage {
	/**
	 * Constructeur créant un piegeur.
	 * @param nom String.
	 * @param numEquipe int (1 ou 2).
	 */
	Piegeur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
			super.nom=nom;
			super.numEquipe=numEquipe;
			super.maxEnergie=100;
			super.energie=maxEnergie;
			super.valeur=10+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe piegeur)";
			super.numEquipe=numEquipe;
			super.maxEnergie=0;
			super.energie=maxEnergie;
			super.valeur=0;
		}
			super.items[0]=false;
			super.items[1]=false;
	}
		
	
}