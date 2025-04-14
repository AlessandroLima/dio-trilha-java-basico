package br.com.sudoku;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.sudoku.models.Board;
import br.com.sudoku.models.Space;
import br.com.sudoku.constants.BoardTemplate;

public class Main {

	private static final Scanner scanner = new Scanner(System.in);
	private static Board board;
	private static final int BOARD_LIMIT = 9;

	public static void main(String[] args) {
		
		args = generateSudokuArgsRandom();

		final Map<String, String> positions = Stream.of(args)
			.map(arg -> arg.split(";"))
			.filter(parts -> parts.length == 3)
			.collect(Collectors.toMap(
				parts -> parts[0],           // Ex: "0,1"
				parts -> parts[1] + ";" + parts[2] // Ex: "5;true"
			));

		int option;

		while (true) {
			System.out.println("""
				Selecione uma das opções:
				1 - Iniciar jogo
				2 - Colocar número
				3 - Remover número
				4 - Visualizar tabuleiro
				5 - Verificar Status do jogo
				6 - Limpar jogo
				7 - Finalizar jogo
				8 - Sair
			""");

			option = scanner.nextInt();

			switch (option) {
				case 1 -> startGame(positions);
				case 2 -> inputNumber();
				case 3 -> removeNumber();
				case 4 -> showCurrentGame();
				case 5 -> showGameStatus();
				case 6 -> clearGame();
				case 7 -> finishGame();
				case 8 -> System.exit(0);
				default -> System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	public static String[] generateSudokuArgsRandom() {
		List<String> argsList = new ArrayList<>();
		Random random = new Random();

		Set<String> usedPositions = new HashSet<>();
		int numFixed = 25;

		while (usedPositions.size() < numFixed) {
			int row = random.nextInt(9);
			int col = random.nextInt(9);
			String key = row + "," + col;
			if (!usedPositions.contains(key)) {
				int value = random.nextInt(9) + 1;
				argsList.add(key + ";" + value + ";true");
				usedPositions.add(key);
			}
		}

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				String pos = row + "," + col;
				if (!usedPositions.contains(pos)) {
					argsList.add(pos + ";0;false");
				}
			}
		}

		return argsList.toArray(new String[0]);
	}

	private static void startGame(Map<String, String> positions) {
		if (board != null) {
			System.out.println("Jogo já foi iniciado!");
			return;
		}

		List<List<Space>> spaces = new ArrayList<>();

		for (int i = 0; i < BOARD_LIMIT; i++) {
			List<Space> rowSpaces = new ArrayList<>();
			for (int j = 0; j < BOARD_LIMIT; j++) {
				String key = i + "," + j;
				String positionConfig = positions.get(key);
				String[] parts = positionConfig.split(";");
				int value = Integer.parseInt(parts[0]);
				boolean fixed = Boolean.parseBoolean(parts[1]);
				rowSpaces.add(new Space(value, fixed));
			}
			spaces.add(rowSpaces);
		}

		board = new Board(spaces);
		System.out.println("Jogo iniciado com sucesso!");
	}

	private static void showCurrentGame() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		Object[] args = new Object[81];
		int argsPos = 0;

		for (int i = 0; i < BOARD_LIMIT; i++) {
			for (int j = 0; j < BOARD_LIMIT; j++) {
				Integer actual = board.getSpaces().get(i).get(j).getActual();
				args[argsPos++] = " " + (actual == null || actual == 0 ? " " : actual);
			}
		}

		System.out.println("Tabuleiro atual:");
		System.out.println(BoardTemplate.BOARD_TEMPLATE.formatted(args));
	}

	private static void inputNumber() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		System.out.println("Informe a coluna:");
		int col = runUntilGetValidNumber(0, BOARD_LIMIT - 1);

		System.out.println("Informe a linha:");
		int row = runUntilGetValidNumber(0, BOARD_LIMIT - 1);

		System.out.printf("Informe o número que será inserido na posição [%s, %s]:%n", col, row);
		int value = runUntilGetValidNumber(1, 9);

		if (board.changeValue(row, col, value)) {
			System.out.println("Número inserido com sucesso!");
		} else {
			System.out.printf("Não foi possível inserir. Já existe valor fixo em [%s, %s].%n", col, row);
		}
	}

	private static void removeNumber() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		System.out.println("Informe a coluna:");
		int col = runUntilGetValidNumber(0, BOARD_LIMIT - 1);

		System.out.println("Informe a linha:");
		int row = runUntilGetValidNumber(0, BOARD_LIMIT - 1);

		if (board.clearValue(row, col)) {
			System.out.println("Número removido com sucesso!");
		} else {
			System.out.printf("Não é possível remover o valor fixo da posição [%s, %s].%n", col, row);
		}
	}

	private static void finishGame() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		if (board.isFinished()) {
			System.out.println("Parabéns, jogo finalizado!");
			showCurrentGame();
		} else if (board.hasError()) {
			System.out.println("O jogo possui erros. Verifique os valores inseridos.");
			showCurrentGame();
		} else {
			System.out.println("O jogo ainda não foi finalizado. Preencha todos os espaços corretamente.");
		}
	}

	private static void clearGame() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		System.out.println("Tem certeza que deseja limpar o jogo? (S/N)");
		String option = scanner.next();

		while (!option.equalsIgnoreCase("S") && !option.equalsIgnoreCase("N")) {
			System.out.println("Opção inválida. Digite S ou N.");
			option = scanner.next();
		}

		if (option.equalsIgnoreCase("S")) {
			board.Reset();
			System.out.println("Jogo limpo com sucesso!");
		} else {
			System.out.println("Jogo não foi limpo.");
		}
	}

	private static void showGameStatus() {
		if (board == null) {
			System.out.println("Jogo ainda não foi iniciado!");
			return;
		}

		System.out.println("Status do jogo: " + board.getStatus().getLabel());
		if (board.hasError()) {
			System.out.println("O jogo possui erros!");
		} else {
			System.out.println("O jogo não possui erros.");
		}
	}

	private static int runUntilGetValidNumber(final int min, final int max) {
		int number = -1;
		while (number < min || number > max) {
			System.out.printf("Digite um número entre %d e %d:%n", min, max);
			number = scanner.nextInt();
		}
		return number;
	}
}
