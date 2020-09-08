package hu.minesweepers.modell.character.movebehaviour;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceBear;

/**
 * Collideable interfész.
 * Az interfész feladata működési felületet biztosítani minden olyan objektumnak, amik egymással ütközhetnek.
 * Az ütközések hatásai az implementáló osztályok implementált függvényeiben lesznek definiálva.
 */
public interface Collideable {

    /**
     * Az objektumunk összeütközik egy karakterrel. A függvény implementációja a hatása az ütközésnek.
     * @param c A karakter, akivel összeütköztünk
     */
    void hitBy(Character c);

    /**
     * Az objektumunk összeütközik egy jegesmedvével. A függvény implementációja a hatása az ütközésnek.
     * @param ib A jegesmedve, akivel összeütköztünk
     */
    void hitBy(IceBear ib);

}
