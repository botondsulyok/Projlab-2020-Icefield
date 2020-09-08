package hu.minesweepers.modell.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A függvényhívások logolásáért felelős osztály.
 */
public class Logger {

    /**
     * Azt tárolja, hogy milyen mélyen vagyunk a függvényhívási láncban.
     */
    private static int deepness = 1;

    /**
     * Ez a karakter sorozat jeleníti meg, ha egy függvényhívás egy másik függvényből történt.
     */
    private static String deepnessString = "   ";

    public static void deepnessFormatter() {
        for(int i=0; i<deepness; i++) {
            System.out.print(deepnessString);
        }
    }

    public static void write(String s) {
        deepnessFormatter();
        System.out.println(s);
    }

    /**
     * kiírja megfelelő formátumban a konstruktorhívást
     * @param ctrName konstruktor neve
     * @param paramteres a konstruktor paraméterei
     * @param type típus
     * @param id azonosító
     */
    public static void writeConstructor(String ctrName, String paramteres, String type, String id) {
        deepnessFormatter();
        System.out.println("Konstruktorhivas: " + "{" + ctrName + "} " + "{" + paramteres + "} " + "{" + type + "} " + "{" + id + "} ");
        deepness++;
    }

    /**
     * kiírja megfelelő formátumban a függvényhívást
     * @param methodName függvény neve
     * @param type típus
     * @param id azonosító
     */
    public static void writeMethodCall(String methodName, String type, String id) {
        deepnessFormatter();
        System.out.println("Fuggvenyhivas: " + "{" + methodName + "} " + "{" + type + "} " + "{" + id + "} ");
        deepness++;
    }

    /**
     * kiírja megfelelő formátumban a függvény visszatérését
     * @param methodName függvény neve
     * @param type típus
     * @param id azonosító
     */
    public static void writeMethodReturn(String methodName, String type, String id) {
        deepness--;
        deepnessFormatter();
        System.out.println("Fuggveny visszateres: " + "{" + methodName + "} " + "{" + type + "} " + "{" + id + "} ");
    }

    public static void resetDeepness() {
        deepness = 0;
    }

    /**
     * beolvas a bemenetről
     * @return visszaadja a beolvasott szöveget
     */
    public static String read() {
        String str = "";
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            str = br.readLine();
        }
        catch(Exception e) {
        }
        return str;
    }

}
