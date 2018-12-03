import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Heuristique {

    static int dimension;
    static int[][] matrice;
    static int betterSolution;

    public static void main(String[] args) {
        init();

        List<Integer> sommetsGlobale = heuristiqueGlobaleIterative();
        int heuristique = calculDistance(sommetsGlobale);
        System.out.println("Résultat de l'heuristique globales de façon itérative : " + heuristique + " pour la permutation : " + displayPermutation(sommetsGlobale));

        /*List<Integer> sommetsAllPossibilities = vericiationAllPossibilities(generateAllPossibilities());
        System.out.println("Résultat de l'heuristique avec toutes les possibilités: " + calculDistance(sommetsAllPossibilities) + " pour la permutation : " + displayPermutation(sommetsAllPossibilities));
        */
        List<Integer> sommetsLocal = sommetsGlobale;

        for (int i : sommetsLocal) {
            for (int j = i + 1; j < dimension - 1; j++) {
                List<Integer> list = swap(sommetsLocal, i, j);
                System.out.println(list);
                int newHeuristique = calculDistance(list);
                if (!(newHeuristique < heuristique)) {
                    swap(sommetsLocal, j, i);
                } else {
                    heuristique = newHeuristique;
                }
            }
        }
        System.out.println("Résultat de l'heuristique Locale : " + heuristique + " pour la permutation : " + displayPermutation(sommetsGlobale));

       /*List<Integer> list = getNewList();
        List<Integer> otherList = swap(list, 2, 5);
        System.out.println(getNewList());
        System.out.println(otherList);*/
    }

    /**
     * Method permettant de swap 2 éléments dans la liste reprenant le systeme de donnée locale
     *
     * @param list  le certificat
     * @param debut le premier élément a swap
     * @param fin   le deuxieme élément a swap
     * @return la liste avec les deux éléments swap
     */
    private static List<Integer> swap(List<Integer> list, int debut, int fin) {
        System.out.println("swap : "+list);
        List<Integer> oldList = list;
        List<Integer> newList = new ArrayList<>();

        for (int i = 0; i <= debut; i++) {
            newList.add(list.get(0));
            list.remove(0);
        }

        newList.add(fin);
        list.remove(new Integer(fin));

        newList.addAll(list);

        list = oldList;
        return newList;
    }

    /**
     * Methode qui permet de calculer la solution optimale de la façon suivante : -
     * Choisir un sommet (soit par l'utilisateur, soit la meilleur) - Trouver la
     * ville la plus proche du dernier sommet visité avec une ville non encore
     * sélectionnée et l’ajouter au tour. - S’arrêter lorsque l’on a sélectionné
     * toutes les villes
     *
     * @return La liste des sommets réprésentant une permutation
     */
    private static List<Integer> heuristiqueGlobaleIterative() {
        Scanner sc = new Scanner(System.in);
        List<Integer> sommets = new ArrayList<>();
        List<Integer> sommetsOut = new ArrayList<>();
        List<Integer> bestSolution = new ArrayList<>();
        int min = 9999;
        int taille = dimension - 1;

        //Pour tous les sommets possible en entrée,
        // choisir la permutation qui a la plus courte distance
        // selon l'algo  globale itérative
        for (int i = 0; i < dimension; i++) {
            List<Integer> sommetsIn = getNewList();
            int currentDistance = searchBestWithTheFirst(i, sommetsIn, sommetsOut);
            if (min > currentDistance) {
                min = currentDistance;
                bestSolution = sommetsOut;
            }
            sommetsOut = new ArrayList<>();
        }
        return bestSolution;
    }

    private static List<Integer> getNewList() {
        List<Integer> sommets = new ArrayList<>();
        for (int i = 0; i < dimension; i++)
            sommets.add(i);

        return sommets;
    }

    /**
     * Algo globale itérative qui crée une permutation selon un sommet de départ
     * et l'ensemble des plus courte distance pour chaque sommets
     *
     * @param sommet     le point de départ
     * @param sommetsIn  la liste des sommets possible
     * @param sommetsOut la permutation finale
     * @return la distance de la permutation sommetsOut
     */
    private static int searchBestWithTheFirst(int sommet, List<Integer> sommetsIn, List<Integer> sommetsOut) {
        // Retire le sommet parmis les sommets disponible
        sommetsIn.remove(new Integer(sommet));
        // L'ajoute la premiere permutation
        sommetsOut.add(sommet);

        int addSommet = 0;
        int cpt = 1;
        while (!sommetsIn.isEmpty() && (cpt < dimension)) {
            int min = 9999;
            for (int i : sommetsIn) {
                if (matrice[sommet][i] != 0 && matrice[sommet][i] < min) {
                    min = matrice[sommet][i];
                    addSommet = i;
                }
            }
            cpt++;
            sommet = addSommet;
            sommetsIn.remove(new Integer(addSommet));
            sommetsOut.add(addSommet);
        }
        return calculDistance(sommetsOut);
    }

    /**
     * Parmis tous les certificats possible, récupère celle avec la solution la plus
     * petite
     *
     * @param list La liste de tous les certificats
     * @return
     */
    static List<Integer> vericiationAllPossibilities(List<List<Integer>> list) {
        int min = 9999;
        List<Integer> bestSolution = new ArrayList<Integer>();

        for (List<Integer> otherList : list) {
            if (calculDistance(otherList) < min) {
                bestSolution = otherList;
                min = calculDistance(otherList);
            }
        }
        return bestSolution;
    }

    /**
     * Crée le premier certicat de la matrice et apelle la methode générant tous les
     * certificats possible
     *
     * @return tous les certificats possible
     */
    static List<List<Integer>> generateAllPossibilities() {
        List<Integer> listPossibilities = getNewList();

        return generatePerm(listPossibilities);
    }

    /**
     * Permet de générer tous les certificats possible en faisant des permutations
     *
     * @param original un certificat permettant de creer les permutations
     * @return La liste de tous les certiicats possible
     */
    static List<List<Integer>> generatePerm(List<Integer> original) {
        if (original.size() == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Integer firstElement = original.remove(0);
        List<List<Integer>> returnValue = new ArrayList<>();
        List<List<Integer>> permutations = generatePerm(original);
        for (List<Integer> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<Integer> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
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

            // Initalize betterSolution
            String line = br.readLine();
            String[] tab = line.split(" ");
            String nb = tab[tab.length - 1];
            betterSolution = Integer.parseInt(nb);

            // Initialize dimension
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
     * Affiche le certificat sous la forme demandé en entrée sur code contest
     *
     * @param list la permutation a afficher
     * @return la permutation sous forme d'une chaine de caractère
     */
    private static String displayPermutation(List<Integer> list) {
        String res = "";
        for (int i : list) {
            res += i + " ";
        }
        return res;
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
