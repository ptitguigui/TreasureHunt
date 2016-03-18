import java.util.HashMap;

/**
 * Class créant le plateau de jeu, l'ile en l'occurence.
 * @author vitsem
 *
 */
public class Ile {
	
	/**
	 * Attribut correspondant au tableau de parcelles constituant l'ile. Il sert à l'affichage du plateau.
	 */
	private Parcelle[][] grille;
	/**
	 * Attribut servant à la création de nombres aléatoires.
	 */
	private Aleatoire alea=new Aleatoire();
	/**
	 * Attribut réunissant les coordonnées de tous les éléments positionnés sur l'ile.
	 */
	private HashMap<String, int[]> entites =new HashMap<>();
		
	
	/**
	 * Constructeur créant une ile de taille x par y.
	 * @param x un entier
	 * @param y un entier
	 */
	Ile(int x, int y) {
		grille=new Parcelle[x][y];
		for(int l=0; l<grille[0].length; l++){
			for(int c=0; c<grille.length; c++){
				grille[l][c]=new Parcelle();
			}
		}
	}
		
	/**
	 * Méthode transformant l'objet en une chaine de caractères String pouvant être affichée.
	 * @return le plateau sous forme de String.
	 */
	public String toString() {
		String plateau="";
		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille[0].length; j++) {
				plateau+="[" + grille[i][j].getValeur() + "] ";
			}
			plateau+="\n";
		}
		return plateau;
	}
	
	/**
	 * Méthode comptant le nombre de cases vides autour d'une case (compte les 8 cases autours, c'est-à-dire les diagonales inclues).
	 * @param x un entier correspondant à la première coordonnée
	 * @param y un entier correspondant à la deuxième coordonnée
	 * @return le nombre de cases vides autour de la case correspondant aux coordonnées passées en paramètre.
	 */
	public int nbVoisinsVide(int x, int y){
		int nb=0;
		if(grille[x+1][y].estVide()){ nb += 1; }
		if(grille[x-1][y].estVide()){ nb += 1; }
		if(grille[x][y+1].estVide()){ nb += 1; }
		if(grille[x][y-1].estVide()){ nb += 1; }
		if(grille[x+1][y+1].estVide()){	nb += 1; }
		if(grille[x+1][y-1].estVide()){ nb += 1; }
		if(grille[x-1][y+1].estVide()){ nb += 1; }
		if(grille[x-1][y-1].estVide()){	nb += 1; }
		return nb;
	}
	
	/**
	 * Méthode plaçant la mer.
	 */
	private void setMers(){
		for(int i=0; i<grille.length; i++) {
			grille[0][i].setValeur(1); // 1 = mer
			entites.put("M", new int[] {0,i});
			grille[grille.length-1][i].setValeur(1);
			entites.put("M", new int[] {grille.length-1,i});
			grille[i][0].setValeur(1);
			entites.put("M", new int[] {i,0});
			grille[i][grille.length-1].setValeur(1);
			entites.put("M", new int[] {i,grille.length-1});
		}
	}
	
	/**
	 * Méthode plaçant aléatoirement des rochers sur l'ile, le nombre de rochers correspond au pourcentage précisé en paramètre selon la taille de l'ile.
	 * @params pourcentage entier entre 0 et 100 correspondant au pourcentage de cases étants des rochers.
	 */
	private void setRochers(int pourcentage) {
		int x, y;
		for(int i=0; i<(int)((grille.length-2)*(grille[0].length-2)*(pourcentage/100.00)-2); i++) {
			do {
				x= alea.tirage(grille.length-2)+1;
				y= alea.tirage(grille.length-2)+1;
			}
			while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)>7));
			grille[x][y].setValeur(2); // 2 = rocher
			entites.put("R"+Integer.toString(i), new int[] {x,y});
		}
	}
	
	
	/**
	 * Méthode qui place les deux navires aléatoirement sur les parcelles en bordure.
	 */
	private void setNavires(){
		//placement du 1er navire
		int x, y;
		do {
			x=alea.tirage(2);
			y=alea.tirage(grille.length-2)+1;
			if (x==0){ x=grille.length-2;}
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)==5));
		grille[x][y].setValeur(3); // 3 = navire1
		entites.put("n", new int[] {x,y});
		
		//placement du 2e navire
		do {
			x= alea.tirage(2);
			y= alea.tirage(grille.length-2)+1;
			if (x==0){ x=grille.length-2;}
		}
		while(!(grille[y][x].estVide()&& nbVoisinsVide(y, x)==5));
		grille[y][x].setValeur(4); // 4 = navire2
		entites.put("N", new int[] {y,x});
	}
	 
	/**
	 * Méthode plaçant aléatoirement le trésor sur l'ile, puis la recouvre d'un rocher.
	 */
	private void setTresor() {
		int x, y;
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(grille.length-2)+1;
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(y, x)>7));
		grille[x][y].setValeur(6); // 6 = tresor
		entites.put("T", new int[] {x,y});
		grille[x][y].setValeur(2); // 2 = rocher
	}
	
	/**
	 * Méthode plaçant aléatoirement la clef sur l'ile, puis la recouvre d'un rocher.
	 */
	private void setClef() {
		int x, y;
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(grille.length-2)+1;
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(y, x)>7));
		grille[x][y].setValeur(5); // 5 = clef
		entites.put("C", new int[] {x,y});
		grille[x][y].setValeur(2); // 2 = rocher
	}
	
	/**
	 * Méthode retournant les coordonnées de la mer.
	 * @return les coordonnées de la mer sous forme d'un tableau à deux dimensions int[][].
	 */
	public int[] getMers(){
		return entites.get("M");
	}
	
	/**
	 * Méthode retournant les coordonnées des rochers (sans compter la clef et le trésor).
	 * @return les coordonnées des rochers sous forme d'un tableau à deux dimensions int[][].
	 */
	public int[][] getRochers() {
		int[][] coord=new int[2][(int)(grille.length*grille[0].length*0.1-2)];
		for(int i=0; i<coord[0].length; i++){
			coord[i]=entites.get("R"+Integer.toString(i));
		}
		return coord;
	}
	
	/**
	 * Méthode retournant les coordonnées des navires.
	 * @return les coordonnées des navires sous forme d'un tableau à deux dimensions int[][].
	 */
	public int[][] getNavires(){
		return new int[][] {entites.get("n"),entites.get("N")};
	}
	
	/**
	 * Méthode retournant les coordonnées du trésor.
	 * @return les coordonnées du trésor sous forme d'un tableau int[].
	 */
	public int[] getTresor() {
		return entites.get("T");
	}
	
	/**
	 * Méthode retournant les coordonnées de la clef.
	 * @return les coordonnées de la clef sous forme d'un tableau int[].
	 */
	public int[] getClef() {
		return entites.get("C");		
	}
	
	/**
	 * Méthode retournant l'ile sous forme de tableau d'entiers à deux dimensions.
	 * @return un tableau de int à deux dimensions.
	 */
	public int[][] getIleTab(){
		int[][] tab = new int[grille.length][grille[0].length];
		for(int i=0; i<grille.length; i++){
			for(int j=0; j<grille[0].length; j++) {
				tab[i][j]=grille[i][j].getValeur();
			}
		}
		return tab;
	}
	
	/**
	 * Méthode permettant d'initialiser l'ile en plaçant tous les éléments nécessaires.
	 * @params pourcentage entier entre 0 et 100 correspondant au pourcentage de case étant des rochers.
	 */
	public void initialiser(int pourcentage){
		setMers();
		setTresor();
		setClef();
		setRochers(pourcentage);		
		setNavires();
	}	
	
}
