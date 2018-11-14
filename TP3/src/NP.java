import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NP {

	static int betterSolution;
	static int dimension;
	static int[][] matrice;
	static int k = 40;

	public static void main(String[] args) {
		initValues();
		
		vericiationAllPossibilities(generateAllPossibilities());
		
		/*
		List<Integer> alea = certificatAleatoire();
		System.out.println(displayList(alea));
		System.out.println("La distance de cette liste aleatoire : " + calculDistance(alea));
		System.out.println("Le certificat aléatoire avec k=" + k + " a-t-il reussis ? " + verifierCertificat(alea, k));

		List<Integer> verification = certificatVerification();
		System.out.println("La distance de cette liste aleatoire : " + calculDistance(verification));
		System.out.println(
				"Le certificat aléatoire avec k=" + k + " a-t-il reussis ? " + verifierCertificat(verification, k));
		*/
	}

	static void initValues() {
		Scanner sc = new Scanner(System.in);
		skip(sc);
		String line = sc.nextLine();

		String[] tab = line.split(" ");
		String nb = tab[tab.length - 1];

		dimension = Integer.parseInt(nb);
		matrice = new int[dimension][dimension];

		skip(sc);
		int cpt = 0;
		for (int i = 0; i < dimension; i++) {
			line = sc.nextLine();
			String[] data = line.split(" ");
			for (int j = 0; j < data.length; j++) {
				if (!data[j].trim().equals("")) {
					matrice[i][cpt] = Integer.parseInt(data[j].trim());
					cpt++;
				}
			}
			cpt = 0;
		}
		// display();
	}

	static List<Integer> certificatAleatoire() {
		List<Integer> list = new ArrayList();
		while (list.size() != dimension) {
			Random random = new Random();
			int nb = random.nextInt(dimension);
			if (!list.contains(nb)) {
				list.add(nb);
			}
		}
		return list;
	}

	static List<Integer> certificatVerification() {
		List<Integer> list = new ArrayList();

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez renseigner un certificat de taille " + dimension);

		String certificat = sc.nextLine();
		String data[] = certificat.split(" ");

		for (int i = 0; i < dimension; i++) {
			list.add(Integer.parseInt(data[i]));
		}

		return list;
	}
	

	static void vericiationAllPossibilities(List<List<Integer>> list) {
		int total = list.size();
		int totalTrue = 0;
		int totalFalse = 0;
		List<List<Integer>> listTrue= new ArrayList<List<Integer>>();
		for (List<Integer> otherList : list) {
			if(verifierCertificat(otherList, k)) {
				totalTrue ++;
				listTrue.add(otherList);
			}else {
				totalFalse ++;
			}
		}
		System.out.println("Il y a un total de "+total+" certificats avec "+totalTrue+" true et "+totalFalse+" false pour k = "+k);
		displayAllPossibilities(listTrue);
	}

	static List<List<Integer>> generatePerm(List<Integer> original) {
		if (original.size() == 0) {
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			result.add(new ArrayList<Integer>());
			return result;
		}
		Integer firstElement = original.remove(0);
		List<List<Integer>> returnValue = new ArrayList<List<Integer>>();
		List<List<Integer>> permutations = generatePerm(original);
		for (List<Integer> smallerPermutated : permutations) {
			for (int index = 0; index <= smallerPermutated.size(); index++) {
				List<Integer> temp = new ArrayList<Integer>(smallerPermutated);
				temp.add(index, firstElement);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}

	static int calculDistance(List<Integer> list) {
		int res = 0;
		for (int i = 0; i < list.size(); i++) {
			res += matrice[list.get(i)][list.get((i + 1) % dimension)];
		}
		return res;
	}

	static boolean verifierCertificat(List<Integer> list, int k) {
		return calculDistance(list) < k;
	}
	

	static void display() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				System.out.print(matrice[i][j] + " ");
			}
			System.out.println("");
		}
	}

	static String displayList(List<Integer> list) {
		String res = "Voici la liste : ";
		for (Integer integer : list) {
			res += integer + " ";
		}
		return res;
	}

	static List<List<Integer>> generateAllPossibilities() {
		List<Integer> listPossibilities = new ArrayList<Integer>();
		for(int i=0; i< dimension; i++) {
			listPossibilities.add(i);
		}
		
		return generatePerm(listPossibilities);
	}
	static void displayAllPossibilities(List<List<Integer>> list) {
		
		for (List<Integer> otherList : list) {
			System.out.println(displayList(otherList));
		}
	}

	private static void skip(Scanner sc) {
		for (int i = 0; i < 3; i++) {
			sc.nextLine();
		}
	}

}
