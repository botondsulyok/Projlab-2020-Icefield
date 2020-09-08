package hu.minesweepers.view;

/**
 * A különböző nézetek interfésze, előírja a megjeleníthető nézetek számára az update metódus megvalósítását.
 */
public interface IView {

    /**
     * A nézetet frissíteni kell a modell adatai alapján.
     */
    void update();

}
