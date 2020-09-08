package hu.minesweepers.view.Drawables;

import java.awt.*;

/**
 * A kirajzolható objektumok interfésze.
 */
public interface IDrawable {

    /**
     * Előírja a rajzolás megvalósítását az implementáló osztályok számára.
     * @param g erre az objektumra lehet rajzolni
     */
    void draw(Graphics g);

    /**
     * Előírja a megadott koordinátákra való rajzolás megvalósítását az implementáló osztályok számára.
     * @param g erre az objektumra lehet rajzolni
     */
    void draw(Graphics g, int x, int y);

}
