package hu.minesweepers.modell.command.commands;

import java.io.*;

import static hu.minesweepers.modell.command.MapParser.loadMap;
import static hu.minesweepers.modell.command.commands.StatsCommand.stats;


/**
 * A statkus osztálynak az egyetlen felelőssége kezelni a "load" parancsot.
 */
public class LoadCommand {

    /**
     * Ez a függvény oldja meg a "load" parancsot. A paranccsal betölthetünk egy pályát (most jégmezőt) egy fájlból.
     * @param filename A fájl neve, amiből beakarjuk tölteni a pályát.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem megfelelő fájl nevet adtunk meg.
     */
    public static Boolean load(String filename) {
        try {
            loadMap(new BufferedReader(new FileReader(filename + ".map")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
