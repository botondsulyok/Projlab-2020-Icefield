package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.UnstableIceUnit;

import java.util.ArrayList;

/**
 * A statikus osztálynak a felelőssége kezelni a "field" és a "neighbours" parancsot.
 */
public class FieldCommand {

    /**
     * Ez a függvény oldja meg a "field" parancsnak az első változatát. Ezzel a verzióval létrehozunk egy új stabil jégtáblát.
     * @param fieldID Az új jégtábla azonosítója.
     * @param snow A hómennyiség, ami rajta lesz a jégtáblán.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Ez a parancs mindig sikeresen fog lefutni.
     */
    public static boolean field(String fieldID, int snow) {
        IceUnit iu = new IceUnit(snow, fieldID);

        CommandUtils.addIceUnit(iu);
        return true;
    }

    /**
     * Ez a függvény oldja meg a "field" parancsnak a második változatát. Ezzel a verzióval létrehozunk egy új instabil jégtáblát.
     * @param fieldID Az új jégtábla azonosítója.
     * @param capacity Az új jégtábla maximum terheltségi szintje.
     * @param broken Az új jégtábla átfordult-e már vagy sem.
     * @param snow A hómennyiség, ami rajta lesz a jégtáblán.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Ez a parancs mindig sikeresen fog lefutni.
     */
    public static boolean field(String fieldID, int capacity, boolean broken, int snow) {
        UnstableIceUnit ui = new UnstableIceUnit(capacity, snow, fieldID, broken);
        CommandUtils.addIceUnit(ui);
        return true;
    }

    /**
     * Ez a függvény oldja meg a "neighbours" parancsot. Ezzel a paranccsal beállíthatjuk egy jégtábla szomszédos jégtábláit.
     * @param fieldID A jégtábla azonosítója, aminek szeretnénk beállítani a szomszédjait.
     * @param neighbourIDs A jégtáblák azonosítója, amiket beállítunk szomszédnak.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítókat adtunk meg a parancs argumentumokban.
     */
    public static boolean neighbours(String fieldID, ArrayList<String> neighbourIDs) {
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        if (iu == null) {
            return false;
        }
        ArrayList<IceUnit> neighbours = new ArrayList<>();
        for (String id : neighbourIDs) {
            IceUnit n = CommandUtils.getIceUnit(id);
            if (n == null) {
                return false;
            }
            neighbours.add(n);
        }
        iu.addNeighbour(neighbours);
        return true;
    }
}
