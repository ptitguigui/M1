package exo2;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class MyHandler extends DefaultHandler {
	private int n;
	private boolean newMaison;
	private List<Integer> maisons;
	private double surface;
	private String name;
	private boolean isSurface;

	public void startDocument() {
		this.n = 1;
		this.newMaison = false;
		this.maisons = new ArrayList<>();
		this.surface = 0;
		this.name = "";
		this.isSurface = false;
	}

	public void endDocument() {
		System.out.println();
	}

	public void startElement(String nameSpaceURI, String localName, String rawName, Attributes attributs) {
		// Lorque nous avons une nouvelle maison
		if (rawName.equals("maison")) {
			System.out.println("Maison " + n);
		}
		// Si il n'y a pas de parent avec une surface et qu'il y a un attribut surface
		if (!isSurface && attributs.getValue("surface-m2") != null) {
			this.isSurface = true;
			this.name = localName;
			surface += Double.parseDouble(attributs.getValue("surface-m2"));
		}

	}

	public void endElement(String nameSpaceURI, String localName, String rawName) {
		// Fermeture de la maison
		if (rawName.equals("maison")) {
			System.out.println("\tsuperficie totale : " + surface + " m²");
			this.n++;
			this.surface = 0;
		}
		// Possibilite d'ajouter une surface
		if (localName.equals(name)) {
			this.isSurface = false;
		}
	}

	public static void main(String[] args) {
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(new MyHandler());
			saxReader.parse(args[0]);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
}
