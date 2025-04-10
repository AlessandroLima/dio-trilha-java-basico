package banco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

	protected int AGENCIA;
	static int NUMERO = 1;
	private int numeroConta;
	protected double saldo = 0;
	private List<String> extrato;
	private static final DecimalFormat df = new DecimalFormat("0.00");

	public ContaBancaria() {
		this.AGENCIA = Agencia.getInstance().getNumero();
		this.extrato = new ArrayList<>();
		this.numeroConta = NUMERO++;
	}

	public String getSaldo() {
		return df.format(saldo);
	}

	public int getAgencia() {
		return AGENCIA;
	}

	public String getNumero() {
		return String.valueOf(numeroConta);
	}

	public double sacar(double valor) {
		if (saldo >= valor) {
			saldo -= valor;
			extrato.add("Saque de R$ " + df.format(valor)+ " realizado.");
			return saldo;
		}
		extrato.add("Tentativa de saque de R$ " + df.format(valor) + " falhou. Saldo insuficiente.");
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
			extrato.add("Transferência de R$ " + df.format(valor) + " realizada para a conta " + conta.getNumero() + ".");
			return saldo;
		}
		extrato.add("Tentativa de transferência de R$ " + df.format(valor) + " falhou. Saldo insuficiente.");
		return saldo;
	}

	public void receberTransferencia(double valor, ContaBancaria conta) {
		this.depositar(valor);
		extrato.add("Recebido R$ " + df.format(valor) + " de transferência da conta " + conta.getNumero() + ".");
	}

	public void extrato() {
		System.out.println("=== EXTRATO DA CONTA ===");
		for (String transacao : extrato) {
			System.out.println(transacao);
		}
		System.out.println("Saldo atual: R$ " + df.format(saldo));
		System.out.println("========================");
	}
}
