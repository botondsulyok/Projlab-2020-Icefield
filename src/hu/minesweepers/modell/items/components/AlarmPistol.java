package hu.minesweepers.modell.items.components;

import hu.minesweepers.controller.GameController;

/**
 * A játék célja ezen osztály egy példányának létrehozása, ez a játék megnyerését jelenti.
 */
public class AlarmPistol {


    /**
     * konstruktor, amikor meghívódik, az a játék győzelmét jelenti
     */
    public AlarmPistol() {
        GameController.getInstance().endGame(true);
    }
}
