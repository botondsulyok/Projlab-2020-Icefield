package hu.minesweepers.modell.character.fallbehaviour;

/**
 *  Az interfész feladata egy olyan közös működés garantálása a megvalósító osztályoknak, amik
 *  ha vízzel érintkeznek, akkor megfulladhatnak.
 */
public interface Terrestrial {

    /**
     * A metódus hatása, hogy jelzi a megvalósító objektumnak, hogy fullad a víz miatt.
     */
    void drowning();
}
