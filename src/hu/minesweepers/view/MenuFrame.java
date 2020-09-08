package hu.minesweepers.view;

import hu.minesweepers.controller.GameController;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A játék indulásakor ez jelenik meg először, ez egy önálló ablak, ami gombokat tartalmaz
 * és az egyik ilyen gombra kattintva lehet elindítani a játékot.
 */
public class MenuFrame extends JFrame implements ActionListener {

    /**
     * A felső menübár.
     */
    private JMenuBar menuBar = new JMenuBar();

    /**
     * A felső menü.
     */
    private JMenu menu = new JMenu("Menu");

    /**
     * A betöltést jelölő menüelem.
     */
    private JMenuItem loadMenu = new JMenuItem("Load Game");

    /**
     * A programból való kilépést jelölő menüelem.
     */
    private JMenuItem exitMenu = new JMenuItem("Exit");

    /**
     * Erre a gombra kattintva indul el a játék.
     */
    private JButton startButton;

    /**
     * Erre a gombra kattintva megjelenik egy felugró ablakban a játék útmutatója.
     */
    private JButton howToPlayButton;

    /**
     * A játék útmutatójának szövege.
     */
    private String howToPlayText = "";

    /**
     * Konstruktor, ami létrehozza az ablakot, és az ablakon megjelenő komponenseket (paneleket, gombokat),
     * továbbá megadja a komponensek eseménykezelőit és egyéb inicializációs lépéseket is végrehajt.
     */
    public MenuFrame() {
        //frame
        setMinimumSize(new Dimension(650, 680));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("IceField from the_minesweepers - Main Menu");

        //panels
        JPanel northPanel = new JPanel();
        JPanel northNorthPanel = new JPanel();
        JPanel southNorthPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel centerNorthPanel = new JPanel();
        JPanel centerCenterPanel = new JPanel();
        JPanel centerSouthPanel = new JPanel();
        JPanel southPanel = new JPanel();

        //menu
        menuBar.add(menu);
        menuBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        menu.add(loadMenu);
        menu.add(exitMenu);
        northNorthPanel.add(menuBar, BorderLayout.NORTH);
        exitMenu.addActionListener(this);
        loadMenu.addActionListener(this);

        //title image
        JLabel titleimage = new JLabel();
        titleimage.setIcon(new ImageIcon(GameController.getInstance().getResourcesLocation() + "titleimage.png"));
        southNorthPanel.add(titleimage);

        northPanel.setLayout(new BorderLayout());
        northPanel.add(northNorthPanel, BorderLayout.LINE_START);
        northPanel.add(southNorthPanel, BorderLayout.SOUTH);

        //buttons
        startButton = new JButton("Start Game");
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        startButton.setPreferredSize(new Dimension(140, 40));
        startButton.addActionListener(this);
        centerNorthPanel.add(startButton);
        howToPlayButton = new JButton("How To Play");
        howToPlayButton.setBackground(Color.LIGHT_GRAY);
        howToPlayButton.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
        howToPlayButton.setPreferredSize(new Dimension(140, 40));
        howToPlayButton.addActionListener(this);
        centerNorthPanel.add(howToPlayButton);

        try {
            howToPlayText = new String(Files.readAllBytes(Paths.get(GameController.getInstance().getResourcesLocation() + "howtoplay.txt")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //centerPanel
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
        centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);

        //mainImage
        JLabel mainImage = new JLabel();
        mainImage.setIcon(new ImageIcon(GameController.getInstance().getResourcesLocation() + "mainimage.png"));
        southPanel.add(mainImage);

        //add panels to the frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Ez a függvény kezeli le a különböző felhasználói eseményeket, úgy mint például egy gombra kattintás.
     * @param ae az objektum ami tartalmazza az esemény paramétereit
     */
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==startButton) {
            if (GameController.getInstance().isMapLoaded()) {
                GameController.getInstance().startGame();
            }
            else {
                JOptionPane.showMessageDialog(this, "Choose a map first!\n(Click on the 'Menu' button and select 'Load Game')",
                        "No map loaded", JOptionPane.WARNING_MESSAGE);
            }
        }

        else if(ae.getSource()==howToPlayButton) {
            JOptionPane.showMessageDialog(this, howToPlayText, "How To Play", JOptionPane.INFORMATION_MESSAGE);
        }

        else if(ae.getSource()==loadMenu) {
            JFileChooser fileChooser = new JFileChooser(GameController.getInstance().getResourcesLocation());
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".map", "map");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                GameController.getInstance().loadGame(fileChooser.getSelectedFile());
                startButton.setForeground(Color.decode("#A71828"));
            }
        }

        else if(ae.getSource() == exitMenu) {
            if(JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        }
    }
}

