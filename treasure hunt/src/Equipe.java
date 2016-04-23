import java.util.ArrayList;

public class Equipe {

	private ArrayList<Personnage> equipe=new ArrayList<>();
	private int numEquipe;
	private boolean tresor=false;
	
	public Equipe(int numEquipe){
		this.numEquipe=numEquipe;
	}
	
	public void addPersonnage(Personnage p){
		if(p.getNumEquipe()==numEquipe){
			equipe.add(p);
		}
	}
	
	public void removePersonnage(Personnage p){
		equipe.remove(p);
	}
	
	public int nbPersonnages(){
		return equipe.size();
	}
	
	public void setTresor(){
		tresor=false;
	}
	
	public boolean aPerdu(){
		return equipe.size()==0;
	}
}