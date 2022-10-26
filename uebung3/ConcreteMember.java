package org.hbrs.s1.ws22.uebung3;

import java.io.Serializable;

/**
 * Konkreter Memberklasse, um das Interface Member nutzen zu können
 */

//bitte töte mich nicht
public class ConcreteMember implements Member, Serializable {
    private Integer id;     //id des Members

    /**
     * Gibt die ID des Members zurück
     * @return ID des Members
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Gibt das Memberobjekt als String zurück
     * @return String, der das Memberobjekt beschreibt
     */
    @Override
    public String toString() {
        return "Member (ID = " + id.toString() + ")";
    }

    /**
     * Konstruktor für einen konkreten Memberobjekt
     * @param id ID die der Member hat soll
     */
    public ConcreteMember (Integer id) {
        this.id = id;
    }


}
