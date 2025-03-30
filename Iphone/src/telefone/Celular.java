package telefone;

public final class Celular implements Telefone {

	@Override
	public void ligar(int numero) {
		System.out.println("Efetuando ligação telefônica para o número: " +numero);

	}


	@Override
	public void atender(boolean atender) {
		if (atender == true) {
			System.out.println("Atendendo a ligação telefônica!");
		}else { 
			System.out.println("Rejeitando ou não atendendo a ligação telefônica!");
		}
		
	}


	@Override
	public void iniciarCorreioDeVoz() {
		System.out.println("Gravando e enviando um correio de voz a um destinatário!");
		
	}

	

}
