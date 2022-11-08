package org.hbrs.s1.ws22.uebung4.persistence;

import java.io.*;
import java.util.List;

public class PersistanceStrategyStream<E> implements PersistenceStrategy<E> {
    // URL of file, in which the objects are stored
    private String location = "objects.ser";
    FileOutputStream fileOut = null;
    ObjectOutputStream objectOut = null;
    FileInputStream fileStream = null;
    ObjectInputStream objStream = null;

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        try {
            fileOut = new FileOutputStream(location);
            objectOut = new ObjectOutputStream(fileOut);
            fileStream = new FileInputStream(location);
            objStream = new ObjectInputStream(fileStream);
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openInput() {
        try {
            fileStream = new FileInputStream(location);
            objStream = new ObjectInputStream(fileStream);
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void closeInput() {
        try {
            fileStream.close();
            objStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            objectOut.close();
            fileOut.close();
            fileStream.close();
            objStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException {
        openConnection();
        try {
            //shoud save the list of members
            objectOut.writeObject(member);
            objectOut.flush();
            System.out.println("Speichern erfolgreich!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeConnection();
    }


    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        openInput();        //mit openConnection wird sonst die Datei resettet mit EOF -> nicht gut
        List<E> memberListe;

        try {
            memberListe = (List<E>) objStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
       closeInput();
        System.out.println("Laden erfolgreich");
        return memberListe;
    }




        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)

    }

