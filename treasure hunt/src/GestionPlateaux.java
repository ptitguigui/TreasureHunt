
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
	private int[][] ile;
	private Ile monIle;
	private boolean[][] jeuJ1b;
	private boolean[][] jeuJ2b;
	private int[][] jeuJ1;
	private int[][] jeuJ2;
	
	GestionPlateaux(Ile monIle){
		this.monIle=monIle;
		plateaux[0]=new SuperPlateau(IMGS,10);
		plateaux[1]=new SuperPlateau(IMGS,10);
		ile=monIle.getIleTab();
		jeuJ1b=new boolean[ile.length][ile[0].length];
		jeuJ2b=new boolean[ile.length][ile[0].length];
		jeuJ1=new int[ile.length][ile[0].length];
		jeuJ2=new int[ile.length][ile[0].length];
	}
	
	public void initialiser(){
		for(int c=0; c<ile.length; c++) {
			for(int l=0; l<ile[0].length; l++){
				jeuJ1b[c][l]=false;
				jeuJ2b[c][l]=false;
			}
		}
	}
	
	public void update(){
		
	}
}
