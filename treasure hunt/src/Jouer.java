
import javax.swing.JOptionPane;

/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer  {


	public static void main(String[] args) {
		

		JOptionPane j2 = new JOptionPane();
		String menu =j2.showInputDialog(null, "1- Jouer\n2- Quitter");
		while(!(menu.matches("[1-2]") && Integer.parseInt(menu)>= 1 && Integer.parseInt(menu)<= 2)){
			j2.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
			menu =j2.showInputDialog(null, "1- Jouer\n2- Option");
			
		}
		if(Integer.parseInt(menu)== 1){
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
		int[][] jeu=monIle.getIleTab();
		
		SuperPlateau[] plateaux=new SuperPlateau[2];
		plateaux[0]=new SuperPlateau(IMGS,10);
		plateaux[1]=new SuperPlateau(IMGS,10);
		plateaux[0].setJeu(jeu);
		plateaux[1].setJeu(jeu);
		plateaux[0].affichage();
		plateaux[1].affichage();
		
		//DEBUT BROUILLARD DE GUERRE
		/*
		GestionPlateaux gestion=new GestionPlateaux(monIle);
		gestion.initialiser();
		gestion.affichageJ1();
		gestion.affichageJ2();*/
		
		//affichage texte
		//System.out.println(monIle);
		}
	}
}
