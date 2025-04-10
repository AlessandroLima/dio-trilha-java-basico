package banco;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
    
    private static volatile Agencia instancia;
    private static final Banco BANCO = Banco.getInstance(); 
    private static final int NUMERO = 1;
    private List<Cliente> clientes; 
    
    private Agencia() {
        this.clientes = new ArrayList<>();
    }

    public static Agencia getInstance() {
        if (instancia == null) {
            synchronized (Agencia.class) {
                if (instancia == null) {
                    instancia = new Agencia();
                }
            }
        }
        return instancia;
    }

    public Banco getBanco() {
        return BANCO;
    }

    public int getNumero() {
        return NUMERO;
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente " + cliente.nome + " cadastrado com sucesso!");
    }

    public void listarClientes() {
        System.out.println("=== Clientes da Agência " + NUMERO + " ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.nome + ", CPF: " + cliente.cpf + ", Agência: " + cliente.contaBancaria.getAgencia() + ", Conta: " + cliente.contaBancaria.getNumero() + ", Tipo: " + cliente.contaBancaria.getClass().getSimpleName()+ ", saldo: R$ " + cliente.contaBancaria.getSaldo());	
            }
        }
        System.out.println("===============================");
    }
}
