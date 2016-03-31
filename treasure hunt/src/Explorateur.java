
public class Explorateur extends Personnage {

	private boolean clef=false;
	
	Explorateur(String nom, int numEquipe, int valeur){
		super.nom=nom;
		super.numEquipe=numEquipe;
		super.energie=100;
		super.valeur=valeur;
	}
	
	public void setClefTrouvee(){
		clef=true;
	}
	
	public boolean clefTrouvee(){
		return clef;
	}
	
	public void deplacer() {
		
	}
	
	public void soulever() {
		
	}
}
