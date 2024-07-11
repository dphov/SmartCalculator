class Employee {

    String name;
    int salary;
    String address;

    Employee() {
        this.name = "unknown";
        this.salary = 0;
        this.address = "unknown";
    }

    Employee(String name, int salary) {
       this.name = name;
       this.salary = salary;
       this.address = "unknown";
    }

    Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}