package hu.minesweepers.modell.character.fallbehaviour;

/**
 * Az interfész felelőssége biztosítani az implementáló osztályoknak olyan viselkedést, ami lehetővé teszi
 * nekik, hogy el tudjanak húzni elhúzható objektumokat.
 */
public interface Puller {

    /**
     * A metódus hatására elhúzzuk a paraméterként kapott elhúzható objektumot
     * @param p Az elhúzható objektum
     */
    void pull(Pullable p);
    /**
     * Minden, az interfészt megvalósító objektumtól elvárjuk, hogy egy karakterlánccal azonosítható legyen.
     * A függvény felelőssége, hogy lekérdezze, és visszatérési értéke ez az azonosító karakterlánc.
     * @return az objektumot azonosító karakterlánc
     */
    String getID();
}
