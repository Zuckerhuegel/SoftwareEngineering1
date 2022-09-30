package org.hbrs.s1.ws22.uebung1.control;

public class Communication {
    public static String translateNumber(int aNumber){
        GermanTranslator com = new GermanTranslator();
        return com.translateNumber(aNumber);
    }
}
