public class ArcadeTest {
    public static void main(String[] args) {
        try {
            Arcade myArcade = new Arcade("Games CO");

            Customer thelma = new Customer("A12345", "Alice", 1000, 18, "STUDENT");
            Customer vee = new Customer("B67890", "Bob", 2500, 19, "STAFF");
            Customer natalie = new Customer("N97898" , "Natalie", 500, 25, "STUDENT");

            myArcade.addCustomer(thelma);
            myArcade.addCustomer(vee);
            myArcade.addCustomer(natalie);

            CabinetGame pacMan = new CabinetGame("C123456789", "Pac-Man", 200, false);
            CabinetGame dollGraber = new CabinetGame("C234768907" , "Doll Graber" , 500 , true);
            ActiveGame airHockey = new ActiveGame("A987654321", "Air Hockey", 300, 10);
            VirtualRealityGame vrRacing = new VirtualRealityGame("AV54321098", "VR Racing", 500, 15, "headsetOnly");
            VirtualRealityGame soccer = new VirtualRealityGame("AV54321098", "Soccer", 500, 15, "headsetAndController");

            myArcade.addGame(pacMan);
            myArcade.addGame(dollGraber);
            myArcade.addGame(airHockey);
            myArcade.addGame(vrRacing);
            myArcade.addGame(soccer);

            System.out.println(myArcade);

            // Processing transactions
            myArcade.processTransaction("A12345", "C123456789", false);
            myArcade.processTransaction("B67890", "AV54321098", true);
            myArcade.processTransaction("N97898", "AV54321098", false);

            System.out.println("After transaction: " + myArcade);

            // Finding the richest customer
            Customer richest = myArcade.findRichestCustomer();
            if (richest != null) {
                System.out.println("Richest customer: " + myArcade.findRichestCustomer());
            }

            System.out.println("Median game price: " + myArcade.getMedianGamePrice() / 100.0 );

            // Counting the game types
            int[] gameCounts = myArcade.countArcadeGames();
            System.out.println("Cabinet Games: " + gameCounts[0] + ", Active Games: " + gameCounts[1] + ", VR Games: " + gameCounts[2]);

            Arcade.printCorporateJargon();

        } catch (Exception e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }
}

