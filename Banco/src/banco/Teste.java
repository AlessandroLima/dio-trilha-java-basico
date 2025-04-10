package banco;

import java.text.DecimalFormat;

public class Teste {
    public static void main(String[] args) {
        Agencia agencia = Agencia.getInstance();

        Cliente[] clientes = new Cliente[10];
        
        for (int i = 0; i < 10; i++) {
            String nome = "Cliente " + (i + 1);
            String cpf = "000.000.000-0" + i;
            ContaBancaria conta;
            
            if (i % 2 == 0) {
                conta = new ContaCorrente();
            } else {
                conta = new ContaPoupanca();
            }
            
            if (i == 0) {
                conta.depositar(1000);
            } else if (i == 1) {
                conta.depositar(100);
            } else {
                conta.depositar(1000 + (i * 500));
            }
            
            clientes[i] = new Cliente(nome, cpf, conta);
            agencia.adicionarCliente(clientes[i]);
        }
        
        agencia.listarClientes();
        
        Cliente cliente1 = clientes[0]; 
        Cliente cliente2 = clientes[1]; 
        
        System.out.println("\nRealizando transações entre " + cliente1.nome + " e " + cliente2.nome);
        
        
        cliente1.contaBancaria.sacar(300);
        
        cliente2.contaBancaria.sacar(200);
        
        cliente1.contaBancaria.transferir(200, cliente2.contaBancaria);
        
        cliente2.contaBancaria.transferir(150, cliente1.contaBancaria);
        
        System.out.println("\nExtrato da conta de " + cliente1.nome);
        cliente1.contaBancaria.extrato();
        
        System.out.println("\nExtrato da conta de " + cliente2.nome);
        cliente2.contaBancaria.extrato();
    }
}
