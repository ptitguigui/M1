package com.lightbend.akka.sample.actor;

import java.util.HashMap;
import java.util.List;

import javax.xml.crypto.Data;

import com.lightbend.akka.sample.Printer.Greeting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
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
	static public Props props(String message, ActorRef printerActor, List<String> fils, ActorSystem system) {
		return Props.create(Node.class, () -> new Node(message, printerActor, fils, system));
	}

	/**
	 * Classe qui définis le message
	 */
	static public class WhoToTell {
		public final String who;
		public final HashMap<String, ActorRef> noeuds;
		public HashMap<String, List<String>> Data;
		public HashMap<String, Boolean> Visited;

		public WhoToTell(String who, HashMap<String, ActorRef> noeuds, HashMap<String, List<String>> Data,
				HashMap<String, Boolean> Visited) {
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
	private String msg;
	private List<String> fils;
	private final ActorSystem system;

	/**
	 * Constructeur du noeud représentant un acteur composés de ses fils
	 *
	 * @param nameNoeud : nom du noeud
	 * @param           printerActor: acteur chargé de print
	 * @param           fils: liste de nom des noeud relié au noeud courent
	 */
	public Node(String nameNoeud, ActorRef printerActor, List<String> fils, ActorSystem system) {
		this.nameNoeud = nameNoeud;
		this.printerActor = printerActor;
		this.fils = fils;
		this.msg = "";
		this.system = system;
	}

	/**
	 * Le noeud courant recevant un message, l'envoie à tout ses fils
	 *
	 * @return
	 */
	public Receive createReceive() {
		return receiveBuilder().match(WhoToTell.class, wtg -> {
			if (fils != null) {
				for (int i = 0; i < fils.size(); i++) {
					if (!wtg.Visited.get(fils.get(i))) {
						ActorRef actorRef = wtg.noeuds.get(fils.get(i));
						wtg.Visited.replace(fils.get(i), true);
						if (wtg.who.contains("delete")) {
							delete(wtg, i);
						}
//						else if (wtg.who.contains("add")) {
//
//						}
						System.out.println(
								"Le " + this.nameNoeud + " envoie le message " + wtg.who + " au noeud_" + fils.get(i));
						actorRef.tell(new WhoToTell(wtg.who, wtg.noeuds, wtg.Data, wtg.Visited), ActorRef.noSender());
						if (wtg.Data.containsKey(wtg.who.substring(wtg.who.length() - 1))
								&& wtg.Data.get(fils.get(i)) != null) {
							if (wtg.Data.get(fils.get(i)).contains(wtg.who.substring(wtg.who.length() - 1))
									&& wtg.Data.get(wtg.who.substring(wtg.who.length() - 1)) == null) {
								wtg.Data.get(fils.get(i)).remove(wtg.who.substring(wtg.who.length() - 1));
								System.out.println("Le noeud_" + wtg.who.substring(wtg.who.length() - 1)
										+ " est supprimé dans la liste des noeuds relié au noeud_" + fils.get(i));
							}
						}
					}
				}
				this.msg = this.nameNoeud + ", " + wtg.who;
				getSelf().tell(new Tell(), ActorRef.noSender());
				if (wtg.who.contains("delete") && nameNoeud.contains(wtg.who.substring(wtg.who.length() - 1))) {
					System.out.println("le systeme arrete l'acteur associé au noeud noeud_"
							+ wtg.who.substring(wtg.who.length() - 1));
					system.stop(getSelf());
				}

			} else {
				this.msg = nameNoeud + ", " + wtg.who + "\n Le " + this.nameNoeud + " n'a pas de fils";
				getSelf().tell(new Tell(), ActorRef.noSender());
				if (wtg.who.contains("delete") && nameNoeud.contains(wtg.who.substring(wtg.who.length() - 1))) {
					System.out.println("le systeme arrete l'acteur associé au noeud noeud_"
							+ wtg.who.substring(wtg.who.length() - 1));
					system.stop(getSelf());
				}

			}

		}).match(Tell.class, x -> {
			printerActor.tell(new Greeting(msg), getSelf());
		}).build();
	}

	/**
	 * gère la suppression d'un noeud
	 * 
	 * @param wtg WhotoTell object
	 * @param i
	 */
	private void delete(WhoToTell wtg, int i) {

		String noeud = wtg.who.substring(wtg.who.length() - 1);
		if (wtg.Data.get(fils.get(i)) != null) {
			if (wtg.Data.get(fils.get(i)).contains(noeud)) {
				if (wtg.Data.get(noeud) != null) {
					if (wtg.Data.get(noeud).size() > 1) {
						if (wtg.Data.get(noeud).get(0) != fils.get(i)) {
							wtg.Data.get(fils.get(i)).set(wtg.Data.get(fils.get(i)).indexOf(noeud),
									wtg.Data.get(noeud).get(0));
							System.out.println(
									"Le noeud_" + noeud + " est remplacé par le noeud_" + wtg.Data.get(noeud).get(0)
											+ " dans la liste des noeuds relié au noeud_" + fils.get(i));
						}
					} else if (wtg.Data.get(noeud).size() == 1) {
						wtg.Data.get(fils.get(i)).remove(noeud);
						System.out.println("Le noeud_" + noeud
								+ " est supprimé dans la liste des noeuds relié au noeud_" + fils.get(i));
					}
				} // else {
//					wtg.Data.get(fils.get(i)).remove(noeud);
//					System.out.println("Le noeud_" + noeud + " est supprimé dans la liste des noeuds relié au noeud_"
//							+ fils.get(i));
//				}
//				if (wtg.noeuds.containsKey(noeud)) {
//					wtg.noeuds.remove(noeud);
//					System.out.println("Le noeud_" + noeud + " est supprimé dans la liste des noeuds");
//				}
			}
		}
		if (!wtg.Visited.containsValue(false)) {
			if (wtg.Data.get(noeud) != null) {
				wtg.Data.get(wtg.Data.get(noeud).get(0)).remove(noeud);
				wtg.Data.get(wtg.Data.get(noeud).get(0))
						.addAll(wtg.Data.get(noeud).subList(1, wtg.Data.get(noeud).size()));
				System.out.println("Le noeud_" + wtg.who.substring(wtg.who.length() - 1)
						+ " est supprimé dans la liste des noeuds relié au noeud_" + wtg.Data.get(noeud).get(0));
				System.out.println("Les noeuds" + " reliés au noeud_" + wtg.Data.get(noeud).get(0) + " deviennent "
						+ wtg.Data.get(wtg.Data.get(noeud).get(0)));
			}

			wtg.Data.remove(noeud);
			wtg.Visited.remove(noeud);
			wtg.noeuds.remove(noeud);
			System.out.println("Le noeud_ " + noeud + " n'est plus accessible dans les datas, noeuds et Visited");
		}
	}

}
