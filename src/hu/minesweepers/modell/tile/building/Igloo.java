package hu.minesweepers.modell.tile.building;

import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.BuildingPlot;

/**
 * A játékban levő iglu elpusztítható épületet valósítja meg.
 */
public class Igloo extends DestructableBuilding {
    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "igloo";

    @Override
    public String getCommonID() {
        return commonID;
    }

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az iglu konstruktora
     * A konstruktor egy kapacitásúra állítja be az iglut.
     * @param bp a hely ahova az iglu kerül
     * @param unique egyedi azonosító
     */
    public Igloo(BuildingPlot bp, String unique)
    {
        super(bp, 1, unique, commonID + Integer.toString(objectCount));
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
