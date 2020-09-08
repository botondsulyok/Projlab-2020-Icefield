package hu.minesweepers.modell.character;

import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az interfész felelőssége, hogy biztosítja az eszközöket egy IceUnit osztályú objektumon való műveletvégzéshez.
 */
public interface IceUnitManipulator {

    /**
     * A metódus, ami elvégzi a paraméterként kapott iceUniton a szükséges műveleteket.
     * @param iu ezen az IceUnit objektumon lehet műveletet végezni
     */
    void useAbility(IceUnit iu);

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
