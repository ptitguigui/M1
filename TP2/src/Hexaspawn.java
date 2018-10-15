import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hexaspawn {
    public static boolean victoireBlanc;
    public static List<Integer> config = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);
        getConfigNaive(pionBlanc, pionNoir, true, false);
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

    private static void getConfigNaive(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour, boolean finis) {
        play(pionBlanc, pionNoir, tour, 0, finis);
//        for (int conf : config) {
//            System.out.println(conf);
//        }
        System.out.println(config.get(0));
    }

    private static void play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour, int cpt, boolean finis) {
        boolean tourBlanc = tour;
        if (!finis) {
            for (int i = 0; i < pionBlanc.length; i++) {
                for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (tourBlanc) {
                        if (pionBlanc[i][j]) {
                            choixPionBlanc(i, j, pionBlanc, pionNoir, cpt);
                        }
                    } else {
                        if (pionNoir[i][j]) {
                            choixPionNoir(i, j, pionBlanc, pionNoir, cpt);
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
                play(pionBlanc, pionNoir, false, ++cpt, false);
                pionBlanc[i][j] = true;
                pionBlanc[i][j - 1] = false;
            }
            //Peux manger en diagonale droite
            if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                pionNoir[i + 1][j - 1] = false;
                pionBlanc[i + 1][j - 1] = true;
                pionBlanc[i][j] = false;
                play(pionBlanc, pionNoir, false, ++cpt, false);
                pionNoir[i + 1][j - 1] = true;
                pionBlanc[i + 1][j - 1] = false;
                pionBlanc[i][j] = true;
            }
            //Peux manger en diagonale gauche
            if (i > 0 && pionNoir[i - 1][j - 1]) {
                pionNoir[i - 1][j - 1] = false;
                pionBlanc[i - 1][j - 1] = true;
                pionBlanc[i][j] = false;
                play(pionBlanc, pionNoir, false, ++cpt, false);
                pionNoir[i - 1][j - 1] = true;
                pionBlanc[i - 1][j - 1] = false;
                pionBlanc[i][j] = true;

            } else {
                //On fait rien
                //play(pionBlanc, pionNoir, false, ++cpt, false);
            }
        } else {
            victoireBlanc = true;
            config.add(cpt);
            play(pionBlanc, pionNoir, true, cpt, true);
        }
        //System.out.println(displayTab(pionBlanc, pionNoir));
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {

        if (j < pionNoir[0].length - 1) {
            //Peux avancer
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                pionNoir[i][j] = false;
                pionNoir[i][j + 1] = true;
                play(pionBlanc, pionNoir, true, ++cpt, false);
                pionNoir[i][j] = true;
                pionNoir[i][j + 1] = false;
            }
            //Peux manger en diagonale gauche
            if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                pionBlanc[i + 1][j + 1] = false;
                pionNoir[i + 1][j + 1] = true;
                pionNoir[i][j] = false;
                play(pionBlanc, pionNoir, true, ++cpt, false);
                pionBlanc[i + 1][j + 1] = true;
                pionNoir[i + 1][j + 1] = false;
                pionNoir[i][j] = true;
            }
            //Peux manger en diagonale droite
            if (i != 0 && pionBlanc[i - 1][j + 1]) {
                pionBlanc[i - 1][j + 1] = false;
                pionNoir[i - 1][j + 1] = true;
                pionNoir[i][j] = false;
                play(pionBlanc, pionNoir, true, ++cpt, false);
                pionBlanc[i - 1][j + 1] = true;
                pionNoir[i - 1][j + 1] = false;
                pionNoir[i][j] = true;
            } else {
                //On fait rien
                play(pionBlanc, pionNoir, true, cpt, false);
            }
        } else {
            config.add(-1 * --cpt);
            victoireBlanc = false;
            //play(pionBlanc, pionNoir, true, ++cpt, true);
        }
    }
}