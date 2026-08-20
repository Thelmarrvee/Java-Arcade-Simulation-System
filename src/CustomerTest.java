public class CustomerTest {
    public static void main(String[] args) {
        try {
            Customer customer1 = new Customer("A12345", "Alice", 0 , 21 , "STAFF");
            System.out.println(customer1);

            customer1.addFunds("A12345" , 300);
            System.out.println("After adding funds: " + customer1);

            Customer customer2 = new Customer("B67890", "Bob", 2500, 15, "STUDENT");
            System.out.println(customer2);

            CabinetGame pacMan = new CabinetGame("C123456789", "Pac-Man", 200, false);
            ActiveGame airHockey = new ActiveGame("A987654321", "Air Hockey", 300, 10);

            // Attempting to charge customer1 for playing a game
            int chargedAmount = customer1.chargeAccount(pacMan, false);
            System.out.println("Charged: " + chargedAmount + " pence");
            System.out.println("After playing Pac-Man: " + customer1);

            // Attempting to play a game without enough balance
            customer1.chargeAccount(airHockey, true);

        } catch (InvalidCustomerException | InsufficientBalanceException | AgeLimitException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        } catch (InvalidGameIdException e) {
            throw new RuntimeException(e);
        }
    }
}

