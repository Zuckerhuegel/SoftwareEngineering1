package org.hbrs.s1.ws22.uebung3;

/**
 * Exception, falls ein Objekt mit derselben ID bereits existiert
 */
public class ContainerException extends Exception{

    /**
     * Gibt eine Fehlermeldung, dass ein Objekt mit der ID bereits im Container existiert
     * @param id ID, die bereits im Container ist
     */
    public ContainerException(Integer id) {
        super("Das Member-Objekt mit der ID" + id + "ist bereits vorhanden");
    }
}
