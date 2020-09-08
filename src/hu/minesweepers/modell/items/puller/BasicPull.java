package hu.minesweepers.modell.items.puller;

import hu.minesweepers.modell.character.fallbehaviour.Pullable;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * Az osztály felelőssége annak az alapvető húzókészülék működésének implementálása, amivel minden karakter rendelkezik
 *  a játék elején
 */
public class BasicPull extends Identifiable implements PullerDevice {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "basicpull";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az osztály konstruktora.
     * @param unique egyedi azonosító
     */
    public BasicPull(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * A játék szabályai szerint az alapvető húzóképesség nem tud húzni, így ez a metódus sikertelenül húz.
     * @param p az elhúzandó objektum
     * @param iu a jégmező, ahová el akarjuk húzni az objektumot
     */
    @Override
    public void pullByDevice(Pullable p, IceUnit iu) {
        //az alapvető húzási képességével a karakter nem tud húzni
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
