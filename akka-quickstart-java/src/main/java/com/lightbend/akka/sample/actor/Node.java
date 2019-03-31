package com.lightbend.akka.sample.actor;

import java.util.HashMap;
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
    static public Props props(String message, ActorRef printerActor, List<String> fils) {
        return Props.create(Node.class, () -> new Node(message, printerActor, fils));
    }

    /**
     * Classe qui définis le message
     */
    static public class WhoToTell {
        public final String who;
        public final HashMap<String, ActorRef> noeuds;
        public HashMap<String, List<String>> Data;
        public HashMap<String, Boolean> Visited;

        public WhoToTell(String who, HashMap<String, ActorRef> noeuds, HashMap<String, List<String>> Data, HashMap<String, Boolean> Visited) {
            this.who = who;
            this.noeuds = noeuds;
            this.Data = Data;
            this.Visited = Visited;
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
    private List<String> fils;

    /**
     * Constructeur du noeud représentant un acteur composés de ses fils
     *
     * @param nameNoeud
     * @param printerActor
     * @param fils
     */
    public Node(String nameNoeud, ActorRef printerActor, List<String> fils) {
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
                for (int i = 0 ; i < fils.size(); i++) {
                	if(!wtg.Visited.get(fils.get(i))) {
                		ActorRef actorRef = wtg.noeuds.get(fils.get(i));
                		wtg.Visited.replace(fils.get(i), true);
                		System.out.println("Le Noeud " + this.nameNoeud + " envoie le message " + wtg.who + " au Noeud noeud_" + fils.get(i));
                        actorRef.tell(new WhoToTell(wtg.who, wtg.noeuds, wtg.Data, wtg.Visited), ActorRef.noSender());
                        this.msg = nameNoeud + ", " + wtg.who;
                        actorRef.tell(new Tell(), ActorRef.noSender());
                        }                	
                }
            } else {
                this.msg = nameNoeud + ", " + wtg.who + "\n Le Noeud " + this.nameNoeud + " n'a pas de fils";
            }

        }).match(Tell.class, x -> {
            printerActor.tell(new Greeting(msg), getSelf());
        }).build();
    }

}
