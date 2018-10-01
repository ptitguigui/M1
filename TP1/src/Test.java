import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public int x;
    public int y;

    Test(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int longueur = reader.nextInt();
        int hauteur = reader.nextInt();
        int nb_point = reader.nextInt();
        int min = hauteur;
        int maxOrdonnee = 0;
        int maxAire = 0;
        List<Test> list = new ArrayList<>();

        list.add(new Test(0, 0));
        list.add(new Test(0, hauteur));

        for (int i = 0; i < nb_point; i++) {
            int x = reader.nextInt();
            int y = reader.nextInt();
            if (y > maxOrdonnee) {
                maxOrdonnee = y;
            }
            if (y < min) {
                list.add(new Test(x, y));
                maxAire = Math.max(maxAire, grandRectangle_n(list, maxOrdonnee));

                list = new ArrayList<>();
                list.add(new Test(x, y));
                min = y;
                maxOrdonnee = y;
            } else {
                list.add(new Test(x, y));
            }
        }
        list.add(new Test(longueur, 0));
        list.add(new Test(longueur, hauteur));
        reader.close();

        maxAire = Math.max(maxAire, grandRectangle_n(list, hauteur));

        System.out.println(maxAire);

    }

    static int grandRectangle_n(List<Test> list, int maxOrdonnee) {
        //System.out.println(calculerAirRectangle(list.get(0).x, list.get(list.size() - 1).x, maxOrdonnee));
        return calculerAirRectangle(list.get(0).x, list.get(list.size() - 1).x, maxOrdonnee);
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
