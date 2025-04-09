package banco;

public class Banco {
    private static volatile Banco instancia;
    private static final String NOME = "Banco Central";
    private static final int NUMERO = 101;

    private Banco() {} // Construtor privado

    public static Banco getInstance() {
        if (instancia == null) {
            synchronized (Banco.class) {
                if (instancia == null) {
                    instancia = new Banco();
                }
            }
        }
        return instancia;
    }

    public String getNome() {
        return NOME;
    }

    public int getNumero() {
        return NUMERO;
    }
}
