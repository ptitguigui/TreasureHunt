
public class GestionPlateaux {
	private SuperPlateau[] plateaux=new SuperPlateau[2];
	private final String[] IMGS={"treasure hunt/images/mer.png",
			"treasure hunt/images/rocher.png.jpg",
			"treasure hunt/images/1.navire.png",
			"treasure hunt/images/2.navire.png",
			"treasure hunt/images/cle.png",
			"treasure hunt/images/coffre.png.jpg",
			"treasure hunt/images/1.explorateur.png",
			"treasure hunt/images/2.explorateur.png"};
		//tableau omniscient
	private int[][] jeu;
	private Ile monIle;
	private boolean[][] jeuJ1b;
	private boolean[][] jeuJ2b;
	private int[][] jeuJ1;
	private int[][] jeuJ2;
	
	GestionPlateaux(Ile monIle){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10);
		plateaux[1]=new SuperPlateau(IMGS,10);
		jeu=monIle.getIleTab();
		jeuJ1b=new boolean[jeu.length][jeu[0].length];
		jeuJ2b=new boolean[jeu.length][jeu[0].length];
		jeuJ1=new int[jeu.length][jeu[0].length];
		jeuJ2=new int[jeu.length][jeu[0].length];
	}
	
	public void initialiser(){
		for(int c=0; c<jeu.length; c++) {
			for(int l=0; l<jeu[0].length; l++){
				jeuJ1b[c][l]=false;
				jeuJ2b[c][l]=false;
			}
		}
		add(monIle.getNavire(1)[0], monIle.getNavire(1)[1]);
		add(monIle.getNavire(2)[0], monIle.getNavire(2)[1]);
	}
	
	public void add(int a, int b){
		jeuJ2b[a][b]=true;
		jeuJ2b[a+1][b]=true;
		jeuJ2b[a-1][b]=true;
		jeuJ2b[a][b+1]=true;
		jeuJ2b[a][b-1]=true;
		update();
	}
	
	public void update(){
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
		plateaux[0].setJeu(jeuJ1);
		plateaux[1].setJeu(jeuJ2);
	}
	
	public void affichageJ1(){
		plateaux[0].affichage();
	}
	public void affichageJ2(){
		plateaux[1].affichage();
	}
}
