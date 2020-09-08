package hu.minesweepers.view.Drawables;

import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;
import hu.minesweepers.view.TextureType;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BuildingView extends TexturedDrawable {

    /**
     * A nézethez tartozó modell, aminek az adatai alapján jelenik meg, rajzolódik ki a nézet.
     */
    private Building building;

    /**
     * Konstruktor, ami a kapott TextureType enum alapján beolvassa, majd beállítja a nézet textúráját.
     * @param tt ez a textúra típusa
     * @param b a nézethez tartozó modellbeli objektum
     */
    public BuildingView(TextureType tt, Building b) {
        BufferedImage texture = null;
        if(tt == TextureType.IGLOO) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "igloo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(tt == TextureType.TENT) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "tent.png"));
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
        building = b;
    }

    /**
     * Konstruktor, ami beolvassa, majd beállítja a nézet megfelelő textúráját.
     * @param b a nézethez tartozó modellbeli objektum
     */
    public BuildingView(Building b) {
        BufferedImage texture = null;
        if(b.getCommonID().equals("igloo")) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "igloo.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(b.getCommonID().equals("tent")) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "tent.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(b.getCommonID().equals("plains")) {
            try {
                texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "plains.png"));
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
        building = b;
    }

}
