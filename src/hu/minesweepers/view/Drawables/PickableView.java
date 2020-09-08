package hu.minesweepers.view.Drawables;

import hu.minesweepers.controller.GameController;
import hu.minesweepers.view.TextureType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Olyan elemeket reprezentál ez az osztály, amik kirajzoláskor egy IceUnitView-n jelennek meg.
 */
public class PickableView extends TexturedDrawable {

    /**
     * Konstruktor, ami a kapott TextureType enum alapján beolvassa, majd beállítja a nézet textúráját.
     * @param tt ez a textúra típusa
     */
    public PickableView(TextureType tt) {
        BufferedImage texture = null;
        switch (tt)
        {
            case CARTRIDGE:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "cartridge.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case DIVINGSUIT:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "divingsuit.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case FOOD:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "food.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case LIGHT:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "light.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PISTOL:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "pistol.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ROPE:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "rope.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case BREAKABLE:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "breakable.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case SHOVEL:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "shovel.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PICKABLETENT:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "pickabletent.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    texture = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "defaulttexture.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        setTexture(texture);
    }

}
