package hu.minesweepers.view.Drawables;

import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.view.TextureType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Egy a modellben szereplő Character objektum kirajzolása a feladata, annak adatai alapján.
 */
public class CharacterView extends PartView {

    /**
     * A nézethez tartozó modell, aminek az adatai alapján jelenik meg, rajzolódik ki a nézet.
     */
    private Character character;

    /**
     * Konstruktor, ami a kapott TextureType enum alapján beolvassa, majd beállítja a nézet textúráját.
     * @param tt ez a textúra típusa
     * @param c a nézethez tartozó modellbeli objektum
     */
    public CharacterView(TextureType tt, Character c) {
        BufferedImage texture = null;
        if(tt == TextureType.EXPLORER) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "explorer.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(tt == TextureType.ESKIMO) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "eskimo.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "defaulttexture.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setTexture(texture);
        character = c;
    }

    /**
     * A karakter kirajzolása, ha ez éppen az aktív karakter, akkor egy piros keret veszi körül.
     * @param g erre az objektumra lehet rajzolni
     * @param x a rajzolás x koordinátája
     * @param y a rajzolás y koordinátája
     */
    @Override
    public void draw(Graphics g, int x, int y) {
        super.draw(g, x, y);
        if(GameController.getInstance().getActiveCharacter() == character) {
            g.setColor(Color.RED);
            g.drawRect(x, y, getWidth(), getHeight());
        }
    }

    /**
     * A nézet visszatér azzal, hogy melyik IceUnit modell objektum tartozik a nézethez tartozó modellbeli objektumhoz.
     * @return a nézethez tartozó modellbeli objektum ezen az IceUnit-on tartózkodik
     */
    @Override
    public IceUnit checkIceUnit() {
        return character.getIceUnit();
    }
}
