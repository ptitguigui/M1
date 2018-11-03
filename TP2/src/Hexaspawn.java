import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jdk.nashorn.internal.ir.annotations.Ignore;



public class Hexaspawn{
	private static Map<String,Integer> conf_map_n= new HashMap<String, Integer>();
	private static Map<String,Integer> conf_map_b= new HashMap<String, Integer>();
    public static void main(String[] args) {
    	ArrayList<Integer> conf = new ArrayList<Integer>();
    	conf.add(null);
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        boolean[][] pionBlanc = new boolean[x][y];
        boolean[][] pionNoir = new boolean[x][y];
        initPlateau(sc, y, x, pionBlanc, pionNoir);
        getConfigNaive(pionBlanc, pionNoir);
        sc.close();
    }

    /**
     * Permet d'initialiser le plateau selon les entrees donnees
     *
     * @param sc        l'entree
     * @param y         le nombre de colonnes
     * @param x         le nombre de lignes
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
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

    /**
     * Methode qui affiche le plateau de jeu avec les pions disposes
     *
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     * @return le plateau de jeu sous forme d'une chaine de caractere
     */
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

    /**
     * Methode qui va determiner la meilleur configuration selon toutes les configurations possible
     *
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
    private static void getConfigNaive(boolean[][] pionBlanc, boolean[][] pionNoir) {
        int res = play_blanc(pionBlanc, pionNoir);
        System.out.println(res);
    }

    /**
     * Methode qui fait jouer tous les pions d'une couleur blanc selon leurtour
     *
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
    private static int play_blanc(boolean[][] pionBlanc, boolean[][] pionNoir) {
        ArrayList<Integer> conf = new ArrayList<Integer>();
        for (int i = 0; i < pionBlanc.length; i++) {
            for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (pionBlanc[i][j]) {
                    	Object res = choixPionBlanc(i, j, pionBlanc, pionNoir);
                    	if(res != null) {
                    		conf.add((Integer) res);
                    	}
                    }
                }
            
        }
        if(conf.isEmpty()) {
        	return 0;
        	
        }
        else {
        	int min = 100000000;
            boolean valeur_pos = false;
        	for(int c : conf){
        		if (c > 0){
        			valeur_pos = true;
        			if(c < min){
                        min = c;
                    }
        		}

        	}
        	if(!valeur_pos){
        		int min_ = Math.abs(conf.get(0));
        		for(int c : conf){
        			if (min_< Math.abs(c)){
        				min_ = Math.abs(c);
        			}
        		}
        		return -1 * min_;
        	}
        	return min;
        }
    }
    
    /**
     * Methode qui fait jouer tous les pions d'une couleur noir selon leurtour
     *
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
    private static int play_noir(boolean[][] pionBlanc, boolean[][] pionNoir) {
        ArrayList<Integer> conf = new ArrayList<Integer>();
        for (int i = 0; i < pionBlanc.length; i++) {
            for (int j = 0; j < pionBlanc[0].length; j++) {
                	if(pionNoir[i][j]) {
	                	Object res = choixPionNoir(i, j, pionBlanc, pionNoir);
	                	if(res != null) {
	                		conf.add((Integer)res);
	                	}
                
                }
            }
            
        }
        if(conf.isEmpty()) {
        	return 0;
        	
        }
        else {
        	int min = 100000000;
            boolean valeur_pos = false;
        	for(int c : conf){
        		if (c > 0){
        			valeur_pos = true;
        			if(c < min){
                        min = c;
                    }
        		}

        	}
        	if(!valeur_pos){
        		int min_ = Math.abs(conf.get(0));
        		for(int c : conf){
        			if (min_< Math.abs(c)){
        				min_ = Math.abs(c);
        			}
        		}
        		return -1 * min_;
        	}
        	return min;
        }
    }

    /**
     * Cette methpde permets de faire toutes les actions possible d'un pion blanc
     *
     * @param i         Position du pion sur l'axe des abscisses
     * @param j         Position du pion sur l'axe des ordonees
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
    private static Object choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir) {
    	
        if (j > 0) {
        	ArrayList<Integer> choix = new ArrayList<Integer>();
            // Peux avancer
            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]){
                // Si il gagne en avancant
                if (j == 1) {
                	return 1;

                } else {
                    // Sinon il avance
                    pionBlanc[i][j] = false;
                    pionBlanc[i][j - 1] = true;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_b.containsKey(tab)) {
                    int res = play_noir(pionBlanc, pionNoir);
                    conf_map_b.put(tab, res);}
                    choix.add(conf_map_b.get(tab));
                    pionBlanc[i][j] = true;
                    pionBlanc[i][j - 1] = false;
                }
            }
            
            // Si peux manger en diagonale droite
            if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
                // Si il gagne en mangeant
                if (j == 1) {
                	return 1;
                } else {
                    pionNoir[i + 1][j - 1] = false;
                    pionBlanc[i + 1][j - 1] = true;
                    pionBlanc[i][j] = false;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_b.containsKey(tab)) {
                    int res = play_noir(pionBlanc, pionNoir);
                    conf_map_b.put(tab, res);}
                    choix.add(conf_map_b.get(tab));
                    pionNoir[i + 1][j - 1] = true;
                    pionBlanc[i + 1][j - 1] = false;
                    pionBlanc[i][j] = true;
                }
            }
            
            // Si peux manger en diagonale gauche
            if (i > 0 && pionNoir[i - 1][j - 1]) {
                // Si il gagne en mangeant
                if (j == 1) {
                	return 1;
                } else {
                    pionNoir[i - 1][j - 1] = false;
                    pionBlanc[i - 1][j - 1] = true;
                    pionBlanc[i][j] = false;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_b.containsKey(tab)) {
                    int res = play_noir(pionBlanc, pionNoir);
                    conf_map_b.put(tab, res);}
                    choix.add(conf_map_b.get(tab));
                    pionNoir[i - 1][j - 1] = true;
                    pionBlanc[i - 1][j - 1] = false;
                    pionBlanc[i][j] = true;
                }
            }
            
            // Si pion bloque
            if (choix.isEmpty()) {
            	return null;
            	
            }
            else if(!choix.isEmpty()) {
            	int min = 100000000;
                int res = 0;
            	for(int c : choix) {
            		if (c <= 0) {
            			if (min > Math.abs(c)) {
                            res = Math.abs(c) + 1;
                            min = Math.abs(c);
                        }
            		}

            	}
            	if(res == 0) {
            		int max = choix.get(0);
            		for(int c : choix) {
            			if (max < c) {
            				max = c;
            			}
            		}
            		return -1 * (max + 1);
            	}
            	return res;
            }
            
        }
        return 0;
    }

    /**
     * Cette methode permets de faire toutes les actions possible d'un pion noir
     *
     * @param i         Position du pion sur l'axe des abscisses
     * @param j         Position du pion sur l'axe des ordonees
     * @param pionBlanc le tableau de pion blanc
     * @param pionNoir  le tableau de pion noir
     */
    private static Object choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir) {
    	
        if (j < pionBlanc[0].length - 1) {
        	ArrayList<Integer> choix = new ArrayList<Integer>();
            // Peux avancer
            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
                // Si il gagne en avancant
                if (j == pionNoir[0].length - 2) {
                    return 1;
                } else {
                    // Sinon il avance
                    pionNoir[i][j] = false;
                    pionNoir[i][j + 1] = true;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_n.containsKey(tab)) {
                    int res = play_blanc(pionBlanc, pionNoir);
                    conf_map_n.put(tab, res);}
                    choix.add(conf_map_n.get(tab));
                    pionNoir[i][j] = true;
                    pionNoir[i][j + 1] = false;
                }
            }
            
            // Si peux manger en diagonale gauche
            if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
                // Si il gagne en mangeant
                if (j == pionNoir[0].length - 2) {
                   return 1;
                } else {
                    pionBlanc[i + 1][j + 1] = false;
                    pionNoir[i + 1][j + 1] = true;
                    pionNoir[i][j] = false;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_n.containsKey(tab)) {
                    int res = play_blanc(pionBlanc, pionNoir);
                    conf_map_n.put(tab, res);}
                    choix.add(conf_map_n.get(tab));
                    pionBlanc[i + 1][j + 1] = true;
                    pionNoir[i + 1][j + 1] = false;
                    pionNoir[i][j] = true;
                }
            }
            
            //Si peux manger en diagonale droite
            if (i != 0 && pionBlanc[i - 1][j + 1]) {
                // Si il gagne en mangeant
                if (j == pionNoir[0].length - 2) {
                    return 1;
                } else {
                    pionBlanc[i - 1][j + 1] = false;
                    pionNoir[i - 1][j + 1] = true;
                    pionNoir[i][j] = false;
                    String tab = displayTab(pionBlanc, pionNoir);
                    if(!conf_map_n.containsKey(tab)) {
                    int res = play_blanc(pionBlanc, pionNoir);
                    conf_map_n.put(tab, res);}
                    choix.add(conf_map_n.get(tab));
                    pionBlanc[i - 1][j + 1] = true;
                    pionNoir[i - 1][j + 1] = false;
                    pionNoir[i][j] = true;
                }

            }
            // Pion bloque
         // Si pion bloque
            if (choix.isEmpty()) {
            	return null;
            	
            }
            else if(!choix.isEmpty()) {
            	int min = 100000000;
                int res = 0;
            	for(int c : choix) {
            		if (c <= 0) {
            			if (min > Math.abs(c)) {
                            res = Math.abs(c) + 1;
                            min = Math.abs(c);
                        }
            		}

            	}
            	if(res == 0) {
            		int max = choix.get(0);
            		for(int c : choix) {
            			if (max < c) {
            				max = c;
            			}
            		}
            		return -1 * (max + 1);
            	}
            	return res;
            }
            
        }
		return 0;
    }

}