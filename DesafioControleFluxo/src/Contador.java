import java.util.InputMismatchException;
import java.util.Scanner;

public class Contador {
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Digite o primeiro parâmetro:");
        int number1 = lerInteiro(scanner); 

        System.out.println("Digite o segundo parâmetro:");
        int number2 = lerInteiro(scanner);

        contar(number1, number2);

    }

    static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		
        if (parametroUm >= parametroDois) {
            throw new ParametrosInvalidosException("O primeiro número deve ser menor que o segundo número.");
        }

        int contagem = parametroDois - parametroUm;

        for (int i = 1; i <= contagem; i++) {
            System.out.println("Imprimindo o número "+i+"");
        }
		
	}

     static int lerInteiro(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw new InputMismatchException("Valor inserido não é um número inteiro.");
        }
    }

}




class ParametrosInvalidosException extends Exception {
    public ParametrosInvalidosException(String message) {
        super(message);
    }
}

