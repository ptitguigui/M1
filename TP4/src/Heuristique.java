import java.io.*;
import java.util.Scanner;

public class Heuristique {

    static int dimension;
    static int[][] matrice;
    static int betterSolution;

    public static void main(String[] args) {
        init();
        heuristiqueGlobaleIterative();
    }


    private static void heuristiqueGlobaleIterative() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir le premier sommet entre 0 et "+dimension+" :");
        int sommet = Integer.parseInt(sc.nextLine());

        System.out.println(sommet);
    }

    /**
     * Permet d'initialiser tout le problème tel que le nombre de villes, la
     * matrice, la longueur maximale autorisé
     */
    private static void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir un fichier parmis la liste suivante :");
        displayFilesFromDirectoryDonnees();
        System.out.print("Votre choix : ");
        String filename = sc.nextLine();

        initValues(filename);

        System.out.println("Voici la matrice récupéré : ");
        displayMatrice();

        System.out.println("La meilleur solution possible est : " + betterSolution);
    }

    /**
     * Methode qui initilise l'ensemble des variables tel que la matrice en lisant
     * un fichier
     *
     * @param filename le nom du fichier à rechercher
     */
    static void initValues(String filename) {
        File file = new File("src/../donnees/" + filename);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            br.readLine();

            //Initalize betterSolution
            String line = br.readLine();
            String[] tab = line.split(" ");
            String nb = tab[tab.length - 1];
            betterSolution = Integer.parseInt(nb);

            //Initialize dimension
            line = br.readLine();
            tab = line.split(" ");
            nb = tab[tab.length - 1];
            dimension = Integer.parseInt(nb);

            matrice = new int[dimension][dimension];

            skip(br);
            initMatriceFromFile(br);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche tous les fichiers de jeux de données
     */
    private static void displayFilesFromDirectoryDonnees() {
        File dir = new File("src/../donnees");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * Permet d'afficher la matrice récupérer dans le fichier
     */
    static void displayMatrice() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.printf("%5d", matrice[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Permet d'insérer les données présent dans un fichier dans la matrice
     *
     * @param br le buffer qui lit le fichier
     * @throws IOException
     */
    private static void initMatriceFromFile(BufferedReader br) throws IOException {
        String line;
        int cpt = 0;
        for (int i = 0; i < dimension; i++) {
            line = br.readLine();
            String[] data = line.split(" ");
            for (String aData : data) {
                if (!aData.trim().equals("")) {
                    matrice[i][cpt] = Integer.parseInt(aData.trim());
                    cpt++;
                }
            }
            cpt = 0;
        }
    }

    /**
     * Permet de parser des élements inutile présent dans le fichier
     *
     * @param br le buffer qui lit le fichier
     * @throws IOException
     */
    private static void skip(BufferedReader br) throws IOException {
        for (int i = 0; i < 3; i++) {
            br.readLine();
        }

    }
}
