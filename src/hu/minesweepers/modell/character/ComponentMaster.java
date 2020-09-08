package hu.minesweepers.modell.character;

import hu.minesweepers.modell.items.components.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ComponentMaster interfész.
 * A megvalósító objektumok tudnak komponensekkel műveleteket végezni. Ezeket a műveletek típusát az interfész
 * definiálja.
 */
public interface ComponentMaster {

    /**
     * A függvény segítségével elrakhatjuk a kapott komponenst.
     * @param c A kapott komponens
     */
    void addToComponents(Component c);

    /**
     * A függvénysegítségével eldobhatunk egy komponenst.
     * @param c Az eldobandó komponens
     */
    void removeComponent(Component c);

    /**
     * A függvény segítségével lekérdezhetőek a komponenseink.
     * @return a komponenseink
     */
    List<Component> getComponents();

    /**
     * A függvény segítségével elkérhetjük más mesterek komponenseit.
     * @return más mesterek komponensei
     */
    ArrayList<Component> getOtherMastersComponents();

    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
