package hu.minesweepers.modell.command.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static hu.minesweepers.modell.command.commands.StatsCommand.stats;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "save" parancsot.
 */
public class SaveCommand {

    /**
     * Ez a függvény oldja meg a "save" parancsot. A paranccsal kimenthetünk egy pályát (most jégmezőt) egy fájlba.
     * @param filename A fájlnév, ahova szeretnénk menteni.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha a fájl létrehozásánál, vagy kimentésénél hiba történt.
     */
    public static Boolean save(String filename) {
        try {
            stats(new BufferedWriter(new FileWriter(filename + ".map")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
