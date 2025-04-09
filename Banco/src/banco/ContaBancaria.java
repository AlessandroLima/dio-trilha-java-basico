package banco;

import java.util.ArrayList;
import java.util.List;

public  class ContaBancaria {
	
	protected int AGENCIA;
	private static int NUMERO = 1;
	protected double saldo = 0;
	private List<String> extrato; 

	public ContaBancaria() {
		this.AGENCIA = Agencia.getInstance().getNumero();
		this.extrato = new ArrayList<>();
		ContaBancaria.NUMERO = NUMERO++;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getAgencia() {
		return AGENCIA;
	}

	public String getNumero() {
		return String.valueOf(NUMERO) ;
	}

	public double sacar(double valor) {
		if (saldo >= valor) {
			saldo -= valor;
			extrato.add("Saque de R$ " + valor + " realizado.");
			return saldo;
		}
		extrato.add("Tentativa de saque de R$ " + valor + " falhou. Saldo insuficiente.");
		return saldo;
	}

	public double depositar(double valor) {
		saldo += valor;
		extrato.add("Depósito de R$ " + valor + " realizado.");
		return saldo;
	}

	public double transferir(double valor, ContaBancaria conta) {
		if (saldo >= valor) {
			saldo -= valor;
			conta.receberTransferencia(valor, this);
			extrato.add("Transferência de R$ " + valor + " realizada para a conta " + conta.getNumero() + ".");
			return saldo;
		}
		extrato.add("Tentativa de transferência de R$ " + valor + " falhou. Saldo insuficiente.");
		return saldo;
	}

	public void receberTransferencia(double valor, ContaBancaria conta) {
		this.depositar(valor);
		extrato.add("Recebido R$ " + valor + " de transferência da conta " + conta.getNumero() + ".");
	}

	// Novo método para exibir o extrato
	public void extrato() {
		System.out.println("=== EXTRATO DA CONTA ===");
		for (String transacao : extrato) {
			System.out.println(transacao);
		}
		System.out.println("========================");
	}
}
