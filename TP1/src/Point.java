import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Point {

    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int longueur = reader.nextInt();
        int hauteur = reader.nextInt();
        int nb_point = reader.nextInt();
        int i;
        List<Point> list = new ArrayList<>();
        list.add(new Point(0, 0));
        list.add(new Point(0, hauteur));
        for (i = 0; i < nb_point; i++) {
            int x = reader.nextInt();
            int y = reader.nextInt();
            list.add(new Point(x, y));
        }
        list.add(new Point(longueur, 0));
        list.add(new Point(longueur, hauteur));
        reader.close();
		/*list.add(new Point(0, 20));
		list.add(new Point(2, 5));
		list.add(new Point(5, 17));
		list.add(new Point(11, 4));
		list.add(new Point(16, 6));
		list.add(new Point(20, 1));
		list.add(new Point(25, 0));
		list.add(new Point(25, 20));*/

        /*	System.out.println( grandRectangle_n3(list));*/
        System.out.println(grandRectangle_n2(list, hauteur));

    }

    /**
     * Retourne l'aire du plus grand rectangle possible selon une liste de
     * points triées par les ordonnées selon l'exercice 1 avec une complexite de
     * O(n3)
     *
     * @param list
     * @return aire du rectangle le plus grand
     */
    static int grandRectangle_n3(List<Point> list) {
        int airMax = 0;
        int minOrdonne;
        int air;

        Point p1, p2;

        for (Point point1 : list) {
            for (Point point2 : list) {
                if (!point1.equals(point2)) {
                    minOrdonne = minOrdonne(point1, point2, list);
                    air = calculerAirRectangle(point1.x, point2.x, minOrdonne);
                    if (airMax < air) {
                        airMax = air;
                        p1 = point1;
                        p2 = point2;
                    }
                }
            }
        }
        return airMax;
    }

    /**
     * Retourne l'aire du plus grand rectangle possible selon une liste de
     * points triées par les ordonnées selon l'exercice 1 avec une complexite de
     * O(n2)
     *
     * @param list
     * @return
     */
    static int grandRectangle_n2(List<Point> list, int hauteur) {
        int airMax = 0;
        int minOrdonne;
        int air, i, j, k;
        Point point1, point2;
        int hauteur_min;

        for (i = 0; i < list.size(); i++) {
            point1 = list.get(i);
            for (j = i + 1; j < list.size(); j++) {
                point2 = list.get(j);
                hauteur_min = hauteur;
                for (k = i + 1; k < j; k++) {
                    Point p = list.get(k);
                    if (p.y < hauteur_min) {
                        hauteur_min = p.y;
                    }
                }
                minOrdonne = hauteur_min;
                air = calculerAirRectangle(point1.x, point2.x, minOrdonne);
                if (airMax < air) {
                    airMax = air;
                }

            }


        }
        return airMax;
    }

    /**
     * On garde l'ordonnée minimum d'un point comprit entre les abscisses des
     * deux points
     *
     * @param point1 l'un des deux points
     * @param point2 l'autre des deux points
     * @param list   la liste de tous les points
     * @return la plus petite ordonnée représentant un point
     */
    static int minOrdonne(Point point1, Point point2, List<Point> list) {
        boolean premier = true;
        int ordonne = 0;
        for (Point point : list) {
            if (point.x > point1.x && point.x < point2.x) {
                if (premier) {
                    ordonne = point.y;
                    premier = false;
                } else if (ordonne > point.y) {
                    ordonne = point.y;
                }
            }
        }
        return ordonne;
    }

    /**
     * Permet de calculer l'aire du rectangle avec la position des abscisses et
     * de l'ordonnée unique
     *
     * @param x1      la premiere abscisse
     * @param x2      la deuxieme abscisse
     * @param ordonne
     * @return l'air du rectangle
     */
    static int calculerAirRectangle(int x1, int x2, int ordonne) {
        return (x2 - x1) * ordonne;
    }


}
