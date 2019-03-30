package com.lightbend.akka.sample;

import com.lightbend.akka.sample.actor.ComplexTree;
import com.lightbend.akka.sample.actor.GraphTree;
import com.lightbend.akka.sample.actor.SimpleTree;
import com.lightbend.akka.sample.actor.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainAkka {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Tree tree = choiceAkka(br);

            String node;
            String message;

            System.out.println("A partir de quel noeud voulez vous lancer la diffusion ?");

            while ((node = br.readLine()) != null) {
                if ("QUIT".equals(node.toUpperCase()))
                    break;

                System.out.println("Veuillez saisir le message que vous souhaitez envoyer: ");
                message = br.readLine();

                tree.tell(node, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Tree choiceAkka(BufferedReader br) throws IOException {
        System.out.println("Choisissez votre application répartie : " +
                "\n 1- tree" +
                "\n 2- tree réparti" +
                "\n 3- GraphTree");
        String choice = br.readLine();
        Tree tree = null;

        switch (choice) {
            case "1":
                tree = new SimpleTree();
                break;
            case "2":
                tree = new ComplexTree();
                break;
            case "3":
                tree = new GraphTree();
                break;
            default:
                System.out.println("Veuillez choisir un choix correcte");
                choiceAkka(br);
                break;
        }
        return tree;
    }
}
