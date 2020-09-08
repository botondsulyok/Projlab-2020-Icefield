package hu.minesweepers.modell.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;


/**
 * Az osztály felelőssége a Parsable interfészt implementáló osztályok objektumaiból egy olyan HashMap-et tárolni,
 * amelynek adatait könnyen fel lehet dolgozni, és azt mentési vagy vizsgálati célból lehet haszálni.
 */
public class ParsedObject {

    /**
     * A parsedObject példány egy HashMap-et tárol, amiből sokkal egyszerűbb feldolgozni az adatokat.
     */
    private HashMap<String, ArrayList<String>> attributes = new LinkedHashMap<>();

    /**
     * Hozzáadjuk a HashMaphez az objektum egyik tulajdonságát.
     * @param key A hash kulcs, amivel kitudjuk keresni könnyedén a tulajdonságot. Itt ez a tulajdonság neve általában.
     * @param value A tulajdonság értéke.
     */
    public void add(String key, String value) {
        ArrayList<String> values = new ArrayList<>();
        if (attributes.containsKey(key)) {
            values = attributes.get(key);
        }
        values.add(value);
        attributes.put(key, values);
    }

    /**
     * Hozzáadunk a hashmaphez egy kulcsot. Azért kell ez a verzió, mert nem feltétlen van értéke a tulajdonságnak.
     * @param key A hash kulcs, amit hozzáadunk.
     */
    public void add(String key) {
        attributes.put(key, new ArrayList<String>());
    }

    /**
<<<<<<< HEAD
     * Felülírja az eddigi értéket
     * @param key
     * @param value
=======
     * Felülírja az egyik értéket a hashmap-ben.
     * @param key A hash kulcs, ahova rakjuk az értéket.
     * @param value Az érték, amivel felülírjuk a régi értéket.
>>>>>>> 10kesz
     */
    public void set(String key, String value) {
        ArrayList<String> values = new ArrayList<>();
        values.add(value);
        attributes.put(key, values);
    }

    public ArrayList<String> get(String key) {
        if (attributes.containsKey(key)) {
            return attributes.get(key);
        }
        return null;
    }

    /**
     * Kiírja egy character-output streamre a hashmap értékeit.
     * @param bw A character-output stream, ahova kiírjuk az értékeket.
     */
    public void print(BufferedWriter bw) {
        try {
        for (Map.Entry<String, ArrayList<String>> entry : attributes.entrySet()) {
            bw.write(entry.getKey() + ": ");
            for (int i = 0; i < entry.getValue().size(); i++) {
                bw.write(entry.getValue().get(i));
                if (i + 1 < entry.getValue().size()) {
                    bw.write(", ");
                }
            }
            if (entry.getValue().size() == 0) {
                bw.write("null");
            }
            bw.newLine();
            bw.flush();
        }
        bw.newLine();
        bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
