package com.lightbend.akka.sample.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.lightbend.akka.sample.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphTree extends Tree {
    HashMap<String, ActorRef> noeuds;
    private final ActorSystem system = ActorSystem.create("Tree");

    /**
     * Constructeur de l'arbre représentant un graphe
     */
    public GraphTree() {
        this.noeuds = new HashMap<>();

        final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");

        final ActorRef noeud_3 = system.actorOf(Node.props("noeud_3", printerActor, null), "noeud_3");
        final ActorRef noeud_4 = system.actorOf(Node.props("noeud_4", printerActor, null), "noeud_4");
        List<ActorRef> liste_2 = new ArrayList<>();
        liste_2.add(noeud_3);
        liste_2.add(noeud_4);
        final ActorRef noeud_2 = system.actorOf(Node.props("noeud_2", printerActor, liste_2), "noeud_2");
        List<ActorRef> liste_4 = new ArrayList<>();
        liste_4.add(noeud_4);
        final ActorRef noeud_6 = system.actorOf(Node.props("noeud_6", printerActor, liste_4), "noeud_6");
        List<ActorRef> liste_5 = new ArrayList<>();
        liste_5.add(noeud_6);
        final ActorRef noeud_5 = system.actorOf(Node.props("noeud_5", printerActor, liste_5), "noeud_5");
        List<ActorRef> liste_1 = new ArrayList<>();
        liste_1.add(noeud_2);
        liste_1.add(noeud_5);
        final ActorRef noeud_1 = system.actorOf(Node.props("noeud_1", printerActor, liste_1), "noeud_1");

        noeuds.put("1", noeud_1);
        noeuds.put("2", noeud_2);
        noeuds.put("3", noeud_3);
        noeuds.put("4", noeud_4);
        noeuds.put("5", noeud_5);
        noeuds.put("6", noeud_6);
    }

    /**
     * Permet d'envoier le message au noeud choisis, ainsi qu'à ses fils
     *
     * @param id      le noeud choisis
     * @param message le message de l'utilisateur
     */
    public void tell(String id, String message) {
        noeuds.get(id).tell(new Node.WhoToTell(message), ActorRef.noSender());
    }
}
