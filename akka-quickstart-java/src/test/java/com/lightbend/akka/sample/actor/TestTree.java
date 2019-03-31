package com.lightbend.akka.sample.actor;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lightbend.akka.sample.Printer.Greeting;
import com.lightbend.akka.sample.actor.Node.Tell;
import com.lightbend.akka.sample.actor.Node.WhoToTell;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;

public class TestTree {
	static ActorSystem system, system_;

	@BeforeClass
	public static void setup() {
		system = ActorSystem.create();
		system_ = ActorSystem.create();
	}

	@AfterClass
	public static void teardown() {
		TestKit.shutdownActorSystem(system);
		system = null;
		TestKit.shutdownActorSystem(system_);
		system_ = null;
	}

	@Test
	public void test_1() throws NoSuchMethodException, SecurityException {
		final TestKit testProbe = new TestKit(system);
		HashMap<String, ActorRef> noeuds = new HashMap<>();
		HashMap<String, List<String>> data = new HashMap<>();
		HashMap<String, Boolean> Visited = new HashMap<>();
		List<String> list = new ArrayList<>();
		list.add("2");
		final ActorRef noeud_2 = system.actorOf(Node.props("noeud_2", testProbe.getRef(), null), "noeud_2");
		final ActorRef actorref = system.actorOf(Node.props("noeud_1", testProbe.getRef(), list), "noeud_1");
		noeuds.put("1", actorref);
		noeuds.put("2", noeud_2);

		Visited.put("1", true);
		Visited.put("2", false);

		data.put("1", list);
		data.put("2", null);

		actorref.tell(new WhoToTell("Akka", noeuds, data, Visited), ActorRef.noSender());
		actorref.tell(new Tell(), ActorRef.noSender());
		Greeting greeting = testProbe.expectMsgClass(Greeting.class);
		assertTrue(String.join("", greeting.message).contains("noeud_2, Akka")
				|| String.join("", greeting.message).contains("noeud_1, Akka"));
		Greeting greeting_ = testProbe.expectMsgClass(Greeting.class);
		assertTrue(String.join("", greeting_.message).contains("noeud_1, Akka")
				|| String.join("", greeting_.message).contains("noeud_2, Akka"));
		assertTrue(!String.join("", greeting_.message).equals(String.join("", greeting.message)));
	}

//	@Test
//	public void test_2() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
//
//		final TestKit testProbe = new TestKit(system_);
//		HashMap<String, ActorRef> noeuds = new HashMap<>();
//		HashMap<String, List<String>> data = new HashMap<>();
//		HashMap<String, Boolean> Visited = new HashMap<>();
//		List<String> list = new ArrayList<>();
//		list.add("2");
//		List<String> list_1 = new ArrayList<>();
//		list_1.add("3");
//		final ActorRef noeud_33 = system_.actorOf(Node.props("noeud_33", testProbe.getRef(), null), "noeud_33");
//		final ActorRef noeud_22 = system_.actorOf(Node.props("noeud_22", testProbe.getRef(), list_1), "noeud_22");
//		final ActorRef actorref = system_.actorOf(Node.props("noeud_11", testProbe.getRef(), list), "noeud_11");
//		noeuds.put("1", actorref);
//		noeuds.put("2", noeud_22);
//		noeuds.put("3", noeud_33);
//
//		Visited.put("1", true);
//		Visited.put("2", false);
//		Visited.put("3", false);
//
//		data.put("1", list);
//		data.put("2", list_1);
//		data.put("3", null);
//
//		actorref.tell(new WhoToTell("Akka", noeuds, data, Visited), ActorRef.noSender());
//		actorref.tell(new Tell(), ActorRef.noSender());
//		Object Response = testProbe.exp(Greeting.class);
//		Greeting greeting__ = ((List<Greeting>) Response).get(0);
//		Greeting greeting = ((List<Greeting>) Response).get(1);
//		Greeting greeting_ = ((List<Greeting>) Response).get(2);
//		assertTrue(String.join("", greeting__.message).contains("noeud_22, Akka")
//				|| String.join("", greeting__.message).contains("noeud_33, Akka")
//				|| String.join("", greeting__.message).contains("noeud_11, Akka"));
//		
//		assertTrue(String.join("", greeting.message).contains("noeud_22, Akka")
//				|| String.join("", greeting.message).contains("noeud_33, Akka")
//				|| String.join("", greeting.message).contains("noeud_11, Akka"));
//		
//		assertTrue(String.join("", greeting_.message).contains("noeud_22, Akka")
//				|| String.join("", greeting_.message).contains("noeud_33, Akka")
//				|| String.join("", greeting_.message).contains("noeud_11, Akka"));
//		assertTrue(!String.join("", greeting_.message).equals(String.join("", greeting.message)));
//		assertTrue(!String.join("", greeting__.message).equals(String.join("", greeting.message)));
//		assertTrue(!String.join("", greeting_.message).equals(String.join("", greeting__.message)));
//	}

}
