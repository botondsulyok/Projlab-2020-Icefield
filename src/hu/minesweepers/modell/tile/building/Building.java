package hu.minesweepers.modell.tile.building;

import hu.minesweepers.modell.character.Refugee;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.BuildingPlot;

import java.util.ArrayList;

/**
 * Az épületeket kezelő absztrakt osztály.
 */
public abstract class Building extends Identifiable implements Parseable {
    /**
     * Eltárolja, hogy az épület melyik épülethelyen található.
     */
    private BuildingPlot plot;

    /**
     * Az épülethelyen tartózkodó menekültek listája.
     */
    private ArrayList<Refugee> refugees = new ArrayList<>();

    /**
     * Maximum ennyien lehetnek benne az épültben.
     */
    private int capacity;

    /**
     * Konstruktora az épületnek
     * @param bp az építési terület, ami tartalmazza az épületet
     * @param c az épület kapacitása, ennyi menekült bújhat meg benne maximum
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public Building(BuildingPlot bp, int c, String unique, String common) {
        super(unique, common);
        plot = bp;
        capacity = c;
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = new ParsedObject();
        obj.add("id", getID());
        obj.add("type");
        obj.add("capacity", String.valueOf(capacity));
        obj.add("plot", plot == null ? "null" : plot.getID());
        obj.add("refugees");
        for (Refugee r : refugees) {
            obj.add("refugees", r.getID());
        }
        return obj;
    }

    /**
     * A metódus hatására a paraméterként kapott menekült megpróbál a vihar elől elbújni az épületbe.
     * @param r A menekült, aki beszeretne menni az épületbe.
     */
    public void getIn(Refugee r) {
        boolean alreadyIn = false;
        for(Refugee ref : refugees) {   //annak a vizsgálata, hogy a karakter bent van-e a már az épületben
            if(ref==r) {
                alreadyIn = true;
                break;
            }
        }
        if(!alreadyIn) {
            if(refugees.size()>=capacity) {
                r.protectionFailed();   //ha nem volt bent az épületben és nem is tud bemenni, akkor nem védi meg az épület
            }
            else {
                refugees.add(r);    //ha volt még hely az épületben, akkor a karakter bemehet, és megvédi az épület
            }
        }
    }

    /**
     * A metódus hatására a paraméterként kapott menekült megpróbál egy jegesmedve elől elbújni az épületbe.
     * @param r A menekült, aki beszeretne menni az épületbe a jegesmedve elől.
     */
    public void getInFromIceBear(Refugee r) {
        boolean alreadyIn = false;
        for(Refugee ref : refugees) {   //annak a vizsgálata, hogy a karakter bent van-e a már az épületben
            if(ref==r) {
                alreadyIn = true;
                break;
            }
        }
        if(!alreadyIn) {
            if(refugees.size()>=capacity) {
                //ha nem volt bent az épületben és nem is tud bemenni, akkor nem védi meg az épület a menekültet a jegesmedvétől
                r.protectionFailedFromBearFailed();
            }
            else {
                refugees.add(r);    //ha volt még hely az épületben, akkor a karakter bemehet, és megvédi az épület
            }
        }
    }

    /**
     * Mivel a karakter átlép egy másik mezőre,
     * ha esetleg benne volt az épületben, akkor elhagyja azt.
     * @param r az aki elhagyja az épületet
     */
    public void leave(Refugee r) {
        refugees.remove(r);
    }

    /**
     * A vihar elkapja az épületet és az épület típusától függően
     * történik valami az épülettel.
     */
    abstract public void struckByStorm();

    /**
     * Megkapjuk az épülethelyet, amin az épület rajta van.
     * @return Visszadja az épülethelyet.
     */
    BuildingPlot getPlot() {
        return plot;
    }


    /**
     * A függvény segítségével jelezzük az épületnek, hogy helyére más épületet akarnak helyezni. A függvény
     * implementációra erre a helyzetre reagálnak az implementáló osztályok felelőssége szerint.
     * @param b az új épület, amit el akarunk helyezni a jelen épület helyére
     */
    public abstract void replace(Building b);

    /**
     * Visszaadja, hogy melyik épülethelyen van az épület.
     * @return az épülethelyen, amin az épület elhelyezkedik
     */
    public BuildingPlot getBuildingPlot() {
        return plot;
    }

    public void setBuldingPlot(BuildingPlot bp) {
        plot = bp;
    }

}
