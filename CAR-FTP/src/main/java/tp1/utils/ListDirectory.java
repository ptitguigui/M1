package tp1.utils;

import java.io.File;

public class ListDirectory {

	public static void main(String args[]) {
		File repertoire = new File("/");
		String liste[] = repertoire.list();

		if (liste != null) {
			for (int i = 0; i < liste.length; i++) {
				System.out.println(liste[i]);
			}
		} else {
			System.err.println("Nom de repertoire invalide");
		}
	}

}
