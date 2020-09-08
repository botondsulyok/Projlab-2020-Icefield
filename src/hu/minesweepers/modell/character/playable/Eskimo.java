package hu.minesweepers.modell.character.playable;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceUnitManipulator;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Igloo;

/**
 * Az osztály felelőssége megvalósítani az egyik játszható karaktertípus, az eszkimó működését.
 */
public class Eskimo extends Character implements IceUnitManipulator {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "eskimo";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Eszkimó osztály konstruktora.
     * @param maxB az eszkimó maximális és alapértelmezett testhője
     * @param defS az eszkimó alapértelmezett munkavégző képessége
     * @param unique egyedi azonosító
     */
    public Eskimo(int maxB, int defS, String unique) {
        super(maxB, defS, unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * Iglut épít a kijelölt IceUnit-ra.
     * @param iu erre kell építeni az iglut
     */
    @Override
    public void useAbility(IceUnit iu) {
        if(getStamina()==0 || iu!=getIceUnit()) {
            return;
        }
        Igloo i = new Igloo(iu, null);  //létrehozza az iglut
        iu.build(i);    //felépíti az iglut
        if(iu.getBuilding()==i) {   //akkor tudott építeni, ha az IceUniton levő épület
            tire();  //ha sikeresen tudott építeni
        }
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
