/**
 * Classe défissant un guerrier.
 * @author vitsem
 *
 */
public class Guerrier extends Personnage {

	/**
	 * Constructeur créant un guerrier.
	 * @param nom String.
	 * @param numEquipe int (1 ou 2).
	 */
	Guerrier(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.maxEnergie=100;
		super.energie=maxEnergie;
		super.valeur=19+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Guerrier)";
			super.numEquipe=numEquipe;
			super.maxEnergie=0;
			super.energie=maxEnergie;
			super.valeur=0;
		}
		super.items[2]=true;
	}
	/**
	 * Methode permettant de faire attaquer le guerrier 
	 * @param p un Personnage, la cible du guerrier
	 * @param degats un entier, les points de dégats infligés
	 */
	public void attaqueEnnemi(Personnage p, int degats){
		p.energie-=degats;
	}
}
