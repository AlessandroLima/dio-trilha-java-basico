package banco;

public sealed interface Conta permits ContaBancaria {
	
	double sacar(double valor);
	double depositar(double valor);
	double transferir(double valor, Conta conta);
	void   receberTransferencia(double valor, Conta conta);

}
