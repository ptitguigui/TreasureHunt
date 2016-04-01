
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer  {


	public static void main(String[] args) {
		
		Object[] option = {"Jouer" , "Quitter"};
		int menu = JOptionPane.showOptionDialog(null, "Bienvenue, que voulez-vous faire ?", "Treasure hunt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
		
		if(menu== 0){
		JOptionPane.showMessageDialog(null, "Lancement du jeu");
		
		final String[] IMGS={"treasure hunt/images/sable.jpg",
				"treasure hunt/images/mer.png",
				"treasure hunt/images/rocher.png.jpg",
				"treasure hunt/images/cle.png",
				"treasure hunt/images/coffre.png.jpg",
				"treasure hunt/images/arbre.png",
				"treasure hunt/images/1.navire.png",
				"treasure hunt/images/2.navire.png",
				"treasure hunt/images/1.explorateur.png",
				"treasure hunt/images/2.explorateur.png",
				"treasure hunt/images/1.piegeur.png",
				"treasure hunt/images/2.piegeur.png"};
		
		//demande de la taille
		//nombre de colonnes
		String rep=new String(JOptionPane.showInputDialog(null,"Entrer la taille x du plateau (entre 10 et 20) :"));
		//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
			rep=JOptionPane.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int nbColonnes=Integer.parseInt(rep);
		//nombre de lignes
		rep=new String(JOptionPane.showInputDialog(null,"Entrer la taille y du plateau (entre 10 et 20) :"));
		//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
			JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
			rep=JOptionPane.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int nbLignes=Integer.parseInt(rep);
		
		
		//demande du pourcentage de rocher
		rep=new String(JOptionPane.showInputDialog(null,"Entrer le pourcentage de rocher :"));
		//Tant que la saisie soit un chiffre et que le nombre de rochers soit >=3 et que'il soit <20% 
		while(!(rep.matches("[1-9][0-9]*")   
				&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)>=3 
				&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)<(nbColonnes-2)*(nbLignes-2)*0.2-1)){
				JOptionPane.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
				rep=JOptionPane.showInputDialog(null,"Entrer le pourcentage de rocher :");
		}
		int pourcentage=Integer.parseInt(rep);
		
		//demande nombres d'explorateur pour les différentes équipes
		rep=new String(JOptionPane.showInputDialog(null,"Entrer le nombre d'explorateur(s) :"));
				while(!(rep.matches("[1-9][0-9]*")   
						&& (int) Integer.parseInt(rep)>0 
						&& (int)Integer.parseInt(rep)<5)){
						JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
						rep=JOptionPane.showInputDialog(null,"Entrer le nombre d'explorateur(s) :");
				}
		int nbExplo=Integer.parseInt(rep);
		
		//demande nombres de voleur pour les différentes équipes
		rep=new String(JOptionPane.showInputDialog(null,"Entrer le nombre de voleur(s) :"));
				while(!(rep.matches("[1-9][0-9]*")   
						&& (int) Integer.parseInt(rep)>0 
						&& (int)Integer.parseInt(rep)<5)){
						JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
						rep=JOptionPane.showInputDialog(null,"Entrer le nombre de voleur(s) :");
				}
		int nbVoleurs=Integer.parseInt(rep);
		
		//Création de l'ile				
		Ile monIle = new Ile(nbColonnes,nbLignes);
		monIle.initialiser(pourcentage);
		monIle.setPersonnages(1, nbExplo, nbVoleurs);
		monIle.setPersonnages(2, nbExplo, nbVoleurs);
		int[][] jeu=monIle.getIleTab();
		
		SuperPlateau[] plateaux=new SuperPlateau[2];
		plateaux[0]=new SuperPlateau(IMGS,10,true);
		plateaux[1]=new SuperPlateau(IMGS,10,true);
		plateaux[0].setJeu(jeu);
		plateaux[1].setJeu(jeu);
		plateaux[0].affichage();
		plateaux[1].affichage();
		
		//affichage texte
				System.out.println(monIle);
		
		//déplacement
		while(true){		        		        
			for(int i=0; i<2; i++){
				monIle.action(plateaux, i);
			}
		}
		 		
		}
		
		//DEBUT BROUILLARD DE GUERRE
		/*
		GestionPlateaux gestion=new GestionPlateaux(monIle);
		gestion.initialiser();
		gestion.affichageJ1();
		gestion.affichageJ2();*/
		
	}
}


