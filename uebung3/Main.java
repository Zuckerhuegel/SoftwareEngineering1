package org.hbrs.s1.ws22.uebung3;

import org.hbrs.s1.ws22.uebung3.persistence.PersistenceException;

public class Main {
    public static Container c = Container.getInstance();
    public static Client cl = new Client(c);

    public static void main(String[] args) {

    }
    public void load() {
        try {
            cl.getContainer().load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }
}
