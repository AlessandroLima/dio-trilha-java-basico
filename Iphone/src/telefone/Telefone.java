package telefone;

public sealed interface Telefone permits Celular {
	void ligar(int numero);
	void atender(boolean atender);
	void iniciarCorreioDeVoz();
}
