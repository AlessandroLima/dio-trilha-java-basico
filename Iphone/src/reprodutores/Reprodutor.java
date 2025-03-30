package reprodutores;

public sealed interface Reprodutor permits ReprodutorMusical {
	void tocar();
	void pausar();
}
