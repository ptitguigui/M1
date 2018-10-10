import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class Hexaspawn {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		int x = sc.nextInt();
		boolean[][] pionBlanc = new boolean[x][y];
		boolean[][] pionNoir = new boolean[x][y];
		initPlateau(sc, y, x, pionBlanc, pionNoir);
		
		//System.out.println(displayTab(pionBlanc, pionNoir));
		play(pionBlanc, pionNoir);
		sc.close();
	}

	private static void initPlateau(Scanner sc, int y, int x, boolean[][] pionBlanc, boolean[][] pionNoir) {
		String ligne = sc.nextLine();
		for (int j = 0; j < y; j++) {
			ligne = sc.nextLine();
			for (int i = 0; i < x; i++) {
				if (ligne.charAt(i) == 'P') {
					pionBlanc[i][j] = true;
				} else if (ligne.charAt(i) == 'p') {
					pionNoir[i][j] = true;
				} else {
					pionBlanc[i][j] = false;
					pionNoir[i][j] = false;
				}
				
			}
		}
	}

	private static String displayTab(boolean[][] pionBlanc, boolean[][] pionNoir) {
		String res = "";
		for (int j = 0; j < pionBlanc[0].length; j++) {
			for (int i = 0; i < pionBlanc.length; i++) {
				if (pionBlanc[i][j] == true) {
					res += "P";
				} else if (pionNoir[i][j] == true) {
					res += "p";
				} else {
					res += "_";
				}
			}
			res += "\n";
		}
		return res;
	}

	private static int getConfigNaive(char[][] plateau) {
		int cpt = 0;
		
		//int meilleurConfig = 0;
		//meilleurConfig = Math.min(meilleurConfig, fonctionRecusive);		 
		
		return cpt;
	}
	
	private static void play(boolean [][] pionBlanc, boolean[][] pionNoir){
		boolean tourBlanc = true;
		for(int j=0; j<pionBlanc[0].length; j++){
			for(int i=0; i<pionBlanc.length; i++){
				if(tourBlanc){
					if(pionBlanc[i][j] == true){
						choixPionBlanc(i, j, pionBlanc, pionNoir);
					}
				}
			}
		}
	}

	private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir) {
		
		//Peux avancer
		if(!pionNoir[i][j-1] && !pionBlanc[i][j-1]){
			pionBlanc[i][j] = false;
			pionBlanc[i][j-1] = true;
		}
		//Peux manger en diagonale droite
		else if(i<pionNoir.length && pionNoir[i+1][j-1]){
			pionNoir[i+1][j-1] = false;
			pionBlanc[i+1][j-1] = true;
			pionBlanc[i][j] = false;
		}
		//Peux manger en diagonale gauche
		else if(i != 0 && pionNoir[i-1][j-1]){
			pionNoir[i-1][j-1] = false;
			pionBlanc[i-1][j-1] = true;
			pionBlanc[i][j] = false;
		}else{
			//On fait rien
			System.out.println("fait rien");
		}
		System.out.println(displayTab(pionBlanc, pionNoir));
	}
}
