package org.hbrs.s1.ws22.uebung4.Tests;

import org.hbrs.s1.ws22.uebung4.Container;
import org.hbrs.s1.ws22.uebung4.ContainerException;
import org.hbrs.s1.ws22.uebung4.Member;
import org.hbrs.s1.ws22.uebung4.Mitarbeiter;
import org.hbrs.s1.ws22.uebung4.persistence.PersistanceStrategyStream;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceException;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    //Ertsmall muss ein Container und Mitarbeiter für die Tests erstellt werden
    static int savings = 0; //gibt an, ob bereits gespeichert wurde
    static Container c = Container.getInstance();   //Container
    static PersistenceStrategy PS = new PersistanceStrategyStream();
    static Mitarbeiter mitarbeiter;
    static Mitarbeiter mitarbeiter2;
    static Mitarbeiter mitarbeiter3;
    @Test
    void merge() throws ContainerException {
        c.setPers(PS);
        //Testen, ob die Methode merge() funktioniert
        //Erstellen von zwei Mitarbeitern
        mitarbeiter = new Mitarbeiter(1, "Max", "Mustermann", "Testrolle", "Testabteilung");
        mitarbeiter2 = new Mitarbeiter(2, "Maxi", "Musterfrau", "Testrolle", "Testabteilung");
        //Hinzufügen der Mitarbeiter
        c.addMember(mitarbeiter);
        c.addMember(mitarbeiter2);
        //Persistentes speichern der Mitarbeter
        try {
            c.store(c.getCurrentList());
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        //Löschen von zwei Mitarbeitern
        c.deleteMember(1);
        c.deleteMember(2);
        //Prüfen ob die Mitarbeiter gelöscht wurden
        assertEquals(0, c.getCurrentList().size());
        //Mitarbeiter3 erstellen und hinzfügen
        mitarbeiter3 = new Mitarbeiter(3, "Felix", "Hase", "Testrolle", "Testabteilung");
        c.addMember(mitarbeiter3);
        assertEquals(1, c.getCurrentList().size());
        /*
        Merge durchführen Nun sollten die Mitarbeiter1 und 2 wieder vorhanden sein
        Mitarbeiter 3 sollt auch noch in der Liste stehen
         */
        try {
            List<Member> saved = (List<Member>) PS.load();
            for(Member m : saved) {
                c.addMember(m);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ContainerException e) {
            e.getMessage();
        }
        //Prüfen ob alle Mitarbeiter wieder vorhanden sind
        assertEquals(3, c.getCurrentList().size());
    }
}