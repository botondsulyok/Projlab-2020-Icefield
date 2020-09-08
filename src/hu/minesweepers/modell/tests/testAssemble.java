//package hu.minesweepers.modell.tests;
//
//import hu.minesweepers.modell.character.playable.Eskimo;
//import hu.minesweepers.controller.GameController;
//import hu.minesweepers.modell.items.components.CARTRIDGE;
//import hu.minesweepers.modell.items.components.Light;
//import hu.minesweepers.modell.items.components.Pistol;
//import hu.minesweepers.modell.tile.IceUnit;
//
//import java.io.IOException;
//
//public class testAssemble {
//
//    public void startTest() throws IOException {
//        System.out.println("Karakter osszeszerel");
//        //inilializálás
//        System.out.println("Inicializalas");
//        GameController gameController = new GameController();
//        Eskimo eskimo0 = new Eskimo();      //egy eszkimó fogja kezdeményezni az összeszerelést
//        IceUnit iceUnit0 = new IceUnit();
//        //iceUnit0.addComponentMaster(eskimo0);
//        eskimo0.setIceUnit(iceUnit0);
//        //test
//        System.out.println("Teszt kezdete");
//        System.out.print("Milyen alkatresszel rendelkezunk, ezzel kezdjuk meg az osszeszerelest? (0: pisztoly, 1: patron, 2: jelzofeny) ");
//        int n = Integer.parseInt(Logger.read());
//        if (n==0) {
//            Pistol pistol0 = new Pistol();
//            pistol0.assemble(eskimo0);
//        }
//        else if(n==1) {
//            CARTRIDGE cartridge0 = new CARTRIDGE();
//            cartridge0.assemble(eskimo0);
//        }
//        else if(n==2) {
//            Light light0 = new Light();
//            light0.assemble(eskimo0);
//        }
//        System.out.println("Teszt vege\n--------------------------\n");
//    }
//}
