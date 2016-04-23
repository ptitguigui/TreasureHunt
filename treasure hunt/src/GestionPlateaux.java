import java.util.ArrayList;
/**
 * Classe permettant de gérer les plateaux
 * @author vitsem
 */
public class GestionPlateaux {
	/**
	 * Attribut désignant les différents plateaux.
	 */
	private SuperPlateau[] plateaux=new SuperPlateau[2];
	/**
	 * Attribut servant à charger les images du jeu.
	 */
	private final String[] IMGS={"treasure hunt/imgs/sable.png",
			"treasure hunt/imgs/mer.png",
			"treasure hunt/imgs/rocher1.png",
			"treasure hunt/imgs/rocher2.png",
			"treasure hunt/imgs/rocher3.png",
			"treasure hunt/imgs/arbre.png",
			"treasure hunt/imgs/clef.png",
			"treasure hunt/imgs/coffre.png",
			"treasure hunt/imgs/piege.png",
			"treasure hunt/imgs/tresor.png",
			"treasure hunt/imgs/epee.png",
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
	/**
	 * Attribut permettant de connaitre le point de vue omniscient de l'ile, et donc de tout savoir en temps réel.
	 */
	private int[][] jeu;
	/**
	 * L'ile liée aux plateaux.
	 */
	private Ile monIle;
	/**
	 * Le tableau de booléens du joueur 1 permettant d'effectuer un filtre sur sa vision de l'ile et donc de faire un effet brouillard de guerre.
	 */
	private boolean[][] jeuJ1b;
	/**
	 * Le tableau de booléens du joueur 2 permettant d'effectuer un filtre sur sa vision de l'ile et donc de faire un effet brouillard de guerre.
	 */
	private boolean[][] jeuJ2b;
	/**
	 * Le tableau d'entier du joueur 1 désignant ce qu'il voit.
	 */
	private int[][] jeuJ1;
	/**
	 * Le tableau d'entier du joueur 2 désignant ce qu'il voit.
	 */
	private int[][] jeuJ2;
	/**
	 * Les coordonnées du trésor.
	 */
	private int[] coordTresor=new int[2];
	
	/**
	 * Constructeur du gestionnaire de plateaux, le brouillard est désactivé par défaut.
	 * @param monIle l'ile liée aux plateaux.
	 */
	GestionPlateaux(Ile monIle){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10,true);
		plateaux[1]=new SuperPlateau(IMGS,10,true);
		jeu=monIle.getIleTab();
		jeuJ1b=new boolean[jeu.length][jeu[0].length];
		jeuJ2b=new boolean[jeu.length][jeu[0].length];
		jeuJ1=new int[jeu.length][jeu[0].length];
		jeuJ2=new int[jeu.length][jeu[0].length];
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				jeuJ1b[c][l]=true;
				jeuJ2b[c][l]=true;
			}
		}
		coordTresor=monIle.getTresor();
	}
	
	/**
	 * Constructeur du gestionnaire de plateaux.
	 * @param monIle l'ile liée aux plateaux.
	 * @param brouillard un booléen permettant d'activer ou non le brouillard de guerre.
	 */
	GestionPlateaux(Ile monIle, boolean brouillard){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10,true);
		plateaux[1]=new SuperPlateau(IMGS,10,true);
		jeu=monIle.getIleTab();
		jeuJ1b=new boolean[jeu.length][jeu[0].length];
		jeuJ2b=new boolean[jeu.length][jeu[0].length];
		jeuJ1=new int[jeu.length][jeu[0].length];
		jeuJ2=new int[jeu.length][jeu[0].length];
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				jeuJ1b[c][l]=!brouillard;
				jeuJ2b[c][l]=!brouillard;
			}
		}
		coordTresor=monIle.getTresor();
		add(monIle.getNavire(1)[0], monIle.getNavire(1)[1], 1);
		add(monIle.getNavire(2)[0], monIle.getNavire(2)[1], 2);
	}
	
	/**
	 * Méthode permettant de retourner les plateaux du gestionnaire.
	 * @return les plateaux du gestionnaire.
	 */
	public SuperPlateau[] getPlateaux(){
		return plateaux;
	}
	
	/**
	 * Méthode permettant d'ajouter une case visible ainsi que les 8 cases autour, dans le champs de vision d'un joueur.
	 * @param a la coordonnée x de la case.
	 * @param b la coordonnée y de la case.
	 * @param numEquipe le numéro d'équipe du joueur.
	 */
	public void add(int a, int b, int numEquipe){
		if(numEquipe==1){
			jeuJ1b[a][b]=true;
			jeuJ1b[a+1][b-1]=true;
			jeuJ1b[a+1][b]=true;
			jeuJ1b[a+1][b+1]=true;
			jeuJ1b[a-1][b-1]=true;
			jeuJ1b[a-1][b]=true;
			jeuJ1b[a-1][b+1]=true;
			jeuJ1b[a][b+1]=true;
			jeuJ1b[a][b-1]=true;
		} else {
			jeuJ2b[a][b]=true;
			jeuJ2b[a+1][b-1]=true;
			jeuJ2b[a+1][b]=true;
			jeuJ2b[a+1][b+1]=true;
			jeuJ2b[a-1][b-1]=true;
			jeuJ2b[a-1][b]=true;
			jeuJ2b[a-1][b+1]=true;
			jeuJ2b[a][b+1]=true;
			jeuJ2b[a][b-1]=true;;
		}
		update();
	}
	
	/**
	 * Méthode permettant de mettre à jour la vision des joueurs.
	 * Elle agit aussi sur la visibilité ou non du trésor et des pièges.
	 */
	public void update(){
		jeu=monIle.getIleTab();
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
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
		
		boolean tresorJ1=monIle.getEquipe(1).tresorVisible();
		boolean tresorJ2=monIle.getEquipe(2).tresorVisible();
		if (jeuJ1b[coordTresor[0]][coordTresor[1]] && tresorJ1){
			jeuJ1[coordTresor[0]][coordTresor[1]]=8;
		} else if (jeuJ1b[coordTresor[0]][coordTresor[1]] && !tresorJ1){
			jeuJ1[coordTresor[0]][coordTresor[1]]=((ParcelleRocher)monIle.getParcelle(coordTresor[0], coordTresor[1])).getValeurIni();
		}
		if (jeuJ2b[coordTresor[0]][coordTresor[1]] && tresorJ2){
			jeuJ2[coordTresor[0]][coordTresor[1]]=8;
		} else if (jeuJ2b[coordTresor[0]][coordTresor[1]] && !tresorJ2){
			jeuJ2[coordTresor[0]][coordTresor[1]]=((ParcelleRocher)monIle.getParcelle(coordTresor[0], coordTresor[1])).getValeurIni();
		}
		
		//pieges visibles ou non
		ArrayList<ParcellePiege> pieges1=monIle.getPieges(1);
		ArrayList<ParcellePiege> pieges2=monIle.getPieges(2);
		if(pieges1.size()>0) {
			for(int idx=0; idx<pieges1.size(); idx++){
				//visible par défaut, donc rendre invisible pour l'adversaire
				int x=pieges1.get(idx).getX();
				int y=pieges1.get(idx).getY();
				jeuJ2[x][y]=1; //sable
			}
		} else if (pieges2.size()>0) {
			for(int idx=0; idx<pieges2.size(); idx++){
				//visible par défaut, donc rendre invisible pour l'adversaire
				int x=pieges2.get(idx).getX();
				int y=pieges2.get(idx).getY();
				jeuJ1[x][y]=1; //sable
			}
		}
		
		plateaux[0].setJeu(jeuJ1);
		plateaux[1].setJeu(jeuJ2);
	}
	
	/**
	 * Méthode permettant d'afficher les plateaux.
	 */
	public void affichage(){
		plateaux[0].affichage();
		plateaux[1].affichage();
	}

	/**
	 * Méthode permettant à un joueur d'effectuer une action.
	 * @param i le numéro du plateau (donc le numéro du joueur -1).
	 */
	public void action(int i){
		monIle.action(plateaux, i);
	}
}
