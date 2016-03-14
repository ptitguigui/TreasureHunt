import tps.Plateau;

/**
 * La classe SuperPlateau permet d'afficher un plateau de jeu carré sur lequel sont disposés des images représentant les éléments du jeu, ainsi que de déplacer ces différents éléments. Les images sont toutes de même taille et carrées.
 * @author Marie
 *
 */
public class SuperPlateau {
	
	/**
	 * Attribut correspondant à un plateau de jeu, c'est à dire un tableau de cases.
	 */
	private Plateau p;
	
	/**
	 * Constructeur de la classe Plateau qui crée un plateau de jeu vide de dimension taille x taille cellules vides.
	 * @param gifs tableau d'images
	 * @param taille taille du plateau (il s'agit d'un carré)
	 */
	SuperPlateau(String[] gifs, int taille){
		p = new Plateau(gifs, taille);
	}	
	
	/**
	 * Méthode permettant d'afficher le plateau.
	 */
	public void affichage(){
		p.affichage();
	}
	
	/**
	 * Retourne le tableau d'entiers représentant le plateau.
	 * @return le tableau d'entiers
	 */
	public int[][] getJeu(){
		return p.getJeu();
	}
	
	/**
	 * Méthode permettant de placer les éléments sur le plateau
	 * @param jeu tableau 2D représentant le plateau la valeur numérique d'une cellule désigne l'image correspondante dans le tableau des chemins (décalé de un, 0 désigne une case vide)
	 */
	public void setJeu( int[][] jeu){
		p.setJeu(jeu);
	}
	
	/**
	 * Vérifie si une case est vide.
	 * @param tab tableau d'entiers à deux dimensions
	 * @param a un entier
	 * @param b un entier
	 * @return vrai si la case est vide, faux sinon
	 */
	public boolean deplacable(int[][] tab, int a, int b){
		if(tab[a][b] == 0){
			return true;
		}	
		return false;
	}
	
	/**
	 * Permet de déplacer la valeur de la case (x,y) dans la case (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 */
	public void deplacer(int x, int y, int a, int b) {
		int[][] tab = p.getJeu();
		if(deplacable(tab,a,b)){
			tab[a][b]= tab[x][y];
			tab[x][y]= 0;
			setJeu(tab);
		}
	}
}
