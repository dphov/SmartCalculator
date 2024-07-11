class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            return;
        }
        if (!firstName.isEmpty()) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            return;
        }
        if (!lastName.isEmpty()) {
            this.lastName = lastName;
        }
        // write your code here
    }

    public String getFullName() {
        if (firstName.isEmpty() && lastName.isEmpty()) {
           return "Unknown";
        }
        if (firstName.isEmpty()) {
            return lastName;
        }
        if (lastName.isEmpty()) {
            return firstName;
        }
        return firstName + " " + lastName; // write your code here
    }
}