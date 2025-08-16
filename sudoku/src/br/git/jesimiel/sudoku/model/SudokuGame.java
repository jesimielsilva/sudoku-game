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

    /**
     * Insere número no tabuleiro com validações
     */
    public boolean insertNumber(int row, int col, int value) {
        SudokuCell cell = board.get(row).get(col);
        if (cell.isFixed()) {
            System.out.println("❌ Não é possível alterar um número fixo!");
            return false;
        }
        if (cell.getValue() != 0) {
            System.out.println("❌ A célula já está preenchida!");
            return false;
        }
        if (value < 1 || value > 9) {
            System.out.println("❌ O valor deve estar entre 1 e 9!");
            return false;
        }
        cell.setValue(value);
        return true;
    }

    /**
     * Remove número (apenas do jogador)
     */
    public boolean removeNumber(int row, int col) {
        SudokuCell cell = board.get(row).get(col);
        if (cell.isFixed()) {
            System.out.println("❌ Não é possível remover números fixos!");
            return false;
        }
        if (cell.getValue() == 0) {
            System.out.println("❌ A célula já está vazia!");
            return false;
        }
        cell.setValue(0);
        return true;
    }

    /**
     * Limpa apenas os números adicionados pelo jogador
     */
    public void clearBoard() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                SudokuCell cell = board.get(r).get(c);
                if (!cell.isFixed()) {
                    cell.setValue(0);
                }
            }
        }
    }

    public void iniciarNovoJogo() {
        // Limpa o tabuleiro
        board.clear();
        for (int r = 0; r < 9; r++) {
            List<SudokuCell> row = new ArrayList<>();
            for (int c = 0; c < 9; c++) {
                row.add(new SudokuCell());
            }
            board.add(row);
        }

        // Carrega exemplo (ou no futuro pode gerar aleatório)
        gerarTabuleiroExemplo();
    }


    /**
     * Verifica se há conflitos (linhas, colunas e blocos 3x3)
     */
    public boolean[][] getConflicts() {
        boolean[][] conflicts = new boolean[9][9];

        // Linhas e colunas
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
                    // Bloco 3x3
                    int startRow = (r / 3) * 3;
                    int startCol = (c / 3) * 3;
                    for (int rr = startRow; rr < startRow + 3; rr++) {
                        for (int cc = startCol; cc < startCol + 3; cc++) {
                            if (!(rr == r && cc == c) && board.get(rr).get(cc).getValue() == v) {
                                conflicts[r][c] = true;
                            }
                        }
                    }
                }
            }
        }
        return conflicts;
    }

    /**
     * Retorna status do jogo
     */
    public String getStatus() {
        boolean hasEmpty = false;
        boolean hasConflict = false;

        boolean[][] conflicts = getConflicts();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                SudokuCell cell = board.get(r).get(c);
                if (cell.getValue() == 0) {
                    hasEmpty = true;
                }
                if (conflicts[r][c]) {
                    hasConflict = true;
                }
            }
        }

        if (isEmptyBoard()) {
            return "Não iniciado";
        }
        if (hasConflict) {
            return "Com erros";
        }
        if (hasEmpty) {
            return "Incompleto";
        }
        return "Completo e correto ✅";
    }

    private boolean isEmptyBoard() {
        for (List<SudokuCell> row : board) {
            for (SudokuCell cell : row) {
                if (cell.getValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Finaliza jogo apenas se estiver completo e correto
     */
    public boolean finishGame() {
        String status = getStatus();
        if (status.equals("Completo e correto ✅")) {
            System.out.println("🎉 Parabéns! Você finalizou o jogo com sucesso!");
            return true;
        }
        System.out.println("❌ O jogo não pode ser finalizado: " + status);
        return false;
    }

    // Exemplo inicial
    private void gerarTabuleiroExemplo() {
        board.get(0).get(0).setValue(9); board.get(0).get(0).setFixed(true);
        board.get(0).get(1).setValue(5); board.get(0).get(1).setFixed(true);
        board.get(0).get(2).setValue(8); board.get(0).get(2).setFixed(true);
        board.get(1).get(1).setValue(4); board.get(1).get(1).setFixed(true);
        board.get(1).get(3).setValue(6); board.get(1).get(3).setFixed(true);
        board.get(1).get(4).setValue(5); board.get(1).get(4).setFixed(true);
        board.get(1).get(5).setValue(3); board.get(1).get(5).setFixed(true);
        board.get(2).get(6).setValue(5); board.get(2).get(6).setFixed(true);
        board.get(2).get(7).setValue(1); board.get(2).get(7).setFixed(true);
        board.get(2).get(8).setValue(7); board.get(2).get(8).setFixed(true);
        board.get(3).get(8).setValue(2); board.get(3).get(8).setFixed(true);
        board.get(4).get(8).setValue(5); board.get(4).get(8).setFixed(true);
        board.get(5).get(3).setValue(5); board.get(5).get(3).setFixed(true);
        board.get(6).get(8).setValue(3); board.get(6).get(8).setFixed(true);
        board.get(6).get(0).setValue(8); board.get(6).get(0).setFixed(true);
        board.get(7).get(4).setValue(8); board.get(7).get(4).setFixed(true);
    }

}
