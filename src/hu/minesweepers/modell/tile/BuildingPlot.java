package hu.minesweepers.modell.tile;

import hu.minesweepers.modell.character.Refugee;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.building.Building;
import hu.minesweepers.modell.tile.building.Plains;

/**
 * A jégmezőn levő épülethelyeket kezelő absztrakt osztály.
 */
public abstract class BuildingPlot extends Identifiable implements Parseable {
    /**
     * Tárolja a jégmezőn levő épületet.
     */
    private Building building;

    /**
     * A BuildingPlot osztály konstruktora
     * @param unique egyedi azonosító
     * @param common autogenerált azonosító
     */
    public BuildingPlot(String unique, String common){
        super(unique, common);
        building = new Plains(this, null);
    }

    /**
     * Az épülethelyen felépül egy épület. A függvény kicseréli a jelenlegi épülettel az eddigi épületet,
     * amennyiben ez lehetséges.
     * @param b Az épület, amit építeni szeretne a karakter.
     */
    public void build(Building b) {
        building.replace(b);
    }

    /**
     * Szétesik az épület, ez miatt átállítja az épülethelyen levő épületet síkra(Plains osztály).
     */
    public void destructBuilding() {
        building = new Plains(this, null);
    }


    /**
     * Megkapjuk az épületet, ami az épülethelyen van.
     * @return Visszadja az épületet.
     */
    public Building getBuilding()
    {
        return building;
    }

    /**
     * Megadjuk az épületet, ami az épülethelyen van.
     * @param b Az épület, amit beállítunk.
     */
    public void setBuilding(Building b)
    {
        building = b;
        b.setBuldingPlot(this);
    }

    /**
     * A jégtáblán a paraméterben kapott menekült keres
     * menedéket egy épületben.
     * @param r A menekeült, aki éppen menedéket keres.
     */
    public void searchShelter(Refugee r)
    {
        building.getIn(r);
    }

    /**
     * A jégtáblán a paraméterben kapott menekült keres
     * menedéket egy épületben, egy jegesmedve elől.
     * @param r A menekeült, aki éppen menedéket keres.
     */
    public void searchShelterFromIceBear(Refugee r) {
        building.getInFromIceBear(r);
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
        obj.add("building", getBuilding() == null ? "null" : getBuilding().getID());
        return obj;
    }

}
