package org.hbrs.s1.ws22.uebung4;

import java.io.Serializable;

public class Expertise implements Serializable {
    private String bezeichnung;
    private int level;

    /**
     * Konstruktor für eine Expertise eines Mitarbeiters
     * @param bezeichnung Name der Expertise
     * @param level Level der Expertise (Wert zwischen 1 und 3)
     */
    public Expertise(String bezeichnung, int level) {
        this.bezeichnung = bezeichnung;
        this.level = level;
    }

    /**
     * Getter fuer das Level der Expertise
     * @return level der Expertise
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter fuer die Bezeichnung, also den Namen der Expertise
     * @return Bezeichnung der Expertise
     */
    public String getBezeichnung() {
        return bezeichnung;
    }
}
