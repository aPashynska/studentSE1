package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Container {
    List<Member> members; // Eine Liste zur Speicherung von Member-Objekten.

    /**
     * Der Konstruktor initialisiert die Mitgliederliste als eine ArrayList.
     */
    public Container() {
        members = new ArrayList<>();
    }

    /**
     * @param member Objekte vom Typ Member zu Container-Object addieren
     * @throws ContainerException falls ein Member-Objekt mit einer bereits vorhandenen ID übergeben wird
     */
    public void addMember(Member member) throws ContainerException {
        for (Member memb : members) {
            if (Objects.equals(memb.getID(), member.getID())) {
                throw new ContainerException(member.getID());
            }
        }
        members.add(member);
    }

    /**
     *
     * @param id Die Methode zum Löschen eines Member-Objekts anhand der ID.
     * @return eine Erfolgsmeldung zurück oder informiert, falls kein passendes Objekt gefunden wurde.
     */
    public String deleteMember(Integer id) {
        for(Member memb: members) {
            if(memb.getID().equals(id)) {
                members.remove(memb);
                return "Das Member-Objekt mit der ID " + id + " ist gelöscht";
            }
        }
        return "Das Member-Objekt mit der ID " + id + " existiert nicht!"; //Fehlende Klarheit und Standardisierung
        // in der Fehlerbehandlung
    }

    public void dump(){
        for(Member memb: members) {
            System.out.println(memb);
        }
    }

    /**
     *
     * @return die Anzahl der in der Liste gespeicherten Member-Objekte zurück.
     *
     */
    public int size() {
        return members.size();
    }

}
