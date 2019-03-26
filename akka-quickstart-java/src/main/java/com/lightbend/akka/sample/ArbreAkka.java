package com.lightbend.akka.sample;
import java.io.IOException;

import com.lightbend.akka.sample.NoeudAkka.Tell;
import com.lightbend.akka.sample.NoeudAkka.WhoToTell;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
public class ArbreAkka {
	public static void main(String[] args) {
	    final ActorSystem system = ActorSystem.create("arbre");
	    try {
	        //#create-actors
	        final ActorRef printerActor = 
	          system.actorOf(Printer.props(), "printerActor");
	        final ActorRef noeud_3= 
		  	          system.actorOf(NoeudAkka.props("noeud_3", printerActor, null, null), "noeud_3");
	  	    final ActorRef noeud_4 = 
	  	    	  system.actorOf(NoeudAkka.props("noeud_4", printerActor, null, null), "noeud_4");
	        final ActorRef noeud_2= 
	  	          system.actorOf(NoeudAkka.props("noeud_2", printerActor, noeud_3, noeud_4), "noeud_2");
	        final ActorRef noeud_6= 
		  	          system.actorOf(NoeudAkka.props("noeud_6", printerActor, null, null), "noeud_6");
	  	    final ActorRef noeud_5 = 
	  	    	  system.actorOf(NoeudAkka.props("noeud_5", printerActor, noeud_6, null), "noeud_5");
	        final ActorRef noeud_1 = 
	          system.actorOf(NoeudAkka.props("noeud_1", printerActor, noeud_2, noeud_5 ), "noeud_1");
	        
	        //#create-actors

	        //#main-send-messages
	        noeud_1.tell(new WhoToTell("Akka"), ActorRef.noSender());
	        noeud_1.tell(new Tell(), ActorRef.noSender());

	        //#main-send-messages

	        System.out.println(">>> Press ENTER to exit <<<");
	        System.in.read();
	      } catch (IOException ioe) {
	      } finally {
	        system.terminate();
	      }
	    }
}
