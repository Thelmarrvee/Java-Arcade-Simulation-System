import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Arcade {
    private String arcadeName;
    private double totalRevenue;
    private List<Customer> customers;
    private List<ArcadeGame> games;

    public Arcade(String arcadeName) {
        this.arcadeName = arcadeName;
        this.totalRevenue = 0.0;
        this.customers = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public String getArcadeName() {

        return arcadeName;
    }

    public double getTotalRevenue() {

        return totalRevenue;
    }

    public void addGame(ArcadeGame g) {
        // Checks if the game ID already exists in the list to prevent duplicates.
        for (ArcadeGame game : games) {
            if (game.getGameId().equals(g.getGameId())) {
                System.out.println("Game already exists: " + g.getGameId());
                return;
            }
        }
        //Adds the game if it does not exist
        games.add(g);
    }

    public void addCustomer(Customer c) {
        // Checks if the customer ID already exists in the list
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(c.getCustomerId())) {
                System.out.println("Customer already exists: " + c.getCustomerId());
                return; // Exit the method if the customer is found
            }
        }
        // Adds the customer if they do not exist
        customers.add(c);
    }

    public Customer getCustomer(String customerId) throws InvalidCustomerException {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        throw new InvalidCustomerException("Customer ID not found: " + customerId);
    }


    public ArcadeGame getArcadeGame(String gameId) throws InvalidGameIdException {
        for (ArcadeGame game : games) {
            if (game.getGameId().equals(gameId)) {
                return game;
            }
        }
        throw new InvalidGameIdException("Game ID not found: " + gameId);
    }


    public boolean processTransaction(String customerId, String gameId, boolean peak) throws Exception {
        // Retrieving the customer by ID
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            throw new Exception("Customer not found: " + customerId);
        }

        // Retrieving the game by ID
        ArcadeGame game = findGameById(gameId);
        if (game == null) {
            throw new Exception("Game not found: " + gameId);
        }

        // Checking for age restriction on certain games
        if (game instanceof ActiveGame) {
            ActiveGame activeGame = (ActiveGame) game;
            if (customer.getAge() < activeGame.getAgeLimit()) {
                throw new Exception("Customer does not meet the age requirement for this game.");
            }
        } else if (game instanceof VirtualRealityGame) {
            VirtualRealityGame vrGame = (VirtualRealityGame) game;
            if (customer.getAge() < vrGame.getAgeLimit()) {
                throw new Exception("Customer does not meet the age requirement for this game.");
            }
        }

        // Calculating the game price, considering peak/off-peak time.
        int price = game.calculatePrice(peak);

        double discount = 1.0;
        if (customer.getDiscountType().equals("STAFF")) {
            discount = 0.9;  // 10% discount for staff
        } else if (customer.getDiscountType().equals("STUDENT")) {
            discount = 0.95; // 5% discount for students
        }

        price = (int) (price * discount);

        // Checking if customer has enough balance for playing the game and throwing an exception for insufficient balance.
        if (customer.getAccountBalance() < price) {
            throw new Exception("Insufficient funds for customer: " + customerId);
        }

        customer.deductBalance(price);

        // Adds the price to the arcade's total revenue when a game is played.
        totalRevenue += price;


        System.out.println("Transaction successful!:\n " + "Customer: " + customerId + "\n" + " Played Game: " + game.getName() + "\n" + " Price: " + price + "p");
        return true;
    }

    // Helper methods to find customer and game by ID
    private Customer findCustomerById(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    private ArcadeGame findGameById(String gameID) {
        for (ArcadeGame game : games) {
            if (game.getGameId().equals(gameID)) {
                return game;
            }
        }
        return null;
    }
// Finding the richest customer by going through all the customer balance inorder.
    public Customer findRichestCustomer() {
        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return null;
        }
        Customer richestCustomer = null;
        int highestAccountBalance = Integer.MIN_VALUE;

        for (Customer customer : customers) {
            int currentBalance = customer.getAccountBalance();

            if (richestCustomer == null || currentBalance > highestAccountBalance) {
                richestCustomer = customer;
                highestAccountBalance = currentBalance;
            }
            //System.out.println("Checked customer: " + customer.getCustomerName() + ", Balance: " + currentBalance);
        }
        if (richestCustomer == null) {
            System.out.println("The richest customer is:" + richestCustomer.getCustomerName() + "with a balance of " + (highestAccountBalance));
        }

        return richestCustomer;
    }
//Calculates and returns the median price per play for all arcade games by sorting the game first.
    public double getMedianGamePrice() {
        if (games.isEmpty()) {
            System.out.println("No arcade games are available in the arcade.");
            return 0.0; // Return 0.0 if there are no games
        }

        List<Integer> gamePrices = new ArrayList<>();

        for (ArcadeGame game : games) {
            int price = game.getPricePerPlay();
            gamePrices.add(price);

        }

        Collections.sort(gamePrices);
        System.out.println("Sorted game prices: " + gamePrices);

        int size = gamePrices.size();
        int middle = size / 2;

        double median;
        if (size % 2 == 1) {
            median = gamePrices.get(middle);
        } else {
            int middlePrice1 = gamePrices.get(middle - 1);
            int middlePrice2 = gamePrices.get(middle);
            median = (middlePrice1 + middlePrice2) / 2.0;
        }


        return median;
    }

    public int[] countArcadeGames() {

        ArrayList<ArcadeGame> gameList = new ArrayList<>();

        for (ArcadeGame game : games) {
            gameList.add(game);
        }

        //Initialize counters for each type of game.
        int cabinetGamesCount = 0;
        int activeGamesCount = 0;
        int virtualRealityGamesCount = 0;

        for (int i = 0; i < gameList.size(); i++) {
            ArcadeGame currentGame = gameList.get(i); // Get game at index i
            String gameType = currentGame.getGameType();

            //Checking for the game type and updating the corresponding counter.
            if (gameType.equals("Cabinet")) {
                cabinetGamesCount++;
            } else if (gameType.equals("Active")) {
                activeGamesCount++;
            } else if (gameType.equals("virtualReality")) {
                virtualRealityGamesCount++;
            } else {
                System.out.println("Error: Unknown game type - " + gameType);
            }
        }

        return new int[]{cabinetGamesCount, activeGamesCount, virtualRealityGamesCount};
    }

    public static void printCorporateJargon() {
        System.out.println("GamesCo does not take responsibility for any accidents or fits of rage that occur on the premises.");
    }

    @Override
    public String toString() {
        return "Arcade Name: " + arcadeName + ", Revenue: " + totalRevenue + " pence, Customers: " + customers.size() + ", Games: " + games.size();
    }

}
