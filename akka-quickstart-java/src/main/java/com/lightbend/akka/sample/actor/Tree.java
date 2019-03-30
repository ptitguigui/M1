package com.lightbend.akka.sample.actor;

import akka.actor.ActorRef;

import java.util.HashMap;

public abstract class Tree {
    HashMap<String, ActorRef> noeuds;

    public Tree() {
    }


    public void tell(String id, String message) {
        noeuds.get(id).tell(new Node.WhoToTell(message), ActorRef.noSender());
    }
}
