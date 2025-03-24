package bsg;

import java.util.Scanner;

public final class InputUtils {

    private static final Scanner INPUT = new Scanner(System.in);

    public Long lerNumeroCelular() {
        Long numero = null;
        while (true) {
            System.out.print("Digite o número ou digite 0 para cancelar: ");
            numero = INPUT.nextLong();

            if (numero.equals(0L)) {
                return null;
            }

            if (String.valueOf(numero).length() != 11) {
                System.out.println("O número deve ter 11 dígitos");
                continue;
            }

            return numero;
        }
    }

}
