package banco;

public non-sealed class ContaBancaria implements Conta {
	
	protected int AGENCIA;
	private static int SEQUENCIAL = 1;
	protected double saldo = 0;

	public double getSaldo() {
		return saldo;
	}

	public int getAgencia() {
		return AGENCIA;
	}

	public int getNumero() {
		return SEQUENCIAL;
	}

	public ContaBancaria() {
		super();
		this.AGENCIA = Agencia.getInstance().getNumero();
		ContaBancaria.SEQUENCIAL = SEQUENCIAL++;
	}

	@Override
	public double sacar(double valor) {
		return saldo - valor;
		
	}

	@Override
	public double depositar(double valor) {
		return saldo + valor;
		
	}

	@Override
	public double transferir(double valor, Conta conta) {
		if (saldo > valor) {
			saldo = saldo - valor;
		}
		
		return saldo;
		
	}

	@Override
	public void receberTransferencia(double valor, Conta conta) {
		conta.depositar(valor);
		
	}

	


}
