package Jeu;

import javax.swing.JOptionPane;

import tps.Plateau;

/**
 * 
 * Classe principale, permettant de lancer le jeu.
 * 
 * @author vitsem
 *
 */
public class Jouer {

	public static void main(String[] args) {

		Object[] option = { "Jouer", "Tester", "Quitter" };
		int menu = JOptionPane.showOptionDialog(null, "Bienvenue, que voulez-vous faire ?", "Treasure hunt",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

		if (menu == 0 || menu == 1) {
			JOptionPane.showMessageDialog(null, "Lancement du jeu");

			// demande de la taille
			// nombre de colonnes
			String rep = new String(
					JOptionPane.showInputDialog(null, "Entrez la taille x du plateau (entre 10 et 20) :"));
			// Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
			while (!(rep.matches("[1-9][0-9]*") && Integer.parseInt(rep) >= 10 && Integer.parseInt(rep) <= 20)) {
				JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep = JOptionPane.showInputDialog(null, "Entrez la taille du plateau (entre 10 et 20) :");
			}
			int nbColonnes = Integer.parseInt(rep);
			// nombre de lignes
			rep = new String(JOptionPane.showInputDialog(null, "Entrez la taille y du plateau (entre 10 et 20) :"));
			// Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
			while (!(rep.matches("[1-9][0-9]*") && Integer.parseInt(rep) >= 10 && Integer.parseInt(rep) <= 20)) {
				JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep = JOptionPane.showInputDialog(null, "Entrez la taille du plateau (entre 10 et 20) :");
			}
			int nbLignes = Integer.parseInt(rep);

			// demande du pourcentage de rocher
			rep = new String(JOptionPane.showInputDialog(null, "Entrez le pourcentage de rocher (<20%) :"));
			// Tant que la saisie soit un chiffre et que le nombre de rochers
			// soit >=3 et que'il soit <20%
			while (!(rep.matches("[1-9][0-9]*")
					&& (int) ((nbColonnes - 2) * (nbLignes - 2) * Integer.parseInt(rep) / 100.00) >= 3
					&& (int) ((nbColonnes - 2) * (nbLignes - 2) * Integer.parseInt(rep) / 100.00) < (nbColonnes - 2)
							* (nbLignes - 2) * 0.2 - 1)) {
				JOptionPane.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur",
						0);
				rep = JOptionPane.showInputDialog(null, "Entrez le pourcentage de rocher (<20%) :");
			}
			int pourcentage = Integer.parseInt(rep);

			// demande nombres de personnage pour les différentes équipes
			String nb = new String(JOptionPane.showInputDialog(null, "Entrez le nombre de personnage(s) :"));
			while (!(nb.matches("[1-9][0-9]*") && (int) Integer.parseInt(nb) > 0 && (int) Integer.parseInt(nb) < 5)) {
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
				nb = JOptionPane.showInputDialog(null, "Entrez le nombre de personnage(s) :");
			}
			int nombre = Integer.parseInt(nb);
			int nbExplo1 = 0, nbExplo2 = 0, nbVoleurs1 = 0, nbVoleurs2 = 0, nbPiegeurs1 = 0, nbPiegeurs2 = 0,
					nbGuerriers1 = 0, nbGuerriers2 = 0;
			boolean valide;
			// Choix des différents personnages par équipe
			for (int i = 1; i < 3; i++) {
				valide = false;
				do {
					// demande nombres d'explorateur pour les différentes
					// équipes
					rep = new String(
							JOptionPane.showInputDialog(null, "J" + i + ", entrez le nombre d'explorateur(s) :"));
					while (!(rep.matches("[1-9][0-9]*") && Integer.parseInt(rep) >= 0
							&& Integer.parseInt(rep) <= Integer.parseInt(nb))) {
						JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
						rep = JOptionPane.showInputDialog(null, "Entrez le nombre d'explorateur(s) :");
					}
					if (i == 1) {
						nbExplo1 = Integer.parseInt(rep);
						nombre = (int) (Integer.parseInt(nb) - nbExplo1);
					} else {
						nbExplo2 = Integer.parseInt(rep);
						nombre = (int) (Integer.parseInt(nb) - nbExplo2);
					}

					// demande nombres de voleur pour les différentes équipes
					if (nombre != 0) {
						rep = new String(
								JOptionPane.showInputDialog(null, "J" + i + ", entrez le nombre de voleur(s) :"));
						while (!(rep.matches("[0-9][0-9]*") && (int) Integer.parseInt(rep) >= 0
								&& (int) Integer.parseInt(rep) <= nombre)) {
							JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
							rep = JOptionPane.showInputDialog(null, "Entrez le nombre de voleur(s) :");
						}
						if (i == 1) {
							nbVoleurs1 = Integer.parseInt(rep);
							nombre -= nbVoleurs1;
						} else {
							nbVoleurs2 = Integer.parseInt(rep);
							nombre -= nbVoleurs2;
						}
					}

					// demande nombres de piegeurs pour les différentes équipes
					if (nombre != 0) {
						rep = new String(
								JOptionPane.showInputDialog(null, "J" + i + ", entrez le nombre de piegeur(s) :"));
						while (!(rep.matches("[0-9][0-9]*") && (int) Integer.parseInt(rep) >= 0
								&& (int) Integer.parseInt(rep) <= nombre)) {
							JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
							rep = JOptionPane.showInputDialog(null, "Entrez le nombre de piegeur(s) :");
						}
						if (i == 1) {
							nbPiegeurs1 = Integer.parseInt(rep);
							nombre -= nbPiegeurs1;
						} else {
							nbPiegeurs2 = Integer.parseInt(rep);
							nombre -= nbPiegeurs2;
						}
					}

					// demande nombres de guerriers pour les différentes équipes
					if (nombre != 0) {
						rep = new String(
								JOptionPane.showInputDialog(null, "J" + i + ", entrez le nombre de guerrier(s) :"));
						while (!(rep.matches("[0-9][0-9]*") && (int) Integer.parseInt(rep) >= 0
								&& (int) Integer.parseInt(rep) <= nombre)) {
							JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
							rep = JOptionPane.showInputDialog(null, "Entrez le nombre de guerrier(s) :");
						}
						if (i == 1) {
							nbGuerriers1 = Integer.parseInt(rep);
							nombre -= nbGuerriers1;
						} else {
							nbGuerriers2 = Integer.parseInt(rep);
							nombre -= nbGuerriers2;
						}
					}
					if (nombre == 0) {
						valide = true;
					} else {
						JOptionPane.showMessageDialog(null, "Vous devez créer une équipe valide", "Erreur", 0);
					}
				} while (!valide);
				if (i == 1) {
					JOptionPane.showMessageDialog(null,
							"J" + i + ", vous avez choisis de faire des équipes de " + nbExplo1 + " explorateur, de "
									+ nbVoleurs1 + " voleur, de " + nbPiegeurs1 + " piegeur et de " + nbGuerriers1
									+ " guerrier");
				} else {
					JOptionPane.showMessageDialog(null,
							"J" + i + ", vous avez choisis de faire des équipes de " + nbExplo2 + " explorateur, de "
									+ nbVoleurs2 + " voleur, de " + nbPiegeurs2 + " piegeur et de " + nbGuerriers2
									+ " guerrier");
				}
			}
			// Création de l'ile
			Ile monIle = new Ile(nbColonnes, nbLignes);
			monIle.initialiser(pourcentage);
			boolean brouillard = true;

			if (menu == 1) {
				brouillard = false;
				monIle.setPersonnages(1, true, nbExplo1, nbVoleurs1, nbPiegeurs1, nbGuerriers1);
				monIle.setPersonnages(2, true, nbExplo2, nbVoleurs2, nbPiegeurs2, nbGuerriers2);
			} else {
				monIle.setPersonnages(1, false, nbExplo1, nbVoleurs1, nbPiegeurs1, nbGuerriers1);
				monIle.setPersonnages(2, false, nbExplo2, nbVoleurs2, nbPiegeurs2, nbGuerriers2);
			}

			// BROUILLARD DE GUERRE
			GestionPlateaux gestion = new GestionPlateaux(monIle, brouillard);
			gestion.setTitle("Treasure Hunt");
			Plateau[] plateaux = gestion.getPlateaux();
			gestion.affichage();

			// affichage texte
			System.out.println(monIle);

			// déplacement
			int i;
			int nbActions=0;
			boolean finis = false;
			while (!finis) {
				i = 0;
				while (!finis && i < 2) {
					plateaux[i].println(">> A votre tour J" + (i+1)) ;
					plateaux[1-i].println(">> Au tour de votre adversaire") ;
					nbActions=0;
					while(nbActions<3){
						nbActions+=gestion.action(i);
						if (monIle.finPartie(plateaux)) {
							finis = true;
							break;
						}
					}
					i++;
				}
			}
		}
	}
}
