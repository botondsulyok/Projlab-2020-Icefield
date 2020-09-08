package hu.minesweepers.modell.control;

import java.util.List;

/**
 * A vihart reprezentáló osztály.
 * Ez az osztály egy katasztrófa, aminek a felelőssége az, hogy megcsapja viharral a jégmezőt.
 */
public class Storm implements Disaster {

    /**
     * A vihar lecsap a paraméterben kapott lecsapható dolgokra.
     * @param strikeables katasztrófa által lecsapható dolgok listája
     */
    @Override
    public void strikeStrikeable(List<Strikeable> strikeables) {
        //meghívja a paraméterként kapott listában levő dolgoknak a struckByDisaster függvényét
        for(Strikeable s : strikeables) {
            s.struckByDisaster();
        }
    }
}
