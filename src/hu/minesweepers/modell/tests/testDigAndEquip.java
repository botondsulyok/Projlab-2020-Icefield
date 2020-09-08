//package hu.minesweepers.modell.tests;
//
//import hu.minesweepers.modell.character.playable.Eskimo;
//import hu.minesweepers.controller.GameController;
//import hu.minesweepers.modell.items.digger.BareHand;
//import hu.minesweepers.modell.items.digger.Digger;
//import hu.minesweepers.modell.items.digger.Shovel;
//import hu.minesweepers.modell.tile.IceUnit;
//import hu.minesweepers.modell.tile.building.Plains;
//
//import java.io.IOException;
//
//
//    public class testDigAndEquip {
//
//        public void startTest() throws IOException {
//            System.out.println("Karakter as (+felvesz dolgot)");
//            //inilializálás
//            System.out.println("Inicializalas");
//            Eskimo eskimo0 = new Eskimo();
//            IceUnit iu0 = new IceUnit();
//            Digger digger = null;
//            //test
//            System.out.print("Mivel asson? (0: kez, 1: aso) ");
//            int n = Integer.parseInt(Logger.read());
//            if(n==0) {
//                digger = new BareHand();
//            }
//            else if(n==1) {
//                digger = new Shovel();
//            }
//            eskimo0.equipDigger(digger);
//            System.out.println("Teszt kezdete");
//            digger.dig(eskimo0, iu0);
//            System.out.println("Teszt vege\n--------------------------\n");
//        }
//    }
