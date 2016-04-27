package Jeu;
import java.util.Random;

/**
 * Classe qui retourne des entiers aléatoires
 * @author vitsem
 *
 */
public class Aleatoire {
	/**
	 * Attribut correspondant a un nombre aléatoire permettant de créer un tirage
	 */
	private Random r=new Random();
	
	/**
	 * Méthode qui retourne un entier aléatoire entre 0 et maxExclu
	 * @param maxExclu la borne maximale exclu
	 * @return un entier compris dans l'intervalle [0 , maxExclu]
	 */
	public int tirage(int maxExclu){
		return r.nextInt(maxExclu);
	}
}
