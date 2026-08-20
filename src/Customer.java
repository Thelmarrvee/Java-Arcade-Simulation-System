public class Customer {
    private String customerId;
    private String customerName;
    private int accountBalance;
    private int age;
    private String discountType;


    public Customer(String customerId, String customerName,  int accountBalance, int age , String discountType) throws InvalidCustomerException {
       if (!isValidAccountId(customerId)){
           throw new InvalidCustomerException("Invalid account ID:" + customerId + "(It must be a 6-character alphanumeric identifier)");
       }
        if (accountBalance < 0) {
            throw new InvalidCustomerException("Initial balance cannot be negative.");
        }

        this.customerId = customerId;
        this.customerName = customerName;
        this.accountBalance = accountBalance;
        this.age = age;
        this.discountType = discountType;

    }
// Helper method to validate a customer ID and making sure it is 6 alphanumeric characters.
    private boolean isValidAccountId(String accountID) {
        if (accountID == null || accountID.length() != 6) {
            return false;
        }
        for (char c : accountID.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public String getCustomerId() {

        return customerId;
    }
    public String getCustomerName() {

        return customerName;
    }
    public int getAge() {

        return age;
    }
    public String getDiscountType() {
        return discountType;
    }
    public int getAccountBalance() {
        return accountBalance;
    }
    // method for adding funds to a customer's account when the amount is positive.
     public boolean addFunds(String accountId, int amount) {
         if (amount > 0) {
             this.accountBalance += amount;
             System.out.println("Funds added successfully.\n " +
                                "New balance: " + this.accountBalance + "\n" +
                                "Account: " + accountId);
             return true;
         } else {
             System.out.println("Invalid amount. Funds must be positive.");
         }
         return false;
     }
     //Method for charging customer's account for playing a game and applying age restrictions , discounts and overdraft policy allowed for students.
   public int chargeAccount(ArcadeGame game , boolean peak) throws InsufficientBalanceException , AgeLimitException{
       if (age < game.getAgeLimit()) {
           throw new AgeLimitException("Customer is not old enough to play this game.");
       }

       int price = game.calculatePrice(peak);

           if (discountType.equals("STAFF")) {
               price = (int) (price * 0.90);
           } else if (discountType.equals("STUDENT")) {
               price = (int) (price * 0.95);
           }

       int allowedOverdraft = discountType.equals("STUDENT") ? -500 : 0;
       if (accountBalance - price < allowedOverdraft) {
           throw new InsufficientBalanceException("Insufficient funds to play the game.");
       }

       accountBalance -= price;
       return price;
   }
     @Override
public String toString() {
    return "Customer - " + "customerId:" + customerId + ", name: " + customerName + ", age:" + age + ", discountType:" + discountType + ", balance: " + accountBalance;
}

    public void deductBalance(int pricePerPlay) {
        return;
    }


    }

