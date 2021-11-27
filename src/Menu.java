import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    //Admin Menu
    static void adminMenu(Admin admin) {
        while (true) {
            System.out.println(admin.getName() + ' ' + admin.getFamily() + ", Modir:");
            System.out.println("1. Ezafe kardane karmand.\n" +
                    "2. Hazfe karmand.\n" +
                    "3. Exit.");

            String selection = scanner.next();
            if (selection.equals("1") || selection.equals("2") || selection.equals("3")) {
                switch (selection) {
                    case "1":
                        System.out.println("1. Ezafe kardane karmand.\n");
                        System.out.print("Employee ID: ");
                        int employeeID;
                        if (scanner.hasNextInt()) {
                            employeeID = scanner.nextInt();
                            System.out.print("Employee Name: ");
                            String name = scanner.next();
                            System.out.print("Employee Family: ");
                            String family = scanner.next();
                            System.out.print("Employee Password: ");
                            String password = scanner.next();
                            admin.addEmployee(employeeID, name, family, password);
                        } else {
                            scanner.next();
                            System.out.println("\n* ID is not Valid! *\n");
                        }
                        continue;
                    case "2":
                        System.out.println("2. Hazfe karmand.\n");
                        if (scanner.hasNextInt()) {
                            System.out.print("Employee ID: ");
                            employeeID = scanner.nextInt();
                            admin.removeEmployee(employeeID);
                        } else {
                            scanner.next();
                            System.out.println("\n* ID is not Valid! *\n");
                        }
                        continue;
                    case "3":
                        System.out.println("* Logging out of Admin Account... *\n");
                        return;
                }
            } else
                System.out.println("Invalid key!");
        }
    }

    //Employee Menu
    static void employeeMenu(Employee employee) {
        while (true) {
            System.out.println(employee.getName() + ' ' + employee.getFamily() + ", Karmand:");
            System.out.println("1. Ezafe kardane moshtari.\n" +
                    "2. Hazfe moshtari.\n" +
                    "3. Exit.");
            String selection = scanner.next();
            if (selection.equals("1") || selection.equals("2") || selection.equals("3")) {
                switch (selection) {
                    case "1":
                        System.out.println("1. Ezafe kardane moshtari.\n");
                        System.out.print("Account ID: ");
                        int accountID;
                        if (scanner.hasNextInt()) {
                            accountID = scanner.nextInt();
                            System.out.print("Customer Name: ");
                            String name = scanner.next();
                            System.out.print("Customer Family: ");
                            String family = scanner.next();
                            System.out.print("Customer Password: ");
                            String password = scanner.next();
                            System.out.print("Customer Phone number: ");
                            String phoneNumber = scanner.next();
                            System.out.print("Customer Address: ");
                            String address = scanner.next();
                            System.out.print("Account balance: ");
                            float accountBalance = scanner.nextFloat();
                            employee.addCustomer(accountID, name, family, password, phoneNumber, address, accountBalance);
                        } else {
                            scanner.next();
                            System.out.println("\n* ID is not Valid! *\n");
                        }
                        continue;
                    case "2":
                        System.out.println("2. Hazfe moshtari.\n");
                        System.out.print("Account ID: ");
                        if (scanner.hasNextInt()) {
                            accountID = scanner.nextInt();
                            employee.removeCustomer(accountID);
                        } else {
                            scanner.next();
                            System.out.println("\n* ID is not Valid! *\n");
                        }
                        continue;
                    case "3":
                        System.out.println("* Logging out of Employee Account... *\n");
                        return;
                }
            } else
                System.out.println("Invalid key!");
        }
    }

    //Customer Menu
    static void customerMenu(Customer customer) {
        while (true) {
            System.out.println(customer.getName() + ' ' + customer.getFamily() + ", Moshtari:");
            System.out.println("1. Mojoodi\n" +
                    "2. daryaft\n" +
                    "3. enteghale vajh\n" +
                    "4. Daryafte etelaate 5 gardeshe akhar\n" +
                    "5. Exit.");
            String selection = scanner.next();
            if (selection.equals("1") || selection.equals("2") || selection.equals("3")
                    || selection.equals("4") || selection.equals("5")) {
                switch (selection) {
                    case "1":
                        System.out.println("Mojodi hesab shoma: " + customer.getAccountBalance());
                        continue;
                    case "2":
                        System.out.print("Mablagh Mored Nazar: ");
                        if (scanner.hasNextFloat()) {
                            float cash = scanner.nextFloat();
                            customer.getCash(cash);
                        } else {
                            scanner.next();
                            System.out.println("\n* Price is not Valid! *\n");
                        }
                        continue;
                    case "3":
                        System.out.print("Hesab Maghsad: ");
                        if (scanner.hasNextInt()) {
                            int destinationAccount = scanner.nextInt();
                            if (Transaction.search(destinationAccount) >= 0) {
                                System.out.print("Mablagh Mored Nazar: ");
                                if (scanner.hasNextFloat()) {
                                    float amount = scanner.nextFloat();
                                    customer.transfer(amount, destinationAccount);
                                } else {
                                    scanner.next();
                                    System.out.println("\n* Price is not Valid! *\n");
                                }
                            } else
                                System.out.println("\nDestination account not found!\n");
                        } else {
                            scanner.next();
                            System.out.println("\n* ID is not Valid! *\n");
                        }
                        continue;
                    case "4":
                        customer.getLast5Records();
                        continue;
                    case "5":
                        System.out.println("* Logging out of Customer Account... *\n");
                        return;
                }
            } else
                System.out.println("Invalid key!");
        }
    }
}