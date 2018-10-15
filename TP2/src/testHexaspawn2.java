import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.sun.corba.se.impl.util.Version;
import com.sun.xml.internal.messaging.saaj.util.ParseUtil;

import sun.util.resources.cldr.pt.CalendarData_pt_ST;

public class testHexaspawn2 {
    //public static boolean fini;
    //public static boolean victoireBlanc;
    //public static int cpt;
    // public static List<Integer> config = new ArrayList<>();

    public static void main(String[] args) {
        //fini = false;
        //cpt = 0;
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
    	List<Integer> config = new ArrayList<Integer>();
    	int config_final = play(pionBlanc, pionNoir, tour, false, config);
        System.out.println(config_final);
       }

    private static int play(boolean[][] pionBlanc, boolean[][] pionNoir, boolean tour, boolean fini, List<Integer> config) {
        boolean tourBlanc = tour;
        if (!fini) {
            for (int i = 0; i < pionBlanc.length; i++) {
                for (int j = 0; j < pionBlanc[0].length; j++) {
                    if (tourBlanc) {
                        if (pionBlanc[i][j]) {
                            choixPionBlanc(i, j, pionBlanc, pionNoir, config);
                            
                        }
                    } else {
                        if (pionNoir[i][j]) {
                            choixPionNoir(i, j, pionBlanc, pionNoir, config);
                        }
                    }
                }
            }
        }
        List<Integer> negatifs = new ArrayList<>();
		for (int i : config) {
        	if( i <= 0) {
        		negatifs.add(i);
        	}
        }
		if(negatifs.size() == 0) {
			int max = 0;
			for(int j : config) {
				max = Math.max(max , j);
			}
			return -(max + 1);
		}
		else {
			int max = -100;
			for(int j : negatifs) {
				max = Math.max(max , j);
			}
			return -(max) + 1;
		}
    }

    private static void choixPionBlanc(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, List<Integer> config) {
    	List<Integer> safe =new ArrayList<Integer>();   
    	safe.addAll(config);
    	boolean continu = true;
    	for(int k=0 ; k<pionBlanc.length; k++) {
    		if(pionNoir[k][pionBlanc[0].length - 1]) {
    			config.add(1);
    			play(pionBlanc, pionNoir, false, true, config);
    			continu = false;
    			break;
    		}
    	};
    	/*if (j == 0) {
    		config.add(0);
            play(pionBlanc, pionNoir, false, true, config);
            //fini = true;
            //victoireBlanc = true;
        }*/
        //Peux bouger
    	if(continu) {
	    	if (j > 0) {
	    		// peut avancer Version l'avant
	            if (!pionNoir[i][j - 1] && !pionBlanc[i][j - 1]) {
	            	config = safe;
	                pionBlanc[i][j] = false;
	                pionBlanc[i][j - 1] = true;
	                config.add(play(pionBlanc, pionNoir, false,false, config));
	                pionBlanc[i][j] = true;
	                pionBlanc[i][j - 1] = false;
	            }
	            //Peux manger en diagonale droite
	            if (i < pionNoir.length - 1 && pionNoir[i + 1][j - 1]) {
	            	config = safe;
	                pionNoir[i + 1][j - 1] = false;
	                pionBlanc[i + 1][j - 1] = true;
	                pionBlanc[i][j] = false;
	                config.add(play(pionBlanc, pionNoir, false,false, config));
	                pionNoir[i + 1][j - 1] = true;
	                pionBlanc[i + 1][j - 1] = false;
	                pionBlanc[i][j] = true;
	            }
	            //Peux manger en diagonale gauche
	            if (i != 0 && pionNoir[i - 1][j - 1]) {
	            	config = safe;
	                pionNoir[i - 1][j - 1] = false;
	                pionBlanc[i - 1][j - 1] = true;
	                pionBlanc[i][j] = false;
	                config.add(play(pionBlanc, pionNoir, false,false, config));
	                pionNoir[i - 1][j - 1] = true;
	                pionBlanc[i - 1][j - 1] = false;
	                pionBlanc[i][j] = true;
	            } 
           
            
        }
    }
    	
        //System.out.println(displayTab(pionBlanc, pionNoir));
    }

    private static void choixPionNoir(int i, int j, boolean[][] pionBlanc, boolean[][] pionNoir, List<Integer> config) {
    	List<Integer> safe =new ArrayList<Integer>();   
    	safe.addAll(config);
    	boolean continu = true;
    	for(int k=0 ; k<pionBlanc.length; k++) {
    		if(pionNoir[k][0]) {
    			config.add(-1);
    			play(pionBlanc, pionNoir, true, true, config);
    			continu = false;
    			break;
    		}
    	};
    	/*if (j == pionBlanc[0].length - 1){
            config.add(1);
            //fini = true;
            //victoireBlanc = false;
            play(pionBlanc, pionNoir, true, true, config);
        }*/
        //Peux avancer
    	if(continu){
	    	if (j < pionBlanc[0].length - 1) {
	            if (!pionBlanc[i][j + 1] && !pionNoir[i][j + 1]) {
	            	config = safe;
	                pionNoir[i][j] = false;
	                pionNoir[i][j + 1] = true;
	                //cpt++;
	                config.add(play(pionBlanc, pionNoir, true, false, config));
	                pionNoir[i][j] = true;
	                pionNoir[i][j + 1] = false;
	            }
	            //Peux manger en diagonale gauche
	            if (i < pionBlanc.length - 1 && pionBlanc[i + 1][j + 1]) {
	            	config = safe;
	                pionBlanc[i + 1][j + 1] = false;
	                pionNoir[i + 1][j + 1] = true;
	                pionNoir[i][j] = false;
	                //cpt++;
	                config.add(play(pionBlanc, pionNoir, true, false, config));
	                pionBlanc[i + 1][j + 1] = true;
	                pionNoir[i + 1][j + 1] = false;
	                pionNoir[i][j] = true;
	            }
	            //Peux manger en diagonale droite
	            if (i != 0 && pionBlanc[i - 1][j + 1]) {
	            	config = safe;
	                pionBlanc[i - 1][j + 1] = false;
	                pionNoir[i - 1][j + 1] = true;
	                pionNoir[i][j] = false;
	                //cpt++;
	                config.add(play(pionBlanc, pionNoir, true, false, config));
	                pionBlanc[i - 1][j + 1] = true;
	                pionNoir[i - 1][j + 1] = false;
	                pionNoir[i][j] = true;
	            } 
            }
            //System.out.println(displayTab(pionBlanc, pionNoir));
        } 
    }
}