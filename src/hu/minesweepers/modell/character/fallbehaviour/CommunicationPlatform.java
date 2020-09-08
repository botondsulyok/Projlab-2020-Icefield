package hu.minesweepers.modell.character.fallbehaviour;

import hu.minesweepers.modell.items.components.Component;

import java.util.ArrayList;

/**
 * Az interfész felelőssége, hogy definiálja, hogy az olyan csatornákon, ahol történhet kommunikáció, azon ez hogyan
 * történhet. Az implementáló osztályok feladata a különböző kommunikációfajták implementálása.
 */
public interface CommunicationPlatform {

    /**
     * A metódus segítségével összegyűjthetőek a környező komponensek.
     * @return az összegyűjtött komponensek
     */
    ArrayList<Component> askForComponents();

    /**
     * A metódus segítségével jelezheti egy elhúzható objektum, hogy elhúzást kér.
     * @param p az elhúzandó objektum
     */
    void callForPullout(Pullable p);

    /**
     * A metódus segítségével utasíthatjuk a csatornában az elhúzásra képes objektumokat, hogy a paraméterként kapott
     * elhúzható objektumot húzzák el.
     * @param p az elhúzandó objektum
     */
    void alertNeighbourForPullout(Pullable p);
}
