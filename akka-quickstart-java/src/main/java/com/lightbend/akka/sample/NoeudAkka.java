package com.lightbend.akka.sample;

import java.util.List;

import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class NoeudAkka extends AbstractActor {

    static public Props props(String message, ActorRef printerActor, List<ActorRef> fils) {
        return Props.create(NoeudAkka.class, () -> new NoeudAkka(message, printerActor, fils));
    }

    static public class WhoToTell {
        public final String who;

        public WhoToTell(String who) {
            this.who = who;
        }
    }

    static public class Tell {
        public Tell() {
        }
    }

    private final String nameNoeud;
    private final ActorRef printerActor;
    private String msg = "";
    private List<ActorRef> fils;

    public NoeudAkka(String nameNoeud, ActorRef printerActor, List<ActorRef> fils) {
        this.nameNoeud = nameNoeud;
        this.printerActor = printerActor;
        this.fils = fils;
    }

    public void addNoeud(ActorRef actor) {
        this.fils.add(actor);
    }

    public void removeNoeud(ActorRef actor) {
        this.fils.remove(actor);
    }

    @Override
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
