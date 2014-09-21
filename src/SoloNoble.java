import java.util.ArrayList;
import java.util.LinkedList;


public class SoloNoble {

	public static boolean solutionTrouvee = false;
	public static LinkedList<Grille> solutionLK = new LinkedList<Grille>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grille gr = new Grille(5, 5);
		gr.Affiche();
		System.out.println(SoloNoble.avancer(gr));
		System.out.println(solutionLK.size());
		gr.AfficherSolution(solutionLK);
	}

	public static boolean avancer(Grille gr){
		boolean rez = false;
		if(solutionTrouvee){
			rez = true;
		} else {
			if(gr.nbBoulesRestantes() == 1){
				solutionTrouvee = true;
				rez = true;
			} else {
				ArrayList<Coup> nbCoup = gr.rechercherNbSolution();
				if(nbCoup.size() != 0){
					for(int i = 0; i<nbCoup.size(); i++){
						gr.modifier(nbCoup.get(i));
						avancer(gr);
						if(!solutionTrouvee){
							gr.annulerModif(nbCoup.get(i));
						} else {
							Grille g_sol = new Grille(gr);
							solutionLK.add(g_sol);
							rez = true;
						}
					}
				}
			}
		}
		return rez;
	}


}
