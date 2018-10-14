import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testHexaspawn {
    public static boolean fini;
    public static boolean victoireBlanc;
    public static int cpt;
    public static List<Integer> config = new ArrayList<>();

    public static void main(String[] args) {
        fini = false;
        cpt = 0;
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);
        getConfigNaive(pionBlanc, pionNoir, true);
        //System.out.println(displayTab(pionBlanc, pionNoir));
        sc.close();
    }

    private static void initPlateau(Scanner sc, int y, int x, boolean[][] pionBlanc, boolean[][] pionNoir) {
        String ligne = sc.nextLine();
        for (int j = 0; j < y; j++) {
            ligne = sc.nextLine();
            for (int i = 0; i < x; i++) {
                if (ligne.charAt(i) == 'P') {
                    pionBlanc[i][j] = true;
                } else if (ligne.charAt(i) == 'p') {
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
                if (pionBlanc[i][j]) {
                    res += "P";
                } else if (pionNoir[i][j]) {
                    res += "p";
                } else {
                    res += "_";
                }
            }
            res += "\n";
        }
        return res;
    }

    private static void getConfigNaive(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour) {
        fini = play(pionBlanc, pionNoir, tour);
        for (int conf : config) {
            System.out.println(conf);
        }
    }

    private static boolean play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour) {
        boolean tourBlanc = tour;
        if (!fini) {
            for (int i = 0; i < pionBlanc.length; i++) {
                for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (tourBlanc) {
                        if (pionBlanc[i][j]) {
                            choixPionBlanc(i, j, pionBlanc, pionNoir);
                        }
                    } else {
                        if (pionNoir[i][j]) {
                            choixPionNoir(i, j, pionBlanc, pionNoir);
                        }
                    }
                }
            }
        }
        return fini;
    }

    private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir) {

        //Peux avancer
        if (j > 0) {
            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]) {
                pionBlanc[i][j] = false;
                pionBlanc[i][j - 1] = true;
                cpt++;
                play(pionBlanc, pionNoir, false);
                pionBlanc[i][j] = true;
                pionBlanc[i][j - 1] = false;
            }
            //Peux manger en diagonale droite
            else if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                pionNoir[i + 1][j - 1] = false;
                pionBlanc[i + 1][j - 1] = true;
                pionBlanc[i][j] = false;
                cpt++;
                play(pionBlanc, pionNoir, false);
                pionNoir[i + 1][j - 1] = true;
                pionBlanc[i + 1][j - 1] = false;
                pionBlanc[i][j] = true;
            }
            //Peux manger en diagonale gauche
            else if (i != 0 && pionNoir[i - 1][j - 1]) {
                pionNoir[i - 1][j - 1] = false;
                pionBlanc[i - 1][j - 1] = true;
                pionBlanc[i][j] = false;
                cpt++;
                play(pionBlanc, pionNoir, false);
                pionNoir[i - 1][j - 1] = true;
                pionBlanc[i - 1][j - 1] = false;
                pionBlanc[i][j] = true;
            } else {
                //On fait rien
                //System.out.println("fait rien");
                cpt++;
                play(pionBlanc, pionNoir, false);
            }
        } else {
            config.add(cpt++);
            fini = true;
            victoireBlanc = true;
        }
        //System.out.println(displayTab(pionBlanc, pionNoir));
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir) {

        //Peux avancer
        if (j < pionBlanc[0].length - 1) {
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                pionNoir[i][j] = false;
                pionNoir[i][j + 1] = true;
                cpt++;
                play(pionBlanc, pionNoir, true);
                pionNoir[i][j] = true;
                pionNoir[i][j + 1] = false;
            }
            //Peux manger en diagonale gauche
            else if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                pionBlanc[i + 1][j + 1] = false;
                pionNoir[i + 1][j + 1] = true;
                pionNoir[i][j] = false;
                cpt++;
                play(pionBlanc, pionNoir, true);
                pionBlanc[i + 1][j + 1] = true;
                pionNoir[i + 1][j + 1] = false;
                pionNoir[i][j] = true;
            }
            //Peux manger en diagonale droite
            else if (i != 0 && pionBlanc[i - 1][j + 1]) {
                pionBlanc[i - 1][j + 1] = false;
                pionNoir[i - 1][j + 1] = true;
                pionNoir[i][j] = false;
                cpt++;
                play(pionBlanc, pionNoir, true);
                pionBlanc[i - 1][j + 1] = true;
                pionNoir[i - 1][j + 1] = false;
                pionNoir[i][j] = true;
            } else {
                //On fait rien
                //System.out.println("fait rien");
                cpt++;
                play(pionBlanc, pionNoir, true);
            }
            //System.out.println(displayTab(pionBlanc, pionNoir));
        } else {
            config.add(-1 * cpt);
            fini = true;
            victoireBlanc = false;
        }
    }
}