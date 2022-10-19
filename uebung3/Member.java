package org.hbrs.s1.ws22.uebung3;

public interface Member {

    /**
     * ID ist über einen Konstruktor einer abgeleiteten Klasse
     * explizit außerhalb der Container-Klasse zu belegen
     *  -> Primärschlüssel zur Unterscheidung aller Member-Objekte
     */
    
    Integer getID();

    public String toString();

}
