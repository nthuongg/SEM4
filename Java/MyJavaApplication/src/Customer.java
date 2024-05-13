import java.util.Scanner;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Customer(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer() {

    }

    public void inputData(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Customer Id:");
        this.id = input.nextInt();
        input.nextLine();
        System.out.println("Enter First Name:");
        this.firstName = input.nextLine();
        System.out.println("Enter Last Name:");
        this.lastName = input.nextLine();
        System.out.println("Enter Email:");
        this.email = input.nextLine();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
