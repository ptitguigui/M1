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
	}

	private static void initPlateau(Scanner sc, int y, int x, boolean[][] pionBlanc, boolean[][] pionNoir) {
		for (int j = 0; j < y; j++) {
			String ligne = sc.next();
			for (int i = 0; i < x; i++) {
				if (ligne.equals('P')) {
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
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
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
