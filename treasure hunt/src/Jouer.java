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
		Ile monIle = new Ile(nbColonnes,nbLignes);
		monIle.initialiser(pourcentage, nbPersonnages);
		
		GestionPlateaux gestion=new GestionPlateaux(monIle);
		
		//initialisation des tableaux des joueurs = aucune visibilité
		boolean[][]jeuJ1b=new boolean[nbColonnes][nbLignes];
		boolean[][]jeuJ2b=new boolean[nbColonnes][nbLignes];
		for(int c=0; c<nbColonnes; c++) {
			for(int l=0; l<nbLignes; l++){
				jeuJ1b[c][l]=false;
				jeuJ2b[c][l]=false;
			}
		}
		int[][]navires=monIle.getNavires();
		jeuJ1b[navires[0][0]][navires[0][1]]=true;
		jeuJ2b[navires[1][0]][navires[1][1]]=true;
		
		//tableaux de int des joueurs, créant une "vue" de l'ile, en fonction de ce qu'ils ont déjà visité.
		int[][]jeuJ1=new int[nbColonnes][nbLignes];
		int[][]jeuJ2=new int[nbColonnes][nbLignes];
		for(int c=0; c<nbColonnes; c++) {
			for(int l=0; l<nbLignes; l++){
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
		
		grille[0].setJeu(jeuJ1);
		grille[1].setJeu(jeuJ2);
		
		//affichage texte
		//System.out.println(monIle);
		
		grille[0].affichage();
		grille[1].affichage();
	}

}
