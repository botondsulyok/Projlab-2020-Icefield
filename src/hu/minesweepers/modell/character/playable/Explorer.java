package hu.minesweepers.modell.character.playable;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceUnitManipulator;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége megvalósítani az egyik játszható karaktertípus, az explorer működését.
 */
public class Explorer extends Character implements IceUnitManipulator {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "explorer";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Felfedező (Explorer) osztály konstruktora.
     * @param maxB az explorer maximális és alapértelmezett testhője
     * @param defS az explorer alapértelmezett munkavégző képessége
     * @param unique egyedi azonosító
     */
    public Explorer(int maxB, int defS, String unique) {
        super(maxB, defS, unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * Megméri, hogy mennyit bír el a paraméterként kapott mező.
     * @param iu ennek az IceUnit-nak kell megnézni a teherbító képességét
     */
    @Override
    public void useAbility(IceUnit iu) {
        if(getStamina()==0 || (!getIceUnit().neigbourUnits(iu) && iu!=getIceUnit())) {
            return;
        }
        iu.show();      //szól az IceUnitnak, hogy jelenítse meg a teherbíróképességét
        tire();
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.set("type", commonID);
        return obj;
    }
}
