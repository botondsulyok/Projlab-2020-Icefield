package hu.minesweepers.modell.tile;

import java.util.ArrayList;

/**
 * A játékban levő viharos jégmezőknek az interfésze.
 */
public interface StormyField {

    /**
     * Végig megy a vihar a jégmezőn.
     */
    void unleashStorm();

    /**
     * A függvény segítségével lekérhető a jégmezőt alkotó összes jégtábla.
     * @return a jégmezőt alkotók jégtáblák egy ArrayList objektumban
     */
    ArrayList<IceUnit> getIceUnits();
}
