package hu.minesweepers.modell.tile;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.items.digger.Digger;

/**
 * Az interfész felelőssége, hogy az őt megvalósító objektumok áshatóak legyenek.
 */
public interface Digsite {

    /**
     * A metódus felelőssége, hogy biztosítja az implementáló osztályokban a működését,
     * amikor egy karakter ás a megvalósító objektumon.
     *@param c az a karakter aki az ásást végzi
     *@param d az ásást végző tárgy
     */
    void digSnow(Character c, Digger d);

}
