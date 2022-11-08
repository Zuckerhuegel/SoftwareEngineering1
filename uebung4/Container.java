package org.hbrs.s1.ws22.uebung4;

import org.hbrs.s1.ws22.uebung4.persistence.PersistenceException;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();       //Speichert die Member
    private static Container instance = null;

    public PersistenceStrategy pers = null; //Kontrolle gemäß des Singleton Patterns

    public void setPers(PersistenceStrategy pers) {
        this.pers = pers;
    }

    /**
     * Singleton Pattern
     * @return Instanz vom Typ Container, entweder eine neue, falls es noch keine gibt, oder dieselbe nochmal
     */
    public static Container getInstance() {
        if(instance  == null) {
            instance = new Container();
        }
        return instance;
    }

    /**
     * Privater Konstruktor um zu verhindern, dass neue Container erzeugt werden
     */
    private Container() {}

    /**
     * fügt einen neuen Member zum Container hinzu, falls kein Member mit der selben ID vorhanden ist
     * @param m Member der hinzugefügt werden soll
     * @throws ContainerException Exception, dass der Container einen Member mit der ID schon
     * beinhaltet
     */
    public void addMember (Member m) throws ContainerException {
        if(checkMember(m)) {
            members.add(m);
            members.sort(new Comparator<Member>() {
                @Override
                public int compare(Member o1, Member o2) {
                    if(o1.getID() < o2.getID()) {
                        return -1;
                    } else if(o1.getID() == o2.getID()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        } else {
            throw new ContainerException(m.getID());
        }
    }


    /**
     * Prüft, ob ein Member bereits im Container vorhanden ist, wäre die bessere Möglichkeit um die Exception
     * zu werfen, da dann direkt in der Funktion addMember behandelt werden kann
     * @param m Member der zu Prüfen ist
     * @return true, falls die ID noch nicht vorhanden ist, falls sonst
     * @throws ContainerException Exception, wenn ID schon vorhanden ist
     */
    private boolean checkMember (Member m) throws ContainerException {
        for (int i = 0; i < members.size(); i++) {
            if(m.getID().equals(members.get(i).getID())) {
               return false;
            }
        }
        return true;
    }

    /**
     * Löscht das Mitglied mit der angegeben ID
     * Wirft keine Exception, sondern gibt eine Fehlermeldung zurueck
     * Exception ist besser, da das eindeutiger ist, statt dem eh erwarteten Rückgabetyp
     * @param id ID des Members der gelöscht werden soll
     * @return Ausgabestring mit ID der Number, die entfernt wurde, oder Fehlermeldung
     */
    public String deleteMember (Integer id) {
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getID().equals(id)) {
                return members.remove(i).toString();
            }
        }
        return "Kein Member mit der ID " + id + " im Container vorhanden";
    }

    /**
     * gibt die Größe des Containers zurück
     * @return
     */
    public int size() {
        return members.size();
    }

    public void store(List<Member> currentList) throws PersistenceException {
        pers.save(getCurrentList());
    }
    public void load() throws PersistenceException{
        members = pers.load();
    }

    public List<Member> getCurrentList () {
        return this.members;
    }

    public void setLocation(String location) {
        pers.setLocation(location);
    }



}
