package hu.minesweepers.modell.tile.building;

import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.tile.BuildingPlot;

/**
 * Az elpusztítható épületeket kezelő absztrakt osztály.
 */
public abstract class DestructableBuilding extends Building {

    /**
     * Az elpusztítható épületnek az élete.
     */
    private int health = 1;

    /**
     * A DestructableBuilding osztály konstruktora
     * @param bp az építési terület, ahol az épület áll
     * @param c az épület kapacitása
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public DestructableBuilding(BuildingPlot bp, int c, String unique, String common)
    {
        super(bp, c, unique, common);
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = super.convertToParsedObject();
        obj.add("health", String.valueOf(health));
        return obj;
    }

    /**
     * A vihar elkapja az épületet. Az elpusztítható épületeknél ez azt jelenti, hogy
     * csökkenti az életüket.
     */
    @Override
    public void struckByStorm()
    {
        decreaseHealth();
    }

    /**
     * Csökkenti az életét az épületnek, illetve felelős arra is, hogy ha
     * nincs több élete az épületnek akkor szóljon, hogy elkell pusztítani.
     */
    public void decreaseHealth() {
        health--;
        if(health==0) {     //ha elfogyott a lerombolható épület élete, akkor megszűnik
            getPlot().destructBuilding();
        }
    }

    /**
     * A metúdus feladata, annak levezénylése, amikor az épület helyére
     * egy másik épület kerülne, a DestructableBuilding típusú objektumok nem lecserélhetőek.
     * @param b az épület ami a jelenlegi épület helyére kerülne
     */
    @Override
    public void replace(Building b) {
        //nem lehet ledönteni, azért hogy egy másik épület kerüljön a helyére
    }
}
