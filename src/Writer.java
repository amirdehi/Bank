import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    static void writeOnFile() {
        try (FileWriter employeeWriter = new FileWriter("Employees");
             FileWriter customerWriter = new FileWriter("Customers")) {
            Transaction.checkTransactions();
            employeeWriter.write(Bank.employees.toString());
            customerWriter.write(Bank.customers.toString());
        } catch (IOException e) {
            System.out.println("Error in final saving!");
            e.printStackTrace();
        }
    }

    static void submitTransaction() {
        try (FileWriter transactionWriter = new FileWriter("Transactions")) {
            transactionWriter.write(Bank.transactions.toString());
        } catch (IOException e) {
            System.out.println("Error in saving transactions!");
            e.printStackTrace();
        }
    }
}