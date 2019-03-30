package com.lightbend.akka.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.lightbend.akka.sample.NoeudAkka.Tell;
import com.lightbend.akka.sample.NoeudAkka.WhoToTell;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class MainAkka {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("arbre");
        try {


            // Arbre arbre = new Arbre();
            ArbreReparti arbre = new ArbreReparti();

            String noeud;
            String message;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("A partir de quel noeud voulez vous lancer la diffusion ?");

            while ((noeud = br.readLine()) != null) {
                if ("QUIT".equals(noeud.toUpperCase()))
                    break;

                System.out.println("Veuillez saisir le message que vous souhaitez envoyer aux différents noeuds : ");
                message = br.readLine();

                arbre.tell(noeud, message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }
}
