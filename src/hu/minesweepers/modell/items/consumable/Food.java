package hu.minesweepers.modell.items.consumable;

import hu.minesweepers.modell.character.Equipper;
import hu.minesweepers.modell.character.Mortal;
import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Identifiable;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * A  játékban a felvehető ételt reprezentálja.
 */
public class Food extends Identifiable implements Consumable {
    /**
     * Ez az érték azt adja meg, hogy mennyire hatékony az étel.
     * Ezt a hatékonyságot úgy kell értelmezni, hogy hány testhő visszatöltésére képes.
     */
    private final int effectiveness;

    /**
     * A tagváltozóban elmentett karakterlánc segítségével alapértelmezett, egyedi azonosítókat generálhatuk.
     */
    private static final String commonID = "food";

    /**
     * A tagváltozó segítségével számoljuk, hogy az adott objektumból hányat hoztunk létre.
     * Az automatikus egyedi azonosítógeneráláshoz szükséges.
     */
    private static int objectCount = 0;

    /**
     * Az osztály konstruktora.
     * @param e az effectiveness ezt az értéket veszi fel
     * @param unique az objektum egyedi azonosítója
     */
    public Food(int e, String unique) {
        super(unique,commonID + Integer.toString(objectCount++));
        effectiveness = e;
    }

    /**
     * A metódus hatására az ételt elfogyasztó, paraméterként kapott Mortalnak
     * az étel effektívségének alapján testhőt tölt vissza.
     * @param m az objektum ami elfogyasztja az ételt
     */
    @Override
    public void consume(Mortal m) {
        m.restoreBodyHeat(effectiveness);
    }

    /**
     * Amikor egy objektumhoz kerül a tárgy, akkor ez metódus ennek a viselkedésnek a levezénylését végzi.
     * Tehát beállítja a karakter osztálynak az egyik attribútumát, ami azt jelöli, hogy a karakter rendelkezik ezzel a tárggyal.
     * Továbbá eltávolítja az itemet az egységen amin volt.
     * @param eq az objektum, aki felveszi az ételt
     * @param iu az az IceUnit ahonnan felveszik az ételt
     */
    @Override
    public void pickedBy(Equipper eq, IceUnit iu) {
        eq.equipConsumable(this);   //aki felveszi az objektumot, az valahogy interakcióba léphet vele
        iu.removePickable();    //eltávolítja az egységről a felvett objektumot, amin korábban volt
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
        obj.add("effectiveness", String.valueOf(effectiveness));
        return obj;
    }
}
