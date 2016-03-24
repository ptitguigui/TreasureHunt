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
		
		
		//demande de la taille
		JOptionPane j=new JOptionPane();
		String rep=new String(j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :"));
		//Tant que le pourcentage soit un chiffre et qu'il soit entre 10 et 20
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				j.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer la taille du plateau (entre 10 et 20) :");
		}
		int taille=Integer.parseInt(rep);
		
		
		//demande du pourcentage de rocher
		rep=new String(j.showInputDialog(null,"Entrer le pourcentage de rocher :"));
		//Tant que le pourcentage soit un chiffre et que le nombre de rochers soit >=3 et que'il soit <20% 
		while(!(rep.matches("[1-9][0-9]*")   
				&& (int)((taille-2)*(taille-2)*Integer.parseInt(rep)/100.00)>=3 
				&& (int)((taille-2)*(taille-2)*Integer.parseInt(rep)/100.00)<(taille-2)*(taille-2)*0.20-2)){
				j.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer le pourcentage de rocher :");
		}
		int pourcentage=Integer.parseInt(rep);
		
		//Création de l'ile
		SuperPlateau[] grille = new SuperPlateau[3];
		grille[0]=new SuperPlateau(imgs,taille,true);
		grille[0].close();
		grille[1]=new SuperPlateau(imgs,taille,true);
		grille[1].close();
		grille[2]=new SuperPlateau(imgs,taille,true);
		grille[2].close();
		
		Ile monIle = new Ile(taille,taille);
		monIle.initialiser(pourcentage);
		grille[0].setJeu(monIle.getIleTab());
		
		//affichage texte
		//System.out.println(monIle);
		
		grille[0].affichage();
	}

}
