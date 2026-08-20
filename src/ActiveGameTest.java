public class ActiveGameTest {
    public static void main(String[] args) {
        try {
            // Creating a valid ActiveGame
            ActiveGame game1 = new ActiveGame("AV23456789", "Ant Play", 300, 12);
            System.out.println("Created: " + game1);
            System.out.println("Peak Price: " + game1.calculatePrice(true) + ", Non-Peak Price: " + game1.calculatePrice(false));

            // Attempting to create an invalid ActiveGame
            try {
                ActiveGame game2 = new ActiveGame("WV87654321", "Areadmill Run", 250, 10);
            } catch (InvalidGameIdException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (InvalidGameIdException e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}