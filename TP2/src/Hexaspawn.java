import java.util.Scanner;


public class Hexaspawn {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		int x = sc.nextInt();
		boolean[][] pionBlanc = new boolean[x][y];
		boolean[][] pionNoir = new boolean[x][y];
		initPlateau(sc, y, x, pionBlanc, pionNoir);
		
		System.out.println(displayTab(y, x, pionBlanc, pionNoir));
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

	private static String displayTab(int y, int x, boolean[][] pionBlanc, boolean[][] pionNoir) {
		String res = "";
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
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

		return cpt;
	}
}
