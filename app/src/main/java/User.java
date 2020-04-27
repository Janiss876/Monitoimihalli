import java.util.Scanner;

public class User {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;

    public void login() {
        Scanner log = new Scanner(System.in);
        System.out.println("Enter email");
        email = log.nextLine();
        System.out.println("Enter password");
        password = log.nextLine();
    }
    }
