import java.util.ArrayList;
import java.util.List;

public class Algorithms {

	public static void main(String[] args) {

		List<Point> list = new ArrayList<>();
		list.add(new Point(0, 0));
		list.add(new Point(0, 20));
		list.add(new Point(2, 5));
		list.add(new Point(5, 17));
		list.add(new Point(11, 4));
		list.add(new Point(16, 6));
		list.add(new Point(20, 1));
		list.add(new Point(25, 0));
		list.add(new Point(25, 20));

		System.out.println("Avec n3 : \n" + grandRectangle_n3(list));
		System.out.println("Avec n2 : \n" + grandRectangle_n2(list));

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

		Point p1 = null;
		Point p2 = null;

		for (Point point1 : list) {
			for (Point point2 : list) {
				if (!point1.equals(point2)) {
					minOrdonne = minOrdonne(point1, point2, list);
					air = airRectangle(point1.x, point2.x, minOrdonne);
					if (airMax < air) {
						airMax = air;
						p1 = point1;
						p2 = point2;
					}
				}
			}
		}
		System.out.println("Point 1 : " + p1.x + "," + p1.y);
		System.out.println("Point 2 : " + p2.x + "," + p2.y);

		return airMax;
	}

	static int grandRectangle_n2(List<Point> list) {
		int airMax = 0;
		int minOrdonne;
		boolean premier;
		int air;

		for (Point point1 : list) {
			for (Point point2 : list) {
				if(list.indexOf(point1) < list.indexOf(point2)){
					
				}
				if (point2.y < minOrdonne) {
					minOrdonne = point2.y;
					air = airRectangle(point1.x, point2.x, minOrdonne);
					if (airMax < air) {
						airMax = air;
					}
				}
			}
		}

		return airMax;
	}

	/**
	 * On garde l'ordonnée minimum d'un point comprit entre les abscisses des
	 * deux points
	 * 
	 * @param point1
	 *            l'un des deux points
	 * @param point2
	 *            l'autre des deux points
	 * @param list
	 *            la liste de tous les points
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
	 * @param x1
	 *            la premiere abscisse
	 * @param x2
	 *            la deuxieme abscisse
	 * @param ordonne
	 * @return l'air du rectangle
	 */
	static int airRectangle(int x1, int x2, int ordonne) {
		return (x2 - x1) * ordonne;
	}

}
