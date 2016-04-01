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
	 * Attribut correspondant au message affiché si le rocher est soulevé.
	 */
	private String msg="Mais vous n'avez rien trouvé en dessous...";
	
	/**
	 * Constructeur initialisant le rocher avec aucun personnage dessus.
	 */
	ParcelleRocher(){
		super.valeur=3;
	}
	
	/**
	 * Méthode permettant de savoir s'il y a un trésor ou non sous le rocher.
	 * @return vrai s'il y a un trésor, faux sinon.
	 */
	public boolean getTresor(){
		return tresor;
	}
	
	/**
	 * Méthode permettant de savoir s'il y a une clef ou non sous le rocher.
	 * @return vrai s'il y a une clef, faux sinon.
	 */
	public boolean getClef(){
		return clef;
	}
	/**
	 * Méthode permettant d'ajouter une clef sur la parcelle.
	 */
	public void setClef(){
		msg="Vous avez trouvé la clef !";
		clef=true;
	}
	
	/**
	 * Méthode permettant d'ajouter un trésor sur la parcelle.
	 */
	public void setTresor() {
		msg="Vous avez trouvé le trésor !";
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
