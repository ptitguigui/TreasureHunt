import java.util.ArrayList;

public class Equipe {
	
	/**
	 * Attribut correspondant à une liste de personnage constituant l'équipe.
	 */
	private ArrayList<Personnage> equipe=new ArrayList<>();
	/**
	 * Attribut correspondant au numero de l'équipe.
	 */
	private int numEquipe;
	/**
	 * Attribut correspondant à un booleén disant si l'équipe a gagné ou non.
	 */
	private boolean gagne=false;
	/**
	 * Attribut permettant de savoir si le joueur (Equipe) a trouvé le trésor ou non, et donc s'il peut le voir ou non.
	 */
	private boolean tresorVisible=false;
	
	/**
	 * Constructeur d'un équipe avec en paramètre son numero d'équipe (1 ou 2)
	 * @param numEquipe un entier
	 */
	public Equipe(int numEquipe){
		this.numEquipe=numEquipe;
	}
	
	/**
	 * Methode permettant d'ajouter un personnage dans la liste d'equipe
	 * @param p un Personnage
	 */
	public void addPersonnage(Personnage p){
		if(p.getNumEquipe()==numEquipe){
			equipe.add(p);
		}
	}
	
	/**
	 * Methode permettant de supprimer un personnage dans la liste d'équipe
	 * @param p
	 */
	public void removePersonnage(Personnage p){
		equipe.remove(p);
	}
	
	/**
	 * Methode permmettant de donner le nombre de personnage d'un equipe
	 * @return le nombre de presonnage dans la liste
	 */
	public int nbPersonnages(){
		return equipe.size();
	}
	
	/**
	 * Méthode permettant de dire que le joueur a trouvé le trésor, et donc qu'il peut voir son emplacement.
	 */
	public void setTresor(){
		tresorVisible=true;
	}
	
	/**
	 * Méthode permettant de savoir si le joueur a trouvé le trésor ou non.
	 * @returnsi le joueur a trouvé le trésor ou non.
	 */
	public boolean tresorVisible(){
		return tresorVisible;
	}
	
	/**
	 * Methode permettant de faire gagner une equipe
	 */
	public void gagne(){
		gagne=true;
	}
	
	/**
	 * Methode permettant de dire si une equipe a perdu
	 * @return si l'équipe a perdu ou non (true or false)
	 */
	public boolean aPerdu(){
		return equipe.size()==0;
	}
	
	/**
	 * Methode permettant de dire si une equipe a gagne
	 * @return si l'équipe a gagne ou non (true or false)
	 */
	public boolean aGagne(){
		return gagne;
	}
}