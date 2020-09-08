package hu.minesweepers.modell.control;

import java.util.List;

/**
 * A játékban levő katasztrófáknak az interfésze.
 */
public interface Disaster {

    /**
     * A katasztrófa elkapja a paraméterben kapott lecsapható dolgokat.
     * @param s katasztrófa által lecsapható dolgok listája
     */
    void strikeStrikeable(List<Strikeable> s);
}
