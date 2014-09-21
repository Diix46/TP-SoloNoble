import java.util.ArrayList;
import java.util.LinkedList;


public class Grille {


	char [][] tab;

	/**
	 * Créer une grille standard
	 * @param colonne Nombre de colonne
	 * @param ligne Nombre de ligne
	 */

	private int nbcol;
	private int nbline;


	public Grille(Grille g){
		this.tab = g.tab;
	}


	public Grille(int colonne, int ligne){
		this.tab = new char[colonne][ligne];
		for (int i=0; i<colonne; i++){
			for (int j=0; j<ligne; j++){
				tab[i][j] = 'o';
			}
		}
		this.nbcol = colonne;
		this.nbline = ligne;
		int k = colonne/2;
		int h = ligne/2;
		tab[k][h] = '.';
	}

	/**
	 * Methode permettant d'afficher le jeu
	 */
	public void Affiche() {

		int count = 0;
		int square_length = String.valueOf(this.nbcol * this.nbline).length();
		String s = "";
		for (int k = 0; k < this.tab.length; k++) {
			for (int l = 0; l < this.tab[k].length; l++) {
				s = String.valueOf(this.tab[k][l]);
				if (s.length() != square_length) {
					for (int i = 0; i <= (square_length - s.length()); i++)
						s = " " + s;
				}
				System.out.print(s + " ");
				count++;
				if (count == this.nbline) {
					System.out.print("\n");
					count = 0;
				}
			}
		}
	}

	public ArrayList<Coup> rechercherNbSolution(){
		ArrayList<Coup> listeCoup = new ArrayList<Coup>();
		for (int i=0; i<this.nbcol; i++){
			for (int j=0; j<this.nbline; j++){
				if(tab[i][j] == '.'){
					if(this.checkBas(i, j)){
						Coup c = new Coup(i, j, i, j+2);
						listeCoup.add(c);
					}
					if(this.checkHaut(i, j)){
						Coup c = new Coup(i, j, i, j-2);
						listeCoup.add(c);
					}
					if(this.checkDroite(i, j)){
						Coup c = new Coup(i, j, i+2, j);
						listeCoup.add(c);
					}
					if(this.checkGauche(i, j)){
						Coup c = new Coup(i, j, i-2, j);
						listeCoup.add(c);
					}
				}
			}
		}
		return listeCoup;
	}

	private boolean checkDroite(int i, int j){
		if(i < this.nbcol - 1){
			if(tab[i+1][j] == 'o' && tab[i+2][j] == 'o'){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean checkGauche(int i, int j){
		if(i > 0){
			if(tab[i-1][j] == 'o' && tab[i-2][j] == 'o'){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean checkHaut(int i, int j){
		if(j > 0){
			if(tab[i-1][j] == 'o' && tab[i-2][j] == 'o'){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	private boolean checkBas(int i, int j){
		if(j < this.nbline - 1){
			if(tab[i+1][j] == 'o' && tab[i+2][j] == 'o'){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void annulerModif(Coup c) {
		tab[c.getI_final()][c.getJ_final()] = 'o';
	}

	public void modifier(Coup c) {
		tab[c.getI_final()][c.getJ_final()] = '.';
	}

	public int nbBoulesRestantes() {
		int count = 0;
		for (int i=0; i<this.nbcol; i++){
			for (int j=0; j<this.nbline; j++){
				if(tab[i][j] == 'o'){
					count++;
				}
			}
		}
		return count;
	}

	public void AfficherSolution(LinkedList solutionLK){
		ArrayList<Coup> nbCoup = new ArrayList<Coup>();
		while(solutionLK.size() != 0){
			this.Affiche();
			nbCoup = this.rechercherNbSolution();
			this.modifier(nbCoup.get((int)solutionLK.getFirst()));
			solutionLK.removeFirst();
			nbCoup.clear();
		}
	}

}
