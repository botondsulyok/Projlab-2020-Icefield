package hu.minesweepers.modell.control;



/**
 * Katasztrófa által sújtható dolgok.
 */
public interface Strikeable {

    /**
     * Katasztrófa sújtja az ezt a függvényt megvalosító objektumot.
     */
    void struckByDisaster();
}
