package hu.minesweepers.modell.character;

import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.command.ParsedObject;

/**
 * Az osztály felelőssége működést biztosítani az olyan mozogni képes objektumoknak, akik rendelkeznek testhővel,
 * és implementálni a testhő változásához kapcsolódó hatásokat az objektumon
 */
public abstract class Mortal extends LocationChanger {

    /**
     * Az objektum testhőjét reprezentáló attribútum
     */
    private int bodyHeat;

    /**
     * A testhő maximumát jelzi.
     */
    private int maxBodyHeat;

    /**
     * A Mortal osztály konstruktora.
     * @param maxB Az objektum által maximum elérhető testhő.
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Mortal(int maxB, String unique, String common) {
        super(unique, common);
        maxBodyHeat = maxB;
        bodyHeat = maxBodyHeat;
    }

    /**
     * A metódus hatása, hogy csökkenthető vele a testhő
     * @param n a mérték, amivel csökken az objektum testhője
     */
    public void reduceBodyHeat(int n) {
        bodyHeat = bodyHeat - n;
    }

    /**
     * Visszaadja a testhő aktuális értékét.
     * @return a testhő értéke
     */
    public int getBodyHeat() {
        return bodyHeat;
    }

    /**
     * A metódus hatása, hogy növelhető vele a testhő
     * @param n a mérték, amivel nő az objektum testhője
     */
    public void restoreBodyHeat(int n) {
        bodyHeat = bodyHeat + n;    //megnöveli a testhő értékét
        if(bodyHeat>maxBodyHeat) {
            bodyHeat = maxBodyHeat;     //a testhő nem lehet több egy bizonyos értéknél
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
        obj.add("bodyheat", String.valueOf(bodyHeat));
        return obj;
    }

    /**
     * Testhőmérséklet beállítása
     * @param b Testhőmérséklet
     */
    public void setBodyHeat(int b) {
        bodyHeat = b;
    }
}
