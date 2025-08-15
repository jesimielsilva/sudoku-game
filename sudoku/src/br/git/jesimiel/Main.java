package br.git.jesimiel;

import br.git.jesimiel.sudoku.model.SudokuGame;
import br.git.jesimiel.sudoku.ui.SudokuFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame();
            frame.setVisible(true);
        });
    }
}
