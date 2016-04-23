import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	 * Attribut correspondant aux deux équipes.
	 */
	private Equipe[] equipes=new Equipe[2];
	/**
	 * Attribut permettant de connaitre la position du trésor.
	 */
	private int[] coordTresor=new int[2];
	/**
	 * Attribut mémorisant le navire du joueur 1 (et donc ses coordonnées aussi).
	 */
	private ParcelleNavire N1;
	/**
	 * Attribut mémorisant le navire du joueur 2 (et donc ses coordonnées aussi).
	 */
	private ParcelleNavire N2;
	
	
	
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
		equipes[0]=new Equipe(1);
		equipes[1]=new Equipe(2);
	}
	
	/**
	 * Méthode retournant une Equipe.
	 * @param numEquipe le numéro d'équipe (1 ou 2).
	 * @return une Equipe.
	 */
	public Equipe getEquipe(int numEquipe){
		return equipes[numEquipe-1];
	}
	
	/**
	 * Methode permettant de supprimer un personnage de coord (x,y) si celui-ci est mort
	 * @param x un entier
	 * @param y un entiter
	 * @param plateaux les plateaux des deux joueurs
	 * @param i le numéro du plateau
	 */
	public void persoMort(int x, int y, SuperPlateau[] plateaux, int i){
		if(((Personnage)getParcelle(x,y)).estMort()){
			equipes[((Personnage)getParcelle(x,y)).getNumEquipe()-1].removePersonnage(((Personnage)getParcelle(x,y)));
			if(((Personnage)getParcelle(x,y)).porteTresor() || ((Personnage)getParcelle(x,y)).porteClef() || ((Personnage)getParcelle(x,y)).porteEpee()){
				int aleaX=alea.tirage(3)-1; // -1, 0 ou 1
				int aleaY=alea.tirage(3)-1;
				//a les 3
				if (((Personnage)getParcelle(x,y)).porteTresor() && ((Personnage)getParcelle(x,y)).porteClef() && ((Personnage)getParcelle(x,y)).porteEpee()){
					//place le trésor
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(10);
					//place l'épée
					if(nbVoisinsVide(x,y,8)>=1){ //s'il y a au moins une case vide, place l'épée, sinon tant pis. (évite la boucle infinie)
						while (!getParcelle(x+aleaX,y+aleaY).estVide()){
							aleaX=alea.tirage(3)-1;
							aleaY=alea.tirage(3)-1;
						}
						getParcelle(x+aleaX,y+aleaY).setValeur(11);
					}
					//place la clef
					if(nbVoisinsVide(x,y,8)>=1){
						while (!getParcelle(x+aleaX,y+aleaY).estVide()){
							aleaX=alea.tirage(3)-1;
							aleaY=alea.tirage(3)-1;
						}
						getParcelle(x+aleaX,y+aleaY).setValeur(7);
					}
				//a clef et trésor
				} else if (((Personnage)getParcelle(x,y)).porteTresor() && ((Personnage)getParcelle(x,y)).porteClef()){
					//placer trésor
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(10);
					//place la clef
					if(nbVoisinsVide(x,y,8)>=1){
						while (!getParcelle(x+aleaX,y+aleaY).estVide()){
							aleaX=alea.tirage(3)-1;
							aleaY=alea.tirage(3)-1;
						}
						getParcelle(x+aleaX,y+aleaY).setValeur(7);
					}
				//Si clef et épée
				} else if (((Personnage)getParcelle(x,y)).porteEpee() && ((Personnage)getParcelle(x,y)).porteClef()){
					//placer clef
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(7);
					//placer l'épée
					if(nbVoisinsVide(x,y,8)>=1){
						while (!getParcelle(x+aleaX,y+aleaY).estVide()){
							aleaX=alea.tirage(3)-1;
							aleaY=alea.tirage(3)-1;
						}
						getParcelle(x+aleaX,y+aleaY).setValeur(11);
					}
				//Si trésor et épée
				} else if(((Personnage)getParcelle(x,y)).porteEpee() && ((Personnage)getParcelle(x,y)).porteTresor()) {
					//placer trésor
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(10);
					//place l'épée
					if(nbVoisinsVide(x,y,8)>=1){
						while (!getParcelle(x+aleaX,y+aleaY).estVide()){
							aleaX=alea.tirage(3)-1;
							aleaY=alea.tirage(3)-1;
						}
						getParcelle(x+aleaX,y+aleaY).setValeur(11);		
					}
				//a trésor
				} else if(((Personnage)getParcelle(x,y)).porteTresor()){
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(10);
				//a clef
				} else if(((Personnage)getParcelle(x,y)).porteClef()){
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(7);
				//a épée
				} else {
					grille[x][y]= new Parcelle();
					getParcelle(x,y).setValeur(11);
				}
			} else {
				grille[x][y]= new Parcelle();
			}
			plateaux[i].println("Votre personnage meurt par manque d'énergie...");
			plateaux[1-i].println("Vous avez tué un personnage !");
			
		}
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
		Personnage receveur=(Personnage)getParcelle(x,y); //aussi égale au voleur
		Personnage donneur=(Personnage)getParcelle(a,b); // personnage 1volé
		if(!vole){
			if(donneur.getNbItems()==0 && receveur.getNbItems()==0){
				plateaux[i].println("Vos deux personnages discutent tranquillement, n'ayant aucun item à s'échanger...") ;
			} else {
				String[] options=new String[]{"1ère séléction", "2e séléction"};
				int rep = JOptionPane.showOptionDialog(null, "Choississez le personnage qui donnera un objet",  null, JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, options, options[0]);
				if(rep==0){ 
					donneur=receveur;
					receveur=(Personnage)getParcelle(a,b);
				}
			}
		}
		//si le donneur n'a rien
		if (!donneur.porteClef() && !donneur.porteTresor() && !donneur.porteEpee()){
			if (vole){ plateaux[i].println("Mais vous ne trouvez rien.") ; }
			else { plateaux[i].println("Vos deux personnages discutent tranquillement,\ncar le personnage choisi n'a aucun item à échanger...") ; }
		//S'il porte quelque chose
		} else {
			int nbItems=donneur.getNbItems();
			int choix=0;
			if (nbItems==1){
				if(donneur.porteClef()){
					choix=0;
				}else if(donneur.porteTresor()){
					choix=1; 						
				} else if(donneur.porteEpee()){
					choix=2;
				}
			} else {
				ArrayList<String> itemsListe=new ArrayList<>();
				String clef="Clef"; String tresor="Trésor"; String epee="Epée";
				if(donneur.porteClef()){
					itemsListe.add(clef);
				}
				if(donneur.porteTresor()){
					itemsListe.add(tresor);						
				}
				if(donneur.porteEpee()){
					itemsListe.add(epee);
				}
				String[] itemsTab = new String[itemsListe.size()];
				for(int j=0; j<itemsTab.length; j++) {
					itemsTab[j]=itemsListe.get(j);
				}
				String str;
				if(vole){ str="voler";}
				else{ str="échanger";}
				int rep = JOptionPane.showOptionDialog(null, "Choississez l'objet que vous souhaitez " + str,  null, JOptionPane.OK_OPTION, JOptionPane.NO_OPTION, null, itemsTab, itemsTab[0]);
				if(itemsTab[rep].equals(clef)){
					choix=0;
				} else if(itemsTab[rep].equals(tresor)){
					choix=1;
				} else if(itemsTab[rep].equals(epee)){
					choix=2;
				}
			}
			//Prend clef
			if(choix==0){  
				if(vole){
					((Voleur)getParcelle(x,y)).setVoleClef(((Personnage)getParcelle(a,b)));
					plateaux[i].println("Et vous lui volé la clé !") ;
					plateaux[1-i].println("On vous a volé la clé !") ;
			} else {
				donneur.donneItem(receveur, 0);
				plateaux[i].println("Vous échangez la clef.") ;
			}
			}
			//Prend trésor
			if(choix==1){ 
				if(vole){
					((Voleur)getParcelle(x,y)).setVoleTresor(((Personnage)getParcelle(a,b)));
					plateaux[i].println("Et vous lui volé le trésor !") ;
					plateaux[1-i].println("On vous a volé le trésor !") ;
				} else {
					donneur.donneItem(receveur, 1);
					plateaux[i].println("Vous échangez le trésor.") ;
				}
			}
			//Prend épée
			if(choix==2){ 
				if(vole){
					((Voleur)getParcelle(x,y)).setVoleEpee(((Personnage)getParcelle(a,b)));
					plateaux[i].println("Et vous lui volé son épée !") ;
					plateaux[1-i].println("On vous a volé votre épée !") ;
				} else {
					donneur.donneItem(receveur, 2);
					plateaux[i].println("Vous échangez l'épée.") ;
				}
			}
		}
	}
	
	/**
	 * Methode permettant de definir si la partie est finis ou non et de montrer qui a gagné
	 * @param plateaux les plateaux des deux joueurs
	 * @return un boolean (true or false)
	 */
	public boolean finPartie(SuperPlateau[] plateaux ){
		if(equipes[1].aPerdu() || equipes[0].aGagne()){
			plateaux[0].println("Felicitation J1, vous avez gagnez la partie") ;
			plateaux[1].println("Malheuresement vous avez perdu la partie") ;
			return true;
		}else if(equipes[0].aPerdu() || equipes[1].aGagne()){
			plateaux[1].println("Felicitation J2, vous avez gagnez la partie") ;
			plateaux[0].println("Malheuresement vous avez perdu la partie") ;
			return true;
		}else{
			return false;
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
	public void rentrerDansNavire(int x, int y, int a, int b, SuperPlateau[] plateaux, int i){
		if(getParcelle(x,y) instanceof Guerrier) {
			((Guerrier)getParcelle(x,y)).ramasseEpee();
		}
		if(((Personnage)getParcelle(x,y)).porteTresor()){
			equipes[i].gagne();
		}
		((Personnage)getParcelle(x,y)).setEnergie(((Personnage)getParcelle(x,y)).getEnergie()-1);
		((ParcelleNavire)getParcelle(a,b)).addPersonnage((Personnage)getParcelle(x,y));
		grille[x][y]=new Parcelle();
		plateaux[i].println("Vous êtes rentré dans votre navire");
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
	 * Méthode permettant de remplacer une parcelle par une autre.
	 * @param x la coordonnée x.
	 * @param y la coordonnée y.
	 * @param p la nouvelle parcelle.
	 */
	public void setParcelle(int x, int y, Parcelle p){
		grille[x][y]=p;
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
		return grille[a][b] instanceof ParcelleRocher && dansChampsAction(x,y,a,b,4); //4 car seul les explorateurs peuvent soulever.
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
		return (grille[a][b] instanceof Personnage)&& dansChampsAction(x, y, a, b, nbDirections);
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
			return getParcelle(x,y).getValeur()%2 == getParcelle(a,b).getValeur()%2;
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
	 * @param directions un entier permettant de savoir le nombre de directions vers lesquels le personnage peut se déplacer.
	 * @return le nombre de cases vides autour de la case correspondant aux coordonnées passées en paramètre.
	 */
	public int nbVoisinsVide(int x, int y, int directions){
		int nb=0;
		if(grille[x+1][y].estVide()){ nb += 1; }
		if(grille[x-1][y].estVide()){ nb += 1; }
		if(grille[x][y+1].estVide()){ nb += 1; }
		if(grille[x][y-1].estVide()){ nb += 1; }
		if(directions==8) {
			if(grille[x+1][y+1].estVide()){	nb += 1; }
			if(grille[x+1][y-1].estVide()){ nb += 1; }
			if(grille[x-1][y+1].estVide()){ nb += 1; }
			if(grille[x-1][y-1].estVide()){	nb += 1; }
		}
		return nb;
	}
	
	/**
	 * Méthode plaçant les explorateurs sur l'ile , le nombre d'explorateurs correspond à l'entier précisé en paramètre.
	 * @param test un booléen permettant de savoir s'il s'agit d'un test ou non
	 * @param nbExplorateurs, le nombre d'explorateurs dans l'équipe
	 * @param numEquipe, le numero de l'équipe où l'explorateur est situé
	 */
	private void setExplorateurs(boolean test, int nbExplorateurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbExplorateurs; i++){
			if(test){
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
			} else {
				int[] coordN=getNavire(numEquipe);
				Personnage p=new Explorateur("Explorateur", numEquipe);
				((ParcelleNavire)grille[coordN[0]][coordN[1]]).addPersonnage(p);
				if(numEquipe==1){
					equipes[0].addPersonnage(p);
				} else {
					equipes[1].addPersonnage(p);
				}
			}
		}
	}
	
	/**
	 *  Méthode plaçant les voleurs sur l'ile , le nombre de voleurs correspond à l'entier précisé en paramètre.
	 * @param test un booléen permettant de savoir s'il s'agit d'un test ou non
	 * @param nbVoleurs, le nombre de voleurs dans l'équipe
	 * @param numEquipe, le numero de l'équipe où le voleur est situé
	 */
	private void setVoleurs(boolean test, int nbVoleurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbVoleurs; i++){
			if(test) {	
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
			} else {
				int[] coordN=getNavire(numEquipe);
				Personnage p=new Voleur("Voleur", numEquipe);
				((ParcelleNavire)grille[coordN[0]][coordN[1]]).addPersonnage(p);
				if(numEquipe==1){
					equipes[0].addPersonnage(p);
				} else {
					equipes[1].addPersonnage(p);
				}
			}
		}
	}
	/**
	 *  Méthode plaçant les piegeurs sur l'ile , le nombre de piegeurs correspond à l'entier précisé en paramètre.
	 * @param test un booléen permettant de savoir s'il s'agit d'un test ou non
	 * @param nbPiegeurs, le nombre de piegeur dans l'équipe
	 * @param numEquipe, le numero de l'équipe où le piegeur est situé
	 */
	private void setPiegeurs(boolean test, int nbPiegeurs, int numEquipe){
		int x, y;
		for(int i=1; i<=nbPiegeurs; i++){
			if (test){
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
			} else {
				int[] coordN=getNavire(numEquipe);
				Personnage p=new Piegeur("Piegeur", numEquipe);
				((ParcelleNavire)grille[coordN[0]][coordN[1]]).addPersonnage(p);
				if(numEquipe==1){
					equipes[0].addPersonnage(p);
				} else {
					equipes[1].addPersonnage(p);
				}
			}
		}
	}
	/**
	 *  Méthode plaçant les guerriers sur l'ile , le nombre de guerriers correspond à l'entier précisé en paramètre.
	 * @param test un booléen permettant de savoir s'il s'agit d'un test ou non
	 * @param nbGuerriers, le nombre de guerriers dans l'équipe
	 * @param numEquipe, le numero de l'équipe où le piegeur est situé
	 */
	private void setGuerriers(boolean test, int nbGuerriers, int numEquipe){
		int x, y;
		for(int i=1; i<=nbGuerriers; i++){
			if (test) {	
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
							
				grille[x][y]=new Guerrier("Guerrier", numEquipe);
			} else {
				int[] coordN=getNavire(numEquipe);
				Personnage p=new Guerrier("Guerrier", numEquipe);
				((ParcelleNavire)grille[coordN[0]][coordN[1]]).addPersonnage(p);
				if(numEquipe==1){
					equipes[0].addPersonnage(p);
				} else {
					equipes[1].addPersonnage(p);
				}
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
			grille[grille.length-1][l]=new ParcelleMer();
			grille[c][0]=new ParcelleMer();
			grille[c][grille[0].length-1]=new ParcelleMer();
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
			while(!(grille[x][y].estVide() && nbVoisinsVide(x, y, 8)>6));
			grille[x][y]=new ParcelleRocher();
		}
	}
	
	/**
	 * Methode plaçant les arbres de façon aléatoire 
	 */
	private void setArbres(){
		int x,y;
		for(int i=0; i<(int)((grille.length-2)*(grille[0].length-2)*(5/100.00)); i++) {
			do {
				x= alea.tirage(grille.length-2)+1;
				y= alea.tirage(grille[0].length-2)+1;
			}
			while(!(grille[x][y].estVide() && nbVoisinsVide(x, y, 8)>6));
			grille[x][y]=new ParcelleArbre();
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
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y, 8)==5));
		N1=new ParcelleNavire(1, x, y);
		grille[x][y]=N1;
		
		//placement du 2e navire
		do {
			x= alea.tirage(grille.length-2)+1;
			y= alea.tirage(2);
			if (y==0){ y=grille[0].length-2;}
		}
		while(!(grille[x][y].estVide()&& nbVoisinsVide(x, y, 8)==5));
		N2=new ParcelleNavire(2, x ,y);
		grille[x][y]=N2;
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
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y, 8)>7));
		grille[x][y]=new ParcelleRocher();
		((ParcelleRocher)grille[x][y]).setTresor();
		coordTresor[0]=x;
		coordTresor[1]=y;
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
		while(!(grille[x][y].estVide() && nbVoisinsVide(x, y, 8)>7));
		grille[x][y]=new ParcelleRocher();
		((ParcelleRocher)grille[x][y]).setClef();
	}
	
	/**
	 * Méthode retournant les coordonnées du navire d'un joueur.
	 * @param numEquipe numéro du joueur.
	 * @return les coordonnées du navire sous forme d'un tableau int[]
	 */
	public int[] getNavire(int numEquipe){
		if(numEquipe==1){
			return new int[]{N1.getX(), N1.getY()};
		} else {
			return new int[]{N2.getX(), N2.getY()};
		}
	}
	
	/**
	 * Méthode retournant les coordonnées du trésor.
	 * @return les coordonnées du trésor sous forme d'un tableau int[].
	 */
		
	public int[] getTresor() {
		return coordTresor;
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
		setArbres();
	}	
	
	/**
	 * Méthode permettant de placer les personnages sur l'ile.
	 * @param numEquipe numéro du joueur.
	 * @param test un booléen permettant de savoir s'il s'agit d'un test ou non
	 * @param nbExplorateurs nombre d'explorateur voulu.
	 * @param nbVoleurs nombre de voleurs voulu.
	 * @param nbPiegeurs nombre de piegeurs voulu.
	 * @param nbGuerriers nombre de guerriers voulu.
	 */
	public void setPersonnages(int numEquipe, boolean test, int nbExplorateurs, int nbVoleurs, int nbPiegeurs, int nbGuerriers){
		if(numEquipe>0 && numEquipe<3) {
			setExplorateurs(test, nbExplorateurs, numEquipe);
			setVoleurs(test, nbVoleurs, numEquipe);
			setPiegeurs(test, nbPiegeurs, numEquipe);
			setGuerriers(test, nbGuerriers, numEquipe);
		}
	}
	
}
