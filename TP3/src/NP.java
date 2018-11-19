import java.io.*;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NP {

    static int dimension;
    static int[][] matrice;
    static boolean[][] matrice_ham;
    static int k = 36;

    public static void main(String[] args) {
        InitNP();
        
        //Version manuelle : verification
        System.out.println("\n-- Version Manuelle --\n");
        List<Integer> verification = certificatVerification();
        System.out.println("La distance de ce certificat : " + calculDistance(verification));
        System.out.println("Le certificat aléatoire avec k=" + k + " a-t-il reussis ? " + verifierCertificat(verification, k));

        //Version Aleatoire : non-deterministe
        System.out.println("\n-- Version Aléatoire --\n");
        List<Integer> alea = certificatAleatoire();
        displayCertificat(alea);
        System.out.println("La distance de ce certificat aleatoire : " + calculDistance(alea));
        System.out.println("Le certificat aléatoire avec k=" + k + " a-t-il reussis ? " + verifierCertificat(alea, k));

        //Version exploration exhaustive
        System.out.println("\n-- Version Exhaustive --\n");
        vericiationAllPossibilities(generateAllPossibilities());
    }

    /**
     * Methode qui initilise l'ensemble des variables tel que la matrice en lisant un fichier
     * @param filename le nom du fichier à rechercher
     */
    static void initValues(String filename) {
        File file = new File("src/../donnees/" + filename);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            skip(br);
            String line = br.readLine();

            String[] tab = line.split(" ");
            String nb = tab[tab.length - 1];

            dimension = Integer.parseInt(nb);
            matrice = new int[dimension][dimension];

            skip(br);
            getDataFromFile(br);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Génère de façon alétoire un certificat
     * @return une liste représentant le certificat aléatoire
     */
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

    /**
     * Génère de façon manuelle un certificat avec une interaction clavier
     * @return ne liste représentant le certificat manuelle
     */
    static List<Integer> certificatVerification() {
        List<Integer> list = new ArrayList();

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez renseigner un certificat de taille " + dimension+" sous cette forme \"x1 x2 x3 ... xn\" :");

        String certificat = sc.nextLine();
        String data[] = certificat.split(" ");

        for (int i = 0; i < dimension; i++) {
            list.add(Integer.parseInt(data[i]));
        }

        return list;
    }

    /**
     * Parmis tous les certificats possible, les affiche et verifie lesquelles sont correcte selon k
     * @param list La liste de tous les certificats
     */
    static void vericiationAllPossibilities(List<List<Integer>> list) {
        int total = list.size();
        int totalTrue = 0;
        int totalFalse = 0;
        List<List<Integer>> listTrue = new ArrayList<>();
        for (List<Integer> otherList : list) {
            if (verifierCertificat(otherList, k)) {
                totalTrue++;
                listTrue.add(otherList);
            } else {
                totalFalse++;
            }
        }
        System.out.println("Il y a un total de " + total + " certificats avec " + totalTrue + " true et " + totalFalse + " false pour k = " + k);
        displayAllPossibilities(listTrue);
    }

    /**
     * Permet de générer tous les certificats possible en faisant des permutations
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
     * Calcule la distance totale d'un certificat
     * @param list le certificat
     * @return la distance totale
     */
    static int calculDistance(List<Integer> list) {
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            res += matrice[list.get(i)][list.get((i + 1) % dimension)];
        }
        return res;
    }

    /**
     * Verifie si le certificat est correcte ou non selon k la longueur maximale
     * @param list le certificat
     * @param k la longueur maximale autorisé
     * @return un boolean si cela est correcte ou non
     */
    static boolean verifierCertificat(List<Integer> list, int k) {
        return calculDistance(list) <= k;
    }

    /**
     *  Crée le premier certicat de la matrice et apelle la methode générant tous les certificats possible
     * @return tous les certificats possible
     */
    static List<List<Integer>> generateAllPossibilities() {
        List<Integer> listPossibilities = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            listPossibilities.add(i);
        }

        return generatePerm(listPossibilities);
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
     * Permet d'afficher le certificat
     * @param list Le certificat
     * @return la String à afficher
     */
    static void displayCertificat(List<Integer> list) {
        String res = "Le certificat : ";
        for (Integer integer : list) {
            res += integer + " ";
        }
        System.out.println(res);
    }

    /**
     * Permet d'afficher tous les certificats poissble
     * @param list la liste de tous les certificats possible
     */
    static void displayAllPossibilities(List<List<Integer>> list) {
        for (List<Integer> otherList : list)
            displayCertificat(otherList);
    }

    /**
     * Affiche tous les fichiers de jeux de données
     */
    private static void DisplayFilesFromDirectoryDonnees() {
        File dir = new File("src/../donnees");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * Permet d'initialiser tout le problème tel que le nombre de villes, la matrice, la longueur maximale autorisé
     */
    private static void InitNP() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez choisir un fichier parmis la liste suivante :");
        DisplayFilesFromDirectoryDonnees();
        System.out.print("Votre choix : ");
        String filename = sc.nextLine();
        if(filename.endsWith("p")) {
        	initValues(filename);}
        else {
        	initValHAM(filename);
        }
        System.out.println("Voici la matrice récupéré : ");
        displayMatrice();

        System.out.println("Veuillez choisir la longueur maximale possible : ");
        k = Integer.parseInt(sc.nextLine());
    }

    /**
     * Permet de récupérer les données présent dans un fichier
     * @param br le buffer qui lit le fichier
     * @throws IOException
     */
    private static void getDataFromFile(BufferedReader br) throws IOException {
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
     * @param br le buffer qui lit le fichier
     * @throws IOException
     */
    private static void skip(BufferedReader br) throws IOException {
        for (int i = 0; i < 3; i++) {
            br.readLine();
        }
        
    }
    
    /**
     * Methode qui initilise l'ensemble des variables tel que la matrice en lisant un fichier hamiltonien
     * @param filename le nom du fichier à rechercher
     */
    private static void initValHAM(String filename) {
    	File file = new File("src/../donnees/" + filename);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            skip(br);
            String line = br.readLine();

            String[] tab = line.split(" ");
            String nb = tab[tab.length - 1];

            dimension = Integer.parseInt(nb);
            matrice = new int[dimension][dimension];

            skip(br);
            getDataFromFileHam(br);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Permet de récupérer les données présent dans un fichier hamiltonien
     * @param br le buffer qui lit le fichier
     * @throws IOException
     */
	private static void getDataFromFileHam(BufferedReader br) throws IOException {
        String line;
        int cpt = 0;
        for (int i = 0; i < dimension; i++) {
            line = br.readLine();
            String[] data = line.split(" ");
            for (String aData : data) {
                if (!aData.trim().equals("")) {
                	int val;
                	if(aData.trim().equals("False")) {val = 9999;}
                	else {
                		Random random = new Random();
                        val = random.nextInt(dimension);}
                    matrice[i][cpt] = val;
                    cpt++;
                }
            }
            cpt = 0;
        }
		
		
	}
    
}
