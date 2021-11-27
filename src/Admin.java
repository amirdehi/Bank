public class Admin {

    //Properties
    String username, password, name, family;

    //Constructors
    public Admin(String username, String password, String name, String family) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.family = family;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    //Methods for Employees
    void addEmployee(int employeeID, String name, String family, String password) {
        if (Bank.employees.stream().noneMatch(employee -> employee.getEmployeeID() == employeeID)) {
            if (Bank.employees.add(new Employee(employeeID, name, family, password)))
                System.out.println("* Employee Added Successfully! *\n");
            else
                System.out.println("* Cannot add Employee! *\n");
        } else
            System.out.println("* Employee ID is not available *\n");
    }

    void removeEmployee(int employeeID) {
        if (Bank.employees.removeIf(employee -> employeeID == employee.getEmployeeID()))
            System.out.println("* Employee Removed Successfully! *\n");
        else
            System.out.println("* Employee Not Found! *\n");
    }

    @Override
    public String toString() {
        return "\nAdmin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}