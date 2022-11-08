package org.hbrs.s1.ws22.uebung4;

import org.hbrs.s1.ws22.uebung4.persistence.PersistenceStrategy;
import org.hbrs.s1.ws22.uebung4.persistence.PersistanceStrategyStream;
import org.hbrs.s1.ws22.uebung4.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static int savings = 0; //gibt an, ob bereits gespeichert wurde
    static Container c = Container.getInstance();   //Container
    static PersistenceStrategy PS = new PersistanceStrategyStream();   //persistent Abspeichern

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);    //scanner
        c.setPers(PS);

        System.out.print("Hallo!\nMit 'help' erhalten sie eine Erklärung über die Funktionen dieses Programms\n");
        String eingabe = "";
        while (!eingabe.equals("exit")) {
            System.out.print("> ");
            eingabe = sc.next();
            //Eingabe ist "enter"
            if(eingabe.equals("enter")) {
                List<Expertise> kompetenzen = new ArrayList<>();    //Liste fuer Expertisen
                Integer id = 0; //eindeutige ID des Mitarbeiters
                boolean ungültigeEingabe = true;
                while(ungültigeEingabe) {   //solange keine "reine" Zahl angegeben wird kann/muss man die Eingabe wiederholen
                    System.out.print("Eindeutige ID: ");
                    try {
                        id = Integer.parseInt(sc.next());
                        ungültigeEingabe = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültige Eingabe");
                        ungültigeEingabe = true;
                    }
                }
                String vorName = null;      //Vorname des Mitarbeiters
                //solange das Pattern nicht übereinstimmt, kann/muss man neu eingeben
                boolean ok = false;
                while (!ok) {
                    System.out.print("Vorname: ");
                    vorName = sc.next();
                    Pattern pattern = Pattern.compile("[A-Z][a-z]+",Pattern.UNICODE_CASE);  //beginnt mit Großbuchstabe, dann beliebig viele Kleinbuchstaben
                    Matcher m = pattern.matcher(vorName);
                    ok = m.find();
                    if (!ok) {
                        System.out.println("ungültige Eingabe, Vornamen dürfen nur aus Buchstaben bestehen und müssen mit einem Großbuchstaben beginnen");
                    } else {
                        break;
                    }

                }
                String name = null; //Name des Mitarbeiters
                boolean okName = false;
                //solange das Pattern nicht übereinstimmt, kann/muss man den Namen neu angeben
                while (!okName) {
                    System.out.print("Nachname: ");
                    name = sc.next();
                    Pattern patternName = Pattern.compile("[A-Z][a-z]+",Pattern.UNICODE_CASE);
                    Matcher m2 = patternName.matcher(name);
                    okName = m2.find();
                    if (!okName) {
                        System.out.println("Ungültige Eingabe, Namen dürfen nur Buchstaben beinhalten und müssen mit einem Großbuchstaben beginnen");
                    } else {
                        break;
                    }
                }
                System.out.print("Rolle: ");    //Rolle des Mitarbeiters
                String rolle = sc.next();
                System.out.print("Abteilung: ");    //Abteilung des Mitarbeiters
                String abteilung = sc.next();
                System.out.print("Bitte geben sie beliebig viele Expertisen an.\nZum beenden geben sie statt einem Bezeichner '.' ein\n");
                String buf = "";
                int i = 1;
                while (!buf.equals(".")) {
                    System.out.printf("Expertisenbezeichnung %d: ",i);
                    buf = sc.next();
                    if(buf.equals(".")){
                        System.out.println();
                    }else {
                        System.out.printf("Expertisenlevel %d: ",i);
                        kompetenzen.add(new Expertise(buf, Integer.parseInt(sc.next())));
                    }
                    i++;
                }
                addNewMitarbeiter(id,vorName,name,rolle,abteilung,kompetenzen);

            }

            else if (eingabe.equals("store")) {
                savings++;  //zählt hoch, wenn gespeichert wird
                store();    //ruft die store Funktion auf
            }

            else if (eingabe.equals("load")) {
                System.out.print("\t Force oder Merge?\n");
                System.out.println("\t\t'force': ersetzt ihre eingegebenen Daten mit den gespeicherten");
                System.out.println("\t\t'merge': Fügt die gespeicherten Daten zu den eingegeben, wobei redundante IDs nicht hinzugefügt werden");
                System.out.print("\t\t> ");
                if(sc.next().equals("force")) {
                    try {
                        //das forcen überschreibt die aktuelle Liste durch Ersetzen
                        c.load();
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                    }
                } else {
                    //ruft die Merge-Funktion auf
                    merge();
                }
            }

            else if (eingabe.equals("help")) {
                //gibt das Hilfe-Menue aus
                printHelp();

            }

            else if (eingabe.equals("dump")) {
                //gibt die aktuelle Liste aus
                toDump(c.getCurrentList());

            }

            else if(eingabe.equals("exit")){
                if(savings < 1) {
                    //sollte noch nicht gespeichert worden sein wird abgefragt, ob man vor dem Verlassen speichern möchte
                    System.out.println("Sie haben noch nicht gespeichert!\nMöchten sie jetzt speichern?");
                    System.out.println("\t'y': ja \n\t'n': nein");
                    System.out.print("\t> ");
                    String dochSave = sc.next();
                    if (dochSave.equals("y")) {
                        store();
                    }
                }
                System.out.println("Programm wird beendet...\nSchönen Tag noch!");

            }

            else if(eingabe.equals("search")){
                System.out.println("Sie können nach folgenden Kategorien suchen:");
                System.out.println("\t-'ID'\n\t-'Vorname'\n\t-'Name'\n\t-'Abteilung'\n\t-'Rolle'\n\t-'Expertise");
                System.out.print("Kategorie: ");
                String searchCategory = sc.next();
                System.out.print("Suchbegriff: ");
                String searchWord = sc.next();
                if(searchCategory.equals("Expertise")) {
                    //falls nach Expertisen gesucht wird kann entschieden werden, ob man auch nach Leveln suchen möchte
                    System.out.println("Möchten sie nach Leveln filtern?\n\t-'y': ja\n\t-'n': nein");
                    System.out.print("\t> ");
                    if(sc.next().equals("y")) {
                        System.out.print("Level der Expertise: ");
                        Integer level = Integer.parseInt(sc.next());
                        toDump(searchExpertise(searchWord,level));
                    } else {
                        toDump(search(searchCategory,searchWord));
                    }
                } else {
                    toDump(search(searchCategory, searchWord));
                }
            }

            else {
                //bei einer ungültigen Eingabe
                System.out.println("Ungültiger Befehl! Mit 'help' erhalten sie Infos zu den möglichen Befehlen");
            }
        }
        sc.close();
    }

    /**
     * Fügt einen neuen Mitarbeiter hinzu
     * @param id Eindeutige ID des Mitarbeiters
     * @param vorName Vorname des Mitarbeiters
     * @param name Nachname des Mitarbeiters
     * @param rolle Rolle des Mitarbeiters
     * @param abteilung Abteilung des Mitarbeiters
     * @param expertisen Expertisen des Mitarbeiters
     */
    private static  void addNewMitarbeiter(Integer id, String vorName, String name, String rolle, String abteilung, List<Expertise> expertisen) {
        try {
            Member m = new Mitarbeiter(id,vorName,name,rolle,abteilung);
            m.addExpertise(expertisen);
            c.addMember(m);
        } catch (ContainerException e) {
            e.getMessage();
        }
    }

    /**
     * Gibt eine Liste in Tabellenform aus
     * @param list Liste, die ausgegeben werden soll
     */
    private static void toDump(List<Member> list) {
        //List<Member> liste = c.getCurrentList();
        //System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%3s %15s %15s %15s %15s", "ID", "Vorname", "Name", "Rolle", "Abteilung");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");

        for(Member m : list) {
            System.out.format("%3d %15s %15s %15s %15s", m.getID(),m.getVorName(),m.getName(),m.getRolle(),m.getAbteilung());
            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    /**
     * Speichert die Mitglieder ab
     */
    private static void store() {
        try {
            c.store(c.getCurrentList());
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Sucht in einer bestimmten Kategorie nach Begriffen
     * @param category Kategorie, in der gesucht werden soll
     * @param word Begriff. nachdem gesucht wird
     * @return Liste der gefundenen Mitarbeiter, die mit den gesuchten Mitarbeitern Uberschneidungen haben
     */
    private static List<Member> search (String category, String word) {
        List<Member> found = null;
        switch (category) {
            case "Vorname":
                found = c.getCurrentList().stream().filter(m -> m.getVorName().equals(word)).collect(Collectors.toList());
                break;
            case "Name":
                found = c.getCurrentList().stream().filter(m -> m.getName().equals(word)).collect(Collectors.toList());
                break;
            case "ID":
                found = c.getCurrentList().stream().filter(m -> m.getID() == Integer.parseInt(word)).collect(Collectors.toList());
                break;
            case "Abteilung":
                found =  c.getCurrentList().stream().filter(m -> m.getAbteilung().equals(word)).collect(Collectors.toList());
                break;
            case "Rolle":
                found = c.getCurrentList().stream().filter(m -> m.getRolle().equals(word)).collect(Collectors.toList());
                break;
            case "Expertise":
                List<Member> members = c.getCurrentList();
                found = new ArrayList<>();
                for (int i = 0; i < c.size(); i++) {
                    for (int j = 0; j < members.get(i).getAnzahlExpertisen(); j++) {
                        if(members.get(i).getExpertise().get(j).getBezeichnung().equals(word)) {
                            found.add(members.get(i));
                        }
                    }
                }
                break;
            default:
                System.out.println("Ungültige Kategorie");
                found = c.getCurrentList();
                break;
        }
        return found;
    }


    /**
     * Sucht bei einer Expertise nach Leveln und gibt nur diese zurück
     * @param expertise Expertise nach der gesucht wird
     * @param level Level, nachdem gesucht wird (bei der entsprechenden Expertise)
     * @return Liste der Member, die diese Expertise mit dem gesuchten Level haben
     */
    private static List<Member> searchExpertise (String expertise, Integer level) {
        List<Member> found = new ArrayList<>();
        List<Member> memberMitExpertise = search("Expertise",expertise);
        for (int i = 0; i < memberMitExpertise.size(); i++) {
            for (int j = 0; j < memberMitExpertise.get(i).getExpertise().size(); j++) {
                if(memberMitExpertise.get(i).getExpertise().get(j).getLevel() == level) {
                    found.add(memberMitExpertise.get(i));
                }
            }
        }
        return found;
    }

    /**
     * Gibt das Hilfe-Menue aus
     */
    public static void printHelp() {
        System.out.print("Das ist das Hilfe-Menue dieser Anwendung\n");
        System.out.print("Sie können folgende Befehle ausführen: \n");
        System.out.print("\tenter: fügt ein neues Mitglied in ihren Container hinzu\n");
        System.out.print("\tstore: speichert ihre aktuelle Liste persistent auf ihrem Gerät ab\n");
        System.out.print("\tload: lädt eine gespeicherte Liste\n");
        System.out.print("\tdump: Listet den Inhalt des Containers auf\n");
        System.out.println("\tsearch: Sucht nach Begriffen");
        System.out.print("\texit: Beendet die Anwendung\n");
        System.out.print("\thelp: Bietet hilfe fuer Befehle an\n");
    }


    /**
     * führt die gespeicherte Liste mit der aktuellen zusammen
     * redundante IDs werden aus der gespeicherten nicht übernommen
     */
    public static void merge () {
        try {
            List<Member> saved = (List<Member>) PS.load();
            for(Member m : saved) {
                c.addMember(m);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (ContainerException e) {
            e.getMessage();
        }
    }
}