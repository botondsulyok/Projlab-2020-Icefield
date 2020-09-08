package hu.minesweepers.modell.tile;

import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Storm;
import hu.minesweepers.modell.control.Strikeable;

import java.util.*;

/**
 * Tárolja, hogy milyen egységekből épül fel a pálya, és az egységeket vezérli.
 */
public class IceField extends Identifiable implements StormyField {

    /**
     * A játék mezői.
     */
    private ArrayList<IceUnit> iceUnits = new ArrayList<>();

    /**
     * Minden olyan objektum a játékban, amire hathat katasztrófa.
     */
    private ArrayList<Strikeable> strikeables = new ArrayList<>();

    /**
     * Minden egyes mezőn létező objektum.
     */
    private ArrayList<Stander> standers = new ArrayList<>();

    /**
     * A StormyField interfész metódusának implementációja, a
     * függvény hatására az összes strikable interfészű objektumot a vihar hatásaival sújtja.
     */
    public void unleashStorm() {
        Storm storm = new Storm();
        storm.strikeStrikeable(strikeables);
    }

    /**
     * Hozzáad egy vihar által sújtható dolgot a listához.
     * @param st A vihar által sújtható dolog.
     */
    public void addStrikeables(Strikeable st)
    {
        strikeables.add(st);
    }

    /**
     * A függvény segítségével lekérhető a jégmezőt alkotó összes jégtábla.
     * @return a jégmezőt alkotók jégtáblák egy ArrayList objektumban
     */
    @Override
    public ArrayList<IceUnit> getIceUnits(){
        return iceUnits;
    }

    /**
     * A függvény segítségével új jégtábla adható a jégmezőhöz.
     * @param newunit a jégtábla, amit hozzáadunk a jégmezőhöz
     */
    public void addIceUnit(IceUnit newunit){
        iceUnits.add(newunit);
    }
}
