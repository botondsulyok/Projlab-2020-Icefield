package hu.minesweepers.modell.items.digger;

import hu.minesweepers.modell.command.ParsedObject;

/**
 * Egy ásó fajta, tud ásni, de nem rendelkezik semmilyen további viselkedéssel.
 */
public class BareHand extends Digger {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "barehand";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * A BareHand osztály konstruktora. A konstruktor az osztály felelősségének megfelelően hólapátoló képességét
     *  egy hó ellapátolására alkalmas mértékre állítja.
     * @param unique az objektum egyedi azonosítója
     */
    public BareHand(String unique) {
        super(1, unique, commonID + Integer.toString(objectCount++));
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
