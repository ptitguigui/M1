package com.lightbend.akka.sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArbreReparti {
    HashMap<String, ActorRef> noeuds;
    private final ActorSystem system = ActorSystem.create("Arbre");
    private final ActorSystem system2 = ActorSystem.create("Arbre");

    public ArbreReparti() {
        this.noeuds = new HashMap<>();

        final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");

        final ActorRef noeud_3 = system2.actorOf(NoeudAkka.props("noeud_3", printerActor, null), "noeud_3");
        final ActorRef noeud_4 = system2.actorOf(NoeudAkka.props("noeud_4", printerActor, null), "noeud_4");
        List<ActorRef> liste_2 = new ArrayList<ActorRef>();
        liste_2.add(noeud_3);
        liste_2.add(noeud_4);
        final ActorRef noeud_2 = system2.actorOf(NoeudAkka.props("noeud_2", printerActor, liste_2), "noeud_2");
        final ActorRef noeud_6 = system.actorOf(NoeudAkka.props("noeud_6", printerActor, null), "noeud_6");
        List<ActorRef> liste_5 = new ArrayList<ActorRef>();
        liste_5.add(noeud_6);
        final ActorRef noeud_5 = system.actorOf(NoeudAkka.props("noeud_5", printerActor, liste_5), "noeud_5");
        List<ActorRef> liste_1 = new ArrayList<ActorRef>();

        liste_1.add(noeud_5);
        final ActorRef noeud_1 = system.actorOf(NoeudAkka.props("noeud_1", printerActor, liste_1), "noeud_1");

        noeuds.put("1", noeud_1);
        noeuds.put("2", noeud_2);
        noeuds.put("3", noeud_3);
        noeuds.put("4", noeud_4);
        noeuds.put("5", noeud_5);
        noeuds.put("6", noeud_6);
    }


    public void tell(String id, String message) {
        noeuds.get(id).tell(new NoeudAkka.WhoToTell(message), ActorRef.noSender());
    }
}
