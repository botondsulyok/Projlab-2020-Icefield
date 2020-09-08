package hu.minesweepers.modell.control;

/**
 * Az interfész felelőssége, hogy az irányítható karakterekhez egy irányítási felüetet biztosíton.
 */
public interface Commandable extends Parseable {

    /**
     * A paraméternek megfelelő parancs végrehajtása.
     */
    void executeCommand();

    /**
     * Elvárjuk minden irányítható objektumtól, hogy azonosítható legyen. A metódus visszatér ezzel az egyedi
     * azonosítóval.
     * @return az irányítható objektum azonosítója
     */
    String getID();
}
