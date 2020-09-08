package hu.minesweepers.modell.character.movebehaviour;

import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 *Az osztály felelőssége, hogy működésével olyan viselkedést garantáljon, ami megfelel a szárazföldi mozgásnak
 */
public class Walk extends Identifiable implements MoveBehaviour {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "walk";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az osztály konstruktora. Meg kell adni az egyedi és a típus azonosító értékét.
     *
     * @param unique egyedi azonosító
     */
    public Walk(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }


    //Ezt átírtam alul, mert hóval fedett lukra nem lehetett vele lépni

    /**
     * A metódus felelőssége megvalósítani a szárzföldi mozgás szabályainak megfelelő mozgást.
     * @param goal a jégmező, ahová tart az objektum
     * @param from a jégmező, ahonnan indul az objektum
     * @param lc a mozgó objektum
     */
    @Override
    public void move(IceUnit goal, IceUnit from, LocationChanger lc) {
        if(from.neigbourUnits(goal)) {      //csak akkor érvényes a mozgás, ha szomszédos jégmezőre mozogna
            if(goal.getSnow() > 0 || !goal.getBroken())      //Ha van rajta hó vagy nem törött, megyünk
                lc.setIceUnit(goal);    //beállítja a LocationChanger tartózkodási helyét
        }
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
