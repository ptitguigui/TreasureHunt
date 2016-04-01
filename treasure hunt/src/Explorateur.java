public class Explorateur extends Personnage {

	
	
	Explorateur(String nom, int numEquipe){
		if (numEquipe>0 && numEquipe <3){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.energie=100;
		super.valeur=8+numEquipe;
		} else {
			super.nom="Problème d'initialisation, mauvais n° d'équipe (classe Explorateur)";
			super.numEquipe=numEquipe;
			super.energie=0;
			super.valeur=0;
		}
		super.items[0]=false;
		super.items[1]=false;
	}
	
	public void setPorteClef(){
		items[0]=true;
	}

	public void setPorteTresor(){
		items[1]=true;
	}
}
