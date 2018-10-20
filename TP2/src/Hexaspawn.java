import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hexaspawn {
    static List<Integer> config = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);
        getConfigNaive(pionBlanc, pionNoir, true, false);
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

    private static void getConfigNaive(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tourBlanc, boolean finis) {
        int min = 10000;
        int res = 0;
        play(pionBlanc, pionNoir, tourBlanc, 0, finis);
        for (int conf : config) {
            //System.out.println(conf);
            if (min > Math.abs(conf)) {
                res = conf;
                min = Math.abs(conf);
            }
        }
        System.out.println(res);
    }

    private static void play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tourBlanc, int cpt, boolean finis) {
        if (!finis) {
            for (int i = 0; i < pionBlanc.length; i++) {
                for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (tourBlanc) {
                        if (pionBlanc[i][j]) {
                            choixPionBlanc(i, j, pionBlanc, pionNoir, cpt + 1);
                        }
                    } else {
                        if (pionNoir[i][j]) {
                            choixPionNoir(i, j, pionBlanc, pionNoir, cpt + 1);
                        }
                    }
                }
            }
        }
    }

    private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {

        if (j > 0) {
            // Peux avancer
            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]) {
                // Si il gagne en avançant
                if (j == 1) {
                    addConfigBlanc(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    // Sinon il avance
                    pionBlanc[i][j] = false;
                    pionBlanc[i][j - 1] = true;
                    play(pionBlanc, pionNoir, false, cpt, false);
                    pionBlanc[i][j] = true;
                    pionBlanc[i][j - 1] = false;
                }
            }
            // Si peux manger en diagonale droite
            if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                // Si il gagne en mangeant
                if (j == 1) {
                    addConfigBlanc(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    pionNoir[i + 1][j - 1] = false;
                    pionBlanc[i + 1][j - 1] = true;
                    pionBlanc[i][j] = false;
                    play(pionBlanc, pionNoir, false, cpt, false);
                    pionNoir[i + 1][j - 1] = true;
                    pionBlanc[i + 1][j - 1] = false;
                    pionBlanc[i][j] = true;
                }
            }
            // Si peux manger en diagonale gauche
            if (i > 0 && pionNoir[i - 1][j - 1]) {
                // Si il gagne en mangeant
                if (j == 1) {
                    addConfigBlanc(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    pionNoir[i - 1][j - 1] = false;
                    pionBlanc[i - 1][j - 1] = true;
                    pionBlanc[i][j] = false;
                    play(pionBlanc, pionNoir, false, cpt, false);
                    pionNoir[i - 1][j - 1] = true;
                    pionBlanc[i - 1][j - 1] = false;
                    pionBlanc[i][j] = true;
                }
            }
            // Si pion bloque
            if (pionNoir[i][j - 1] || pionBlanc[i][j - 1]) {
                if (jeuBloque(pionBlanc, pionNoir)) {
                    addConfigNoir(cpt);
                    play(pionBlanc, pionNoir, false, cpt, true);
                } /*
                 * else { play(pionBlanc, pionNoir, false, ++cpt, true); }
                 */
            }
        } else {
            addConfigBlanc(cpt);
            play(pionBlanc, pionNoir, true, cpt, true);
        }
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {

        if (j < pionBlanc[0].length - 1) {
            // Peux avancer
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                // Si il gagne en avançant
                if (j == pionNoir[0].length - 2) {
                    addConfigNoir(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    // Sinon il avance
                    pionNoir[i][j] = false;
                    pionNoir[i][j + 1] = true;
                    play(pionBlanc, pionNoir, true, cpt, false);
                    pionNoir[i][j] = true;
                    pionNoir[i][j + 1] = false;
                }
            }
            // Si peux manger en diagonale gauche
            if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                // Si il gagne en mangeant
                if (j == pionNoir[0].length - 2) {
                    addConfigNoir(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    pionBlanc[i + 1][j + 1] = false;
                    pionNoir[i + 1][j + 1] = true;
                    pionNoir[i][j] = false;
                    play(pionBlanc, pionNoir, true, cpt, false);
                    pionBlanc[i + 1][j + 1] = true;
                    pionNoir[i + 1][j + 1] = false;
                    pionNoir[i][j] = true;
                }
            }
            //Si peux manger en diagonale droite
            if (i != 0 && pionBlanc[i - 1][j + 1]) {
                // Si il gagne en mangeant
                if (j == pionNoir[0].length - 2) {
                    addConfigNoir(cpt);
                    play(pionBlanc, pionNoir, true, cpt, true);
                } else {
                    pionBlanc[i - 1][j + 1] = false;
                    pionNoir[i - 1][j + 1] = true;
                    pionNoir[i][j] = false;
                    play(pionBlanc, pionNoir, true, cpt, false);
                    pionBlanc[i - 1][j + 1] = true;
                    pionNoir[i - 1][j + 1] = false;
                    pionNoir[i][j] = true;
                }

            }
            // Pion bloque
            if (pionNoir[i][j + 1] || pionBlanc[i][j + 1]) {
                if (jeuBloque(pionBlanc, pionNoir)) {
                    addConfigBlanc(cpt);
                    play(pionBlanc, pionNoir, false, cpt, true);
                }
            }
        } else {
            addConfigNoir(cpt);
            play(pionBlanc, pionNoir, true, cpt, true);
        }
    }

    private static boolean jeuBloque(boolean[][] pionBlanc, boolean[][] pionNoir) {
        /*
         * for (int i = 0; i < pionBlanc.length; i++) { for (int j = 0; j <
         * pionBlanc[0].length; j++) { if (pionBlanc[i][j]) { if (!pionNoir[i][j - 1] &&
         * !pionBlanc[i][j - 1]) { return false; } } if (pionNoir[i][j]) { if
         * (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) { return false; } } } }
         */
        return false;
    }

    private static void addConfigBlanc(int cpt) {
        if (!config.contains(cpt)) {
            config.add(cpt);
        }
    }

    private static void addConfigNoir(int cpt) {
        if (!config.contains(-1 * cpt)) {
            config.add(-1 * cpt);
        }

    }
}