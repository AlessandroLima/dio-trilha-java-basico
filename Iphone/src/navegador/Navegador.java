package navegador;

public sealed interface Navegador permits NavegadorInternet {
	
	void exibirPagina(String url);
	void adicionarNovaAba();
	void atualizarPagina();
}
