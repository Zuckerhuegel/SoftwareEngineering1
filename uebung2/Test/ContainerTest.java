package org.hbrs.se1.ws22.uebung2.Test;

import org.hbrs.se1.ws22.uebung2.ConcreteMember;
import org.hbrs.se1.ws22.uebung2.Container;
import org.hbrs.se1.ws22.uebung2.ContainerException;
import org.junit.jupiter.api.*;

/**
 * Testklasse für die verschiedenen Fälle
 */
public class ContainerTest {
    static Container c = new Container();   //Testcontainer

    //befüllt den Container
    @BeforeEach
    public void set() {
        for (int i = 1; i <= 5; i++) {
            try {
                c.addMember(new ConcreteMember(i));
            } catch (ContainerException e) {
                e.toString();
            }
        }
    }

    /**
     * Container wird ein Member hinzugefügt, der eine ID hat, der noch nicht im Container ist
     * Erwartet, dass die Size von Container um eins erhöht wird
     */
    @Test
    public void test1 () {
        try {
            c.addMember(new ConcreteMember(42));
        } catch (ContainerException ce) {
            ce.toString();
        }
        Assertions.assertEquals(6,c.size());
        //c1.dump();
    }

    /**
     * Entfernt ein Objekt aus Container 2, dass nicht vorhanden ist,
     * Erwartet, dass die Größe sich nicht verändert
     */
    @Test
    public void test2() {
        Assertions.assertEquals("Kein Member mit der ID 7 im Container vorhanden",c.deleteMember(7));
        Assertions.assertEquals(5,c.size());
    }


    /**
     * löscht ein Objekt aus dem Container, dass vorhanden ist
     * Erwartet, dass die Size sich um eins verringert
     */
    @Test
    public void test3 () {
        Assertions.assertEquals("Member (ID = 2)",c.deleteMember(2));
        Assertions.assertEquals(4,c.size());
    }

    /**
     * resettet den Container für den nächsten Test
     */
    @AfterEach
    public void reset() {
        c = new Container();
    }
}
