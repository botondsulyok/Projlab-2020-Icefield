package hu.minesweepers.modell.character;

import hu.minesweepers.modell.character.movebehaviour.Collideable;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.IceUnit;

import java.io.Serializable;

/**
 * Az osztály felelőssége, hogy reprezentálja a játékban azokat az objektumokat, akik jégmezőkön állhatnak.
 */
public abstract class Stander extends Identifiable implements Collideable, Parseable, Serializable {

    /**
     * A jégmező, amin megtalálható az objektum.
     */
    private IceUnit iceUnit;

    /**
     * Stander osztály konstruktora, a paraméterek az azonosíthatósághoz szükségesek.
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Stander(String unique, String common){
        super(unique, common);
    }

    /**
     * A függvény felelőssége, hogy implementálva lereagálja, ha a reprezentált objektum éppen esik le a jégmezőről.
     */
    abstract public void fall();

    /**
     * A függvény felelőssége, hogy implementálja azt a cselekedetet, amikor az objektum leesett a jégmezőről.
     */
    abstract public void fallenDown();

    /**
     *A függvény hatása, hogy beállítja a jégmezőt, amin tartózkodik az objektum.
     * @param iu a beállítandó jégmező
     */
    public void setIceUnit(IceUnit iu)
    {
        iceUnit = iu;
    }

    /**
     *A függvény visszaadja a jégmezőt, amin tartózkodik az objektum.
     * @return a jégmező, amin tartózkodik az objektum
     */
    public IceUnit getIceUnit(){
        return iceUnit;
    }

    /**
     * A függvény hatására létrehozunk egy ParsedObject példányt, ami az objektum adatainak felhasználására kényelmes
     * felületet biztosít.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = new ParsedObject();
        obj.add("id", getID());
        obj.add("type");
        obj.add("iceUnit", (iceUnit == null) ? "null" : iceUnit.getID());
        return obj;
    }

}
