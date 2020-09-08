package hu.minesweepers.modell.items.protector;

import hu.minesweepers.modell.character.fallbehaviour.Terrestrial;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;

/**
 * Azt reprezentálja, amikor a karakter nem rendelkezik semmilyen a víz ellen védő eszközzel és ekkor a karakter meg tud halni.
 * Az osztály feladata a tüdő, mint víztől védőtárgy modellezése.
 */
public class Lung extends Identifiable implements WaterProtecter {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "lung";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Lung osztály konstruktora.
     * @param unique egyedi azonosító
     */
    public Lung(String unique) {
        super(unique, commonID + Integer.toString(objectCount++));
    }

    /**
     * A tüdő nem képes a víztől védeni, ezt jelzi a védelmet kereső objektumnak
     * @param t a vízre érzékeny objektum, akit meg kell védenie a tárgynak
     */
    @Override
    public void protectFromWater(Terrestrial t) {
        t.drowning();   //jelezzük a védelmet keresőnek, hogy a védelem sikertelen volt, ami a karakter fulladását idézi elő
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
