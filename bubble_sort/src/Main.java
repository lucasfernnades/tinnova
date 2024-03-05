import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] vetor = new int[]{5, 3, 2, 4, 7, 1, 0, 6};

        int length = vetor.length;

        while (length > 1) {
            for (int i = 0; i < length - 1; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    int aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                }
            }
            length--;
        }

        System.out.println(Arrays.toString(vetor));
    }
}