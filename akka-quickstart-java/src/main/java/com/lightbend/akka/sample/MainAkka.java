package com.lightbend.akka.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lightbend.akka.sample.actor.ComplexTree;
import com.lightbend.akka.sample.actor.GraphTree;
import com.lightbend.akka.sample.actor.Tree;

/**
 * Main application, execute une application selon le choix de l'utilisateur
 */
public class MainAkka {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Tree tree = choiceAkka(br);

            String node;
            String message;
//            String delete;

            while (!br.readLine().equals("QUIT")) {
                System.out.println("A partir de quel noeud voulez vous lancer la diffusion ?");
                node = br.readLine();
                System.out.println("Veuillez saisir le message que vous souhaitez envoyer: ");
                message = br.readLine();
//                System.out.println("Voulez-vous supprimer un noeud? si oui indiquez le numero si non tapez non");
//                delete = br.readLine();
//                if(!delete.equals("non")) {
//                	tree.delete(delete);
//                }

                tree.tell(node, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de déterminer l'arbre choisis selon l'utilisateur
     * Il y a trois choix possible à savoir :
     * - Arbre avec un systeme
     * - Arbre avec deux systemes
     * - Arbre représentant un graphe
     *
     * @param br le reader
     * @return l'abre choisis
     * @throws IOException
     */
    private static Tree choiceAkka(BufferedReader br) throws IOException {
        System.out.println("Choisissez votre application répartie : " +
                "\n 1- tree ou GraphTree" +
                "\n 2- tree réparti");
        String choice = br.readLine();
        Tree tree = null;

        switch (choice) {
            case "1":
                tree = new GraphTree(br);
                break;
            case "2":
                tree = new ComplexTree();
                break;
            default:
                System.out.println("Veuillez choisir un choix correcte");
                choiceAkka(br);
                break;
        }
        return tree;
    }
}
