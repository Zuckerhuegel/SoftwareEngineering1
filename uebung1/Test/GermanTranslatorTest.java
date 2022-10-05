package org.hbrs.s1.ws22.uebung1.Test;

import org.hbrs.s1.ws22.uebung1.control.Communication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    String fehler(int fehlerZahl){
        return "Uebersetzung der Zahl " + fehlerZahl + " nicht moeglich, Interface Version 1.0";
    }

    int numArr[] = {-5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 200};
    String ergbArr[] = {fehler(-5),  fehler(0),"eins", "zwei", "drei", "vier" ,"fuenf","sechs", "sieben", "acht", "neun", "zehn", fehler(200)};

    int i = 0;
    @org.junit.jupiter.api.Test
    void translateNumber() {

        Assertions.assertEquals(ergbArr[i], Communication.translateNumber(numArr[i]));
        System.out.print("Erwartet: " + ergbArr[i] ); System.out.println(" Ist: " + Communication.translateNumber(numArr[i]));
        i++;
        if(i<11){
            translateNumber();
        }
    }
}