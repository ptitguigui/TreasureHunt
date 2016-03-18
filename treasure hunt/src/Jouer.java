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
				// image clef à trouver
				"treasure hunt/images/coffre.png.jpg"};
		
		//demande de la taille
		JOptionPane j=new JOptionPane();
		String rep=new String(j.showInputDialog(null,"Entrer la taille du plateau (entre 8 et 20) :"));
		while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=8 && Integer.parseInt(rep)<=20)){
				j.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer la taille du plateau (entre 8 et 20) :");
		}
		int taille=Integer.parseInt(rep);
		
		//demande du pourcentage de rocher
		rep=new String(j.showInputDialog(null,"Entrer le pourcentage de rocher :"));
		while(!(rep.matches("[1-9][0-9]*")
				&& (int)((taille-2)*(taille-2)*Integer.parseInt(rep)/100.00-2)>=1 
				&& (int)((taille-2)*(taille-2)*Integer.parseInt(rep)/100.00-2)<=(taille-4)*(taille-4)*0.20-2)){
				j.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
				rep=j.showInputDialog(null,"Entrer le pourcentage de rocher :");
		}
		int pourcentage=Integer.parseInt(rep);
		
		SuperPlateau grille=new SuperPlateau(imgs,taille);
		
		Ile monIle = new Ile(taille,taille);
		monIle.initialiser(pourcentage);
		grille.setJeu(monIle.getIleTab());
		
		//affichage texte
		//System.out.println(monIle);
		
		grille.affichage();
	}

}
