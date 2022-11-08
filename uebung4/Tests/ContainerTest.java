package org.hbrs.s1.ws22.uebung4.Tests;

//import org.hbrs.s1.ws22.uebung4.ConcreteMember;
import org.hbrs.s1.ws22.uebung4.Container;
import org.hbrs.s1.ws22.uebung4.ContainerException;
import org.hbrs.s1.ws22.uebung4.Mitarbeiter;
import org.hbrs.s1.ws22.uebung4.persistence.PersistanceStrategyStream;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceException;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceStrategy;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceStrategyMongoDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ContainerTest {

    static Container c = Container.getInstance();

    @BeforeEach
    public void set() {
        for (int i = 1; i <= 5; i++) {
            try {
                c.addMember(new Mitarbeiter(i, "Max", "Mustermnn", "Testrolle", "Testabteilung"));
            } catch (ContainerException e) {
                e.toString();
            }
        }
    }

    @Test
        //Keine Strategie von Außen gesetzt, persistence == null
    void setPers() {
        assertNull(c.pers);
    }


    /**
     * Kontrolliert, ob bei der PersistenceStrategy MongoDB die Exception entsprechend geworfen wird
     * @throws PersistenceException
     */
    @Test
    void store() throws PersistenceException {
        PersistenceStrategy PSMB = new PersistenceStrategyMongoDB();
        c.setPers(PSMB);
        String eMessage = null;
        try{
            c.store(c.getCurrentList());
        }catch(UnsupportedOperationException e){
            eMessage = e.getMessage();
        }
        assertEquals("Not implemented!", eMessage);

    }


    @Test
    /*
    Testet eine fehlerhafte Location in der objectStream seine Objetke abspeichern möchte, sollte dann auf Null zeigen und meckern
     */
    public void fileFail(){
        PersistenceStrategy PS = new PersistanceStrategyStream();
        c.setPers(PS);
        c.pers.setLocation("/");
        String eMessage = null;
        try{
            c.store(c.getCurrentList());
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }catch(NullPointerException e){
            eMessage = e.getMessage();
        }
        assertEquals("Cannot invoke \"java.io.ObjectOutputStream.writeObject(Object)\" because \"this.objectOut\" is null", eMessage);


    }


    /**
     * Testet das gewünschte Szenario (4)
     */
    @Test
    public void scenario() {
        PersistenceStrategy stream = new PersistanceStrategyStream();
        c.setPers(stream);

        Assertions.assertEquals(5,c.size());    //vergleicht, ob bei keiner Veränderung die Größe gleich ist

        try {
            c.addMember(new Mitarbeiter(42, "Max", "Mustermnn", "Testrolle", "Testabteilung"));
        } catch (ContainerException e) {
            e.getMessage();
        }
        Assertions.assertEquals(6,c.size());    //Größe sollte eins größer sein, wenn ein neues Objekt hinzugefügt wird

        //Speichern der Member
        try {
            c.store(c.getCurrentList());
        } catch (PersistenceException e) {
            System.out.printf("Das speichern klappt nicht \n");
        }
        Assertions.assertEquals(6,c.size());    //nach dem Speichern sollte sich die Größe nicht verändert haben
        c.deleteMember(42);
        for (int i = 1; i <= 5; i++) {
            c.deleteMember(i);
        }
        Assertions.assertEquals(0,c.size());    //nachdem alle Objekte entfernt wurden sollte die size 0 sein

        //Laden der Member aus der Datei
        try {
            c.load();
        } catch (PersistenceException e) {
            System.out.printf("Kann nicht laden\n");
        }

        Assertions.assertEquals(6,c.size());    //nach dem Laden sollte die Größe wieder 6 sein

    }

    @AfterEach
    void clear(){
        c.setPers(null);
    }

}