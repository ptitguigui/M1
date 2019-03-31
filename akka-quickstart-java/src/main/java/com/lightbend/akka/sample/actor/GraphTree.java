package com.lightbend.akka.sample.actor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.lightbend.akka.sample.Printer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class GraphTree extends Tree {
	private HashMap<String, ActorRef> noeuds;
	private final ActorSystem system = ActorSystem.create("Tree");
	private HashMap<String, List<String>> Data = new HashMap<>();
	private HashMap<String, Boolean> Visited = new HashMap<>();
	private final ActorRef printerActor;

	/**
	 * Constructeur de l'arbre représentant un graphe
	 * 
	 * @throws IOException
	 */
	public GraphTree(BufferedReader br) throws IOException {
		this.noeuds = new HashMap<>();
		printerActor = system.actorOf(Printer.props(), "printerActor");
		System.out.println("Combien de noeuds voulez-vous créer?");
		String nb_node = br.readLine();
		for (int i = 1; i <= Integer.parseInt(nb_node); i++) {
			List<String> l_nodes = new ArrayList<>();
			System.out.println("Veuillez saisir les numeros de noeuds reliés au noeud " + Integer.toString(i)
					+ " \n sous forme de numero séparé de ," + "\n si noeud sans fils put none");
			String nodes = br.readLine();
			if (nodes.equals("none")) {
				l_nodes = null;
			} else {
				if (nodes.contains(",")) {
					l_nodes = new ArrayList<String>(Arrays.asList(nodes.split(",")));
				} else {
					l_nodes.add(nodes);
				}

			}
			Data.put(Integer.toString(i), l_nodes);

		}
		initiate(printerActor);

//        String num = Integer.toString(i);
//    	final ActorRef node = system.actorOf(Node.props("noeud_"+num, printerActor, null), "noeud_"+num);
//    	noeuds.put(num, node);
//		final ActorRef noeud_3 = system.actorOf(Node.props("noeud_3", printerActor, null), "noeud_3");
//		final ActorRef noeud_4 = system.actorOf(Node.props("noeud_4", printerActor, null), "noeud_4");
//		List<ActorRef> liste_2 = new ArrayList<>();
//		liste_2.add(noeud_3);
//		liste_2.add(noeud_4);
//		final ActorRef noeud_2 = system.actorOf(Node.props("noeud_2", printerActor, liste_2), "noeud_2");
//		List<ActorRef> liste_4 = new ArrayList<>();
//		liste_4.add(noeud_4);
//		final ActorRef noeud_6 = system.actorOf(Node.props("noeud_6", printerActor, liste_4), "noeud_6");
//		List<ActorRef> liste_5 = new ArrayList<>();
//		liste_5.add(noeud_6);
//		final ActorRef noeud_5 = system.actorOf(Node.props("noeud_5", printerActor, liste_5), "noeud_5");
//		List<ActorRef> liste_1 = new ArrayList<>();
//		liste_1.add(noeud_2);
//		liste_1.add(noeud_5);
//		final ActorRef noeud_1 = system.actorOf(Node.props("noeud_1", printerActor, liste_1), "noeud_1");
//
//		noeuds.put("1", noeud_1);
//		noeuds.put("2", noeud_2);
//		noeuds.put("3", noeud_3);
//		noeuds.put("4", noeud_4);
//		noeuds.put("5", noeud_5);
//		noeuds.put("6", noeud_6);
	}

	/**
	 * Permet d'envoier le message au noeud choisis, ainsi qu'à ses fils
	 *
	 * @param id      le noeud choisis
	 * @param message le message de l'utilisateur
	 */
	public void tell(String id, String message) {
		initialize_visited();
		this.Visited.replace(id, true);
		this.noeuds.get(id).tell(new Node.WhoToTell(message, this.noeuds, this.Data, this.Visited),
				ActorRef.noSender());
	}

	public void initiate(ActorRef printerActor) {
		for (String id : Data.keySet()) {
			final ActorRef noeud = system.actorOf(Node.props("noeud_" + id, printerActor, Data.get(id)), "noeud_" + id);
			this.noeuds.put(id, noeud);
			this.Visited.put(id, false);
		}
	}

	public void initialize_visited() {
		for (String id : this.Data.keySet()) {
			this.Visited.put(id, false);
		}
	}
}
