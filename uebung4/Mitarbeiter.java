package org.hbrs.s1.ws22.uebung4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mitarbeiter implements Serializable, Member {
    //Attribute
    private final Integer id;
    private String vorName;
    private String name;
    private String rolle;                       //könnte auch selber ein Objekt sein/Enum
    private String abteilung;                   //könnte auch selber ein Objekt sein/Enum
    private List<Expertise> expertisen = null;    //eine Liste aus Expertisen

    /**
     * Konstruktor für einen Mitarbeiter
     * @param id Eindeutige Id des Mitarbeiters
     * @param vorName Vorname des Mitarbeiters
     * @param name Nachname des Mitarbeiters
     * @param rolle Rolle des Mitarbeiters im Unternehmen
     * @param abteilung Abteilung, in der der Mitarbeiter arbeitet
     */
    public Mitarbeiter(Integer id, String vorName, String name, String rolle, String abteilung) {
        this.id = id;
        this.vorName = vorName;
        this.name = name;
        this.rolle = rolle;
        this.abteilung = abteilung;
        expertisen = new ArrayList<>();
    }

    /**
     * Fügt eine Expertise zu den Kompetenzen des Mitarbeiters hinzu
     * @param expertisen Kompetenz des Mitarbeiters
     */
    @Override
    public void addExpertise(List<Expertise> expertisen) {
        this.expertisen = expertisen;
    }

    public int getAnzahlExpertisen() {
        return expertisen.size();
    }

    @Override
    public Integer getID() {
        return id;
    }

    public String getVorName() {return vorName;}

    public String getName() {return name;}

    public String getRolle() {return rolle;}

    public String getAbteilung() {return abteilung;}

    public String toString() {
        return "ID: " + id + ", Vorname: " + vorName + ", Name: " + name + ", Rolle: " + rolle + ", Abteilung: " + abteilung;
    }

    public List<Expertise> getExpertise () {
        return this.expertisen;
    }
}
