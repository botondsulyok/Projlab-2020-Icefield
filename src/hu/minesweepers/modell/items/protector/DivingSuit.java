package hu.minesweepers.modell.items.protector;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.character.fallbehaviour.Terrestrial;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége a játékban található búvárruha helyes, szabályszerű modellezése
 * Az osztály objektumai felvehetőek
 */public class DivingSuit extends Identifiable implements Pickable, WaterProtecter {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "suit";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * DivingSuit osztály konstruktora.
     * @param unique egyedi azonosító
     */
    public DivingSuit(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * Amikor egy objektumhoz kerül a tárgy, akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * Tehát beállítja a karakter osztálynak az egyik attribútumát, ami azt jelöli, hogy a karakter rendelkezik ezzel a tárggyal.
     * Továbbá eltávolítja az itemet az egységen amin volt.
     * @param eq az objektum, aki felveszi az búvárruhát
     * @param iu az IceUnit ahonnan felveszik az búváruhát
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.equipWaterProtecter(this);
        iu.removePickable();
    }

    /**
     * Az objektum megvédi a védelmet kérő objektumot a vízbeeséstől.
     * @param t a vízre érzékeny objektum, akit meg kell védenie a tárgynak
     */
    @Override
    public void protectFromWater(Terrestrial t) {
        //Megvédi a védelmet kérő objektumot, nem jelez neki a fulladás miatt, hiszen nem fullad
    }

    @Override
    public String getCommonID() {
        return "divingsuit";
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
