package hu.minesweepers.modell.character;

import hu.minesweepers.modell.items.protector.WaterProtecter;
import hu.minesweepers.modell.items.consumable.Consumable;
import hu.minesweepers.modell.items.digger.Digger;
import hu.minesweepers.modell.items.puller.PullerDevice;
import hu.minesweepers.modell.tile.building.Tent;

/**
 * A tárgyakat felvenni képes objektumok interfésze.
 */
public interface Equipper extends ComponentMaster {

    /**
     * A függvény segítségével felvehetünk egy elfogyasztható objektumot.
     * @param cons az elfogyasztható objektum
     */
    void equipConsumable(Consumable cons);

    /**
     * A függvény segítségével felvhetünk egy ásótárgyat.
     * @param d az ásótárgy, amit felveszünk
     */
    void equipDigger(Digger d);

    /**
     * A függvény segítségével felvehetünk egy víztől védő tárgyat.
     * @param wp a felvett víztől védő tárgy
     */
    void equipWaterProtecter(WaterProtecter wp);

    /**
     * A függvény segítségével felvehetünk egy húzótárgyat.
     * @param p a felvett húzótárgy
     */
    void equipPuller(PullerDevice p);

    /**
     * A függvény segítségével felvehetünk egy összerakható sátrat.
     * @param t a felvett lerakható sátor
     */
    void equipTent(Tent t);
}
