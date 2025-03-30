import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do saldo inicial
        System.out.println("Inserir saldo inicial: ");
        double saldoInicial = lerDouble(scanner);

        // Entrada das três transações
        System.out.println("Inserir valor da transação 1: ");
        double transacao1 = lerDouble(scanner);

        System.out.println("Inserir valor da transação 2: ");
        double transacao2 = lerDouble(scanner);

        System.out.println("Inserir valor da transação 3: ");
        double transacao3 = lerDouble(scanner);

        // Cálculo do saldo final
        double saldoFinal = saldoInicial + transacao1 + transacao2 + transacao3;

        // Exibição do saldo final formatado com duas casas decimais
        System.out.printf("\nSaldo final: %.2f\n", saldoFinal);

        scanner.close();
    }

    static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble(); 
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido não é um número decimal válido. Tente novamente.");
                scanner.nextLine(); 
            }
        }
    }
}