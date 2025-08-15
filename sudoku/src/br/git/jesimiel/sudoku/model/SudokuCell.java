package br.git.jesimiel.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public class SudokuCell {

    private int value; // 0 = vazio
    private boolean fixed;
    private final Set<Integer> notes = new HashSet<>();

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Set<Integer> getNotes() {
        return notes;
    }
}
