import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * Classe permettant de gérer les plateaux
 * @author vitsem
 */
public class GestionPlateaux {
	/**
	 * Attribut désignant les différents plateaux.
	 */
	private SuperPlateau[] plateaux=new SuperPlateau[2];
	/**
	 * Attribut servant à charger les images du jeu.
	 */
	private final String[] IMGS={"../ressources/tps/sable.png",
			"../ressources/tps/mer.png",
			"../ressources/tps/rocher1.png",
			"../ressources/tps/rocher2.png",
			"../ressources/tps/rocher3.png",
			"../ressources/tps/arbre.png",
			"../ressources/tps/clef.png",
			"../ressources/tps/coffre.png",
			"../ressources/tps/piege.png",
			"../ressources/tps/tresor.png",
			"../ressources/tps/epee.png",
			"../ressources/tps/1_bateau.png",
			"../ressources/tps/2_bateau.png",
			"../ressources/tps/1_explorateur.png",
			"../ressources/tps/2_explorateur.png",
			"../ressources/tps/1_voleur.png",
			"../ressources/tps/2_voleur.png",
			"../ressources/tps/1_piegeur.png",
			"../ressources/tps/2_piegeur.png",
			"../ressources/tps/1_guerrier.png",
			"../ressources/tps/2_guerrier.png"};
	/**
	 * Attribut permettant de connaitre le point de vue omniscient de l'ile, et donc de tout savoir en temps réel.
	 */
	private int[][] jeu;
	/**
	 * L'ile liée aux plateaux.
	 */
	private Ile monIle;
	/**
	 * Le tableau de booléens du joueur 1 permettant d'effectuer un filtre sur sa vision de l'ile et donc de faire un effet brouillard de guerre.
	 */
	private boolean[][] jeuJ1b;
	/**
	 * Le tableau de booléens du joueur 2 permettant d'effectuer un filtre sur sa vision de l'ile et donc de faire un effet brouillard de guerre.
	 */
	private boolean[][] jeuJ2b;
	/**
	 * Le tableau d'entier du joueur 1 désignant ce qu'il voit.
	 */
	private int[][] jeuJ1;
	/**
	 * Le tableau d'entier du joueur 2 désignant ce qu'il voit.
	 */
	private int[][] jeuJ2;
	/**
	 * Les coordonnées du trésor.
	 */
	private int[] coordTresor=new int[2];
	/**
	 * ArrayList mémorisant les pieges posés par le joueur 1 (et donc leurs coordonnées aussi).
	 */
	private ArrayList<ParcellePiege> pieges1=new ArrayList<>();
	/**
	 * ArrayList mémorisant les pieges posés par le joueur 2 (et donc leurs coordonnées aussi).
	 */
	private ArrayList<ParcellePiege> pieges2=new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur du gestionnaire de plateaux, le brouillard est désactivé par défaut.
	 * @param monIle l'ile liée aux plateaux.
	 */
	GestionPlateaux(Ile monIle){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10,true);
		plateaux[1]=new SuperPlateau(IMGS,10,true);
		jeu=monIle.getIleTab();
		jeuJ1b=new boolean[jeu.length][jeu[0].length];
		jeuJ2b=new boolean[jeu.length][jeu[0].length];
		jeuJ1=new int[jeu.length][jeu[0].length];
		jeuJ2=new int[jeu.length][jeu[0].length];
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				jeuJ1b[c][l]=true;
				jeuJ2b[c][l]=true;
			}
		}
		coordTresor=monIle.getTresor();
	}
	
	/**
	 * Constructeur du gestionnaire de plateaux.
	 * @param monIle l'ile liée aux plateaux.
	 * @param brouillard un booléen permettant d'activer ou non le brouillard de guerre.
	 */
	GestionPlateaux(Ile monIle, boolean brouillard){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10,true);
		plateaux[1]=new SuperPlateau(IMGS,10,true);
		jeu=monIle.getIleTab();
		jeuJ1b=new boolean[jeu.length][jeu[0].length];
		jeuJ2b=new boolean[jeu.length][jeu[0].length];
		jeuJ1=new int[jeu.length][jeu[0].length];
		jeuJ2=new int[jeu.length][jeu[0].length];
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				jeuJ1b[c][l]=!brouillard;
				jeuJ2b[c][l]=!brouillard;
			}
		}
		coordTresor=monIle.getTresor();
		add(monIle.getNavire(1)[0], monIle.getNavire(1)[1], 1);
		add(monIle.getNavire(2)[0], monIle.getNavire(2)[1], 2);
		update();
	}
	
	/**
	 * Méthode permettant de retourner les plateaux du gestionnaire.
	 * @return les plateaux du gestionnaire.
	 */
	public SuperPlateau[] getPlateaux(){
		return plateaux;
	}
	
	/**
	 * Méthode permettant d'ajouter une case visible ainsi que les 8 cases autour, dans le champs de vision d'un joueur.
	 * @param a la coordonnée x de la case.
	 * @param b la coordonnée y de la case.
	 * @param numEquipe le numéro d'équipe du joueur.
	 */
	public void add(int a, int b, int numEquipe){
		if(numEquipe==1){
			jeuJ1b[a][b]=true;
			jeuJ1b[a+1][b-1]=true;
			jeuJ1b[a+1][b]=true;
			jeuJ1b[a+1][b+1]=true;
			jeuJ1b[a-1][b-1]=true;
			jeuJ1b[a-1][b]=true;
			jeuJ1b[a-1][b+1]=true;
			jeuJ1b[a][b+1]=true;
			jeuJ1b[a][b-1]=true;
		} else {
			jeuJ2b[a][b]=true;
			jeuJ2b[a+1][b-1]=true;
			jeuJ2b[a+1][b]=true;
			jeuJ2b[a+1][b+1]=true;
			jeuJ2b[a-1][b-1]=true;
			jeuJ2b[a-1][b]=true;
			jeuJ2b[a-1][b+1]=true;
			jeuJ2b[a][b+1]=true;
			jeuJ2b[a][b-1]=true;;
		}
	}
	
	/**
	 * Méthode permettant de mettre à jour la vision des joueurs.
	 * Elle agit aussi sur la visibilité ou non du trésor et des pièges.
	 */
	public void update(){
		jeu=monIle.getIleTab();
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				if(jeuJ1b[c][l]==true) {
					jeuJ1[c][l]=jeu[c][l];
				} else {
					jeuJ1[c][l]=0;
				}
				if(jeuJ2b[c][l]==true) {
					jeuJ2[c][l]=jeu[c][l];
				} else {
					jeuJ2[c][l]=0;
				}
			}
		}
		
		boolean tresorJ1=monIle.getEquipe(1).tresorVisible();
		boolean tresorJ2=monIle.getEquipe(2).tresorVisible();
		if (jeuJ1b[coordTresor[0]][coordTresor[1]] && tresorJ1){
			jeuJ1[coordTresor[0]][coordTresor[1]]=8; //coffre
		} else if (jeuJ1b[coordTresor[0]][coordTresor[1]] && !tresorJ1){
			jeuJ1[coordTresor[0]][coordTresor[1]]=((ParcelleRocher)monIle.getParcelle(coordTresor[0], coordTresor[1])).getValeurIni();
		}
		if (jeuJ2b[coordTresor[0]][coordTresor[1]] && tresorJ2){
			jeuJ2[coordTresor[0]][coordTresor[1]]=8; //coffre
		} else if (jeuJ2b[coordTresor[0]][coordTresor[1]] && !tresorJ2){
			jeuJ2[coordTresor[0]][coordTresor[1]]=((ParcelleRocher)monIle.getParcelle(coordTresor[0], coordTresor[1])).getValeurIni();
		}
		
		//pieges visibles ou non
		if(pieges1.size()>0) {
			for(int idx=0; idx<pieges1.size(); idx++){
				//visible par défaut, donc rendre invisible pour l'adversaire
				int x=pieges1.get(idx).getX();
				int y=pieges1.get(idx).getY();
				if(jeuJ2b[x][y]){
					jeuJ2[x][y]=1; //sable
				}
			}
		} else if (pieges2.size()>0) {
			for(int idx=0; idx<pieges2.size(); idx++){
				//visible par défaut, donc rendre invisible pour l'adversaire
				int x=pieges2.get(idx).getX();
				int y=pieges2.get(idx).getY();
				if(jeuJ1b[x][y]){
					jeuJ1[x][y]=1; //sable
				}
			}
		}
		
		plateaux[0].setJeu(jeuJ1);
		plateaux[1].setJeu(jeuJ2);
	}
	
	/**
	 * Méthode permettant d'afficher les plateaux.
	 */
	public void affichage(){
		plateaux[0].affichage();
		plateaux[1].affichage();
	}

	/**
	 * Méthode permettant à un joueur d'effectuer une action.
	 * @param i le numéro du plateau (donc le numéro du joueur -1).
	 */
	public void action(int i){
		InputEvent event ;
		int x = 0,y = 0,a = 0,b =0;
		boolean action = false;
		Aleatoire alea = new Aleatoire();
		int chance = 0;
		int keyCode = 0;

		plateaux[i].affichage();
		plateaux[i].println(">> A votre tour J" + (i+1)) ;
		plateaux[1-i].println(">> Au tour de votre adversaire") ;
		
		// Vérification de la selection : doit être un personnage ou navire de son équipe
    	do {
	    	event=  plateaux[i].waitEvent();
	    	while(!(event instanceof MouseEvent)){
	    		event=  plateaux[i].waitEvent();
	    	}
		 	x = plateaux[i].getX((MouseEvent) event) ;
		   	y = plateaux[i].getY((MouseEvent) event) ;
	    } while(!((getParcelle(x, y) instanceof Personnage || (getParcelle(x,y) instanceof ParcelleNavire && ((ParcelleNavire)getParcelle(x,y)).getNbPersonnage()!=0)) && getParcelle(x, y).getValeur()%2==i ));
    	
    	//Actions si navire
    	if(getParcelle(x,y) instanceof ParcelleNavire && getParcelle(x, y).getValeur()%2==i && ((ParcelleNavire)getParcelle(x,y)).getNbPersonnage()!=0){ 
    		plateaux[i].println("Vous avez choisis votre navire");
    		
    		highlight(i, x, y);
    		
    		Personnage p;
    		if (((ParcelleNavire)getParcelle(x,y)).getNbPersonnage()==1){
    			p=((ParcelleNavire)getParcelle(x,y)).getPersonnage(0);
    			plateaux[i].println("Seul le personnage "+p.getNom()+" est dans le navire, il a "+p.getEnergie()+" energie.");
    		} else {
    			String[] choix = new String[((ParcelleNavire)getParcelle(x,y)).getNbPersonnage()];
    			for(int j=0; j<choix.length; j++) {
    				choix[j]=((ParcelleNavire)getParcelle(x,y)).getPersonnage(j).getNom();
    			}
    		    String nom = (String)JOptionPane.showInputDialog(null, "Quel personnage voulez-vous sortir ?", "Equipage du navire",
    		    		JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
    		    p=((ParcelleNavire)getParcelle(x,y)).getPersonnage(nom);
    		    plateaux[i].println("Vous avez selectionné le personnage "+p.getNom()+", il a "+p.getEnergie()+" energie.");
    		}
    		plateaux[i].println("Où souhaitez-vous le déplacer ?");
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			if(event instanceof KeyEvent){
    				keyCode = ((KeyEvent) event).getKeyCode() ;
    			}else if(event instanceof MouseEvent){
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
    			}
			    
				if (keyCode == 27) { 
					plateaux[i].println("Vous décidez de passer votre tour");
            		action = true ;
            	}
    			//sortie du personnage
    			if(getParcelle(a,b).estVide() && dansChampsAction(x, y, a, b, 4)){
    				setParcelle(a,b,p);
    				((ParcelleNavire)getParcelle(x,y)).removePersonnage(p);
    				if(getParcelle(x, y).getValeur()%2==0){
    					plateaux[0].setJeu(jeuJ1);
    				} else {
    					plateaux[1].setJeu(jeuJ2);
    				}
    				action = true;
    				//ajoute de la visibilité dans le brouillard
					add(a,b,i+1);
    			}
    		}
    	}
		    
    	//Actions si explorateur
    	if(getParcelle(x,y) instanceof Explorateur && getParcelle(x, y).getValeur()%2==i ){ 
    		plateaux[i].println("Vous avez choisis un explorateur de J"+(i+1)+", il a " + ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		
    		highlight(i, x, y);
    		
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			if(event instanceof KeyEvent){
    				keyCode = ((KeyEvent) event).getKeyCode() ;
    			}else if(event instanceof MouseEvent){
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
    			}
			    
				if (keyCode == 27) { 
					plateaux[i].println("Vous décidez de passer votre tour");
            		action = true ;
            	}
			    
    			//déplacement
    			if(getParcelle(a,b).terrainClair() && dansChampsAction(x, y, a, b, 4)){
    				deplacer(x, y, a, b, i);
    				action = true;
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 4)){
    				echangeItem(x, y, a, b, plateaux, i, false);
    				action = true;
    			//Soulève un rocher
    			} else if (rocherACote(x, y, a, b)){
    				plateaux[i].println("L'explorateur soulève un rocher");
    		    	event = plateaux[i].waitEvent(500) ;    				
					plateaux[i].println(((ParcelleRocher)getParcelle(a,b)).getMessage());
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-5);
    				if(((ParcelleRocher)getParcelle(a,b)).getTresor()){ //Si trésor
    					((ParcelleRocher)getParcelle(a, b)).visible();
						monIle.getEquipe(i+1).setTresor();
						//modification de l'affichage, affiche le coffre(réaffichage général dans la classe Jouer, juste après qu'une action est faite)
						if(i==0){
							jeuJ1[a][b]=8;//coffre
							plateaux[i].setJeu(jeuJ1);
						} else {
							jeuJ2[a][b]=8;//coffre
							plateaux[i].setJeu(jeuJ2);
						}
    					
    					if (((Personnage)getParcelle(x,y)).porteClef()){
    						((Explorateur)getParcelle(x,y)).setPorteTresor();
    						((ParcelleRocher)getParcelle(a,b)).setTresor(); //change le message du trésor
    						plateaux[i].println("Votre clef rentre parfaitement dans la serrure, vous l'ouvrez et vous emparez du trésor !");
    					} else {
    						plateaux[i].println("Malheureusement, vous n'avez pas la clef...");
    					}
    				} else if(((ParcelleRocher)getParcelle(a,b)).getClef()){ //Si clef
    					((ParcelleRocher)getParcelle(a, b)).visible();
    					//modification de l'affichage, affiche la clef (réaffichage général dans la classe Jouer, juste après qu'une action est faite)
						if(i==0){
							jeuJ1[a][b]=7;//clef
							plateaux[i].setJeu(jeuJ1);
						} else {
							jeuJ2[a][b]=7;//clef
							plateaux[i].setJeu(jeuJ2);
						}
    					((Explorateur)getParcelle(x,y)).setPorteClef();
    					((ParcelleRocher)getParcelle(a,b)).setClef(); //passe la clef à false et change le message
    					event = plateaux[i].waitEvent(1000);
    					((ParcelleRocher)getParcelle(a, b)).hide();
    				}
       		    	persoMort(x,y,plateaux,i);
    				action=true;
        		//Si trésor déjà trouvé, mais qu'on revient avec la clef
    			} else if (getParcelle(a,b).getValeur()==8 && dansChampsAction(x,y,a,b,4)){
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-5);
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
    				persoMort(x,y,plateaux,i);
    				action=true;
    			//Rentrer dans le bateau
    			} else if(getParcelle(a,b) instanceof ParcelleNavire && getParcelle(x, y).getValeur()%2==i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(monIle.getEquipe(i+1).nbPersonnages())){
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
    	if(getParcelle(x,y) instanceof Voleur && getParcelle(x, y).getValeur()%2==i ){ 
    		plateaux[i].println("Vous avez choisis un voleur de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		
    		highlight(i, x, y);
    		
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			if(event instanceof KeyEvent){
    				keyCode = ((KeyEvent) event).getKeyCode() ;
    			}else if(event instanceof MouseEvent){
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
    			}
			    
				if (keyCode == 27) { 
					plateaux[i].println("Vous décidez de passer votre tour");
            		action = true ;
            	}
			    	
    			//déplacement
    			if(getParcelle(a,b).terrainClair() && dansChampsAction(x, y, a, b, 8)){
    				deplacer(x, y, a, b, i);
    				action = true;
    			//Fouille un personnage
    			} else if(dansChampsAction(x,y,a,b,8) && getParcelle(a,b) instanceof Personnage && !personnageAllieACote(x, y, a, b, 8)){
    				plateaux[i].println("Vous fouillez un personnage...") ;
    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-10);
    				echangeItem(x, y, a, b, plateaux, i, true); //voler
    				persoMort(x,y,plateaux,i);
    				action = true;
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i, false);
    				action = true;
    			//Rentrer dans navire
    			} else if(getParcelle(a,b) instanceof ParcelleNavire && getParcelle(x, y).getValeur()%2==i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(monIle.getEquipe(i+1).nbPersonnages())){
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}  		
    	}
    	
    	
    	//Action si piegeur 
    	if(getParcelle(x,y) instanceof Piegeur && getParcelle(x, y).getValeur()%2==i ){ 
    		plateaux[i].println("Vous avez choisis un piegeur de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?");
    		plateaux[i].println("Mines restantes : " + ((Piegeur)getParcelle(x,y)).getMines()) ;
    		
    		highlight(i, x, y);
    		
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			if(event instanceof KeyEvent){
    				keyCode = ((KeyEvent) event).getKeyCode() ;
    			}else if(event instanceof MouseEvent){
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
    			}
			    
				if (keyCode == 27) { 
					plateaux[i].println("Vous décidez de passer votre tour");
            		action = true ;
            	}
			    	
    			//2 choix possible: déplacement ou piège
    			if(getParcelle(a,b).terrainClair() && dansChampsAction(x, y, a, b, 8)){
    				if(((Piegeur)getParcelle(x,y)).getMines()>0){
		    			String[] option = {"Déplacement" , "Poser un piege"};
		    			int choix = JOptionPane.showOptionDialog(null, "Choississez votre option",  null, JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, option, option[0]);
		    			//déplacement
		    			if(choix ==0){   	
		    				deplacer(x, y, a, b, i);
		    			//pose un piege	
		    			}else if(choix ==1){
		    				if(getParcelle(a,b).estVide()){
		    					setPiege(i+1,a,b);
		    					((Piegeur)getParcelle(x,y)).retirerMine();
		    					((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-5);
		    					persoMort(x,y,plateaux,i);
		    				} else {
		    					plateaux[i].println("Vous ne pouvez poser de piège, un objet s'y trouve.");
		    				}
		    			}
    				} else {
    					deplacer(x, y, a, b, i);
    				}
	    			action = true;  				
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i, false);
    				action = true;
    			//Rentrer dans navire
    			} else if(getParcelle(a,b) instanceof ParcelleNavire && getParcelle(x, y).getValeur()%2==i && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(monIle.getEquipe(i+1).nbPersonnages())){
    					((Piegeur)getParcelle(x,y)).setMinesFull();
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}    
    	}
    	
    	//Action si Guerrier
    	if(getParcelle(x,y) instanceof Guerrier && getParcelle(x, y).getValeur()%2==i ){ 
    		plateaux[i].println("Vous avez choisis un guerrier de J"+(i+1)+", il a "+ ((Personnage)getParcelle(x,y)).getEnergie() + " points d'energie, que souhaitez-vous faire ?") ;
    		
    		highlight(i, x, y);
    		
    		while(!action){
    			event=  plateaux[i].waitEvent();
    			while(!(event instanceof MouseEvent || event instanceof KeyEvent)){
        			event=  plateaux[i].waitEvent();
        		}
    			if(event instanceof KeyEvent){
    				keyCode = ((KeyEvent) event).getKeyCode() ;
    			}else if(event instanceof MouseEvent){
    			a = plateaux[i].getX((MouseEvent) event) ;
    			b = plateaux[i].getY((MouseEvent) event) ;
    			}
			    
				if (keyCode == 27) { 
					plateaux[i].println("Vous décidez de passer votre tour");
            		action = true ;
            	}
			    	
    			//déplacement
    			if(getParcelle(a,b).terrainClair() && dansChampsAction(x, y, a, b, 8)){
    				deplacer(x, y, a, b, i);
    				action = true;
    			//attaque un ennemi
    			} else if(dansChampsAction(x,y,a,b,8) && getParcelle(a,b) instanceof Personnage && !personnageAllieACote(x, y, a, b, 8)){
    				if(!((Personnage)getParcelle(x,y)).porteEpee()){
    					plateaux[i].println("Vous ne pouvez pas l'attaquer, vous n'avez plus votre épée.") ;
    				} else {
    					//attaque seulement s'il a une épée
	    				chance = alea.tirage(2);
	    				plateaux[i].println("Vous attaquez un personnage...") ;
	    				plateaux[1-i].println("On vous attaque !") ;
	    				((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-10);
	    				if(chance==0){
	    					//dégats aléatoire entre 1 et 50 
		    				int degats=alea.tirage(50)+1;
		    				plateaux[i].println("Et vous lui infliger " + degats + " points de dégâts !!") ;
		    				plateaux[1-i].println("Vous avez perdu " + degats + " points d'énergie...") ;
		    				((Guerrier)getParcelle(x,y)).attaqueEnnemi(((Personnage)getParcelle(a,b)), degats); 
		    				persoMort(a,b,plateaux,1-i);
	    				}else{
	    					plateaux[i].println("Mais vous manquez votre cible...") ;
	    					plateaux[1-i].println("Vous avez esquivé.") ;
	    				}
	    				persoMort(x,y,plateaux,i);
    				}
    				action = true;    				
    			//Echange avec un personnage	
    			}else if(personnageAllieACote(x, y, a, b, 8)){
    				echangeItem(x, y, a, b, plateaux, i, false);
    				action = true;
    			//Rentrer dans navire
    			} else if(getParcelle(a,b) instanceof ParcelleNavire && getParcelle(x, y).getValeur()%2==i  && dansChampsAction(a,b,x,y,8)){
    				if (((ParcelleNavire)getParcelle(a,b)).peutMonterABord(monIle.getEquipe(i+1).nbPersonnages())){
    					((Guerrier)getParcelle(x,y)).ramasseEpee();
    					rentrerDansNavire(x,y,a,b,plateaux,i);
    					action=true;
    				} else {
    					plateaux[i].println("Vous ne pouvez pas rentrer dans le navire, vous etes le dernier personnage sur l'île."
    							+ "\n Choississez une autre action.");
    				}
    			}
    		}  
    	}
    	//Recupération d'énergie pour les personnages dans le navire
    	int[] coordNavire= monIle.getNavire(i+1);
    	for(int p=0; p<((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getNbPersonnage(); p++){
    		((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getPersonnage(p).setEnergie(((ParcelleNavire)getParcelle(coordNavire[0],coordNavire[1])).getPersonnage(p).getEnergie()+10);
    	}
    	update();
		event = plateaux[i].waitEvent(500);	// Délai pour permettre la lecture.
	}

	/**
	 * Methode qui place un piege selon les coordonnée donnée et selon l'équipe du joueur
	 * @param numEquipe un entier 1 ou 2
	 * @param a un entier (coordonnée x)
	 * @param b un entier (coordonnée y)
	 */
	public void setPiege(int numEquipe, int a, int b){
		ParcellePiege piege=new ParcellePiege(numEquipe, a, b);
		setParcelle(a,b, piege);
		if(numEquipe==1){
			pieges1.add(piege);
		} else {
			pieges2.add(piege);
		}
	}
	
	/**
	 * Méthode permettant de déplacer le personnage de coordonnées (x,y) à la case de coordonnées (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param i le numéro du plateau courrant
	 */
	public void deplacer(int x, int y, int a, int b, int i){
		if(getParcelle(a,b) instanceof ParcellePiege){
			plateaux[i].println("Déplacement effectué...");
			((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-30);
			plateaux[i].println("Vous être pris dans un piege ! Vous perdez 30 points d'énergie.");
			plateaux[((ParcellePiege)getParcelle(a,b)).getNumEquipe()-1].println("Quelqu'un a marché dans votre piège !") ;
			ParcellePiege piege=(ParcellePiege) getParcelle(a,b);
			if(piege.getNumEquipe()==1){
				pieges1.remove(piege);
			} else {
				pieges2.remove(piege);
			}
			setParcelle(a,b, getParcelle(x,y));
			setParcelle(x,y, new Parcelle());
			//ajoute de la visibilité dans le brouillard
			add(a,b,i+1);
			persoMort(a,b,plateaux, i);
		}else{
			((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-1);
			if(getParcelle(a,b).getValeur()==7){
				((Personnage)getParcelle(x,y)).ramasseClef();
				plateaux[i].println("Vous avez ramassé la clef.");
				setParcelle(a,b, new Parcelle());
				persoMort(x,y,plateaux, i);	
			} else if(getParcelle(a,b).getValeur()==10){
				((Personnage)getParcelle(x,y)).ramasseTresor();
				plateaux[i].println("Vous avez ramassé le trésor.");
				setParcelle(a,b, new Parcelle());
				persoMort(x,y,plateaux, i);	
			} else if (getParcelle(a,b).getValeur()==11){
				((Personnage)getParcelle(x,y)).ramasseEpee();
				plateaux[i].println("Vous avez ramassé une épée.");
				setParcelle(a,b, new Parcelle());
				persoMort(x,y,plateaux, i);	
			} else {
				plateaux[i].println("Déplacement effectué...");
				echangeParcelles(x, y, a, b);
				//ajoute de la visibilité dans le brouillard
				add(a,b,i+1);
				persoMort(a,b,plateaux, i);	
			}
		}
	}
	
	/**
	 * Méthode retournant la parcelle de coordonnée x,y.
	 * @param x un entier.
	 * @param y un entier.
	 * @return la parcelle correspondante.
	 */
	public Parcelle getParcelle(int x, int y){
		return monIle.getParcelle(x, y);
	}
	
	/**
	 * Méthode permettant de remplacer une parcelle par une autre.
	 * @param x la coordonnée x.
	 * @param y la coordonnée y.
	 * @param p la nouvelle parcelle.
	 */
	public void setParcelle(int x, int y, Parcelle p){
		monIle.setParcelle(x, y, p);
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
		return monIle.dansChampsAction(x, y, a, b, nbDirections);
	}
	
	/**
	 * Méthode permetant d'échanger de position la parcelle de coordonnée a,b avec celle de coordonnée x,y.
	 * @param x un entier.
	 * @param y un entier.
	 * @param a un entier.
	 * @param b un entier.
	 */
	public void echangeParcelles(int x, int y, int a, int b){
		 monIle.echangeParcelles(x, y, a, b);
		
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
	public void rentrerDansNavire(int x, int y, int a, int b, SuperPlateau[] plateaux, int i){
		monIle.rentrerDansNavire(x, y, a, b, plateaux, i);
	}
	
	/**
	 * Méthode permettant d'échanger un item (donner ou prendre) selon un ordre d'importance (trésor puis clef) entre le personnage (x,y) et le personnage (a,b).
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau courrant
	 * @param vole booléen permettant de savoir s'il s'agit d'un vol ou d'un échange
	 */
	public void echangeItem(int x, int y, int a, int b, SuperPlateau[] plateaux, int i, boolean vole){
		monIle.echangeItem(x, y, a, b, plateaux, i, vole);
	}

	/**
	 * Methode permettant de supprimer un personnage de coord (x,y) si celui-ci est mort
	 * @param x un entier
	 * @param y un entiter
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau
	 */
	public void persoMort(int x, int y, SuperPlateau[] plateaux, int i){
		monIle.persoMort(x, y, plateaux, i);
	}
	
	/**
	 * Methode qui renvoi un booleen pour savoir si un personnage de coord (x,y) se situe a coté d'un rocher de coord(a,b)
	 * @param x un entier
	 * @param y un entier
	 * @param a un entier
	 * @param b un entier
	 * @return un booleen
	 */
	public boolean rocherACote(int x, int y, int a, int b){
		return monIle.rocherACote(x, y, a, b);
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
		return monIle.personnageACote(x, y, a, b, nbDirections);
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
		return monIle.personnageAllieACote(x, y, a, b, nbDirections);
	}
	
	/**
	 * Méthode permettant de mettre la case courante en surbrillance (couleur vert) ainsi que les cases autours en fonction des différentes actions possible pour le personnage de la case courante (couleur bleu).
	 * @param i
	 * @param x
	 * @param y
	 */
	public void highlight(int i, int x, int y){
		Color c=Color.GREEN;
		//highlight de la case selectionnée
		plateaux[i].setHighlight(y, x,c);
		
		//highlight des déplacements/actions possibles
		c=Color.BLUE;
		int[] valeursDeplacable;
		int directions=8;
		if(getParcelle(x,y) instanceof ParcelleNavire){
			valeursDeplacable=new int[]{1,7,9,10,11}; //sable, clef, piege, trésor, épée
			directions=4;
		} else if(getParcelle(x,y) instanceof Explorateur){
			valeursDeplacable=new int[]{1,3,4,5,7,8,9,10,11,12+i,14+i,16+i,18+i,20+i}; //sable, rochers, clef, coffre, piege, trésor, épée, navire allié et alliés
			directions=4;
		} else if(getParcelle(x,y) instanceof Voleur || getParcelle(x,y) instanceof Guerrier){
			valeursDeplacable=new int[]{1,7,9,10,11,12+i,14,15,16,17,18,19,20,21}; //sable, clef, piege, trésor, épée, navire allié et tout les personnages
		} else if(getParcelle(x,y) instanceof Piegeur){
			valeursDeplacable=new int[]{1,7,9,10,11,12+i,14+i,16+i,18+i,20+i}; //sable, clef, piege, trésor, épée, navire allié et alliés
		} else {
			valeursDeplacable=new int[]{};
		}
		for(int j=0; j<valeursDeplacable.length; j++){
			if (getParcelle(x+1,y).getValeur()==valeursDeplacable[j]){
				plateaux[i].setHighlight(y,x+1,c);
			}
			if (getParcelle(x-1,y).getValeur()==valeursDeplacable[j]){
				plateaux[i].setHighlight(y,x-1,c);
			}
			if (getParcelle(x,y+1).getValeur()==valeursDeplacable[j]){
				plateaux[i].setHighlight(y+1,x,c);
			}
			if (getParcelle(x,y-1).getValeur()==valeursDeplacable[j]){
				plateaux[i].setHighlight(y-1,x,c);
			}
			if(directions==8){
				if (getParcelle(x+1,y+1).getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(y+1,x+1,c);
				}
				if (getParcelle(x+1,y-1).getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(y-1,x+1,c);
				}
				if (getParcelle(x-1,y+1).getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(y+1,x-1,c);
				}
				if (getParcelle(x-1,y-1).getValeur()==valeursDeplacable[j]){
					plateaux[i].setHighlight(y-1,x-1,c);
				}
			}
		}
	}
	
	/**
	 * Détermine le titre de la fenetre associée.
	 * @param title Le titre à afficher.
	 */
	public void setTitle(String title) {
		plateaux[0].setTitle(title);
		plateaux[1].setTitle(title);
	}
}