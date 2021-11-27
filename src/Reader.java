import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class Reader {
    static void readEmployees() {
        try (BufferedReader employeeIn = new BufferedReader(new FileReader("Employees"))) {
            //Read employees
            String line = employeeIn.readLine();
            while (line != null) {
                String[] s = line.split(",");
                if (s[0].equals("employee")) {
                    Employee employee = new Employee(Integer.parseInt(s[1]), s[2], s[3], s[4]);
                    Bank.employees.add(employee);
                }
                line = employeeIn.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error in reading employees!");
            e.printStackTrace();
        }
        //Sorting Arrays
        Collections.sort(Bank.employees);
    }

    static void readCustomers() {
        try (BufferedReader customerIn = new BufferedReader(new FileReader("Customers"))) {
            //Read Customers
            String line = customerIn.readLine();
            while (line != null) {
                String[] s = line.split(",");
                if (s[0].equals("customer")) {
                    Customer customer = new Customer(Integer.parseInt(s[1]), s[2], s[3], s[4], s[5], s[6], Float.parseFloat(s[7]));
                    Bank.customers.add(customer);
                }
                line = customerIn.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error in reading customers!");
            e.printStackTrace();
        }
        //Sorting Arrays
        Collections.sort(Bank.customers);
    }

    static void readTransactions() {
        Bank.transactions.clear();
        try (BufferedReader transactionIn = new BufferedReader(new FileReader("Transactions"))) {
            //Read Transactions
            String line = transactionIn.readLine();

            while (line != null) {
                String[] s = line.split(",");
                if (s[0].equals("transaction")) {
                    Transaction transaction = null;
                    if (s.length == 4)
                        transaction = new Transaction(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Float.parseFloat(s[3]));
                    else if (s.length == 3)
                        transaction = new Transaction(Integer.parseInt(s[1]), Float.parseFloat(s[2]));
                    Bank.transactions.add(transaction);
                }
                line = transactionIn.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error in reading transactions!");
            e.printStackTrace();
        }
        //Sorting Arrays
        Collections.sort(Bank.transactions);
    }
}