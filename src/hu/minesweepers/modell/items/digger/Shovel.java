package hu.minesweepers.modell.items.digger;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Egy ásó fajta, amivel több havat lehet eltakarítani, ahhoz képest,
 * mint ha nem rendelkeznénk ezzel az eszközzel.
 */
public class Shovel extends Digger implements Pickable {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "shovel";

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
     * Az osztály konstruktora.
     * @param e az effectiveness tagváltozó ezt az értéket veszi fel
     * @param unique az objektum egyedi azonosítója
     */
    public Shovel(int e, String unique) {
        super(e, unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * Amikor egy objektumhoz kerül a tárgy, akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * Tehát beállítja a karakter osztálynak az egyik attribútumát, ami azt jelöli, hogy a karakter rendelkezik ezzel a tárggyal.
     * Továbbá eltávolítja az itemet az egységen amin volt.
     * @param eq az objektum, aki felveszi az ásót
     * @param iu az IceUnit ahonnan felveszik az ásót
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.equipDigger(this);       //aki felveszi az objektumot, annak beállítja, hogy rendelkezik ezzel a felvehető ásóval
        iu.removePickable();        //eltávolítja az egységről a felvett ásót
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
