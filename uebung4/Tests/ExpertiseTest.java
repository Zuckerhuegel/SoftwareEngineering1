package org.hbrs.s1.ws22.uebung4.Tests;

import org.hbrs.s1.ws22.uebung4.Expertise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpertiseTest {

    @Test
    void getLevel() {
        Expertise expertise = new Expertise("Java", 1);
        assertEquals(1, expertise.getLevel());
    }

    @Test
    void getBezeichnung() {
        Expertise expertise = new Expertise("Java", 1);
        assertEquals("Java", expertise.getBezeichnung());
    }
}