/**
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer {

	public static void main(String[] args) {
		String[] imgs={"images/mer.png",
				"images/rocher.png.jpg",
				"images/1.navire.png",
				"images/2.navire.png",
				// image clef à trouver
				"images/coffre.png.jpg"};
		
		int taille=10;
		SuperPlateau grille=new SuperPlateau(imgs,taille);
		
		Ile monIle = new Ile(taille,taille);
		monIle.initialiser(10);
		
		grille.setJeu(monIle.getIleTab());
		grille.affichage();
	}

}
