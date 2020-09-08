package hu.minesweepers.view.Drawables;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Egy olyan absztrakt osztály, ami kirajzolható és rendelkezik valamilyen textúrával,
 * amit egy képfájlból lehet beolvasni.
 */
public abstract class TexturedDrawable implements IDrawable {

    /**
     * A textúra.
     */
    private BufferedImage texture;

    /**
     * A kirajzolás szélessége.
     */
    private int width = 40;

    /**
     * A kirajzolás magassága.
     */
    private int height = 40;

    /**
     * Kirajzolja magát, a megadott koordinátákra, a megfelelő méretben.
     * @param g erre az objektumra lehet rajzolni
     * @param x a rajzolás x koordinátája
     * @param y a rajzolás y koordinátája
     */
    @Override
    public void draw(Graphics g, int x, int y) {
        Image image = getTexture().getScaledInstance(height, width, Image.SCALE_DEFAULT);
        g.drawImage(image, x, y, null);
    }

    /**
     * Ha nem kap paraméterben koordinátákat, akkor nem lehet rajzolni.
     * @param g erre az objektumra lehet rajzolni
     */
    @Override
    public void draw(Graphics g) {

    }

    /**
     * Visszaadja a textúráját.
     * @return a textúrája
     */
    public BufferedImage getTexture() {
        return texture;
    }

    /**
     * Beállítható a textúra.
     * @param t a kapott textúre
     */
    public void setTexture(BufferedImage t) {
        texture = t;
    }

    /**
     * Visszaadja a kirajzolás szélességét.
     * @return a kirajzolás szélessége
     */
    public int getWidth() {
        return width;
    }

    /**
     * Visszaadja a kirajzolás magasságát.
     * @return a kirajzolás magasságát
     */
    public int getHeight() {
        return height;
    }

}
