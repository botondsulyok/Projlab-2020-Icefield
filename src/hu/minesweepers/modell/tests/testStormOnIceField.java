//package hu.minesweepers.modell.tests;
//
//import hu.minesweepers.modell.character.playable.Eskimo;
//import hu.minesweepers.controller.GameController;
//import hu.minesweepers.modell.items.digger.BareHand;
//import hu.minesweepers.modell.items.digger.Shovel;
//import hu.minesweepers.modell.tile.IceField;
//import hu.minesweepers.modell.tile.IceUnit;
//import hu.minesweepers.modell.tile.building.Igloo;
//
//import java.io.IOException;
//
//public class testStormOnIceField {
//
//    public void startTest() throws IOException {
//        System.out.println("Feltamad a vihar (+karakter megfagy)");
//
//        System.out.println("Inicializalas");
//
//        //Összes karakterre ugyanazt csinálja, most példának egy eszkimót veszünk
//        Eskimo esk = new Eskimo();
//        IceUnit iu = new IceUnit(esk);
//        esk.setIceUnit(iu);
//        IceField icefield = new IceField();
//        icefield.addStrikeables(esk);
//        icefield.addStrikeables(iu);
//        GameController.addDisastrousNotifiable(icefield);
//
//        System.out.println("Teszt kezdete");
//
//        System.out.print("Legyen iglu a jegtablan, amin a karakter all? (0: igen, 1: nem) ");
//        int n = Integer.parseInt(Logger.read());
//        if(n == 0)
//            iu.setBuilding(new Igloo(iu));
//        GameController.notifyDisastrous();
//        System.out.println("Teszt vege\n--------------------------\n");
//    }
//}
