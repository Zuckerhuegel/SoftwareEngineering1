package org.hbrs.s1.ws22.uebung1.Test;

import org.hbrs.s1.ws22.uebung1.control.Communication;
import org.junit.jupiter.api.Assertions;

class GermanTranslatorTest {
    String fehler;

    int numArr[] = {-3, -2, -1, 0, 1, 3, 5, 7, 9, 10, 11, 467};
    String ergbArr[] = {"Uebersetzung der Zahl [" + -3 + "] nicht möglich " + 1.0, "Uebersetzung der Zahl [" + -2 + "] nicht möglich " + 1.0, "Uebersetzung der Zahl [" + -1+ "] nicht möglich " + 1.0 , "Uebersetzung der Zahl [" + 0 + "] nicht möglich " + 1.0,"eins", "drei", "fuenf", "sieben", "neun", "zehn", "Uebersetzung der Zahl [" + 11 + "] nicht möglich " + 1.0, "Uebersetzung der Zahl [" + 467 + "] nicht möglich " + 1.0};

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