package hu.minesweepers.view;

import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.items.components.Component;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.view.Drawables.IceUnitView;
import hu.minesweepers.view.Drawables.PartView;
import hu.minesweepers.view.Drawables.PickableView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A játék ezen a panelen rajzolódik ki, tehát az osztály feladata ennek a megvalósítása,
 * ez egy fajta nézet, ami a játékot gráf formában jeleníti meg.
 */
public class GraphView extends JPanel implements IView, MouseListener {

    /**
     * A view-hoz tartozó ablak.
     */
    private JFrame parentFrame;

    /**
     * Az IceUnitView és IceUnit objektumok összerendelései.
     */
    private HashMap<IceUnit, IceUnitView> iceUnitViews = new HashMap<IceUnit, IceUnitView>();

    /**
     * A PartView objektumok, amik majd ki fognak rajzolódni a nézeten.
     */
    private ArrayList<PartView> parts = new ArrayList<PartView>();

    /**
     * Jobb szélső panel, ebben jelenik meg az inventory, és az information panel.
     */
    private JPanel eastPanel = new JPanel();

    /**
     * Jobb oldalt levő panel felső része, ebben jelenik meg az invetory felirat.
     */
    private JPanel northEastPanel = new JPanel();

    /**
     * Jobb oldalt levő panel középső része, ebben jelennek meg az inventoryban levő tárgyak.
     */
    private JPanel centerEastPanel = new JPanel();

    /**
     * Jobb oldalt levő panel alsó része, ebben jelennek meg a játékhoz kapcsolódó információk.
     */
    private JPanel southEastPanel = new JPanel();

    /**
     * Az aktív karakter sorszámát jeleníti meg ez a címke.
     */
    private JLabel playerInfoLabel = new JLabel("Player: " + "-");

    /**
     * Az aktív karakter testhőjének értékét jeleníti meg ez a címke.
     */
    private JLabel bodyHeatLabel = new JLabel("Bodyheat: " + "-");

    /**
     * Az aktív játékos staminájának értékét jeleníti meg ez a címke.
     */
    private JLabel staminaLabel = new JLabel("Remaining stamina: " + "-");

    /**
     * A vihart jelző ikon.
     */
    private JLabel snowLabel = new JLabel();

    /**
     * Az inventoryban levő tágyak egy-egy címke ikonjaként jelennek meg.
     */
    private JLabel[] inventoryItems = new JLabel[10];

    /**
     * A pálya háttere.
     */
    private BufferedImage backgroundImage;

    /**
     * Konstruktor, ami létrehozza a nézetet, és a megjelenő komponenseket (paneleket, gombokat),
     * továbbá megadja a komponensek eseménykezelőit és egyéb inicializációs lépéseket is végrehajt.
     */
    public GraphView(JFrame frame) {

        //main panel setup
        parentFrame = frame;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(parentFrame.getWidth(),parentFrame.getHeight()));

        //event handling
        this.addMouseListener(this);

        eastPanel.setPreferredSize(new Dimension(150, frame.getHeight()));

        //inventory and information panel setup (images, texts)
        JLabel inventoryLabel = new JLabel(new ImageIcon(GameController.getInstance().getResourcesLocation() + "inventory.png"));
        northEastPanel.add(inventoryLabel);
        southEastPanel.setLayout(new BoxLayout(southEastPanel, BoxLayout.Y_AXIS));
        JLabel informationLabel = new JLabel();
        informationLabel.setIcon(new ImageIcon(GameController.getInstance().getResourcesLocation() + "info.png"));
        southEastPanel.add(informationLabel);
        southEastPanel.add(playerInfoLabel);
        southEastPanel.add(bodyHeatLabel);
        southEastPanel.add(staminaLabel);
        southEastPanel.add(snowLabel);

        //inventory and information panel setup (layout)
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(northEastPanel, BorderLayout.NORTH);
        eastPanel.add(centerEastPanel, BorderLayout.CENTER);
        eastPanel.add(southEastPanel, BorderLayout.SOUTH);
        this.add(eastPanel, BorderLayout.EAST);
        for(int i=0; i<inventoryItems.length; i++) {
            JLabel label = new JLabel();
            inventoryItems[i] = label;
            centerEastPanel.add(inventoryItems[i]);
        }

        //background
        try {
            backgroundImage = ImageIO.read(new File(GameController.getInstance().getResourcesLocation() + "backgroundwater.jpg"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ez a függvény kezeli le azt az eseményt, amikor a felhasználó kattint egyet a pályán, ekkor
     * a koordináták alapján meghatározásra kerül az, hogy melyik IceUnit-ra kattintott,
     * és ha ténylegesen ki lett jelölve egy IceUnit, akkor a kattintás
     * gombjának alapján meghívódik a megfelelő függvénye a GameControllernek.
     * @param e az objektum ami tartalmazza az egérkattintás esemény paramétereit
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(GameController.getInstance().isEnd()) {
            return;
        }
        int x = e.getX();
        int y = e.getY();
        IceUnitView selected = null;
        for (IceUnitView iuv : iceUnitViews.values()) {
            if(x>iuv.getX() && x<iuv.getX()+iuv.getWidth() && y>iuv.getY() && y<iuv.getY()+iuv.getHeight()) {
                selected = iuv;
                break;
            }
        }
        if(selected != null) {
            IceUnit iu = selected.getIceUnit();
            if(e.getButton() == MouseEvent.BUTTON1) {
                GameController.getInstance().moveAction(iu);
            }
            if(e.getButton() == MouseEvent.BUTTON3) {
                GameController.getInstance().abilityUseAction(iu);
            }
        }
    }

    /**
     * Egérlenyomáskor nem történik semmi.
     */
    @Override
    public void mousePressed(MouseEvent e) { }

    /**
     * Az egérgomb felengedésekor nem történik semmi.
     */
    @Override
    public void mouseReleased(MouseEvent e) { }

    /**
     * Ekkor sem történik semmi.
     */
    @Override
    public void mouseEntered(MouseEvent e) { }

    /**
     * Ekkor sem történik semmi.
     */
    @Override
    public void mouseExited(MouseEvent e) { }

    /**
     * A nézetet frissíteni kell a modell adatai alapján.
     * A játékmodellben valamilyen változás történt, ezért a GraphView nézetnek frissítenie,
     * azaz újra rajzolnia kell magát az változott adatoknak megfelelően.
     * Meghívja az összes IceUnitView clearParts metódusát.
     * Majd meghívja a tartalmazott PartView objektumoknak a checkIceUnit metódusát,
     * majd elvégzi a megfelelő IceUnitView-hoz való rendelését
     * Majd egy repaint függvényhívás történik, ezzel jelez magának, hogy rajzolja újra magát.
     */
    public void update() {
        for (IceUnitView iuv : iceUnitViews.values()) {
            iuv.clearParts();
        }
        for(PartView pv : parts) {
            IceUnit iceUnit = pv.checkIceUnit();
            if(iceUnit != null) {
                iceUnitViews.get(iceUnit).addPart(pv);
            }
        }
        repaint();
    }

    /**
     * Ez a metódus végzi a rajzolást, a paraméterként kapott Graphics objektumra.
     * Kirajzolja a hátteret, frissíti az inventory és info panel megjelenését.
     * Összeköti az IceUnitokat.
     * Végül meghívja a kirajzolható IceUnitView objektumoknak a draw függvényét a Graphics g objektumával paraméterezve.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);

        Image scaledBackgroundImage = backgroundImage.getScaledInstance(parentFrame.getWidth()-150, parentFrame.getHeight()-50, Image.SCALE_DEFAULT);
        g.drawImage(scaledBackgroundImage, 0, 0, null);

        //oldalsó panelek frissítése
        updateInventory();
        updateInfo();

        //IceUnit-ok összekötése
        Graphics2D g2 = (Graphics2D) g;
        Stroke s = g2.getStroke();
        g2.setColor(Color.MAGENTA);
        g2.setStroke(new BasicStroke(5));
        for(IceUnit iu : iceUnitViews.keySet()) {
            IceUnitView iuv = iceUnitViews.get(iu);
            ArrayList<IceUnit> neighbours = iu.getNeighbours();
            for(IceUnit n : neighbours) {
                IceUnitView niuv = iceUnitViews.get(n);
                g2.drawLine((iuv.getX()+iuv.getX()+iuv.getWidth())/2, (iuv.getY()+iuv.getY()+iuv.getHeight())/2, (niuv.getX()+niuv.getX()+niuv.getWidth())/2, (niuv.getY()+niuv.getY()+niuv.getHeight())/2);
            }
        }
        g2.setStroke(new BasicStroke(2));

        //IceUnitok kirajzolása
        for (IceUnitView iuv : iceUnitViews.values()) {
            iuv.draw(g);
        }

        if(GameController.getInstance().isEnd()) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.PLAIN, 200));
            g.drawString("GAME OVER", 0, 200);
        }

    }

    /**
     * Az inventory frissítése az aktív karakter adatai alapján.
     */
    private void updateInventory() {
        for(int i=0; i<inventoryItems.length; i++) {
            inventoryItems[i].setIcon(new ImageIcon());
        }
        int i = 0;
        Character activeCharacter = GameController.getInstance().getActiveCharacter();
        if(activeCharacter.getTent() != null) {
            inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.PICKABLETENT).getTexture()));
            i++;
        }
        if(activeCharacter.getDigger().getCommonID().equals("shovel")) {
            inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.SHOVEL).getTexture()));
            i++;
        }
        if(activeCharacter.getDigger().getCommonID().equals("breakable")) {
            inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.BREAKABLE).getTexture()));
            i++;
        }
        if(activeCharacter.getPullerDevice().getCommonID().equals("rope")) {
            inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.ROPE).getTexture()));
            i++;
        }
        if(activeCharacter.getWaterProtecter().getCommonID().equals("divingsuit")) {
            inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.DIVINGSUIT).getTexture()));
            i++;
        }
        if(activeCharacter.getComponents() != null) {
            for (Component c : activeCharacter.getComponents()) {
                if(c.getCommonID().equals("pistol")) {
                    inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.PISTOL).getTexture()));
                    i++;
                }
                else if(c.getCommonID().equals("cartridge")) {
                    inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.CARTRIDGE).getTexture()));
                    i++;
                }
                else if(c.getCommonID().equals("light")) {
                    inventoryItems[i].setIcon(new ImageIcon(new PickableView(TextureType.LIGHT).getTexture()));
                    i++;
                }
            }
        }
    }

    /**
     * Az információs panel frissítése az aktív karakter adatai alapján.
     */
    private void updateInfo() {
        Character activeCharacter = GameController.getInstance().getActiveCharacter();
        playerInfoLabel.setText("Player: " + (GameController.getInstance().getActiveCharacterNumber()+1));
        bodyHeatLabel.setText(("Bodyheat: " + activeCharacter.getBodyHeat()));
        staminaLabel.setText(("Remaining stamina: " + activeCharacter.getStamina()));
        if(GameController.getInstance().isStormComing()) {
            snowLabel.setIcon(new ImageIcon(GameController.getInstance().getResourcesLocation() + "snow.png"));
        }
        else {
            snowLabel.setIcon(new ImageIcon());
        }
    }

    /**
     * IceUnitView hozzáadása tárolójához.
     * @param iu IceUnit modellbeli objektum
     * @param iuv IceUnitView objektum, ami az IceUnit-hoz tartozik
     */
    public void addIceUnitView(IceUnit iu, IceUnitView iuv) {
        iceUnitViews.put(iu, iuv);
    }

    /**
     * PartView hozzáadása a tárolójához.
     * @param partView a PartView tíousú objektum ami hozzáadásra kerül a tárolóhoz
     */
    public void addPartView(PartView partView) {
        parts.add(partView);
    }

    /**
     * Visszaadja az IceUnit-IceUnitView összerendeléseket.
     * @return IceUnit-IceUnitView összerendelések HashMap-je
     */
    public HashMap<IceUnit, IceUnitView> getIceUnitViews() {
        return iceUnitViews;
    }

}
