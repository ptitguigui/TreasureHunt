import java.util.ArrayList;

public class Equipe {
	
	private ArrayList<Personnage> equipe=new ArrayList<>();
	private int numEquipe;
	
	public Equipe(int numEquipe){
		this.numEquipe=numEquipe;
	}
	
	public int getNumEquipe(){
		return numEquipe;
	}
	
	public void addPersonnage(Personnage p){
		equipe.add(p);
	}
	
	public void removePersonnage(Personnage p) {
		equipe.remove(p);
	}
	
	public int nbPersonnages(){
		return equipe.size();
	}
}
