package reprodutores;
import java.util.ArrayList;
import java.util.List;

public final class ReprodutorMusical implements Reprodutor {
	
	private static List<Musica> playlist = new ArrayList<>();
	private Musica musicaSelecionada  = null;
	
	public static void main(String[] args) {
		
        playlist.add(new Musica("Bohemian Rhapsody", "Queen", 354, Genero.ROCK));
        playlist.add(new Musica("Imagine", "John Lennon", 183, Genero.POP));
        playlist.add(new Musica("So What", "Miles Davis", 545, Genero.JAZZ));
	}

	@Override
	public void tocar() {
		if (musicaSelecionada != null) {
			System.out.println("Tocando: " + musicaSelecionada);
		}
    }

	@Override
	public void pausar() {
		if (musicaSelecionada != null) {
			System.out.println("Pausando: " + musicaSelecionada);
		}

	}
	
	public void selecionarMusica(int faixa) {
		
		if (faixa >= 0 && faixa < playlist.size()) {
            musicaSelecionada = playlist.get(faixa);
            System.out.println("Música no índice " + faixa + ": " + musicaSelecionada);
            
        } else {
            System.out.println("Índice fora dos limites da playlist.");
        }
	\}

}
