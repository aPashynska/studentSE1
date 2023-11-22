package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * 
 */

public class Container {
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Nachteil: ggf. geringer Speicherbedarf, da Singleton zu Programmstart schon erzeugt wird
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "allStories.ser";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception {
		Container con = Container.getInstance();
		con.startEingabe(); 
	}
	
	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {
		String strInput = null;
		
		// Initialisierung des Eingabe-View
		Scanner scanner = new Scanner( System.in );

		while ( true ) {
			// Ausgabe eines Texts zur Begruessung
			System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends) edited " +
					"a little bit by Anastasiia Pashynska");

			System.out.print( "> "  );

			strInput = scanner.nextLine();
		
			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if ( strings[0].equals("help") ) {
				System.out.println("Folgende Befehle stehen zur Verfuegung: " +
						"help, dump, enter, store, load, exit");
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("dump") ) {
				startAusgabe();
				dumpStories();
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("enter") ) {
				this.addUserStory(enterData(scanner));
				System.out.println("UserSTory was succesfull added!");
				// Daten einlesen ...
				// this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.

			}
								
			if (  strings[0].equals("store")  ) {
				this.store();
			}

			if (  strings[0].equals("load")  ) {
				this.load();
			}
			if (  strings[0].equals("exit")  ) {
				System.out.println("Closing the programm...");
				break;
			}
		}scanner.close();// Ende der Schleife
	}

	private void dumpStories() {
		String format = "| %-10s | %-30s | %-20s | %-15s | %n";
		System.out.format("+------------+--------------------------------+----------------------+-----------------+%n");
		System.out.format("| ID         | Titel                   | Priority | Project         |%n");
		System.out.format("+------------+--------------------------------+----------------------+-----------------+%n");
		for (UserStory story : this.liste) {
			System.out.format(format, story.getId(), story.getTitel(), story.getPrio(), story.getProject());
		}
		System.out.format("+------------+--------------------------------+----------------------+-----------------+%n");
	}

	/**
	 * Hilsfsmethode fuer enter-Option in der Methode startEingabe()
	 * @param scanner
	 * @return UserStory
	 */
	private static UserStory enterData(Scanner scanner) {
		UserStory userStory = new UserStory();
		System.out.print("ID: ");
		userStory.setId(scanner.nextInt());
		System.out.print("Aufwand: ");
		int temp = scanner.nextInt();
		if (temp<= 0 | temp > 5) {
			while (temp <= 0 | temp > 5) {
				System.out.print("Wrong value! 0 < Aufwand <= 5!");
				System.out.print("Aufwand: ");
				temp = scanner.nextInt();
			}
		}
		userStory.setAufwand(temp);
		System.out.print("Titel: ");
		userStory.setTitel(scanner.next());
		System.out.print("Mehrwert: ");
		temp = scanner.nextInt();
		if (temp <= 0 | temp > 5) {
			while (temp <= 0 | temp > 5) {
				System.out.print("Wrong value! 0 < Mehrwert <= 5!");
				System.out.print("Mehrwert: ");
				temp = scanner.nextInt();
			}
		}
		userStory.setMehrwert(temp);
		System.out.print("Strafe: ");
		temp = scanner.nextInt();
		if (temp <= 0 | temp > 5) {
			while (temp <= 0 | temp > 5) {
				System.out.print("Wrong value! 0 < Strafe <= 5!");
				System.out.print("Strafe: ");
				temp = scanner.nextInt();
			}
		}
		userStory.setStrafe(temp);
		System.out.print("Risk: ");
		temp = scanner.nextInt();
		if (temp<= 0 | temp > 5) {
			while (temp <= 0 | temp > 5) {
				System.out.print("Wrong value! 0 < Risk <= 5!");
				System.out.print("Risk: ");
				temp = scanner.nextInt();
			}
		}
		userStory.setRisk(temp);
		System.out.print("Project: ");
		userStory.setProject(scanner.next());
		userStory.findPrio();
		return userStory;
	}
	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		Collections.sort( this.liste );

		// Klassische Ausgabe ueber eine For-Each-Schleife
		for (UserStory story : liste) {
			System.out.println(story.toString());
		}

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		// (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
		List <UserStory> storyList = this.liste.stream()
				.filter(story -> story.getRisk() >= 5)
				.filter(story -> story.getProject().equals("Coll@HBRS"))
				.toList();


	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 * 
	 */
	private void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject( this.liste );
			System.out.println( this.size() + " UserStory wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * 
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);
		  
		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;
		  }
		  System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte UserStory zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}
}
