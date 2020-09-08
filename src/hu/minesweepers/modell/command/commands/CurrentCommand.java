package hu.minesweepers.modell.command.commands;

import hu.minesweepers.controller.GameController;

/**
 * A CurrentCommand osztály felelőssége megvalósítani, és biztosítani a helyes működését a bementi nyelv részét képző
 * current parancsnak.
 */
public class CurrentCommand {

    /**
     * A metódus hatása, hogy kiírjuk a standard kimenetre a pillanatban körön lévő objektum azonosítóját.
     */
    static public void printCurrent(){
        //System.out.println(GameController.getInstance().getCurrent().getID()+ "\n");
    }

}
