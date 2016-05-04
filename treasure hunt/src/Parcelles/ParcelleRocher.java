package Parcelles;
import Jeu.Aleatoire;

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
	private String msg;
	/**
	 * Attribut permettant de tirer un nombre aléatoire.
	 */
	private Aleatoire alea=new Aleatoire();
	/**
	 * Attribut permettant de garder la valeur aléatoire tirée comme étant la valeur du rocher.
	 */
	private int valeurRocherIni;
	
	/**
	 * Constructeur initialisant le rocher avec aucun personnage dessus.
	 */
	public ParcelleRocher(){
		super.valeur=alea.tirage(3)+5;
		valeurRocherIni=valeur;
		msg="Mais vous n'avez rien trouvé en dessous...";
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
	 * Méthode retournant la valeur initiale du rocher, défini par un random à sa création.
	 * @return la valeur initiale du rocher.
	 */
	public int getValeurIni(){
		return valeurRocherIni;
	}
	/**
	 * Méthode permettant d'ajouter ou retirer une clef sur la parcelle.
	 */
	public void setClef(){
		if(!clef){
			msg="Vous avez trouvé la clef !";
		} else {
			msg="Vous distinguez une empreinte de clef dans le sable, ainsi que des traces de pas...";
		}
		clef=!clef;
	}
	
	/**
	 * Méthode permettant d'ajouter ou de retirer un trésor sur la parcelle.
	 */
	public void setTresor() {
		if(!tresor){
			msg="Vous avez trouvé le coffre !";
		} else {
			msg="Vous avez trouvé le coffre ! Malheureusement pour vous, il a déjà été vidé de son contenu...";
		}
		tresor=!tresor;
	}
	
	/**
	 * Méthode retournant le message lorsque l'on soulève le rocher.
	 * @return le message lorsque l'on soulève le rocher.
	 */
	public String getMessage(){
		return msg;
	}
	
	/**
	 * Méthode permettant de rendre visible la clef ou le coffre (selon le contenu de la parcelle).
	 */
	public void visible(){
		if(clef){
			super.valeur=7;
		} else if(tresor) {
			super.valeur=8;
		}
	}
	
	/**
	 * Méthode permettant de cacher la clef ou le trésor en replaçant un rocher par dessus.
	 */
	public void hide() {
		super.valeur=valeurRocherIni;
	}
}
