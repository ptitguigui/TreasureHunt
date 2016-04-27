package Jeu;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import tps.Plateau;

/**
 * La classe SuperPlateau permet d'afficher un plateau de jeu carré sur lequel sont disposés des images représentant les éléments du jeu, ainsi que de déplacer ces différents éléments. Les images sont toutes de même taille et carrées.
 * @author Marie
 *
 */
public class SuperPlateau {
	
	/**
	 * Attribut correspondant à un plateau de jeu, c'est à dire un tableau de cases.
	 */
	private Plateau p;
	
	/**
	 * Constructeur de la classe Plateau qui crée un plateau de jeu vide; de dimension taille x taille cellules vides.
	 * @param gifs tableau d'images
	 * @param taille taille du plateau (il s'agit d'un carré)
	 */
	SuperPlateau(String[] gifs, int taille){
		p = new Plateau(gifs, taille);
	}	
	
	/**
	 * Constructeur de la classe Plateau qui crée un plateau de jeu vide de dimension taille x taille cellules vides.
	 * @param gifs tableau d'images
	 * @param taille taille du plateau(il s'agit d'un carré)
	 * @param withTextArea true si zone de texte, false sinon
	 */
	SuperPlateau(String[] gifs, int taille, boolean withTextArea){
		p = new Plateau(gifs, taille, withTextArea);
	}
	
	
	
	//REDEFINITION DES METHODES DE PLATEAU
	/**
	 * Méthode permettant d'afficher le plateau.
	 */
	public void affichage(){
		p.affichage();
	}
	
	/**
	 * Retourne le tableau d'entiers représentant le plateau.
	 * @return le tableau d'entiers
	 */
	public int[][] getJeu(){
		return p.getJeu();
	}
	
	/**
	 * Méthode permettant de placer les éléments sur le plateau
	 * @param jeu tableau 2D représentant le plateau la valeur numérique d'une cellule désigne l'image correspondante dans le tableau des chemins (décalé de un, 0 désigne une case vide)
	 */
	public void setJeu( int[][] jeu){
		p.setJeu(jeu);
	}
	
	/**
	 * Provoque la destruction du plateau. 
	 * L'arrêt du programme est conditionné par la fermeture de tous les plateaux ouverts.
	 */
	public void close(){
		p.close();
	}
	
	/**
	 * Calcule la ligne de la case ciblée par un mouseEvent.
	 * Attention: il est possible si l'utilsateur agrandi la fenêtre que le clic
	 * se produise à l'extérieur du plateau. Cette configuration n'est pas détectée par cette méthode
	 * qui retourne alors une valeur hors des limites. 
	 *
	 * @param event L'évenement souris capturé.
	 * @return le numéro de la colonne ciblée (0 à taille-1)
	 */
	public int getX(MouseEvent event){
		return p.getX(event);
	}
	
	/**
	 * Calcule la colonne de la case ciblée par un mouseEvent.
	 * Attention: il est possible si l'utilsateur agrandi la fenêtre que le clic
	 * se produise à l'extérieur du plateau. Cette configuration n'est pas détectée par cette méthode
	 * qui retourne alors une valeur hors des limites. 
	 *
	 * @param event L'évenement souris capturé.
	 * @return le numéro de la colonne ciblée (0 à taille-1)
	 */
	public int getY(MouseEvent event){
		return p.getY(event);
	}
	
	/**
	 * Provoque le masquage du plateau.
	 * Le plateau est conservé en mémoire et peut être réaffiché par {@link #affichage()}.
	 */
	public void masquer(){
		p.masquer();
	}
	
	/**
	 * Affiche un message dans la partie texte du plateau.
	 * Si le plateau a été construit sans zone texte, cette méthode est sans effet.
	 * Cela provoque aussi le déplacement du scroll vers l'extrémité basse de façon 
	 * à rendre le texte ajouté visible.
	 * @param message String
	 */
	public void println(String message){
		p.println(message);
	}
	
	/**
	 * Attends (indéfiniment) un événement clavier ou souris. 
	 * Pour limiter le temps d'attente (timeout) voir {@link #waitEvent(int)}.
	 * 
	 * @return L'événement observé.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html">java.awt.event.InputEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html">java.awt.event.MouseEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html">java.awt.event.KeyEvent</a>
	 */
	public InputEvent waitEvent() {
		return p.waitEvent();
	}
	
	/**
	 * Attends (au maximum timeout ms) l'apparition d'une entrée (souris ou clavier).
	 * 
	 * @param timeout La durée maximale d'attente.
	 * @return L'événement observé si il y en a eu un, <i>null</i> sinon.
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/InputEvent.html">java.awt.event.InputEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseEvent.html">java.awt.event.MouseEvent</a>
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html">java.awt.event.KeyEvent</a>
	 */
	public InputEvent waitEvent(int timeout){
		return p.waitEvent(timeout);
	}
	
	/**
	 * Efface la surbrillance pour toutes les cellules du plateau. 
	 */
	public void clearHighlight() {
		p.clearHighlight();
	}
	/**
	 * Place une cellule en surbrillance. Les cellules peuvent revenir à leur état normal:
	 * <ul>
	 * <li>globalement par un appel à {@link #clearHighlight()}</li>
	 * <li>individuellement par un appel à {@link #resetHighlight(int, int)}</li>
	 * </ul>
	 * @param x La ligne de la cellule.
	 * @param y La colonne de la cellule.
	 */
	public void setHighlight(int x, int y, Color c) {
		p.setHighlight(x, y, c);
	}
	/**
	 * Efface la surbrillance pour une cellule du plateau. La cellule est déterminée par
	 * son numéro de ligne et de colonne. Si la cellule n'était pas en surbrillance, 
	 * cette méthode est sans effet.
	 * @param x Numéro de ligne de la cellule à affecter.
	 * @param y Numéro de colonne de la cellule à affecter.
	 */
	public void resetHighlight(int x, int y) {
		p.resetHighlight(x, y);
	}
	/**
	 * Permet de savoir si une cellule est actuellement en surbrillance.
	 * @param x Le numéro de ligne de la cellule.
	 * @param y Le numéro de colonne de la cellule.
	 * @return true si la cellule est actuellement en surbrillance.
	 */
	public boolean isHighlight(int x, int y) {
		return p.isHighlight(x, y);
	}
	/**
	 * Détermine le titre de la fenetre associée.
	 * @param title Le titre à afficher.
	 */
	public void setTitle(String title) {
		p.setTitle(title);
	}
}
