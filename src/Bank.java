import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {

    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        //Admin Info
        Admin admin = new Admin("admin", "123", "Mohammad", "Hamzei");

        //Read From File
        readFromFile();

        //Menu
        //Variables
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Username (Q for exit): ");
            String username = scanner.next();

            if (username.toLowerCase().equals("q"))
                break;

            System.out.print("Password: ");
            String password = scanner.next();

            if (!username.isEmpty() && !password.isEmpty()) {

                if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
                    Menu.adminMenu(admin);
                    continue;
                }

                Optional<Employee> employee = Bank.employees.stream()
                        .filter(employee1 -> employee1.getEmployeeID() == Integer.parseInt(username))
                        .filter(employee1 -> employee1.getPassword().equals(password)).findFirst();
                if (employee.isPresent()) {
                    Menu.employeeMenu(employee.get());
                    continue;
                }

                Optional<Customer> customer = Bank.customers.stream()
                        .filter(customer1 -> customer1.getAccountID() == Integer.parseInt(username))
                        .filter(customer1 -> customer1.getPassword().equals(password)).findFirst();
                if (customer.isPresent()) {
                    Menu.customerMenu(customer.get());
                    continue;
                }
            }
            System.out.println("Try again!\n");
        }

        //Write On File
        Writer.writeOnFile();

        System.out.println("Shut Down...");
    }

    //Read Data From File
    static void readFromFile() {
        Reader.readEmployees();
        Reader.readCustomers();
        Transaction.checkTransactions();
    }
}