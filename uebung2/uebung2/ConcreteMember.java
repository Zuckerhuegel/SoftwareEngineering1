package org.hbrs.se1.ws22.uebung2;

/**
 * Konkreter Memberklasse, um das Interface Member nutzen zu können
 */
public class ConcreteMember implements Member{
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
