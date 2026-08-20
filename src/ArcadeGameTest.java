public class ArcadeGameTest {
    public static void main(String[] args) {
        try {
            // Testing a CabinetGame without reward
            CabinetGame cabinetGame1 = new CabinetGame("C1234567Y9","Cartoon Snatch", 500, false);
            System.out.println(cabinetGame1);
            System.out.println("Peak Price: " + cabinetGame1.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + cabinetGame1.calculatePrice(false) + " pence\n");

            // Testing a CabinetGame with reward
            CabinetGame cabinetGame2 = new CabinetGame("C987654321", "Doll Catcher", 99, true);
            System.out.println(cabinetGame2);
            System.out.println("Peak Price: " + cabinetGame2.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + cabinetGame2.calculatePrice(false) + " pence\n");

            // Testing an ActiveGame
            ActiveGame activeGame1 = new ActiveGame("A345678901", "Air Hockey", 99 , 18);
            System.out.println(activeGame1);
            System.out.println("Peak Price: " + activeGame1.calculatePrice(true) + " pence");
            System.out.println("Off-Peak Price: " + activeGame1.calculatePrice(false) + " pence\n");

            // Testing a VirtualReality game
            VirtualRealityGame vrGame1 = new VirtualRealityGame("AV12345678" , "Hunting" , 600 , 16 , "Headset and Controller");
            System.out.println(vrGame1);
            System.out.println("Peak Price: " + vrGame1.calculatePrice(true) + " pence");
            System.out.println("Off-peak Price: " + vrGame1.calculatePrice(false) + " pence\n");

            // Testing an Invalid Game ID
            new CabinetGame("X123456789", "Fake Game", 200, true);

        } catch (InvalidGameIdException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }
}









