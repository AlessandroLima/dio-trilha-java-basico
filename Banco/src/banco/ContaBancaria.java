package banco;

public class ContaBancaria {
	
	private Agencia agencia;
	private int numero;
	private float saldo;

	public float getSaldo() {
		return saldo;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public ContaBancaria(Agencia agencia, int numero, float saldo) {
		super();
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
	}

}
