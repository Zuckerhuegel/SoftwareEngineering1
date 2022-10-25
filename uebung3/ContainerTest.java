package org.hbrs.s1.ws22.uebung3;

import org.hbrs.s1.ws22.uebung3.persistence.PersistenceException;
import org.hbrs.s1.ws22.uebung3.persistence.PersistenceStrategy;
import org.hbrs.s1.ws22.uebung3.persistence.PersistenceStrategyMongoDB;
import org.junit.jupiter.api.AfterEach;
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
                c.addMember(new ConcreteMember(i));
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
    @AfterEach
    void clear(){
        c.setPers(null);
    }

}