import java.util.Scanner;

public class Username_checker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Confirm username: ");
        String confirmation = sc.nextLine();

        int length1 = username.length();
        int length2 = confirmation.length();
        System.out.println("Length 1: " + length1);
        System.out.println("Length 2: " + length2);
        System.out.println("Lengths match: " + ((length1 == length2) ?  "true" : "false"));
        System.out.println("Strings match: " +((username.equals(confirmation)) ?  "true" : "false"));

        sc.close();
    }
}
