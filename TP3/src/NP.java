import java.util.Scanner;

public class NP {
	
	static int betterSolution;
	static int dimension;
	static int[][] matrice;
		
	public static void main(String[] args) {
		initValues();
	}
	
	static void initValues() {
		Scanner sc = new Scanner(System.in);
		skip(sc);
		String line = sc.nextLine();
		
		String [] tab = line.split(" ");
		String nb = tab[tab.length-1];
		
		dimension = Integer.parseInt(nb);
		matrice = new int[dimension][dimension];
		
		skip(sc);
		for(int i =0; i < dimension; i++) {
			line = sc.nextLine();
			String[] data= line.split(" ");
			System.out.println("");
			System.out.println(line);
			for (int j = 0; j < data.length; j++) {
				if(!data[j].equals("") && !data[j].equals("\t")) {
					System.out.println(data[j]+"test");
					matrice[i][j] = Integer.parseInt(data[j]);
				}
			}
		}
		display();
	}
	
	static void display() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				System.out.print(matrice[i][j]+ " ");
			}
			System.out.println("");
		}
	}

	private static void skip(Scanner sc) {
		for(int i = 0; i < 3; i++) {
			sc.nextLine();
		}
	}
	
}
