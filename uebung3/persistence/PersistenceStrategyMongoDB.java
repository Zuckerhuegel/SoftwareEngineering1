package org.hbrs.s1.ws22.uebung3.persistence;

import java.util.List;

public class PersistenceStrategyMongoDB <E> implements PersistenceStrategy <E>{
    @Override
    public void openConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void closeConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void save(List<E> member){
        throw new UnsupportedOperationException("Not implemented!");

    }

    @Override
    public List<E> load() {
        throw new UnsupportedOperationException("Not implemented!");
    }

    public void setLocation(String location) {

    }
}

