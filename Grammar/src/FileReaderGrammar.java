import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileReaderGrammar {

    static List<String> alphabet = new ArrayList<>();

    public FileReaderGrammar() {
    }

    public static void main(String[] args) {
        String filename = "ex2.txt";
        File file = new File("exemples/" + filename);

        try {
            initAlphabet(file);
            System.out.println("Alphabet : ");
            for (String lettre : alphabet) {
                System.out.println(lettre);
            }
            Random r = new Random();
            String mot = "";
            for (int i = 0; i < r.nextInt(10) + 5; i++) {
                mot += alphabet.get(r.nextInt(alphabet.size()));
            }
            System.out.println("Un mot qui n'appartient pas au langage : "+ mot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initAlphabet(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String letter;

        while ((line = br.readLine()) != null) {
            if (!line.startsWith("#")) {
                List<String> data1 = Arrays.asList(line.split("\\|"));
                List<String> data;
                for (String el : data1) {
                    data = Arrays.asList(el.trim().split(" "));
                    for (String el2 : data) {
                        if (el2.matches("\"([^\"]*)\"")) {
                            int taille = el2.length();
                            if (taille > 2) {
                                letter = (el2.substring(1, el2.length() - 1));
                            } else {
                                letter = "";
                            }
                            if (!alphabet.contains(letter)) {
                                alphabet.add(letter);
                            }
                        }
                    }
                }
            }
        }
    }
}
