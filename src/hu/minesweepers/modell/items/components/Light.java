package hu.minesweepers.modell.items.components;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * A  Light felelőssége az, hogy a jelzőfényespisztoly komponenseként belőle összeállítható legyen a jelzőfényespisztoly.
 */
public class Light extends AlarmPistolComponent implements Pickable {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "light";

    @Override
    public String getCommonID() {
        return commonID;
    }

    /**
     * Komponens típusa
     */
    private static final String type = "Light";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    public Light(String unique){
        super(unique, commonID + Integer.toString(objectCount++), type);
    }

    /**
     * Amikor egy objektumhoz kerül a tárgy, akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * Tehát beállítja a karakter osztálynak az egyik attribútumát, ami azt jelöli, hogy a karakter rendelkezik ezzel a tárggyal.
     * Továbbá eltávolítja az itemet az egységen amin volt.
     * @param eq az objektum, aki felveszi a jelzőfényt
     * @param iu az IceUnit ahonnan felveszik a jelzőfényt
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.addToComponents(this);      //aki felveszi az objektumot, annak beállítja, hogy rendelkezik ezzel a felvehető dologgal
        iu.removePickable();           //eltávolítja az egységről a felvett objektumot
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
