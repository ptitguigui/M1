package com.lightbend.akka.sample;


import com.lightbend.akka.sample.NoeudAkka.Tell;
import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class NoeudAkka extends AbstractActor {

	static public Props props(String message, ActorRef printerActor, ActorRef filsG, ActorRef filsD) {
		return Props.create(NoeudAkka.class, () -> new NoeudAkka(message, printerActor, filsG, filsD));
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

	// #greeter-messages

	private final String message;
	private final ActorRef printerActor;
	private String msg = "";
	private ActorRef filsG;
	private ActorRef filsD;

	public NoeudAkka(String message, ActorRef printerActor, ActorRef filsG, ActorRef filsD) {
		this.message = message;
		this.printerActor = printerActor;
		this.filsD = filsD;
		this.filsG = filsG;
	}

	@Override
	public Receive createReceive() {
		// TODO Auto-generated method stub
		return receiveBuilder().match(WhoToTell.class, wtg -> {
			if (this.filsG != null) {
				System.out.println("Le Noeud "+ this.message + "reçoit le message");
				this.filsG.tell(new WhoToTell(wtg.who), ActorRef.noSender());
				this.filsG.tell(new Tell(), ActorRef.noSender());
				this.msg = message + ", " + wtg.who;
			}
			if (this.filsD != null) {
				System.out.println("Le Noeud "+ this.message + "reçoit le message");
				this.filsD.tell(new WhoToTell(wtg.who), ActorRef.noSender());
				this.filsD.tell(new Tell(), ActorRef.noSender());
				this.msg = message + ", " + wtg.who;
			}

			if (this.filsD == null && this.filsG == null) {
				this.msg = message + ", " + wtg.who + "\n Le Noeud " + this.message + " n'a pas de fils";
				
			}
			
		}).match(Tell.class, x -> {
			// #greeter-send-message
			printerActor.tell(new Greeting(msg), getSelf());
			// #greeter-send-message
		}).build();
	}

}
