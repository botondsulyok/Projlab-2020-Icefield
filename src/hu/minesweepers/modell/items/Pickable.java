package hu.minesweepers.modell.items;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * A felvehető tárgyak számára írja elő a felvétel megvalósítását
 */
public interface Pickable extends Parseable{

    /**
     * A felvevés módjának megvalósításának implementálását írja elő.
     * @param eq a felvevő objektum
     * @param iu ahonnan felveszi a felvehető objektumot
     */
    void pickedBy(Equipper eq, IceUnit iu);

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
