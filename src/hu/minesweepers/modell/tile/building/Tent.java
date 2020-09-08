package hu.minesweepers.modell.tile.building;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.character.Refugee;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.tile.BuildingPlot;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * A sátort reprezentálja,
 * ami megvédi a benne tartózkodókat a vihartól, de medvetámadástól viszont már nem.
 */
public class Tent extends DestructableBuilding implements Pickable, Commandable {

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "tent";

    /**
     * Azt jelzi, hogy ekkor rakták le a sátrat, tehát az első körében még nem dől le.
     */
    boolean firstRound = true;

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
     * A tent osztály konstruktora
     * A konstruktor a sátor kapacitását egyre állítja.
     * @param bp a hely ahova a síkság kerül
     * @param unique egyedi azonosító
     */
    public Tent(BuildingPlot bp, String unique) {
        super(bp, 1, unique, commonID + Integer.toString(objectCount));
    }

    /**
     * Bemegy a sátorba a paraméterben kapott jegesmedve elől menekülő objektum,
     * azonban ez semmilyen fajta védelmet nem nyújt neki a medvetámadás elől.
     * @param r az az objektum aki bemenekül a sátorba
     */
    @Override
    public void getInFromIceBear(Refugee r) {
        r.protectionFailedFromBearFailed();
    }

    /**
     * Végrehajtja a megfelelő parancsot, ez minden körben egyszer következik be a sátor esetében,
     * és ez azt eredményezi, hogy a sátor egy kör után eltűnik,
     * eltávolítva magát az épülethelyéről, természetesen abban a körben, amikor lerakták még nem tűnik el.
     */
    @Override
    public void executeCommand() {
        if(!firstRound) {
            getPlot().destructBuilding();
        }
        firstRound = false;
    }

    /**
     * Amikor egy karakterhez kerül a sátor,
     * akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * @param eq a felvevő sátor
     * @param iu ahonnan felveszi a sátrat
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.equipTent(this);
        iu.removePickable();
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
