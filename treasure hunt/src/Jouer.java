import javax.swing.JOptionPane;

/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer {

	public static void main(String[] args) {
		Menu lancer = new Menu();
		
		String[] imgs={"treasure hunt/images/mer.png",
				"treasure hunt/images/rocher.png.jpg",
				"treasure hunt/images/1.navire.png",
				"treasure hunt/images/2.navire.png",
				"treasure hunt/images/cle.png",
				"treasure hunt/images/coffre.png.jpg",
				"treasure hunt/images/1.explorateur.png",
				"treasure hunt/images/2.explorateur.png"};
		
		
		JOptionPane j=new JOptionPane();
		//demande de la taille
			//nombre de colonnes
		String rep=new String(j.showInputDialog(null,"Entrer la taille x du plateau (entre 10 et 20) :"));
		//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				j.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int nbColonnes=Integer.parseInt(rep);
			//nombre de lignes
		rep=new String(j.showInputDialog(null,"Entrer la taille y du plateau (entre 10 et 20) :"));
		//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				j.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int nbLignes=Integer.parseInt(rep);
		
		
		//demande du pourcentage de rocher
		rep=new String(j.showInputDialog(null,"Entrer le pourcentage de rocher :"));
		//Tant que la saisie soit un chiffre et que le nombre de rochers soit >=3 et que'il soit <20% 
		while(!(rep.matches("[1-9][0-9]*")   
				&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)>=3 
				&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)<(nbColonnes-2)*(nbLignes-2)*0.2-1)){
				j.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer le pourcentage de rocher :");
		}
		int pourcentage=Integer.parseInt(rep);
		
		//demande nombres de personnage pour les différentes
		rep=new String(j.showInputDialog(null,"Entrer le nombre de personnage:"));
				//Tant que la saisie soit un chiffre et que le nombre de rochers soit >=3 et que'il soit <20% 
				while(!(rep.matches("[1-9][0-9]*")   
						&& (int) Integer.parseInt(rep)>0 
						&& (int)Integer.parseInt(rep)<5)){
						j.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
						rep=j.showInputDialog(null,"Entrer le nombre de personnage :");
				}
		int nbPersonnages=Integer.parseInt(rep);
		
		//Création de l'ile
		SuperPlateau[] grille = new SuperPlateau[2];
		grille[0]=new SuperPlateau(imgs,10);
		grille[0].close();
		grille[1]=new SuperPlateau(imgs,10);
		grille[1].close();
				
		Ile monIle = new Ile(nbColonnes,nbLignes);
		monIle.initialiser(pourcentage, nbPersonnages);
			//jeu=tableau omniscient
		int[][]jeu=monIle.getIleTab();
		
		//initialisation des tableaux des joueurs = aucune visibilité
		boolean[][]jeuJ1=new boolean[nbColonnes][nbLignes];
		boolean[][]jeuJ2=new boolean[nbColonnes][nbLignes];
		for(int c=0; c<nbColonnes; c++) {
			for(int l=0; l<nbLignes; l++){
				jeuJ1[c][l]=false;
				jeuJ2[c][l]=false;
			}
		}
		int[][]navires=monIle.getNavires();
		jeuJ1[navires[0][0]][navires[0][1]]=true;
		jeuJ2[navires[1][0]][navires[1][1]]=true;
		

		grille[0].setJeu(jeu);
		grille[1].setJeu(jeu);
		
		//affichage texte
		System.out.println(monIle);
		
		grille[0].affichage();
	}

}
