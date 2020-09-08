package hu.minesweepers.modell.character.movebehaviour;

import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége, hogy olyan működést garantáljon, ami megfelel kétéltű mozgásnak.
 */
public class Amphibious extends Identifiable implements MoveBehaviour {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "amph";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az osztály konstruktora. Meg kell adni az egyedi és a típus azonosító értékét.
     *
     * @param unique egyedi azonosító
     */
    public Amphibious(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * A metódus felelőssége megvalósítani a kétéltű mozgás szabályainak megfelelő mozgást.
     * @param goal a jégmező, ahová tart az objektum
     * @param from a jégmező, ahonnan indul az objektum
     * @param lc a mozgó objektum
     */
    @Override
    public void move(IceUnit goal, IceUnit from, LocationChanger lc) {
        if (from.neigbourUnits(goal)) {     //csak akkor érvényes a mozgás, ha szomszédos jégmezőre mozogna
            lc.setIceUnit(goal);    //beállítja a LocationChanger tartózkodási helyét
        }
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = new ParsedObject();
        obj.add("id", getID());
        obj.add("type", commonID);
        return obj;
    }
}
