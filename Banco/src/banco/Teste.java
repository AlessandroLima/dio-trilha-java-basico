package banco;

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
            
            conta.depositar(1000 + (i * 500)); 
            clientes[i] = new Cliente(nome, cpf, conta);
            agencia.adicionarCliente(clientes[i]);
        }
        
        
        agencia.listarClientes();
        
        for (Cliente cliente : clientes) {
            System.out.println("Extrato da conta de " + cliente.nome);
            cliente.contaBancaria.extrato();
        }
    }
}
