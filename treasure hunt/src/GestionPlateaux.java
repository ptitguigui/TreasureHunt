
public class GestionPlateaux {
	private SuperPlateau[] plateaux=new SuperPlateau[2];
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
		//tableau omniscient
	private int[][] jeu;
	private Ile monIle;
	private boolean[][] jeuJ1b;
	private boolean[][] jeuJ2b;
	private int[][] jeuJ1;
	private int[][] jeuJ2;
	private boolean tresorJ1=false;
	private boolean tresorJ2=false;
	private int[] coordTresor=monIle.getTresor();
	
	//pas de brouillard par défaut
	GestionPlateaux(Ile monIle){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10);
		plateaux[1]=new SuperPlateau(IMGS,10);
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
	}
	
	GestionPlateaux(Ile monIle, boolean brouillard){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10);
		plateaux[1]=new SuperPlateau(IMGS,10);
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
		add(monIle.getNavire(1)[0], monIle.getNavire(1)[1], 1);
		add(monIle.getNavire(2)[0], monIle.getNavire(2)[1], 2);
	}
	
	public SuperPlateau[] getPlateaux(){
		return plateaux;
	}
	
	public void add(int a, int b, int numEquipe){
		if(numEquipe==1){
			jeuJ1b[a][b]=true;
			jeuJ1b[a+1][b]=true;
			jeuJ1b[a-1][b]=true;
			jeuJ1b[a][b+1]=true;
			jeuJ1b[a][b-1]=true;
		} else {
			jeuJ2b[a][b]=true;
			jeuJ2b[a+1][b]=true;
			jeuJ2b[a-1][b]=true;
			jeuJ2b[a][b+1]=true;
			jeuJ2b[a][b-1]=true;
		}
		update();
	}
	
	public void update(){
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				if(jeuJ1b[c][l]==true) {
					jeuJ1[c][l]=jeu[c][l];					
					//piège visible ou non
					if(monIle.getParcelle(c,l) instanceof ParcellePiege) {
						if(((ParcellePiege)monIle.getParcelle(c, l)).getNumEquipe()!=1){
							jeuJ1[c][l]=1; //sable
						}
					}
				} else {
					jeuJ1[c][l]=0;
				}
				if(jeuJ2b[c][l]==true) {
					jeuJ2[c][l]=jeu[c][l];
					//piège visible ou non
					if(monIle.getParcelle(c,l) instanceof ParcellePiege) {
						if(((ParcellePiege)monIle.getParcelle(c, l)).getNumEquipe()!=2){
							jeuJ1[c][l]=1; //sable
						}
					}
				} else {
					jeuJ2[c][l]=0;
				}
			}
		}
		
		//trésor visible ou non
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
		
		plateaux[0].setJeu(jeuJ1);
		plateaux[1].setJeu(jeuJ2);
	}
	
	public void affichage(){
		plateaux[0].affichage();
		plateaux[1].affichage();
	}

	public void action(int i, int nbPersonnages){
		monIle.action(plateaux, i, nbPersonnages);
	}
}
