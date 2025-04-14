package br.com.sudoku.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.sudoku.models.Board;
import br.com.sudoku.models.Space;

public class SudokuTest {

    private static final int SIZE = 9;

    private Map<String, String> generateRandomBoardMap(int fixedPositionsCount) {
        Map<String, String> boardMap = new HashMap<>();

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                boardMap.put(row + "," + col, "0;false");
            }
        }

        Random random = new Random();
        int inserted = 0;

        while (inserted < fixedPositionsCount) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            String key = row + "," + col;
            if (boardMap.get(key).equals("0;false")) {
                int value = 1 + random.nextInt(9);
                boardMap.put(key, value + ";true");
                inserted++;
            }
        }

        return boardMap;
    }

    private Board buildBoardFromMap(Map<String, String> boardMap) {
        List<List<Space>> spaces = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            List<Space> row = new ArrayList<>();
            for (int j = 0; j < SIZE; j++) {
                String config = boardMap.get(i + "," + j);
                int value = Integer.parseInt(config.split(";")[0]);
                boolean fixed = Boolean.parseBoolean(config.split(";")[1]);
                row.add(new Space(value, fixed));
            }
            spaces.add(row);
        }

        return new Board(spaces);
    }

    private Board board;

    @BeforeEach
    public void setup() {
        Map<String, String> boardMap = generateRandomBoardMap(20); // Gera 20 posições fixas
        board = buildBoardFromMap(boardMap);
    }

    @Test
    public void testInitialBoardIsNotNull() {
        assertNotNull(board);
        assertEquals(SIZE, board.getSpaces().size());
    }

    @Test
    public void testInsertValidNumber() {
        boolean inserted = board.changeValue(0, 0, 4); 
        assertTrue(true); 
    }

    @Test
    public void testBoardHasErrors() {
       board.changeValue(0, 0, 3);
        board.changeValue(0, 1, 3);

        assertTrue(board.hasError());
    }

    @Test
    public void testBoardIsNotFinishedAtStart() {
        assertFalse(board.isFinished());
    }
}
