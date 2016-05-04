package Personnages;

/**
 * Classe définissant un voleur.
 * @author vitsem
 *
 */
public class Voleur extends Personnage {
 
	/**
	 * Constructeur créant un voleur.
	 * @param nom String.
	 * @param numEquipe int (1 ou 2).
	 */
	public Voleur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.maxEnergie=100;
		super.energie=maxEnergie;
		super.valeur=18+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Voleur)";
			super.numEquipe=numEquipe;
			super.maxEnergie=0;
			super.energie=maxEnergie;
			super.valeur=0;
		}
	}
	
	/**
	 * Méthode permettant de voler la clef d'un personnage et de l'ajouter aux items du voleur.
	 * @param p personnage auquel on vole la clef.
	 */
	public void setVoleClef(Personnage p){
		items[0]=true;
		p.items[0]=false;
	}

	/**
	 * Méthode permettant de voler le trésor d'un personnage et de l'ajouter aux items du voleur.
	 * @param p personnage auquel on vole le trésor.
	 */
	public void setVoleTresor(Personnage p){
		items[1] = true ;
		p.items[1]=false;
	}
	
	/**
	 * Méthode permettant de voler l'épée d'un personnage et de l'ajouter aux items du voleur.
	 * @param p personnage auquel on vole l'épée.
	 */
	public void setVoleEpee(Personnage p){
		items[2] = true ;
		p.items[2]=false;
	}
}
