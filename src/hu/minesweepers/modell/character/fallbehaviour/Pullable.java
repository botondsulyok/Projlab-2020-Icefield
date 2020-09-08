package hu.minesweepers.modell.character.fallbehaviour;

import hu.minesweepers.modell.tile.IceUnit;

/**
 * Pullable interfész.
 * A Pullable osztály által reprezentált objektumok rendelkeznek a játékban azzal a tulajdonsággal, hogy elhúzhatóak.
 * Ehhez rendelkeznek a megfelelő felülettel, hogy számukra jelezhető legyen, hogy el lettek húzva.
 */
public interface Pullable{

    /**
     * Ezzel a metódussal jelezhető az objektumnak, hogy elhúzták egy másik jégmezőre
     * @param goal a jégtábla, ahová elhúzták az objektumot
     */
    void reposition(IceUnit goal);

    /**
     * Visszaadja, hogy az elhúzható éppen melyik jégtáblán van
     * @return a jégtábla, amin van az elhúzható objektum
     */
    IceUnit getIceUnit();

    /**
     * A metódussal jelezhető, hogy sikertelen volt az elhúzható objektum elhúzása
     */
    void helpFailed();

}
