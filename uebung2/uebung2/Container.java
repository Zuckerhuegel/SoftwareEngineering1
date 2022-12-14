package org.hbrs.se1.ws22.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();

    /**
     * fügt einen neuen Member zum Container hinzu, falls kein Member mit der selben ID vorhanden ist
     * @param m Member der hinzugefügt werden soll
     * @throws ContainerException Exception, dass der Container einen Member mit der ID schon
     * beinhaltet
     */
    public void addMember (Member m) throws ContainerException{
        if(checkMember(m)) {
            members.add(m);
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
     * gibt alle Member im Container aus
     */
    public void dump() {
        for(Member m : members) {
            System.out.println(m.toString());
        }
    }

    /**
     * gibt die Größe des Containers zurück
     * @return
     */
    public int size() {
        return members.size();
    }
}
