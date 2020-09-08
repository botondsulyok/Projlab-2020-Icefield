package hu.minesweepers.modell.tile.building;

import hu.minesweepers.modell.character.Refugee;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.BuildingPlot;

/**
 * Egy épület, ahol nincs semmi. Alapból minden jégtáblán "sík épület" van, és kicserélődik , ha
 * építenek rá egy másik épületet, illetve erre cserélődik, ha szétesik egy épület.
 */
public class Plains extends Building {
    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "plains";

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
     * A Plains osztály konstruktora
     * A konstruktor nulla kapacitást állít be.
     * @param bp a hely ahova a síkság kerül
     * @param unique egyedi azonosító
     */
    public Plains(BuildingPlot bp, String unique) {
        super(bp, 0, unique,  commonID + Integer.toString(objectCount++));
    }

    @Override
    /**
     * A síkon a menekült nem tud sehova sem bemenni. Ezért mindenképp veszít testhőt.
     * @param r A menekült, aki nem tud elmenekülni a vihar elől.
     */
    public void getIn(Refugee r) {
        r.protectionFailed();
    }

    /**
     * Felülírja az ősosztály függvényét.
     * A síkon a paraméterben kapott menekült nem tud sehova bemenekülni a jégmedve elől.
     * @param r A menekült, aki beszeretne menni az épületbe a jegesmedve elől.
     */
    @Override
    public void getInFromIceBear(Refugee r) {
        r.protectionFailedFromBearFailed();
    }

    /**
     * Elkapja a vihar az épületet. A síknál ez nem jelent semmit, hiszen nincs épület.
     */
    @Override
    public void struckByStorm() {
        //Ha vihar éri a síkot, akkor abban nem tesz kárt.
    }

    /**
     * A metúdus feladata, annak levezénylése, amikor az épület helyére
     * egy másik épület kerülne, a Plains objektumok lecserélhetőek.
     * @param b az épület ami a jelenlegi épület helyére kerülne
     */
    @Override
    public void replace(Building b) {
        BuildingPlot bp = getBuildingPlot();
        bp.setBuilding(b);
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
