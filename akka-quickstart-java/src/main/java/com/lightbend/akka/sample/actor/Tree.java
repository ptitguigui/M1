package com.lightbend.akka.sample.actor;

import akka.actor.ActorRef;

import java.util.HashMap;

/**
 * Classe représentant un arbre
 */
public abstract class Tree {
    HashMap<String, ActorRef> noeuds;

    /**
     * Constructeur de l'arbre
     */
    public Tree() {
    }

    /**
     * Permet d'envoier le message au noeud choisis, ainsi qu'à ses fils
     * @param id le noeud choisis
     * @param message le message de l'utilisateur
     */
    public void tell(String id, String message) {
        noeuds.get(id).tell(new Node.WhoToTell(message), ActorRef.noSender());
    }
}
