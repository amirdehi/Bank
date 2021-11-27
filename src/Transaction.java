import java.io.FileWriter;
import java.io.IOException;

public class Transaction implements Comparable {
    //Properties
    int sourceAccountID, destinationAccountID;
    float amount;

    //Getters and Setters
    public int getSourceAccountID() {
        return sourceAccountID;
    }

    public int getDestinationAccountID() {
        return destinationAccountID;
    }

    public float getAmount() {
        return amount;
    }

    //Constructors
    public Transaction(int sourceAccountID, int destinationAccountID, float amount) {
        this.sourceAccountID = sourceAccountID;
        this.destinationAccountID = destinationAccountID;
        this.amount = amount;
        Customer sourceCustomer, destinationCustomer;
        if ((sourceCustomer = getCustomer(sourceAccountID)) != null
                && (destinationCustomer = getCustomer(destinationAccountID)) != null) {
            Bank.customers.remove(sourceCustomer);
            Bank.customers.remove(destinationCustomer);
            sourceCustomer.setAccountBalance(sourceCustomer.getAccountBalance() - amount);
            destinationCustomer.setAccountBalance(destinationCustomer.getAccountBalance() + amount);
            Bank.customers.add(sourceCustomer);
            Bank.customers.add(destinationCustomer);
        }
    }

    public Transaction(int sourceAccountID, float amount) {
        this.sourceAccountID = sourceAccountID;
        this.amount = amount;
        Customer customer;
        if ((customer=getCustomer(sourceAccountID))!=null) {
            Bank.customers.remove(customer);
            customer.setAccountBalance(customer.getAccountBalance() - amount);
            Bank.customers.add(customer);
        }
    }

    //Methods
    static void submitGetCash(int sourceAccountID, float amount) {
        Bank.transactions.add(new Transaction(sourceAccountID, amount));
        Writer.submitTransaction();
        System.out.println("\n* Your request for get cash successfully submitted! *\n");
    }

    static void submitMoneyTransfer(int sourceAccountID, int destinationAccountID, float amount) {
        if (sourceAccountID != destinationAccountID) {
            Bank.transactions.add(new Transaction(sourceAccountID, destinationAccountID, amount));
            Writer.submitTransaction();
            System.out.println("\n* Your request for money transfer successfully submitted! *\n");
        } else
            System.out.println("\n* You cannot transfer to your account! *\n");
    }

    Customer getCustomer(int accountID) {
        int index = search(accountID);
        if (index >= 0)
            return Bank.customers.get(index);
        else
            return null;
    }

    //Do transactions
    static void checkTransactions() {
        Reader.readTransactions();
        if (!Bank.transactions.isEmpty()) {
            for (Transaction transaction : Bank.transactions) {
                int sourceAccountKey = search(transaction.getSourceAccountID());
                if (sourceAccountKey >= 0) {
                    Customer sourceCustomer = Bank.customers.get(sourceAccountKey);
                    //transfer
                    if (transaction.getDestinationAccountID() != 0) {
                        //source
                        Bank.customers.remove(sourceCustomer);
                        sourceCustomer.setAccountBalance(sourceCustomer.getAccountID() - transaction.amount);
                        Bank.customers.add(sourceCustomer);
                        //destination account
                        int destinationAccountKey = search(transaction.getDestinationAccountID());
                        Customer destinationCustomer = Bank.customers.get(destinationAccountKey);
                        Bank.customers.remove(destinationCustomer);
                        destinationCustomer.setAccountBalance(destinationCustomer.getAccountID() + transaction.getAmount());
                        Bank.customers.add(destinationCustomer);

                        System.out.println("Money transfer (from: " + sourceCustomer.getAccountID()
                                + ", to: " + destinationCustomer.getAccountID() + ", price: " + transaction.getAmount()
                                + ") was Successfully!");
                    }
                    //cash
                    else {
                        Bank.customers.remove(sourceCustomer);
                        sourceCustomer.setAccountBalance(sourceCustomer.getAccountID() - transaction.getAmount());
                        Bank.customers.add(sourceCustomer);
                        System.out.println("Get cash (from account: " + sourceCustomer.getAccountID()
                                + ", cash: " + transaction.getAmount() + ") was Successfully!");
                    }
                }
            }
        }
        try (FileWriter transactionWriter = new FileWriter("Transactions")) {
            transactionWriter.write("");
        } catch (IOException e) {
            System.out.println("Error in saving transactions!");
            e.printStackTrace();
        }
    }

    //Binary search
    static int search(int element) {
        int lowIndex = 0;
        int highIndex = Bank.customers.size() - 1;
        int elementPos = -1;

        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            if (element == Bank.customers.get(midIndex).getAccountID()) {
                elementPos = midIndex;
                break;
            } else if (element < Bank.customers.get(midIndex).getAccountID()) {
                highIndex = midIndex - 1;
            } else if (element > Bank.customers.get(midIndex).getAccountID()) {
                lowIndex = midIndex + 1;
            }
        }
        return elementPos;
    }

    @Override
    public String toString() {
        if (destinationAccountID == 0)
            return "\ntransaction," + sourceAccountID + ',' + amount + '\n';
        else
            return "\ntransaction," + sourceAccountID + ',' + destinationAccountID + ',' + amount + '\n';
    }

    @Override
    public int compareTo(Object o) {
        Transaction transaction = (Transaction) o;
        return this.getSourceAccountID() - transaction.getSourceAccountID();
    }
}