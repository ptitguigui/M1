import java.util.Scanner;


public class Hexaspawn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);

        //System.out.println(displayTab(pionBlanc, pionNoir));
        int min = 10000;
        min = Math.min(min, play(pionBlanc, pionNoir, true, false, 0));

        System.out.println("Le minimum de coup est " + min);
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

    private static int play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tourBlanc, boolean end, int cpt) {
        if (!end) {
            for (int j = 0; j < pionBlanc[0].length; j++) {
                for (int i = 0; i < pionBlanc.length; i++) {
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
        System.out.println(cpt);
        return cpt;
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {
        if (j == pionNoir[0].length - 2 && !pionBlanc[i][j + 1]) {
            play(pionBlanc, pionNoir, false, true, cpt);
            //System.out.println("Noir Gagner !!!!!");
        } else {
            //Peux avancer
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                pionNoir[i][j] = false;
                pionNoir[i][j + 1] = true;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, true, false, ++cpt);
                pionNoir[i][j] = true;
                pionNoir[i][j + 1] = false;
            }
            //Peux manger un blanc en diagonale gauche
            else if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                pionNoir[i + 1][j + 1] = true;
                pionBlanc[i + 1][j + 1] = false;
                pionNoir[i][j] = false;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, true, false, ++cpt);
                pionNoir[i + 1][j + 1] = false;
                pionBlanc[i + 1][j + 1] = true;
                pionNoir[i][j] = true;
            }
            //Peux manger un blanc en diagonale gauche
            else if (i != 0 && pionBlanc[i - 1][j + 1]) {
                pionBlanc[i - 1][j + 1] = false;
                pionNoir[i - 1][j + 1] = true;
                pionNoir[i][j] = false;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, true, false, ++cpt);
                pionBlanc[i - 1][j + 1] = true;
                pionNoir[i - 1][j + 1] = false;
                pionNoir[i][j] = true;
            } else {
                //On fait rien
                //System.out.println("fait rien");
            }
        }
    }

    private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, int cpt) {
        if (j == 1 && !pionNoir[i][j - 1]) {
            play(pionBlanc, pionNoir, false, true, cpt);
            //System.out.println("Blanc Gagner !!!!!");
        } else {
            //Peux avancer
            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]) {
                pionBlanc[i][j] = false;
                pionBlanc[i][j - 1] = true;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, false, false, ++cpt);
                pionBlanc[i][j] = true;
                pionBlanc[i][j - 1] = false;
            }
            //Peux manger un noir en diagonale droite
            else if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                pionNoir[i + 1][j - 1] = false;
                pionBlanc[i + 1][j - 1] = true;
                pionBlanc[i][j] = false;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, false, false, ++cpt);
                pionNoir[i + 1][j - 1] = true;
                pionBlanc[i + 1][j - 1] = false;
                pionBlanc[i][j] = true;
            }
            //Peux manger un noir en diagonale gauche
            else if (i != 0 && pionNoir[i - 1][j - 1]) {
                pionNoir[i - 1][j - 1] = false;
                pionBlanc[i - 1][j - 1] = true;
                pionBlanc[i][j] = false;
                //System.out.println(displayTab(pionBlanc, pionNoir));
                play(pionBlanc, pionNoir, false, false, ++cpt);
                pionNoir[i - 1][j - 1] = true;
                pionBlanc[i - 1][j - 1] = false;
                pionBlanc[i][j] = true;
            } else {
                //On fait rien
                //System.out.println("fait rien");
            }
        }
    }
}
