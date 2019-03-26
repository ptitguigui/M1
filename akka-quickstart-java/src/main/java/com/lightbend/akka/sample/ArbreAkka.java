package com.lightbend.akka.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.lightbend.akka.sample.NoeudAkka.Tell;
import com.lightbend.akka.sample.NoeudAkka.WhoToTell;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class ArbreAkka {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("arbre");
		try {

			HashMap<Integer, ActorRef> map = new HashMap<>();
			// #create-actors
			final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");
			final ActorRef noeud_3 = system.actorOf(NoeudAkka.props("noeud_3", printerActor, null), "noeud_3");
			final ActorRef noeud_4 = system.actorOf(NoeudAkka.props("noeud_4", printerActor, null), "noeud_4");
			List<ActorRef> liste_2 = new ArrayList<ActorRef>();
			liste_2.add(noeud_3);
			liste_2.add(noeud_4);
			final ActorRef noeud_2 = system.actorOf(NoeudAkka.props("noeud_2", printerActor, liste_2), "noeud_2");
			final ActorRef noeud_6 = system.actorOf(NoeudAkka.props("noeud_6", printerActor, null), "noeud_6");
			List<ActorRef> liste_5 = new ArrayList<ActorRef>();
			liste_5.add(noeud_6);
			final ActorRef noeud_5 = system.actorOf(NoeudAkka.props("noeud_5", printerActor, liste_5), "noeud_5");
			List<ActorRef> liste_1 = new ArrayList<ActorRef>();
			liste_1.add(noeud_2);
			liste_1.add(noeud_5);
			final ActorRef noeud_1 = system.actorOf(NoeudAkka.props("noeud_1", printerActor, liste_1), "noeud_1");

			map.put(1, noeud_1);
			map.put(2, noeud_2);
			map.put(3, noeud_3);
			map.put(4, noeud_4);
			map.put(5, noeud_5);
			map.put(6, noeud_6);

			Scanner scanner = new Scanner(System.in);

			String response;
			System.out.println("A partir de quel noeud voulez vous lancer la diffusion ?");
			do {
				response = scanner.next();

				if (!response.equals("q")) {
					ActorRef noeud = map.get(Integer.parseInt(response));
					noeud.tell(new WhoToTell("Akka"), ActorRef.noSender());
					noeud.tell(new Tell(), ActorRef.noSender());
				}
			} while (!response.equals("q"));

		} finally {
			system.terminate();
		}
	}
}
