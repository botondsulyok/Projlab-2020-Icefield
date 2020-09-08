package hu.minesweepers.modell.items.puller;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.character.fallbehaviour.Pullable;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége annak a felvehető tárgynak a működésének az implementálása, ami segítségével a karakterek
 *  el tudnak húzni elhúzható objektumokat.
 */
public class Rope extends Identifiable implements Pickable, PullerDevice {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "rope";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Rope osztály konstruktora.
     * @param unique egyedi azonosító
     */
    public Rope(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * Amikor egy objektumhoz kerül a tárgy, akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * Tehát beállítja a karakter osztálynak az egyik attribútumát, ami azt jelöli, hogy a karakter rendelkezik ezzel a tárggyal.
     * Továbbá eltávolítja az itemet az egységen amin volt.
     * @param eq az objektum, aki felveszi a kötelet
     * @param iu az IceUnit ahonnan felveszik a kötelet
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.equipPuller(this);
        iu.removePickable();
    }

    /**
     * A metódus felelőssége, hogy elhúzza  kapott elhúzható objektumot a kért jégmezőre
     * @param p az elhúzandó objektum
     * @param iu a jégmező, ahová el akarjuk húzni az objektumot
     */
    @Override
    public void pullByDevice(Pullable p, IceUnit iu) {
        p.reposition(iu);   //jelezzük az elhúzandó objektumnak, hogy új pozícióba került a húzás okán
    }

    @Override
    public String getCommonID() {
        return commonID;
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
