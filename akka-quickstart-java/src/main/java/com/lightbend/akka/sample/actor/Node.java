package com.lightbend.akka.sample.actor;

import java.util.List;

import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Node extends AbstractActor {

    /**
     * Créer les propriété du noeud
     *
     * @param message
     * @param printerActor
     * @param fils
     * @return
     */
    static public Props props(String message, ActorRef printerActor, List<ActorRef> fils) {
        return Props.create(Node.class, () -> new Node(message, printerActor, fils));
    }

    /**
     * Classe qui définis le message
     */
    static public class WhoToTell {
        public final String who;

        public WhoToTell(String who) {
            this.who = who;
        }
    }

    /**
     * Classe qui envoie les messages
     */
    static public class Tell {
        public Tell() {
        }
    }

    private final String nameNoeud;
    private final ActorRef printerActor;
    private String msg = "";
    private List<ActorRef> fils;

    /**
     * Constructeur du noeud représentant un acteur composés de ses fils
     *
     * @param nameNoeud
     * @param printerActor
     * @param fils
     */
    public Node(String nameNoeud, ActorRef printerActor, List<ActorRef> fils) {
        this.nameNoeud = nameNoeud;
        this.printerActor = printerActor;
        this.fils = fils;
    }

    /**
     * Le noeud courant recevnant un message, l'envoie à tout ses fils
     *
     * @return
     */
    public Receive createReceive() {
        return receiveBuilder().match(WhoToTell.class, wtg -> {
            if (fils != null) {
                System.out.println("Le Noeud " + this.nameNoeud + " envoie le message " + wtg.who + " à ses fils");
                for (ActorRef actorRef : fils) {
                    actorRef.tell(new WhoToTell(wtg.who), ActorRef.noSender());
                    this.msg = nameNoeud + ", " + wtg.who;
                    actorRef.tell(new Tell(), ActorRef.noSender());
                }
            } else {
                this.msg = nameNoeud + ", " + wtg.who + "\n Le Noeud " + this.nameNoeud + " n'a pas de fils";
            }

        }).match(Tell.class, x -> {
            printerActor.tell(new Greeting(msg), getSelf());
        }).build();
    }

}
