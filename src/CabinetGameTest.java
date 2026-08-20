public class CabinetGameTest {
    public static void main(String[] args) {
        try {
            //Testing a valid game.
            CabinetGame game1 = new CabinetGame("C123456789", "Pinball", 900, true);
            System.out.println("Created: " + game1);
            System.out.println("Peak Price: " + game1.calculatePrice(true) + ", Non-Peak Price: " + game1.calculatePrice(false));

            // Testing valid game with no payout reward.
            CabinetGame game2 = new CabinetGame("C987654321", "Pac-Man", 700, false);
            System.out.println("Created: " + game2);
            System.out.println("Peak Price: " + game2.calculatePrice(true) + ", Non-Peak Price: " + game2.calculatePrice(false));

            //Testing an  invalid game id
            new CabinetGame("X111111111", "Not Working", 250, true);

        } catch (InvalidGameIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

