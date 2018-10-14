import java.util.Scanner;

public class Hexaspawn {
    public static boolean fini;
    public static boolean victoireBlanc;

    public static void main(String[] args) {
        fini = false;
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);
        System.out.println(getConfigNaive(pionBlanc, pionNoir, true, 0));
        //System.out.println(displayTab(pionBlanc, pionNoir));
        sc.close();
    }

    private static void initPlateau(Scanner sc, int y, int x, boolean[][] pionBlanc, boolean[][] pionNoir) {
        sc.nextLine();
        String line;
        for (int j = 0; j < y; j++) {
            line = sc.nextLine();
            for (int i = 0; i < x; i++) {
                if (line.charAt(i) == 'P') {
                    pionBlanc[i][j] = true;
                } else if (line.charAt(i) == 'p') {
                    pionNoir[i][j] = true;
                } else {
                    pionBlanc[i][j] = false;
                    pionNoir[i][j] = false;
                }

            }
        }
    }

    private static String displayTab(boolean[][] pionBlanc, boolean[][] pionNoir) {
        String res = "";
        for (int j = 0; j < pionBlanc[0].length; j++) {
            for (int i = 0; i < pionBlanc.length; i++) {
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

    private static int getConfigNaive(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour, int cpt) {
        play(pionBlanc, pionNoir, tour, cpt);

        System.out.println("Victoire blanc ? "+victoireBlanc+" Nombre de coup(s)"+ cpt);
        return victoireBlanc ? ++cpt : ++cpt * -1;
    }

    private static void play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour, int cpt) {
        boolean tourBlanc = tour;
        if (!fini) {
            for (int i = 0; i < pionBlanc.length; i++) {
                for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (tourBlanc) {
                        if (pionBlanc[i][j]) {
                            choixPionBlanc(i, j, pionBlanc, pionNoir, ++cpt);
                        }
                    } else {
                        if (pionNoir[i][j]) {
                            choixPionNoir(i, j, pionBlanc, pionNoir, ++cpt);
                        }
                    }
                }
            }
        }
    }

    private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {

        if (j > 0) {
            //Peux avancer
            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]) {
                pionBlanc[i][j] = false;
                pionBlanc[i][j - 1] = true;
                play(pionBlanc, pionNoir, false, ++cpt);
                pionBlanc[i][j] = true;
                pionBlanc[i][j - 1] = false;
            }
            //Peux manger en diagonale droite
            else if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                pionNoir[i + 1][j - 1] = false;
                pionBlanc[i + 1][j - 1] = true;
                pionBlanc[i][j] = false;
                play(pionBlanc, pionNoir, false, ++cpt);
                pionNoir[i + 1][j - 1] = true;
                pionBlanc[i + 1][j - 1] = false;
                pionBlanc[i][j] = true;
            }
            //Peux manger en diagonale gauche
            else if (i > 0 && pionNoir[i - 1][j - 1]) {
                pionNoir[i - 1][j - 1] = false;
                pionBlanc[i - 1][j - 1] = true;
                pionBlanc[i][j] = false;
                play(pionBlanc, pionNoir, false, ++cpt);
                pionNoir[i - 1][j - 1] = true;
                pionBlanc[i - 1][j - 1] = false;
                pionBlanc[i][j] = true;

            } else {
                //On fait rien
                play(pionBlanc, pionNoir, false, ++cpt);
            }
        } else {
            fini = true;
            victoireBlanc = true;
            play(pionBlanc, pionNoir, true, ++cpt);
        }
        //System.out.println(displayTab(pionBlanc, pionNoir));
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {

        if (j < pionNoir[0].length - 1) {
            //Peux avancer
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                pionNoir[i][j] = false;
                pionNoir[i][j + 1] = true;
                play(pionBlanc, pionNoir, true, ++cpt);
                pionNoir[i][j] = true;
                pionNoir[i][j + 1] = false;
            }
            //Peux manger en diagonale gauche
            else if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                pionBlanc[i + 1][j + 1] = false;
                pionNoir[i + 1][j + 1] = true;
                pionNoir[i][j] = false;
                play(pionBlanc, pionNoir, true, ++cpt);
                pionBlanc[i + 1][j + 1] = true;
                pionNoir[i + 1][j + 1] = false;
                pionNoir[i][j] = true;
            }
            //Peux manger en diagonale droite
            else if (i != 0 && pionBlanc[i - 1][j + 1]) {
                pionBlanc[i - 1][j + 1] = false;
                pionNoir[i - 1][j + 1] = true;
                pionNoir[i][j] = false;
                play(pionBlanc, pionNoir, true, ++cpt);
                pionBlanc[i - 1][j + 1] = true;
                pionNoir[i - 1][j + 1] = false;
                pionNoir[i][j] = true;
            } else {
                //On fait rien
                play(pionBlanc, pionNoir, true, ++cpt);
            }
        } else {
            fini = true;
            victoireBlanc = false;
            play(pionBlanc, pionNoir, true, ++cpt);
        }
    }
}