package hu.minesweepers.modell.character.movebehaviour;

import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az interfész feladata, egy közös hozzáférési felület biztosítása azok osztályok számára, amiknek a
 * felelőssége
 */
public interface MoveBehaviour extends Parseable {
    /**
     * A metódus hatására az implementáló osztály szabályai szerint mozog a mozgó objektum
     * @param goal a jégmező, ahová tart az objektum
     * @param from a jégmező, ahonnan indul az objektum
     * @param lc a mozgó objektum
     */
    void move(IceUnit goal, IceUnit from, LocationChanger lc);
    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
