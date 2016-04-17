import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Random;

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
	 * @param nbColonnes un entier
	 * @param nbLignes un entier
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
		Random r = new Random();
		int chance = 0;

		plateaux[i].affichage();
		plateaux[i].println(">> A votre tour J" + (i+1)) ;
		plateaux[1-i].println(">> Au tour de votre adversaire") ;
		
		int[][] jeu;
		
		// Vérification de la selection : doit être un personnage de son équipe
    	do {
    		event=  plateaux[i].waitEvent();
    		while(!(event instanceof MouseEvent)){
    			event=  plateaux[i].waitEvent();
    		}
		   	x = plateaux[i].getX((MouseEvent) event) ;
	    	y = plateaux[i].getY((MouseEvent) event) ;
    	} while(!(getValeurParcelle(x, y)>=9 && getValeurParcelle(x, y)%2!=i ));
		    
    	//Actions si explorateur
    	if(getValeurParcelle(x,y) == 9+i){ 
    		plateaux[i].println("Vous avez choisis un explorateur de J"+(i+1)+", il a " + ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		//highlight(plateaux, i, getValeurParcelle(x,y), x, y);
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    
    			//déplacement
    			if(getParcelle(a,b).estVide() && dansChampsAction(x, y, a, b, 4)){
    				deplacer(x, y, a, b, plateaux, i);
    				action = true;
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 4)){
    				echangeItem(x, y, a, b, plateaux, i);
    				action = true;
    			//Soulève un rocher
    			} else if (rocherACote(x, y, a, b)){
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
    				} else if(((ParcelleRocher)getParcelle(a,b)).getClef()){ //Si clef
    					((ParcelleRocher)getParcelle(a, b)).visible();
    					jeu=getIleTab();
    					plateaux[0].setJeu(jeu);
    					plateaux[1].setJeu(jeu);
    					((Explorateur)getParcelle(x,y)).setPorteClef();
    					((ParcelleRocher)getParcelle(a,b)).setClef(); //passe la clef à false et change le message
    					event = plateaux[i].waitEvent(1000);
    					((ParcelleRocher)getParcelle(a, b)).hide();
    					jeu=getIleTab();
    					plateaux[0].setJeu(jeu);
    					plateaux[1].setJeu(jeu);
    				}
    				action=true;
        		//Si trésor déjà trouvé, mais qu'on revient avec la clef
    			} else if (getValeurParcelle(a,b)==5 && dansChampsAction(x,y,a,b,4)){
    				if (!((ParcelleRocher)getParcelle(a,b)).getTresor()){
    					plateaux[i].println("Vous avez trouvé le coffre ! Malheureusement pour vous, il a déjà été vidé de son contenu...");
    				} else {
    					if(((Personnage)getParcelle(x,y)).porteClef()){
    						plateaux[i].println("Votre clef rentre parfaitement dans la serrure, vous l'ouvrez et vous emparez du trésor !");
    						((Explorateur)getParcelle(x,y)).setPorteTresor();
    						((ParcelleRocher)getParcelle(a,b)).setTresor(); //change le message du trésor
    					} else {
    						plateaux[i].println("Vous tentez de vous emparer du trésor, malheureusement vous n'avez pas la clef...");
    					}
    				}
    				action=true;
    			//Rentrer dans le bateau
    			} else if(getValeurParcelle(a,b)==7+i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(nbPersonnages)){
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}
    	}
		//Action si voleur
    	if(getValeurParcelle(x,y) == 11+i){ 
    		plateaux[i].println("Vous avez choisis un voleur de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    	
    			//déplacement
    			if(getParcelle(a,b).estVide() && dansChampsAction(x, y, a, b, 8)){
    				deplacer(x, y, a, b, plateaux, i);
    				action = true;
    			//Fouille un personnage
    			} else if(dansChampsAction(x,y,a,b,8) && getValeurParcelle(a,b)>8 && !personnageAllieACote(x, y, a, b, 8)){
    				plateaux[i].println("Vous fouillez un personnage...") ;
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-10);
    				//s'il ne porte rien
    				if (! (((Personnage)getParcelle(a,b)).porteClef() && ((Personnage)getParcelle(a,b)).porteTresor())){
    					plateaux[i].println("Mais vous ne trouvez rien.") ; //sinon rien   
    				}
    				//si le personnage porte la clef
    				if(((Personnage)getParcelle(a,b)).porteClef()){  
    					((Voleur)getParcelle(x,y)).setVoleClef(((Personnage)getParcelle(a,b)));
    					plateaux[i].println("Et vous lui volez la clé ! ") ;
    				}
    				//si le personnage porte le trésor
    				if(((Personnage)getParcelle(a,b)).porteTresor()){ 
    					((Voleur)getParcelle(x,y)).setVoleTresor(((Personnage)getParcelle(a,b)));
    					plateaux[i].println("Et vous lui volez le trésor ! ") ;
    				}
    				action = true;
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i);
    				action = true;
    			//Rentrer dans navire
    			} else if(getValeurParcelle(a,b)==7+i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(nbPersonnages)){
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					plateaux[i].println("Vous êtes rentré dans votre navire");
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}    		
    	}
    	
    	//Action si piegeur !!!!!!
    	if(getValeurParcelle(x,y) == 13+i){ 
    		plateaux[i].println("Vous avez choisis un piegeur de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    	
    			//déplacement
    			if(getParcelle(a,b).estVide() && dansChampsAction(x, y, a, b, 8)){
    				deplacer(x, y, a, b, plateaux, i);
    				action = true;
    			// TODO pose un piege
    				
    				
    				
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i);
    				action = true;
    			//Rentrer dans navire
    			} else if(getValeurParcelle(a,b)==7+i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(nbPersonnages)){
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					plateaux[i].println("Vous êtes rentré dans votre navire");
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}    		
    	}
    	
    	//Action si Guerrier !!!!!!
    	if(getValeurParcelle(x,y) == 15+i){ 
    		plateaux[i].println("Vous avez choisis un guerrier de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
			    	
    			//déplacement
    			if(getParcelle(a,b).estVide() && dansChampsAction(x, y, a, b, 8)){
    				deplacer(x, y, a, b, plateaux, i);
    				action = true;
    			//attaque un ennemi
    			} else if(dansChampsAction(x,y,a,b,8) && getValeurParcelle(a,b)>8 && !personnageAllieACote(x, y, a, b, 8)){
    				chance = r.nextInt(99)+1;
    				plateaux[i].println("Vous attaquez un personnage...") ;
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-10);
    				if(chance >50){
    				plateaux[i].println("Et vous lui infliger 30 points de dégâts !!") ;
    				((Guerrier)getParcelle(x,y)).attaqueEnnemi(((Personnage)getParcelle(a,b))); 
    				}else{
    					plateaux[i].println("Mais vous manquez votre cible...") ;
    				}
    				action = true;    				
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i);
    				action = true;
    			//Rentrer dans navire
    			} else if(getValeurParcelle(a,b)==7+i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(nbPersonnages)){
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					plateaux[i].println("Vous êtes rentré dans votre navire");
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}    		
    	}
    	//Recupération d'énergie pour les personnages dans le navire
    	int[] coordNavire= getNavire(i+1);
    	for(int p=0; p<((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getNbPersonnage(); p++){
    		((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getPersonnage(p).setEnergie(((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getPersonnage(p).getEnergie()+10);
    	}
		event = plateaux[i].waitEvent(500);	// Délai pour permettre la lecture.
	}
	
	/**
	 * Méthode permettant de déplacer le personnage de coordonnées (x,y) à la case de coordonnées (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau courrant
	 */
	public void deplacer(int x, int y, int a, int b, SuperPlateau[] plateaux, int i){
		((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-1);
		echangeParcelles(x, y, a, b);
		plateaux[0].setJeu(getIleTab());
		plateaux[1].setJeu(getIleTab());
		plateaux[i].println("Déplacement effectué.");
	}
	
	/**
	 * Méthode permettant d'échanger un item (donner ou prendre) selon un ordre d'importance (trésor puis clef) entre le personnage (x,y) et le personnage (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau courrant
	 */
	public void echangeItem(int x, int y, int a, int b, SuperPlateau[] plateaux, int i){
		if(((Personnage)getParcelle(x,y)).porteTresor()){  //si le personnage (x,y) porte le tresor
			((Personnage)getParcelle(x,y)).donneItem((Personnage)getParcelle(a,b), 1);	
			plateaux[i].println("Vous donnez le trésor à un allié.") ;
		}else if (((Personnage)getParcelle(a,b)).porteTresor()) { //si le personnage (a,b) porte le tresor
			((Personnage)getParcelle(a,b)).donneItem((Personnage)getParcelle(x,y), 1);	
			plateaux[i].println("Vous prennez le trésor à un allié.") ;
		}else if (((Personnage)getParcelle(x,y)).porteClef()){  //si le personnage (x,y) porte la clef
			((Personnage)getParcelle(x,y)).donneItem((Personnage)getParcelle(a,b), 0);
			plateaux[i].println("Vous donnez la clef a un allié.") ;
		}else if (((Personnage)getParcelle(a,b)).porteClef()){  //si le personnage (a,b) porte la clef
			((Personnage)getParcelle(a,b)).donneItem((Personnage)getParcelle(x,y), 0);
			plateaux[i].println("Vous prennez la clef a un allié.") ;
		}else {
			plateaux[i].println("Vos deux personnages discutent tranquillement...") ;
		}
	}
	
	/**
	 * Méthode permettant de rentrer le personnage (x,y) dans le navire en (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau courrant
	 */
	public void  rentrerDansNavire(int x, int y, int a, int b, SuperPlateau[] plateaux, int i){
		((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-1);
		((ParcelleNavire)getParcelle(a,b)).addPersonnage((Personnage)getParcelle(x,y));
		grille[x][y]=new Parcelle();
		plateaux[0].setJeu(getIleTab());
		plateaux[1].setJeu(getIleTab());
		plateaux[i].println("Vous êtez rentré dans votre navire");
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
	
	/**
	 * Methode qui renvoie un boolean pour savoir si le personnage de coord (x,y) peut se déplacer sur une case de coord (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param nbDirections un entier correspondant au nombre de direction possible pour ses actions
	 * @return vrai s'il peut agir sur cette case, faux sinon.
	 */
	public boolean dansChampsAction(int x, int y, int a, int b, int nbDirections){
		if (nbDirections==4) {
			return((a == x+1 && b == y) || (a==x-1 && b==y) || (a==x && b==y+1) || (a==x && b==y-1));
		}
		if (nbDirections==8) {
			return((a == x-1 && b == y-1) || (a==x-1 && b==y) || (a==x-1 && b==y+1) || (a==x && b==y-1)
					|| (a == x && b == y+1) || (a==x+1 && b==y-1) || (a==x+1 && b==y) || (a==x+1 && b==y+1));
		}
		return false;
	}
	/*
	public void highlight(SuperPlateau[] plateaux, int i, int valeurPerso, int x, int y){
		if(valeurPerso==9+i){
			int[] valeursDeplacable=new int[]{1,3,5,7+i,9+i,11+i,13+i,15+i};
			for(int j=0; j<valeursDeplacable.length; j++){
				if (grille[x+1][y].getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(x+1,y);
				} else if (grille[x-1][y].getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(x-1,y);
				} else if (grille[x][y+1].getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(x,y+1);
				} else if (grille[x][y-1].getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(x,y-1);
				}
			}
		}
		
	}*/
	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un rocher de coord(a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un booleen
	 */
	public boolean rocherACote(int x, int y, int a, int b){
		return (grille[a][b].getValeur() == 3)&& dansChampsAction(x,y,a,b,4); //4 car seul les explorateurs peuvent soulever.
	}
	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un autre personnage de coord(a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param nbDirections un entier correspondant au nombre de direction possible pour ses actions
	 * @return un booleen
	 */
	public boolean personnageACote(int x, int y, int a, int b, int nbDirections){
		return (grille[a][b].getValeur() > 8)&& dansChampsAction(x, y, a, b, nbDirections);
	}	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un autre personnage de coord(a,b) et si celui est un allié.
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param nbDirections un entier correspondant au nombre de direction possible pour ses actions
	 * @return un booleen
	 */
	public boolean personnageAllieACote(int x, int y, int a, int b, int nbDirections){
		if(personnageACote(x,y,a,b,nbDirections)){
			return getValeurParcelle(x,y)%2 == getValeurParcelle(a,b)%2;
		}
		return false;
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
	 *  Méthode plaçant les explorateurs sur l'ile , le nombre d'explorateurs correspond à l'entier précisé en paramètre.
	 * @param nbExplorateurs, le nombre d'explorateurs dans l'équipe
	 * @param numEquipe, le numero de l'équipe où l'explorateur est situé
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
	
	/**
	 *  Méthode plaçant les voleurs sur l'ile , le nombre de voleurs correspond à l'entier précisé en paramètre.
	 * @param nbVoleurs, le nombre d'explorateurs dans l'équipe
	 * @param numEquipe, le numero de l'équipe où le voleur est situé
	 */
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
	
	private void setPiegeurs(int nbPiegeurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbPiegeurs; i++){
				
			String saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée x du piegeur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille.length-1)){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée x du piegeur n°" + i + " de l'équipe " + numEquipe);
			}
			x=Integer.parseInt(saisie);
					
			saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée y du piegeur n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille[0].length-1 && grille[x][Integer.parseInt(saisie)].estVide())){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou case déjà occupée.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée y du piegeur n°" + i + " de l'équipe " + numEquipe);
			}
			y=Integer.parseInt(saisie);
						
			grille[x][y]=new Piegeur("Piegeur", numEquipe);
			if (numEquipe==1){
				entites.put("P"+Integer.toString(i), new int[] {x,y});
			} else {
				entites.put("p"+Integer.toString(i), new int[] {x,y});
			}
		}
	}
	
	private void setGuerriers(int nbGuerriers, int numEquipe){
		int x, y;
		for(int i=1; i<=nbGuerriers; i++){
				
			String saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée x du guerrier n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille.length-1)){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée x du guerrier n°" + i + " de l'équipe " + numEquipe);
			}
			x=Integer.parseInt(saisie);
					
			saisie=new String(JOptionPane.showInputDialog(null,"Entrer la coordonnée y du guerrier n°" + i + " de l'équipe " + numEquipe));
			//Tant que la saisie soit un chiffre et qu'il soit entre 2 et la taille de la grille-2
			while(!(saisie.matches("[1-9][0-9]*")&& Integer.parseInt(saisie)>=1 && Integer.parseInt(saisie)<grille[0].length-1 && grille[x][Integer.parseInt(saisie)].estVide())){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou case déjà occupée.", "Erreur", 0);
				saisie=JOptionPane.showInputDialog(null,"Entrer la coordonnée y du guerrier n°" + i + " de l'équipe " + numEquipe);
			}
			y=Integer.parseInt(saisie);
						
			grille[x][y]=new Guerrier("guerrier", numEquipe);
			if (numEquipe==1){
				entites.put("G"+Integer.toString(i), new int[] {x,y});
			} else {
				entites.put("g"+Integer.toString(i), new int[] {x,y});
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
	 * @param pourcentage entier entre 0 et 100 correspondant au pourcentage de cases étants des rochers.
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
	 * Méthode retournant les coordonnées du navire d'un joueur.
	 * @param numEquipe numéro du joueur.
	 * @return les coordonnées du navire sous forme d'un tableau int[]
	 */
	public int[] getNavire(int numEquipe){
		if(numEquipe==1){
			return entites.get("n");
		} else {
			return entites.get("N");
		}
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
	 * @param pourcentage entier entre 0 et 100 correspondant au pourcentage de case étant des rochers.
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
	public void setPersonnages(int numEquipe, int nbExplorateurs, int nbVoleurs, int nbPiegeurs, int nbGuerriers){
		if(numEquipe>0 && numEquipe<3) {
			setExplorateurs(nbExplorateurs, numEquipe);
			setVoleurs(nbVoleurs, numEquipe);
			setPiegeurs(nbPiegeurs, numEquipe);
			setGuerriers(nbGuerriers, numEquipe);
		}
	}
	
}
