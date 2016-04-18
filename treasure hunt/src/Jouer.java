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
			
			/*final String[] IMGS={"treasure hunt/images/sable.jpg",
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
					"treasure hunt/images/2.piegeur.png"};*/
			
			final String[] IMGS={"treasure hunt/imgs/sable.png",
					"treasure hunt/imgs/mer.png",
					"treasure hunt/imgs/rocher1.png",
					"treasure hunt/imgs/rocher2.png",
					"treasure hunt/imgs/rocher3.png",
					"treasure hunt/imgs/arbre.png",
					"treasure hunt/imgs/clef.png",
					"treasure hunt/imgs/tresor.png",
					"treasure hunt/imgs/piege.png",
					"treasure hunt/imgs/1_bateau.png",
					"treasure hunt/imgs/2_bateau.png",
					"treasure hunt/imgs/1_explorateur.png",
					"treasure hunt/imgs/2_explorateur.png",
					"treasure hunt/imgs/1_voleur.png",
					"treasure hunt/imgs/2_voleur.png",
					"treasure hunt/imgs/1_piegeur.png",
					"treasure hunt/imgs/2_piegeur.png",
					"treasure hunt/imgs/1_guerrier.png",
					"treasure hunt/imgs/2_guerrier.png"};
			
			//demande de la taille
			//nombre de colonnes
			String rep=new String(JOptionPane.showInputDialog(null,"Entrez la taille x du plateau (entre 10 et 20) :"));
			//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
			while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=JOptionPane.showInputDialog(null,"Entrez la taille du plateau (entre 10 et 20) :");
			}
			int nbColonnes=Integer.parseInt(rep);
			//nombre de lignes
			rep=new String(JOptionPane.showInputDialog(null,"Entrez la taille y du plateau (entre 10 et 20) :"));
			//Tant que la saisie soit un chiffre et qu'il soit entre 10 et 20
			while(!(rep.matches("[1-9][0-9]*")&& Integer.parseInt(rep)>=10 && Integer.parseInt(rep)<=20)){
				JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct.", "Erreur", 0);
				rep=JOptionPane.showInputDialog(null,"Entrez la taille du plateau (entre 10 et 20) :");
			}
			int nbLignes=Integer.parseInt(rep);
			
			
			//demande du pourcentage de rocher
			rep=new String(JOptionPane.showInputDialog(null,"Entrez le pourcentage de rocher :"));
			//Tant que la saisie soit un chiffre et que le nombre de rochers soit >=3 et que'il soit <20% 
			while(!(rep.matches("[1-9][0-9]*")   
					&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)>=3 
					&& (int)((nbColonnes-2)*(nbLignes-2)*Integer.parseInt(rep)/100.00)<(nbColonnes-2)*(nbLignes-2)*0.2-1)){
					JOptionPane.showMessageDialog(null, "Nombre incorrecte ou pourcentage impossible à réaliser.", "Erreur", 0);
					rep=JOptionPane.showInputDialog(null,"Entrez le pourcentage de rocher :");
			}
			int pourcentage=Integer.parseInt(rep);
			
			
			//demande nombres de personnage pour les différentes équipes
			String nb=new String(JOptionPane.showInputDialog(null,"Entrez le nombre de personnage(s) :"));
			while(!(nb.matches("[1-9][0-9]*")   
					&& (int) Integer.parseInt(nb)>0 
					&& (int)Integer.parseInt(nb)<5)){
				JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
				nb=JOptionPane.showInputDialog(null,"Entrez le nombre de personnage(s) :");
			}
	
			int nombre = Integer.parseInt(nb);
			int nbExplo1=0,nbExplo2=0,nbVoleurs1=0,nbVoleurs2=0, nbPiegeurs1=0, nbPiegeurs2=0, nbGuerriers1=0, nbGuerriers2=0;
			boolean valide;
			//Choix des différents personnages par équipe
			for(int i=1; i<3; i++){		
				valide = false;
				do{					
					//demande nombres d'explorateur pour les différentes équipes
					rep=new String(JOptionPane.showInputDialog(null,"J"+i+", entrez le nombre d'explorateur(s) :"));
							while(!(rep.matches("[1-9][0-9]*")   
									&& Integer.parseInt(rep)>=0 
									&& Integer.parseInt(rep)<=Integer.parseInt(nb))){
									JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
									rep=JOptionPane.showInputDialog(null,"Entrez le nombre d'explorateur(s) :");
							}
					if(i==1){
					nbExplo1= Integer.parseInt(rep);
					nombre = (int)(Integer.parseInt(nb) - nbExplo1);
					}else{
						nbExplo2= Integer.parseInt(rep);
						nombre = (int)(Integer.parseInt(nb) - nbExplo2);
					}
	
					//demande nombres de voleur pour les différentes équipes
					if(nombre !=0){
						rep=new String(JOptionPane.showInputDialog(null,"J"+i+", entrez le nombre de voleur(s) :"));
							while(!(rep.matches("[0-9][0-9]*")   
									&& (int) Integer.parseInt(rep)>=0 && (int) Integer.parseInt(rep)<=nombre)){
								JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
								rep=JOptionPane.showInputDialog(null,"Entrez le nombre de voleur(s) :");
							}
						if(i==1){
							nbVoleurs1= Integer.parseInt(rep);
							nombre -= nbVoleurs1;
						}else{
							nbVoleurs2= Integer.parseInt(rep);
							nombre -= nbVoleurs2;
						}
					}
	
					//demande nombres de piegeurs pour les différentes équipes
					if(nombre !=0){
						rep=new String(JOptionPane.showInputDialog(null,"J"+i+", entrez le nombre de piegeur(s) :"));
								while(!(rep.matches("[0-9][0-9]*")   
										&& (int) Integer.parseInt(rep)>=0 && (int) Integer.parseInt(rep)<=nombre)){
										JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
										rep=JOptionPane.showInputDialog(null,"Entrez le nombre de piegeur(s) :");
								}
					if(i==1){
						nbPiegeurs1= Integer.parseInt(rep);
						nombre -= nbPiegeurs1;
						}else{
							nbPiegeurs2= Integer.parseInt(rep);
							nombre -= nbPiegeurs2;
						}
					}
					
					//demande nombres de guerriers pour les différentes équipes
					if(nombre !=0){
						rep=new String(JOptionPane.showInputDialog(null,"J"+i+", entrez le nombre de guerrier(s) :"));
								while(!(rep.matches("[0-9][0-9]*")   
										&& (int) Integer.parseInt(rep)>=0 && (int) Integer.parseInt(rep)<=nombre)){
										JOptionPane.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
										rep=JOptionPane.showInputDialog(null,"Entrez le nombre de guerrier(s) :");
								}
						if(i==1){		
							nbGuerriers1=Integer.parseInt(rep);
							nombre -= nbGuerriers1;
						}else{
							nbGuerriers2=Integer.parseInt(rep);
							nombre -= nbGuerriers2;
						}
					}
				if(nombre == 0){
					valide = true;
				}else{
					JOptionPane.showMessageDialog(null, "Vous devez créer une équipe valide", "Erreur", 0);
				}
				}while(!valide);
					if(i==1){
						JOptionPane.showMessageDialog(null, "J"+i+", vous avez choisis de faire des équipes de "+nbExplo1+" explorateur, de "+nbVoleurs1+" voleur, de "+nbPiegeurs1+" piegeur et de "+nbGuerriers1+" guerrier");
					}else{
						JOptionPane.showMessageDialog(null, "J"+i+", vous avez choisis de faire des équipes de "+nbExplo2+" explorateur, de "+nbVoleurs2+" voleur, de "+nbPiegeurs2+" piegeur et de "+nbGuerriers2+" guerrier");				
				}
			}
			//Création de l'ile				
			Ile monIle = new Ile(nbColonnes,nbLignes);
			monIle.initialiser(pourcentage);
			monIle.setPersonnages(1, nbExplo1, nbVoleurs1, nbPiegeurs1, nbGuerriers1);
			monIle.setPersonnages(2, nbExplo2, nbVoleurs2, nbPiegeurs2, nbGuerriers2);
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
					monIle.action(plateaux, i, nbExplo1+nbVoleurs1+nbPiegeurs1+nbGuerriers1);
				}	 		
			}
				//DEBUT BROUILLARD DE GUERRE
				/*
				GestionPlateaux gestion=new GestionPlateaux(monIle);
				gestion.affichage();*/
				
		}
	}
}


