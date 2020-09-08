package hu.minesweepers.modell.items.protector;

import hu.minesweepers.modell.character.fallbehaviour.Terrestrial;
import hu.minesweepers.modell.control.Parseable;

/**
 * Az interfész felelőssége olyan közös viselkedés biztosítása az implementáló osztályoknak, hogy azok
 * működésük szerint képesek legyenek megvédeni vízzel érintkezésre érzékeny objektumokat
 */
public interface WaterProtecter extends Parseable {

    /**
     * A függvény hatása, hogy megvédi a víztől az implementálás szerint majd a paraméterként kapott objektumot
     * @param t a vízre érzékeny objektum, akit meg kell védenie a tárgynak
     */
    void protectFromWater(Terrestrial t);

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();

    String getCommonID();
}
