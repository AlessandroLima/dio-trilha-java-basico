package banco;


public class Agencia {
	
	private static volatile Agencia instancia;
	private static final Banco BANCO = Banco.getInstance(); 
	private static final int NUMERO = 1;
	
	
	private Agencia() {}
	
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
	
}