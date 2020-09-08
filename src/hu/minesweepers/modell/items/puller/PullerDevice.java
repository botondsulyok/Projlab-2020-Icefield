package hu.minesweepers.modell.items.puller;

import hu.minesweepers.modell.character.fallbehaviour.Pullable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az interfész felelőssége egy olyan működési felületet implementálni a megvalósító osztályoknak, hogy
 *  azoktól lehessen segítséget kérni egy objektum elhúzásához
 */
public interface PullerDevice extends Parseable {

    /**
     * A metódus felelőssége az objektum elhúzása a cél jégtáblára.
     * @param p az elhúzandó objektum
     * @param iu a jégmező, ahová el akarjuk húzni az objektumot
     */
    void pullByDevice(Pullable p, IceUnit iu);

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();

    String getCommonID();
}
