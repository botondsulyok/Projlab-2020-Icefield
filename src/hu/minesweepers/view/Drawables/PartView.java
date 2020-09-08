package hu.minesweepers.view.Drawables;

import hu.minesweepers.modell.tile.IceUnit;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Egy olyan kirajzolható objektum, amit kirajzolódik bizonyos koordinátákra.
 */
public abstract class PartView extends TexturedDrawable {

    /**
     *
     * @return Implementálni kell azt a működést, hogy a nézet visszatér azzal,
     * hogy melyik IceUnit modell objektum tartozik a nézethez tartozó modellbeli objektumhoz.
     */
    public abstract IceUnit checkIceUnit();

}
