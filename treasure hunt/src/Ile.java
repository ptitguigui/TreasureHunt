import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JOptionPane;

/**
 * Classe créant le plateau de jeu, l'ile en l'occurence.
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
	Ile(int nbColonnes, int nbLignes) {
		grille=new Parcelle[nbColonnes][nbLignes];
		for(int c=0; c<grille.length; c++){
			for(int l=0; l<grille[0].length; l++){
				grille[c][l]=new Parcelle();
			}
		}
	}
	
	
	public void action(SuperPlateau[] plateaux, int i, int nbPersonnages) {
		InputEvent event ;
		int x,y,a,b =0;
		boolean action = false;

		plateaux[i].affichage();
		plateaux[i].println("A votre tour J" + (i+1)) ;
		plateaux[1-i].println("Au tour de votre adversaire") ;
		
		int[][] jeu=getIleTab();
		
		// Vérification de la selection : doit être un personnage de son équipe
    	do {
    		event=  plateaux[i].waitEvent();
		   	x = plateaux[i].getX((MouseEvent) event) ;
	    	y = plateaux[i].getY((MouseEvent) event) ;
    	} while(!(getValeurParcelle(x, y)>=9 && getValeurParcelle(x, y)%2!=i));
		    
    	//Actions si explorateur
    	if(getValeurParcelle(x,y) == 9+i){ 
    		plateaux[i].println("Vous avez choisis un explorateur de J"+(i+1)+", il a " + ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faite ?") ;
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    
    			//déplacement
    			if(plateaux[i].deplacable(jeu,a,b) && parcelleValideExplorateur(x, y, a, b)){
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-1);
    				echangeParcelles(x, y, a, b);
    				jeu=getIleTab();
    				plateaux[0].setJeu(jeu);
    				plateaux[1].setJeu(jeu);
    				plateaux[i].println("Déplacement effectué");
    				action = true;
    				
    			//Soulève un rocher
    			} else if (getValeurParcelle(x,y) == 9+i && rocherACote(x, y, a, b)){
    				plateaux[i].println("L'explorateur soulève un rocher");
    		    	event = plateaux[i].waitEvent(500) ;    				
					plateaux[i].println(((ParcelleRocher)getParcelle(a,b)).getMessage());
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-5);
    				if(((ParcelleRocher)getParcelle(a,b)).getTresor()){ //Si trésor
    					((ParcelleRocher)getParcelle(a, b)).visible();
    					jeu=getIleTab();
    					plateaux[0].setJeu(jeu);
    					plateaux[1].setJeu(jeu);
    					if (((Personnage)getParcelle(x,y)).porteClef()){
    						((Explorateur)getParcelle(x,y)).setPorteTresor();
    						((ParcelleRocher)getParcelle(a,b)).setTresor(); //change le message du trésor
    						plateaux[i].println("Votre clef rentre parfaitement dans la serrure, vous l'ouvrez et vous emparez du trésor !");
    					} else {
    						plateaux[i].println("Malheureusement, vous n'avez pas la clef...");
    					}
    					//Si trésor déjà trouvé, mais on revient avec la clef
    				} else if (getValeurParcelle(a,b)==5 && ((Personnage)getParcelle(x,y)).porteClef()){
    					plateaux[i].println("Votre clef rentre parfaitement dans la serrure, vous l'ouvrez et vous emparez du trésor !");
    					((Explorateur)getParcelle(x,y)).setPorteTresor();
						((ParcelleRocher)getParcelle(a,b)).setTresor(); //change le message du trésor
    				} else if(((ParcelleRocher)getParcelle(a,b)).getClef()){ //Si clef
    					((ParcelleRocher)getParcelle(a, b)).visible();
    					jeu=getIleTab();
    					plateaux[0].setJeu(jeu);
    					plateaux[1].setJeu(jeu);
    					((Explorateur)getParcelle(x,y)).setPorteClef();
    					((ParcelleRocher)getParcelle(a,b)).setClef(); //passe la clef à false et change le message
    			    	event = plateaux[i].waitEvent(1000);
    			    	((ParcelleRocher)getParcelle(a, b)).hide();
    				}
    				action = true;
    			}	
    			//Rentrer dans le bateau
    				//ajouter le perso dans la liste de parcelleNavire et remplacé sa case par une parcelle
    			if(getValeurParcelle(a,b)==6+i && ((ParcelleNavire)getParcelle(a,b)).peutMonterABord(nbPersonnages)){
    				((ParcelleNavire)getParcelle(a,b)).addPersonnage(((Personnage)getParcelle(x,y)));
    				grille[x][y]=new Parcelle();
    			}
    		}
    	}
    	
    	//A MODIFIER

		//Action si voleur
    	if(getValeurParcelle(x,y) == 11+i){ 
    		plateaux[i].println("Vous avez choisis un voleur de J"+(i+1)+", faites une action") ;
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    	
    			//déplacement
    			if(plateaux[i].deplacable(jeu,a,b) && parcelleValideVoleur(x, y, a, b)){ //a changer en une methode deplacableVoleur
    				echangeParcelles(x, y, a, b);
    				jeu=getIleTab();
    				plateaux[0].setJeu(jeu);
    				plateaux[1].setJeu(jeu);
    				plateaux[i].println("Déplacement effectué") ;
    				action = true;
			    }
			    	
    			//Fouille un personnage
    			if(getValeurParcelle(x,y) == 11+i && personnageACote(x, y, a, b)){
    				plateaux[i].println("Vous fouillez un personnage...") ;
    				//si le personnage porte la clef
    				//si le personnage porte le trésor
    				//sinon rien
    				action = true;
    			}					    	
    		}
    	}

    	action = false;
    	event = plateaux[i].waitEvent(500);	// Délai pour permettre la lecture.
	}
	
	
	/**
	 * Méthode retournant la parcelle de coordonnée x,y.
	 * @param x un entier.
	 * @param y un entier.
	 * @return la parcelle correspondante.
	 */
	public Parcelle getParcelle(int x, int y){
		return grille[x][y];
	}
	
	/**
	 * Méthode retournant la valeur de la parcelle de coordonnée x,y.
	 * @param x un entier.
	 * @param y un entier.
	 * @return la valeur de la parcelle correspondante.
	 */
	public int getValeurParcelle(int x, int y){
		return grille[x][y].getValeur();
	}
	
	/**
	 * Méthode permetant d'échanger de position la parcelle de coordonnée a,b avec celle de coordonnée x,y.
	 * @param x un entier.
	 * @param y un entier.
	 * @param a un entier.
	 * @param b un entier.
	 */
	public void echangeParcelles(int x, int y, int a, int b){
		 Parcelle p = grille[x][y];
		 grille[x][y] = grille[a][b];
		 grille[a][b] = p;
	}
			
		//bug : déplacement sur un autre personnage
	/**
	 * Methode qui renvoi un boolean pour savoir si l'explorateur de coord (x,y) peut se déplacer sur une case de coord (a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un booleen
	 */
	public boolean parcelleValideExplorateur(int x, int y, int a, int b){
		return((a == x+1 && b == y) || (a==x-1 && b==y) || (a==x && b==y+1) || (a==x && b==y-1));			
	}
	/**
	 * Methode qui renvoi un boolean pour savoir si le voleur de coord (x,y) peut se déplacer sur une case de coord (a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un boolean
	 */
	
	public boolean parcelleValideVoleur(int x, int y, int a, int b){
		return((a == x+1 && b == y) || (a==x-1 && b==y) || (a==x && b==y+1) || (a==x && b==y-1) || (a==x+1 && b==y+1) ||(a==x-1 && b==y-1) || (a==x+1 && b==y-1) || (a==x-1 && b==y+1)  );			
	}
	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un rocher de coord(a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un boolean
	 */
	public boolean rocherACote(int x, int y, int a, int b){
		return (grille[a][b].getValeur() == 3)&&((a == x+1 && b == y) || (a==x-1 && b==y) || (a==x && b==y+1) || (a==x && b==y-1));
	}
	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un autre personnage de coord(a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un boolean
	 */
	public boolean personnageACote(int x, int y, int a, int b){
		return (grille[a][b].getValeur() > 8)&&((a == x+1 && b == y) || (a==x-1 && b==y) || (a==x && b==y+1) || (a==x && b==y-1));
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
	 * Méthode plaçant aléatoirement les personnage sur l'ile, le nombre de personnages correspond à l'entier précisé en paramètre selon la taille de l'ile.
	 * @params nombres de personnages par équipe entre 1 et 4.
	 */
	private void setExplorateurs(int nbExplorateurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbExplorateurs; i++){
				
			String saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée x de l'explorateur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille.length-1)){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée x de l'explorateur n°" + i + " de l'équipe " + numEquipe);
			}
			x=Integer.parseInt(saisie);
					
			saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée y de l'explorateur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille[0].length-1 && grille[x][Integer.parseInt(saisie)].estVide())){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou case déjà occupée.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée y de l'explorateur n°" + i + " de l'équipe " + numEquipe);
			}
			y=Integer.parseInt(saisie);
						
			grille[x][y]=new Explorateur("Explorateur", numEquipe);
			if (numEquipe==1){
				entites.put("E"+Integer.toString(i), new int[] {x,y});
			} else {
				entites.put("e"+Integer.toString(i), new int[] {x,y});
			}
		}
	}
	
	private void setVoleurs(int nbVoleurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbVoleurs; i++){
				
			String saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée x du voleur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille.length-1)){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée x du voleur n°" + i + " de l'équipe " + numEquipe);
			}
			x=Integer.parseInt(saisie);
					
			saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée y du voleur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille[0].length-1 && grille[x][Integer.parseInt(saisie)].estVide())){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou case déjà occupée.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée y du voleur n°" + i + " de l'équipe " + numEquipe);
			}
			y=Integer.parseInt(saisie);
						
			grille[x][y]=new Voleur("Voleur", numEquipe);
			if (numEquipe==1){
				entites.put("V"+Integer.toString(i), new int[] {x,y});
			} else {
				entites.put("v"+Integer.toString(i), new int[] {x,y});
			}
		}
	}
	
	/**
	 * Méthode plaçant la mer.
	 */
	private void setMers(){
		for(int c=0; c<grille.length; c++) {
			for(int l=0; l<grille[0].length; l++) {
			grille[0][l]=new ParcelleMer();
			entites.put("M", new int[] {0,l});
			grille[grille.length-1][l]=new ParcelleMer();
			entites.put("M", new int[] {grille.length-1,l});
			grille[c][0]=new ParcelleMer();
			entites.put("M", new int[] {c,0});
			grille[c][grille[0].length-1]=new ParcelleMer();
			entites.put("M", new int[] {c,grille[0].length-1});
			}
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
				y= alea.tirage(grille[0].length-2)+1;
			}
			while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)>6));
			grille[x][y]=new ParcelleRocher();
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
			y=alea.tirage(grille[0].length-2)+1;
			if (x==0){ x=grille.length-2;}
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)==5));
		grille[x][y]=new ParcelleNavire(1);
		entites.put("n", new int[] {x,y});
		
		//placement du 2e navire
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(2);
			if (y==0){ y=grille[0].length-2;}
		}
		while(!(grille[x][y].estVide()&& nbVoisinsVide(x, y)==5));
		grille[x][y]=new ParcelleNavire(2);
		entites.put("N", new int[] {x,y});
	}
	 
	/**
	 * Méthode plaçant aléatoirement le trésor sur l'ile, puis la recouvre d'un rocher.
	 */
	private void setTresor() {
		int x, y;
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(grille[0].length-2)+1;
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)>7));
		grille[x][y]=new ParcelleRocher();
		((ParcelleRocher)grille[x][y]).setTresor();
		entites.put("T", new int[] {x,y});
	}
	
	/**
	 * Méthode plaçant aléatoirement la clef sur l'ile, puis la recouvre d'un rocher.
	 */
	private void setClef() {
		int x, y;
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(grille[0].length-2)+1;
		}
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y)>7));
		grille[x][y]=new ParcelleRocher();
		((ParcelleRocher)grille[x][y]).setClef();
		entites.put("C", new int[] {x,y});
	}
	
	
//	/**
//	 * Méthode retournant les coordonnées des personnages du J1.
//	 * @return les coordonnées des personnages sous forme d'un tableau à deux dimensions int[][].
//	 */
//	public int[][] getPersonnagesJ1(int nbPersonnages) {
//		int[][]coord = new int[2][nbPersonnages];
//		for(int i=0; i<coord[0].length; i++){
//			coord[i]=entites.get("E"+Integer.toString(i));
//		}
//		return coord;
//	}
//	/**
//	 * Méthode retournant les coordonnées des personnages du J2.
//	 * @return les coordonnées des personnages sous forme d'un tableau à deux dimensions int[][].
//	 */
//	public int[][] getPersonnagesJ2(int nbPersonnages) {
//		int[][]coord = new int[2][nbPersonnages];
//		for(int i=0; i<coord[0].length; i++){
//			coord[i]=entites.get("e"+Integer.toString(i));
//		}
//		return coord;
//	}
		
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
	 * Méthode retournant les coordonnées du navire de J2.
	 * @return les coordonnées du navire J1 sous forme d'un tableau int[]
	 */
	public int[] getNavireJ1(){
		return entites.get("n");
	}
	/**
	 * Méthode retournant les coordonnées du navire de J2.
	 * @return les coordonnées du navire J2 sous forme d'un tableau int[].
	 */
	public int[] getNavireJ2(){
		return entites.get("N");
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
	 * Méthode permettant d'initialiser l'ile en plaçant tous les éléments fixes.
	 * @params pourcentage entier entre 0 et 100 correspondant au pourcentage de case étant des rochers.
	 */
	public void initialiser(int pourcentage){
		setMers();	
		setTresor();
		setClef();
		setRochers(pourcentage);	
		setNavires();
	}	
	
	/**
	 * Méthode permettant de placer les personnages sur l'ile.
	 * @param numEquipe numéro du joueur.
	 * @param nbExplorateurs nombre d'explorateur voulu.
	 * @param nbVoleurs nombre de voleurs voulu.
	 */
	public void setPersonnages(int numEquipe, int nbExplorateurs, int nbVoleurs){
		if(numEquipe>0 && numEquipe<3) {
			setExplorateurs(nbExplorateurs, numEquipe);
			setVoleurs(nbVoleurs, numEquipe);
		}
	}
	
}
