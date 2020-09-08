package hu.minesweepers.modell.items.consumable;

import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.character.Mortal;

/**
 * Az elfogyasztható tárgyak interfésze.
 */
public interface Consumable extends Pickable, Parseable {

    /**
     * Előírja az elfogyasztás módjának implementálását.
     * @param m az az aki elfogyasztja az objektumot
     */
    void consume(Mortal m);

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
