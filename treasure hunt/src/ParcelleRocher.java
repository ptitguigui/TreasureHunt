/**
 * Classe créant une parcelle correspondant à un rocher, voire un trésor ou une clef.
 * @author vitsem
 *
 */
public class ParcelleRocher extends Parcelle{

	/**
	 * Attribut indiquant si le rocher cache une clef ou non.
	 */
	private boolean clef=false;
	/**
	 * Attribut indiquant si le rocher cache un trésor ou non.
	 */
	private boolean tresor=false;
	
	/**
	 * Constructeur initialisant le rocher avec aucun personnage dessus.
	 */
	ParcelleRocher(){
		super.valeur=3;
	}
	public boolean getTresor(){
		return tresor;
	}
	
	public boolean getClef(){
		return clef;
	}
	/**
	 * Méthode permettant d'ajouter une clef sur la parcelle.
	 */
	public void setClef(){
		clef=true;
	}
	
	/**
	 * Méthode permettant d'ajouter un trésor sur la parcelle.
	 */
	public void setTresor() {
		tresor=true;
	}
	
	/**
	 * Méthode permettant de rendre visible la clef ou le coffre (selon le contenu de la parcelle).
	 */
	public void visible(){
		if(clef){
			super.valeur=4;
		} else if(tresor) {
			super.valeur=5;
		}
	}
	
	/**
	 * Méthode permettant de cacher la clef ou le trésor en replaçant un rocher par dessus.
	 */
	public void hide() {
		super.valeur=3;
	}
}
