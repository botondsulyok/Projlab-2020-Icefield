package hu.minesweepers.view;

import hu.minesweepers.controller.GameController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A játék ebben az ablakban jelenik meg.
 * A felelőssége, hogy az ablak a megfelelő tartalmat jelenítse meg
 * és ezen az ablakon a panelek és gombok megfelelően helyezkedjenek el.
 */
public class GameFrame extends JFrame implements ActionListener {

    /**
     * Az ablakhoz tartozó nézet, erre rajzolódik ki a pálya.
     */
    private GraphView activeView;

    /**
     * Az alsó panel, amiben a gombok fognak elhelyezkedni.
     */
    private JPanel bottomPanel = new JPanel();

    /**
     * A felső menübár.
     */
    private JMenuBar menuBar = new JMenuBar();

    /**
     * A felső menü.
     */
    private JMenu menu = new JMenu("Menu");

    /**
     * A mentést jelölő menüelem.
     */
    private JMenuItem saveMenu = new JMenuItem("Save");

    /**
     * A kilépést jelölő menüelem.
     */
    private JMenuItem exitMenu = new JMenuItem("Exit");

    /**
     * Az a gomb, amire való kattintás kiváltja az ásás műveletét.
     */
    private JButton digActionButton = new JButton("Dig");

    /**
     * Az a gomb, amire való kattintás kiváltja az összeszerelés műveletét.
     */
    private JButton assembleActionButton = new JButton("Assemble");

    /**
     * Az a gomb, amire való kattintás kiváltja a sátor építés műveletét.
     */
    private JButton buildTentButton = new JButton("Build Tent");

    /**
     * Az a gomb, amire való kattintás kiváltja az következő karakterre váltás műveletét.
     */
    private JButton nextCharacterActionButton = new JButton("Next Character");

    /**
     * Konstruktor, ami létrehozza az ablakot, és az ablakon megjelenő komponenseket (paneleket, gombokat),
     * továbbá megadja a komponensek eseménykezelőit és egyéb inicializációs lépéseket is végrehajt.
     */
    public GameFrame() {

        //frame
        this.setMinimumSize(new Dimension(1450, 850));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("IceField");

        //menu
        menuBar.add(menu);
        menu.add(saveMenu);
        menu.add(exitMenu);
        this.add(menuBar, BorderLayout.NORTH);
        exitMenu.addActionListener(this);
        saveMenu.addActionListener(this);

        //buttons event handling and add to a panel
        digActionButton.addActionListener(this);
        bottomPanel.add(digActionButton);
        assembleActionButton.addActionListener(this);
        bottomPanel.add(assembleActionButton);
        buildTentButton.addActionListener(this);
        bottomPanel.add(buildTentButton);
        nextCharacterActionButton.addActionListener(this);
        bottomPanel.add(nextCharacterActionButton);
        nextCharacterActionButton.setForeground(Color.MAGENTA);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }


    /**
     * Ez a függvény kezeli le a különböző felhasználói eseményeket, úgy mint például egy gombra kattintás.
     * @param ae az objektum ami tartalmazza az esemény paramétereit
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        boolean end = GameController.getInstance().isEnd();
        if(ae.getSource() == exitMenu) {
            if(JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }
        else if(ae.getSource() == saveMenu) {
            JFileChooser fileChooser = new JFileChooser(GameController.getInstance().getResourcesLocation());
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".map", "map");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                GameController.getInstance().saveGame(fileChooser.getSelectedFile());
            }
        }
        else if(ae.getSource() == digActionButton && !end) {
            GameController.getInstance().digAction();
        }
        else if(ae.getSource() == assembleActionButton && !end) {
            GameController.getInstance().assemble();
        }
        else if(ae.getSource() == buildTentButton && !end) {
            GameController.getInstance().buildTentAction();
        }
        else if(ae.getSource() == nextCharacterActionButton && !end) {
            GameController.getInstance().nextCharacterAction();
        }
    }

    /**
     * Beállítja az ablakhoz tartozó aktív nézetet.
     * @param graphView a nézet, ami az ablakhoz fog tartozni
     */
    public void setActiveView(GraphView graphView) {
        activeView = graphView;
        this.add(activeView);
        activeView.update();
    }
}

