
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
		JOptionPane j2 = new JOptionPane();
		int menu = j2.showOptionDialog(null, "Bienvenue, que voulez-vous faire ?", "Treasure hunt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
		
			
			
		
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
		
		//demande nombres de personnage pour les différentes équipes
		rep=new String(j.showInputDialog(null,"Entrer le nombre de personnages :"));
				while(!(rep.matches("[1-9][0-9]*")   
						&& (int) Integer.parseInt(rep)>0 
						&& (int)Integer.parseInt(rep)<5)){
						j.showMessageDialog(null, "Saisie incorrecte ou trop élevée.", "Erreur", 0);
						rep=j.showInputDialog(null,"Entrer le nombre de personnages :");
				}
		int nbPersonnages=Integer.parseInt(rep);
		
		//Création de l'ile				
		Ile monIle = new Ile(nbColonnes,nbLignes);
		monIle.initialiser(pourcentage, nbPersonnages);
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
		
		//test des actions
		boolean boucleInfinis= false;
		 do {
		        InputEvent event ;
		        int x,y,a,b =0;
		        boolean action = false;
		        		        
		        for(int i=0; i<2; i++){
		        	plateaux[i].affichage();
				    plateaux[i].println("A votre tour J" + (i+1)) ;
				    plateaux[1-i].println("Au tour de votre adversaire") ;
			    		
				    	
				    // Vérification de la selection : doit être un personnage de son équipe
			    	do {
			    		event=  plateaux[i].waitEvent();
					   	x = plateaux[i].getX((MouseEvent) event) ;
				    	y = plateaux[i].getY((MouseEvent) event) ;
			    	} while(!(monIle.getValeurParcelle(x, y)>=9 && monIle.getValeurParcelle(x, y)%2!=i));
					    
					    //Actions si explorateur
					    if(monIle.getValeurParcelle(x,y) == 9+i){ 
					    	plateaux[i].println("Vous avez choisis un " + monIle.getParcelle(x,y).getPersonnage().getNom()+ " de J"+(i+1)+", faites une action") ;
					    	while(!action){
				        		event=  plateaux[i].waitEvent();
				        		a = plateaux[i].getX((MouseEvent) event) ;
						    	b = plateaux[i].getY((MouseEvent) event) ;
						    	if(plateaux[i].deplacable(jeu,a,b) && monIle.parcelleValide(x, y, a, b)){
						    		monIle.echangeParcelles(x, y, a, b);
						    		plateaux[0].setJeu(monIle.getIleTab());
						    		plateaux[1].setJeu(monIle.getIleTab());
						    		plateaux[i].println("Déplacement effectué") ;
						    		action = true;
						    	}
						    	if(monIle.getValeurParcelle(x,y) == 9+i && monIle.rocherACote(x, y, a, b)){
						    		plateaux[i].println("L'explorateur soulève un rocher") ;
						    		if(((ParcelleRocher)monIle.getParcelle(a,b)).getTresor()){
						    			((ParcelleRocher)monIle.getParcelle(a, b)).visible();
							    		plateaux[i].println("Vous avez trouvé le trésor") ;
							    		plateaux[0].setJeu(monIle.getIleTab());
							    		plateaux[1].setJeu(monIle.getIleTab());
						    		}else if(((ParcelleRocher)monIle.getParcelle(a,b)).getClef()){
						    			((ParcelleRocher)monIle.getParcelle(a, b)).visible();
						    			plateaux[0].setJeu(monIle.getIleTab());
							    		plateaux[1].setJeu(monIle.getIleTab());
							    		plateaux[i].println("Vous avez trouvé la clef") ;
						    		}else{
							    		plateaux[i].println("Mais vous avez rien trouve en dessous...") ;
						    		}
						    		action = true;
						    	}					    	
					    	}
				    	}/*
					  //Actions si voleur
					    if(monIle.getValeurParcelle(x,y) == 11+i){ 
					    	plateaux[i].println("Vous avez choisis un " + monIle.getParcelle(x,y).getPersonnage().getNom()+ " de J"+(i+1)+", faites une action") ;
					    	while(!action){
				        		event=  plateaux[i].waitEvent();
				        		a = plateaux[i].getX((MouseEvent) event) ;
						    	b = plateaux[i].getY((MouseEvent) event) ;
						    	if(plateaux[i].deplacable(jeu,a,b) && monIle.parcelleValide(x, y, a, b)){
						    		monIle.echangeParcelles(x, y, a, b);
						    		plateaux[0].setJeu(monIle.getIleTab());
						    		plateaux[1].setJeu(monIle.getIleTab());
						    		plateaux[i].println("Déplacement effectué") ;
						    		action = true;
						    	}
						    	if(monIle.getValeurParcelle(x,y) == 11+i && monIle.joueurACote(x, y, a, b)){
						    		plateaux[i].println("L'explorateur soulève un rocher") ;
						    		if(((ParcelleRocher)monIle.getParcelle(a,b)).getTresor()){
						    			((ParcelleRocher)monIle.getParcelle(a, b)).visible();
							    		plateaux[i].println("Vous avez trouvé le trésor") ;
							    		plateaux[0].setJeu(monIle.getIleTab());
							    		plateaux[1].setJeu(monIle.getIleTab());
						    		}else if(((ParcelleRocher)monIle.getParcelle(a,b)).getClef()){
						    			((ParcelleRocher)monIle.getParcelle(a, b)).visible();
						    			plateaux[0].setJeu(monIle.getIleTab());
							    		plateaux[1].setJeu(monIle.getIleTab());
							    		plateaux[i].println("Vous avez trouvé la clef") ;
						    		}else{
							    		plateaux[i].println("Mais vous avez rien trouve en dessous...") ;
						    		}
						    		action = true;
						    	}					    	
					    	}
					    }*/
					    action = false;
					    event = plateaux[i].waitEvent(200) ;	// Délai pour permettre la lecture.
		        }
				 
				    	
			    		
		 } while (!boucleInfinis) ;
		 		
		}
		//DEBUT BROUILLARD DE GUERRE
		/*
		GestionPlateaux gestion=new GestionPlateaux(monIle);
		gestion.initialiser();
		gestion.affichageJ1();
		gestion.affichageJ2();*/
		
<<<<<<< HEAD
		
		}
	}
=======
		//affichage texte
		//System.out.println(monIle);
		}
			
>>>>>>> branch 'master' of https://github.com/ptitguigui/TreasureHunt.git
}


