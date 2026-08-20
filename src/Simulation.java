/***
 File        : Simulation Java

 Date         : 09 May 2025

 Author       :pjd24fpu@uea.ac.uk

 Description  : This Java system models arcade operations with various game types, customer accounts, and dynamic pricing.
                  It handles transactions, discounts, and age checks, while tracking revenue and game stats.
                  Data is loaded from files and simulated via a transaction log.

 ***/


import java.io.*;

public class Simulation {
    public static void main(String[] args) {
        File gamesFile = new File("games.txt");
        File customerFile = new File("customers.txt");
        File transactionFile = new File("transactions.txt");
        Arcade arcade = initialiseArcade("Games Co", gamesFile, customerFile);
        simulateFun(arcade, transactionFile);
    }


    public static Arcade initialiseArcade(String arcadeName, File gamesFile, File customerFile) {
        Arcade arcade = new Arcade(arcadeName);

        // Loading and adding customers
        try (BufferedReader customerReader = new BufferedReader(new FileReader(customerFile))) {
            String line;
            while ((line = customerReader.readLine()) != null) {
                try {
                    String[] parts = line.split("#");
                    String id = parts[0];
                    String name = parts[1];
                    int balance = Integer.parseInt(parts[2]);
                    int age = Integer.parseInt(parts[3]);
                    String discountType = parts.length > 4 ? parts[4] : "NONE"; // Handling cases without a discount

                    Customer customer = new Customer(id, name, balance, age, discountType);
                    arcade.addCustomer(customer);
                    System.out.println("Customer added: " + name);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error adding customer from line: " + line);
                } catch (InvalidCustomerException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customer file.");
        }

        // Loading and adding all the games.
        try (BufferedReader gameReader = new BufferedReader(new FileReader(gamesFile))) {
            String line;
            while ((line = gameReader.readLine()) != null) {
                try {
                    String[] parts = line.split("@");
                    String gameId = parts[0];
                    String name = parts[1];
                    String gameType = parts[2];
                    int pricePerPlay = Integer.parseInt(parts[3]);

                    //Using a switch case to determine the game type and creating an appropriate game object.
                    switch (gameType) {
                        case "cabinet":
                            boolean canPayOutReward = parts[4].equalsIgnoreCase("yes");
                            ArcadeGame cabinetGame = new CabinetGame(gameId, name, pricePerPlay, canPayOutReward);
                            arcade.addGame(cabinetGame);

                            System.out.println("Successfully added Cabinet game: " + name);
                            break;
                        case "active":
                            int ageLimit = Integer.parseInt(parts[4]);
                            ArcadeGame activeGame = new ActiveGame(gameId, name, pricePerPlay, ageLimit);
                            arcade.addGame(activeGame);
                            System.out.println("Successfully added Active game: " + name);
                            break;

                        case "virtualReality":
                            String equipmentUsed = parts[5];
                            ageLimit = Integer.parseInt(parts[4]);
                            ArcadeGame vrGame = new VirtualRealityGame(gameId, name, pricePerPlay, ageLimit ,equipmentUsed);
                            arcade.addGame(vrGame);
                            System.out.println("Successfully added Virtual Reality game: " + name);
                            break;

                        default:
                            System.out.println("Unknown game type: " + gameType);
                            continue;
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error adding game from line: " + line);
                } catch (InvalidGameIdException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading game file."); // throwing an exception from error reading the file
        }
        return arcade;
    }
// Processing all the transactions from the files using arcade
    public static void simulateFun(Arcade arcade, File transactionFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(transactionFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String action = parts[0];

                try {
                    switch (parts[0]) {
                        case "PLAY":
                            if (parts.length != 4){
                                System.out.println("Skipping invalid PLAY transaction: " + String.join(",", parts));
                                break;
                            }

                            try {
                                Customer customer = arcade.getCustomer(parts[1]);
                                ArcadeGame game = arcade.getArcadeGame(parts[2]);
                                boolean peak = Boolean.parseBoolean(parts[3]);

                                if (arcade.processTransaction(parts[1], parts[2], peak)) {

                                }
                            } catch (InvalidCustomerException e) {
                                System.out.println("Error: Customer ID not found: " + parts[1]);
                            } catch (InvalidGameIdException e) {
                                System.out.println("Error: Game ID not found: " + parts[2]);
                            } catch (Exception e) {
                                System.out.println("Transaction denied: " + e.getMessage());
                            }
                            break;

                        case "ADD_FUNDS":
                            try {
                                String customerId = parts[1];
                                int amount = Integer.parseInt(parts[2]);

                                Customer customer = arcade.getCustomer(customerId);
                                if (customer != null) {
                                    if (customer.addFunds(customerId, amount)) {
                                        System.out.println("Successfully added " + amount + " to account: " + customerId);
                                    } else {
                                        System.out.println("Failed to add funds. Invalid amount.");
                                    }
                                } else {
                                    System.out.println("Customer not found: " + customerId);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid amount format for ADD_FUNDS.");
                            } catch (InvalidCustomerException e ) {
                                System.out.println( e.getMessage());
                            }
                            break;


                        case "NEW_CUSTOMER":
                            if (parts.length != 5 && parts.length != 6) {
                              System.out.println("Error processing line: " + line + " - Incorrect format");
                                break;
                            }
                            String newAccountId = parts[1];
                            String newCustomerName = parts[2];
                            String newDiscountType;
                            int newAccountBalance;
                            int newAge;

                            try {
                                // Checking for optional discount
                                    if (parts.length == 6) {
                                        newDiscountType = parts[3];
                                        newAccountBalance = Integer.parseInt(parts[4]);
                                        newAge = Integer.parseInt(parts[5]);
                                    } else {
                                        newDiscountType = "NONE";
                                        newAccountBalance = Integer.parseInt(parts[3]);
                                        newAge = Integer.parseInt(parts[4]);
                                    }
                                Customer newCustomer = new Customer(newAccountId, newCustomerName, newAccountBalance, newAge, newDiscountType);
                                arcade.addCustomer(newCustomer);
                                System.out.println("Success: Added new customer " + newCustomerName);
                            } catch (Exception e) {
                                System.out.println("Error adding new customer: " + e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Unknown transaction type: " + parts[0]);
                    }
                     } catch (Exception e) {
                    System.out.println("Error processing line: " + line );
                    }
            }


                    // The simulation results summary
                    System.out.println("\n=== Arcade Summary ===");
                    System.out.println("Richest Customer: " + arcade.findRichestCustomer());
                    System.out.println("Median Game Price: " + arcade.getMedianGamePrice() /100.0);
                    int[] gameCounts = arcade.countArcadeGames();
                    System.out.println("Cabinet Games: " + gameCounts[0] + ", Active Games: " + gameCounts[1] + ", VR Games: " + gameCounts[2]);
                    System.out.println("Total Revenue: £" + arcade.getTotalRevenue() / 100.0);

                    Arcade.printCorporateJargon();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e ) {
                    throw new RuntimeException(e);
                }
            }
        }


