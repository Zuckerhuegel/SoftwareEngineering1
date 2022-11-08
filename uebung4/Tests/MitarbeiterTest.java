package org.hbrs.s1.ws22.uebung4.Tests;

import org.hbrs.s1.ws22.uebung4.Expertise;
import org.hbrs.s1.ws22.uebung4.Mitarbeiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MitarbeiterTest {
    static Mitarbeiter mitarbeiter;
    static Mitarbeiter mitarbeiter2;
    @BeforeEach
    void setUp() {
        mitarbeiter = new Mitarbeiter(1, "Max", "Mustermann", "Software Engineer", "Software Engineering");
        mitarbeiter2 = new Mitarbeiter(2, "Maxi", "Musterfrau", "Software Engineer", "Software Engineering");

        Expertise expertise = new Expertise("Java", 1);
        Expertise expertise1 = new Expertise("C++", 2);
        Expertise expertise2 = new Expertise("C#", 3);
        ArrayList<Expertise> expertiseArrayList = new ArrayList<>();
        expertiseArrayList.add(expertise);
        expertiseArrayList.add(expertise1);
        expertiseArrayList.add(expertise2);
        mitarbeiter.addExpertise(expertiseArrayList);

    }

    @Test
    void getAnzahlExpertisen() {
        assertEquals(3, mitarbeiter.getAnzahlExpertisen());
        assertEquals(0, mitarbeiter2.getAnzahlExpertisen());

    }

    @Test
    void getID() {
        assertEquals(1, mitarbeiter.getID());
        assertEquals(2, mitarbeiter2.getID());
    }

    @Test
    void getVorName() {
        assertEquals("Max", mitarbeiter.getVorName());
        assertEquals("Maxi", mitarbeiter2.getVorName());
    }

    @Test
    void getName() {
        assertEquals("Mustermann", mitarbeiter.getName());
        assertEquals("Musterfrau", mitarbeiter2.getName());
    }

    @Test
    void getRolle() {
        assertEquals("Software Engineer", mitarbeiter.getRolle());
        assertEquals("Software Engineer", mitarbeiter2.getRolle());
    }

    @Test
    void getAbteilung() {
        assertEquals("Software Engineering", mitarbeiter.getAbteilung());
        assertEquals("Software Engineering", mitarbeiter2.getAbteilung());
    }

    @Test
    void testToString() {
        assertEquals("ID: 1, Vorname: Max, Name: Mustermann, Rolle: Software Engineer, Abteilung: Software Engineering", mitarbeiter.toString());
        assertEquals("ID: 2, Vorname: Maxi, Name: Musterfrau, Rolle: Software Engineer, Abteilung: Software Engineering", mitarbeiter2.toString());
    }

    @Test
    void getExpertise() {
        assertEquals("Java", mitarbeiter.getExpertise().get(0).getBezeichnung());
    }
}