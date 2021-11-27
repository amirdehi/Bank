import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Customer implements Comparable {

    //Properties
    int accountID;
    String name, family, phoneNumber, address, password;
    float accountBalance;

    //Getters and Setters
    public int getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getPassword() {
        return password;
    }

    //Constructors
    public Customer(int accountID, String name, String family, String phoneNumber, String address,
                    String password, float accountBalance) {
        this.accountID = accountID;
        this.name = name;
        this.family = family;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountBalance = accountBalance;
        this.password = password;
    }

    //Methods
    void getCash(float amount) {
        if (amount <= this.getAccountBalance())
            Transaction.submitGetCash(this.getAccountID(), amount);
        else
            System.out.println("* Mojodi kam ast! *");
    }

    void transfer(float amount, int destinationAccountID) {
        if (amount <= this.getAccountBalance())
            Transaction.submitMoneyTransfer(this.getAccountID(), destinationAccountID, amount);
        else
            System.out.println("* Mojodi kam ast! *");
    }

    void getLast5Records() {
        Stream<Transaction> matchingObject = Bank.transactions.stream().
                filter(transaction -> transaction.sourceAccountID == getAccountID());

        List<Transaction> transactions = matchingObject.collect(Collectors.toList());

        if (transactions.size() >= 0 && transactions.size() < 5) {
            for (Transaction transaction : transactions) {
                if (transaction.getDestinationAccountID() == 0)
                    System.out.println("Bardasht -> " + transaction.getAmount() + '\n');
                else
                    System.out.println("Enteghal Vajh -> Hesab maghsad= " + transaction.getDestinationAccountID()
                            + " Meghdar= " + transaction.getAmount() + '\n');
            }
        } else if (transactions.size() > 5) {
            for (int i = transactions.size() - 1; i > transactions.size() - 6; i--) {
                if (transactions.get(i).getDestinationAccountID() == 0)
                    System.out.println("Bardasht -> " + transactions.get(i).getAmount() + '\n');
                else
                    System.out.println("Enteghal Vajh -> Hesab maghsad= " + transactions.get(i).getDestinationAccountID()
                            + " Meghdar= " + transactions.get(i).getAmount() + '\n');
            }
        }
    }

    @Override
    public String toString() {
        return "\ncustomer," + accountID + ',' + name + ',' + family
                + ',' + phoneNumber + ',' + address + ',' + password + ',' + accountBalance + '\n';
    }

    @Override
    public int compareTo(Object o) {
        Customer customer = (Customer) o;
        return this.getAccountID() - customer.getAccountID();
    }
}