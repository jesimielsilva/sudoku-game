package br.git.jesimiel.sudoku.model;

import java.util.*;

public class SudokuGame {
    private final List<List<SudokuCell>> board = new ArrayList<>();

    public SudokuGame() {
        // Inicializa tabuleiro 9x9 vazio
        for (int r = 0; r < 9; r++) {
            List<SudokuCell> row = new ArrayList<>();
            for (int c = 0; c < 9; c++) {
                row.add(new SudokuCell());
            }
            board.add(row);
        }
        gerarTabuleiroExemplo();
    }

    public List<List<SudokuCell>> getBoard() {
        return board;
    }

    public boolean[][] getConflicts() {
        boolean[][] conflicts = new boolean[9][9];
        // Verifica conflitos simples: mesma linha ou coluna
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int v = board.get(r).get(c).getValue();
                if (v != 0) {
                    // Linha
                    for (int cc = 0; cc < 9; cc++) {
                        if (cc != c && board.get(r).get(cc).getValue() == v) {
                            conflicts[r][c] = true;
                        }
                    }
                    // Coluna
                    for (int rr = 0; rr < 9; rr++) {
                        if (rr != r && board.get(rr).get(c).getValue() == v) {
                            conflicts[r][c] = true;
                        }
                    }
                }
            }
        }
        return conflicts;
    }

    private void gerarTabuleiroExemplo() {
        // Apenas exemplo inicial
        board.get(0).get(0).setValue(5);
        board.get(0).get(0).setFixed(true);
        board.get(1).get(3).setValue(6);
        board.get(1).get(3).setFixed(true);
    }

}
