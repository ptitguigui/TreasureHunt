/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer {

	public static void main(String[] args) {
		String[] imgs={"treasure hunt/images/mer.png",
				"treasure hunt/images/rocher.png.jpg",
				"treasure hunt/images/1.navire.png",
				"treasure hunt/images/2.navire.png",
				// image clef à trouver
				"treasure hunt/images/coffre.png.jpg"};
		
		int taille=15;
		SuperPlateau grille=new SuperPlateau(imgs,taille);
		
		Ile monIle = new Ile(taille,taille);
		int pourcentage=10;
		monIle.initialiser(pourcentage);
		
		grille.setJeu(monIle.getIleTab());
		grille.affichage();
	}

}
