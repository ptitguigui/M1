import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Heuristique {

    static int dimension;
    static int[][] matrice;
    static int betterSolution;

    public static void main(String[] args) {
        init();
        List<Integer> sommets = heuristiqueGlobaleIterative();
        System.out.println("Résultat de l'heuristique globales de façon itérative : " + calculDistance(sommets) + " pour la permutation : " + sommets);
    }

    /**
     * Methode qui permet de calculer la solution optimale de la façon suivante :
     * - Choisir un sommet (soit par l'utilisateur, soit la meilleur)
     * - Trouver la ville la plus proche du dernier sommet visité avec une ville non encore sélectionnée
     * et l’ajouter au tour.
     * - S’arrêter lorsque l’on a sélectionné toutes les villes
     *
     * @return La liste des sommets réprésentant une permutation
     */
    private static List<Integer> heuristiqueGlobaleIterative() {
        Scanner sc = new Scanner(System.in);
        List<Integer> sommetsIn = new ArrayList<>();
        List<Integer> sommetsOut = new ArrayList<>();
        int taille = dimension - 1;

        //Ajoute tous les cas possible
        for (int i = 0; i < dimension; i++)
            sommetsIn.add(i);

        System.out.println("Veuillez choisir le premier sommet entre 0 et " + taille + " :");
        int sommet = Integer.parseInt(sc.nextLine());

        //int sommet = getDepartSommet();


        sommetsIn.remove(new Integer(sommet));
        sommetsOut.add(sommet);

        int addSommet = 0;
        while (!sommetsIn.isEmpty()) {
            int min = 9999;
            for (int i : sommetsIn) {
                if (matrice[sommet][i] != 0 && matrice[sommet][i] < min) {
                    min = matrice[sommet][i];
                    addSommet = i;
                }
            }
            sommet = addSommet;
            sommetsIn.remove(new Integer(addSommet));
            sommetsOut.add(addSommet);
        }
        return sommetsOut;
    }

    /**
     * Methode qui retourne le sommet qui à la distance la plus courte
     *
     * @return l'entier représentant le sommet
     */
    private static int getDepartSommet() {
        int min = matrice[0][0];
        int sommet = 0;

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                if (matrice[i][j] != 0 && matrice[i][j] < min) {
                    sommet = i;
                    min = matrice[i][j];
                }
        return sommet;
    }

    /**
     * Calcule la distance totale d'un certificat en cycle (retour vers le premier)
     *
     * @param list le certificat
     * @return la distance totale
     */
    static int calculDistance(List<Integer> list) {
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += matrice[list.get(i)][list.get((i + 1) % (dimension))];
        }
        return res;
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
