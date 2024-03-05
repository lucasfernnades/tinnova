import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberToFactorial;

        System.out.print("Insert the number that you want to know the factorial: ");
        numberToFactorial = scanner.nextInt();

        BigInteger result = calculateFactorial(numberToFactorial);

        System.out.println("The factorial of " + numberToFactorial + "! is " + result);
    }

    private static BigInteger calculateFactorial(int number) {
        if (number == 0 || number == 1) {
            return BigInteger.valueOf(1);
        }

        BigInteger resultFactorial = BigInteger.valueOf(1);

        for (int i = 2; i <= number; i++) {
            resultFactorial = resultFactorial.multiply(BigInteger.valueOf(i));
        }

        return resultFactorial;
    }
}