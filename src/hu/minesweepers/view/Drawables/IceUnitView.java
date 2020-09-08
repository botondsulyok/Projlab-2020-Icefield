package hu.minesweepers.view.Drawables;

import hu.minesweepers.modell.command.ParsedObject;
import hu.minesweepers.modell.control.Parseable;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;
import java.awt.*;
import java.util.ArrayList;

/**
 * Egy a modellben is szereplő IceUnit kirajzolása a feladata, annak teljes tartalmával együtt.
 * Az IceUniton vizuálisan is megjelenhetnek más objektumok.
 */
public class IceUnitView implements IDrawable, Parseable {

    /**
     * A nézethez tartozó modell, aminek az adatai alapján jelenik meg a nézet.
     */
    private IceUnit iceUnit;

    /**
     * Az IceUniton megjelenő objektumok.
     */
    private ArrayList<PartView> parts = new ArrayList<PartView>();

    /**
     * Az IceUniton megjelenő PickableView objektum.
     */
    private PickableView pickableView;

    /**
     * Az IceUnitView objektum elhelyezkedésének x koordinátája.
     */
    private int x;

    /**
     * Az IceUnitView objektum elhelyezkedésének y koordinátája.
     */
    private int y;

    /**
     * Az IceUnitView szélessége.
     */
    private int width;

    /**
     * Az IceUnitView magassága.
     */
    private int height;

    public IceUnitView(int c1, int c2, int w, int h, IceUnit iu) {
        x = c1;
        y = c2;
        width = w;
        height = h;
        iceUnit = iu;
    }

    /**
     * A paraméterként kapott Graphics objektumra kirajzolja magát,
     * és a tartalmazott IDrawable interfészt megvalósító objektumoknak is meghívja a draw függvényeiket.
     * @param g erre az objektumra lehet rajzolni
     */
    @Override
    public void draw(Graphics g) {
        //color of the iceunit
        if(iceUnit.getBroken()) {
            g.setColor(Color.CYAN);
        }
        else if(iceUnit.getSnow()>0) {
            g.setColor(Color.WHITE);
        }
        else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        //parts, egymás mellé kirajzolva, ha betelik a sor, akkor új sort kezd
        int px = x;
        int py = y;
        int r = 0;
        int c = 0;

        for(PartView pv : parts) {
            px = x + c * pv.getWidth();
            py = y + r * pv.getHeight();
            pv.draw(g, px, py);
            c++;
            if(x+c*pv.getWidth()>=x+width) {
                c = 0;
                r++;
            }
        }

        //ha nincs hó a mezőn, akkor látható az IceUnit-on levő felvehető tárgy
        if(iceUnit.getPickable()!=null && iceUnit.getSnow()==0) {
            px = x + c * pickableView.getWidth();
            py = y + r * pickableView.getHeight();
            pickableView.draw(g, px, py);
            c++;
            if(x+c*pickableView.getWidth()>=x+width) {
                c = 0;
                r++;
            }
        }

        //az IceUnit-on levő Building kirajzolása
        Building b = iceUnit.getBuilding();
        BuildingView buildingView = new BuildingView(b);
        px = x + c * buildingView.getWidth();
        py = y + r * buildingView.getHeight();
        buildingView.draw(g, px, py);

        //kapacitás megjelenítése, ha kell
        if(iceUnit.isShow() == true) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString(iceUnit.getCapacity(), x+5, y+height-5);
        }

    }

    /**
     * Tárolja már előre a rajzolás helyének paramétereit.
     */
    @Override
    public void draw(Graphics g, int x, int y) {

    }

    /**
     * Part objektum hozzáadása a megfelelő tárolóhoz.
     * @param part objektum, amit a tárolóhoz kell adni
     */
    public void addPart(PartView part) {
        parts.add(part);
    }

    /**
     * A parts tároló tartalmának törlése.
     */
    public void clearParts() {
        parts.clear();
    }

    /**
     * Beállítja az IceUnit-on levő PickableView objektumot.
     * @param p a PickablView típusú objektum, ami az IceUnit-on van
     */
    public void setPickableView(PickableView p) {
        pickableView = p;
    }

    /**
     * Viszaadja az IceUnit x koordinátáját.
     * @return az x koordináta értéke
     */
    public int getX() {
        return x;
    }

    /**
     * Viszaadja az IceUnit y koordinátáját.
     * @return az y koordináta értéke
     */
    public int getY() {
        return y;
    }

    /**
     * Viszaadja az IceUnit szélességét.
     * @return a szélesség értéke
     */
    public int getWidth() {
        return width;
    }

    /**
     * Viszaadja az IceUnit magasságát.
     * @return a magasság értéke
     */
    public int getHeight() {
        return height;
    }

    /**
     * Visszaadja, azt a modellbeli objektumot, amihez a nézet tartozi.
     * @return modellbeli objektum
     */
    public IceUnit getIceUnit() {
        return iceUnit;
    }

    /**
     * A függvény az objektum adataiból létrehoz egy ParsedObject példányt, amivel dolgozva egyszerűen feldolgozhatjuk
     * az objektum adatait.
     * @return az objektum, ami tartalmazza a feldolgozandó adatokat kényelmes formában
     */
    @Override
    public ParsedObject convertToParsedObject() {
        ParsedObject obj = iceUnit.convertToParsedObject();
        obj.add("x", Integer.toString(x));
        obj.add("y", Integer.toString(y));
        obj.add("width", Integer.toString(width));
        obj.add("height", Integer.toString(height));
        return obj;
    }
}
