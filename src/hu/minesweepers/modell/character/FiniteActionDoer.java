package hu.minesweepers.modell.character;

/**
 * Az interfész felelőssége, hogy az implementáló osztályoknak biztosítson olyan viselkedést, ami lehetővé
 * teszi számukra, hogy azok munkaképésségét kívülről befolyásolni lehessen.
 */
public interface FiniteActionDoer {

    /**
     * A metódus hatására az objektum munkavégző képessége csökken
     */
    void rest();

    /**
     * A metódus a hatására az objektum munkavégző képessége nő
     */
    void tire();
}
