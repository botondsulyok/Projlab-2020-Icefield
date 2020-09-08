package hu.minesweepers.modell.character.movebehaviour;

import hu.minesweepers.modell.character.FiniteActionDoer;
import hu.minesweepers.modell.character.Stander;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége, hogy olyan objektumokat definiáljon, akik  valamilyen módon
 * képesek mozogni jégtábláról jégtáblára.
 */
public abstract class LocationChanger extends Stander implements FiniteActionDoer {
    /**
     * Az objektum mozgásformáját definiálja, és a mozgás eszerint a viselkedés szerint valósul meg.
     */
    private MoveBehaviour behaviour;

    /**
     * LocationChanger osztály konstruktora, a paraméterek az azonosíthatósághoz szükségesek.
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public LocationChanger(String unique, String common){
        super(unique, common);
    }

    /**
     * A függvény hatása, hogy az objektum mozgási viselkedése megváltoztatható vele
     * @param mb a beállítandó mozgási viselkedés
     */
    public void changeMoveBehaviour(MoveBehaviour mb) {
        behaviour = mb;
    }

    /**
     * A függvény hatására az objektum a paraméterben átadott cél jégmező elmozog.
     * @param goal A cél jégmező, amire el tervezünk mozdulni
     */
    public void move(IceUnit goal, IceUnit from) {
        behaviour.move(from, goal, this);
    }

    /**
     * A metódus felelőssége, hogy meghívásával az objektum munkavégző képességét csökkentsük.
     */
    abstract public void tire();

    /**
     * A metódus felelőssége, hogy meghívásával az objektum munkavégző képességét növeljük.
     */
    abstract public void rest();

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.add("movebehaviour", behaviour.getID());
        return obj;
    }

    /**
     * Visszaadja az objektum mozgásformáját
     * @return Objektum mozgásformája
     */
    public MoveBehaviour getBehaviour() {
        return behaviour;
    }
}
