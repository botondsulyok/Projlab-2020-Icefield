package hu.minesweepers.modell.character;

import hu.minesweepers.modell.control.Strikeable;

/**
 * A játékban levő menekülteknek az interfésze, ezeket érhet valamilyen katasztrófa.
 */
public interface Refugee extends Strikeable{

    /**
     * Ha a menekültnek nincsen fedezéke a katasztrófák ellen, akkor nincsen neki protekciója.
     */
    void protectionFailed();

    /**
     * Ha a menekültnek nincsen fedezéke a jegesmedve ellen, akkor nincsen neki protekciója.
     */
    void protectionFailedFromBearFailed();

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
