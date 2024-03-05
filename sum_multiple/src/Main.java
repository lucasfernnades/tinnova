import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number;

        System.out.print("Enter the limit number to find out the sum of the natural numbers divisible by 3 and 5 " +
                "up to the number entered: ");
        number = scanner.nextInt();

        int sum = 0;

        for (int i = 0; i < number; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        }

        System.out.println("\nThe sum of all natural numbers less than " + number + " is: " + sum);
    }
}