public class Employee implements Comparable {

    //Properties
    int employeeID;
    String name, family, password;

    //Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getPassword() {
        return password;
    }

    //Constructors
    public Employee(int employeeID, String name, String family, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.family = family;
        this.password = password;
    }

    //Methods
    void addCustomer(int accountID, String name, String family, String password, String phoneNumber,
                     String address, float accountBalance) {
        if (Bank.customers.stream().noneMatch(customer -> customer.getAccountID() == accountID)) {
            if (Bank.customers.add(new Customer(accountID, name, family, password, phoneNumber, address, accountBalance)))
                System.out.println("* Customer Added Successfully! *\n");
            else
                System.out.println("* Cannot add Customer! *\n");
        } else
            System.out.println("* Account ID is not available *\n");
    }

    void removeCustomer(int accountID) {
        if (Bank.customers.removeIf(customer -> accountID == customer.getAccountID()))
            System.out.println("* Deleted Successfully! *\n");
        else
            System.out.println("Customer Not Found!");
    }

    @Override
    public String toString() {
        return "\nemployee," + employeeID + ',' + name + ',' + family + ',' + password + '\n';
    }

    @Override
    public int compareTo(Object o) {
        Employee employee = (Employee) o;
        return this.getEmployeeID() - employee.getEmployeeID();
    }
}