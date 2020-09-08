package hu.minesweepers.view.Drawables;

import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.character.IceBear;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.view.TextureType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Egy a modellben szereplő IceBear objektum kirajzolása a feladata, annak adatai alapján.
 */
public class IceBearView extends PartView {

    /**
     * A nézethez tartozó modell, aminek az adatai alapján jelenik meg, rajzolódik ki a nézet.
     */
    private IceBear iceBear;

    /**
     * Konstruktor, ami a kapott TextureType enum alapján beolvassa, majd beállítja a nézet textúráját.
     * @param tt ez a textúra típusa
     * @param ic a nézethez tartozó modellbeli objektum
     */
    public IceBearView(TextureType tt, IceBear ic) {
        BufferedImage texture = null;
        if(tt == TextureType.ICEBEAR) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "icebear.jpg"));
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
        iceBear = ic;
    }

    /**
     * A nézet visszatér azzal, hogy melyik IceUnit modell objektum tartozik a nézethez tartozó modellbeli objektumhoz.
     * @return a nézethez tartozó modellbeli objektum ezen az IceUnit-on tartózkodik
     */
    @Override
    public IceUnit checkIceUnit() {
        return iceBear.getIceUnit();
    }

}
