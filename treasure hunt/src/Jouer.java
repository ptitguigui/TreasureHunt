import javax.swing.JOptionPane;

/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * @author vitsem
 *
 */
public class Jouer {

	public static void main(String[] args) {
		String[] imgs={"treasure hunt/images/mer.png",
				"treasure hunt/images/rocher.png.jpg",
				"treasure hunt/images/1.navire.png",
				"treasure hunt/images/2.navire.png",
				"treasure hunt/images/cle.png",
				"treasure hunt/images/coffre.png.jpg"};
		
		
		JOptionPane j=new JOptionPane();
		//demande de la taille
			//nombre de colonnes
		String rep=new String(j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :"));
		//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				j.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int nbColonnes=Integer.parseInt(rep);
			//nombre de lignes
		rep=new String(j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :"));
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
				&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)<(nbColonnes-2)*(nbLignes-2)*0.20-2)){
				j.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer le pourcentage de rocher :");
		}
		int pourcentage=Integer.parseInt(rep);
		
		//Création de l'ile
<<<<<<< HEAD
		SuperPlateau grille=new SuperPlateau(imgs,nbColonnes);
=======
		SuperPlateau[] grille = new SuperPlateau[3];
		grille[0]=new SuperPlateau(imgs,taille,true);
		grille[0].close();
		grille[1]=new SuperPlateau(imgs,taille,true);
		grille[1].close();
		grille[2]=new SuperPlateau(imgs,taille,true);
		grille[2].close();
>>>>>>> branch 'master' of https://github.com/ptitguigui/TreasureHunt.git
		
		Ile monIle = new Ile(nbColonnes,nbLignes);
		monIle.initialiser(pourcentage);
		grille[0].setJeu(monIle.getIleTab());
		
		//affichage texte
		//System.out.println(monIle);
		
		grille[0].affichage();
	}

}
