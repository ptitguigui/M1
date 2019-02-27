import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderGrammar {

	static List<String> alphabet;

	public FileReaderGrammar() {
		this.alphabet = new ArrayList<>();
	}

	public static void main(String[] args) {
		String filename = "ex1.txt";
		File file = new File("exemples/" + filename);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				if (!line.startsWith("#")) {
					System.out.println(line);
					List<String> data1 = Arrays.asList(line.split("\\|"));
					List<String> data;
					for (String el : data1) {
						data = Arrays.asList(el.split(" "));
						// System.out.println(el);
						for (String el2 : data) {
							System.out.println(el2 + el2.matches("\"?*\""));
							if (el2.matches("\"?*\"")){
								int taille = el2.length();
								/*if (taille > 2) {
									System.out.println(el2.substring(1, el2.length() - 1));
									//alphabet.add(el2.substring(1, el2.length() - 1));
								} else {
									System.out.println("E");
									//alphabet.add("E");
								}*/
							}
						}
					}
				}
			}
			System.out.println("Alphabet : ");
			for (String lettre : alphabet) {
				System.out.println(lettre);
			}

		} catch (Exception e) {

		}
	}
}
