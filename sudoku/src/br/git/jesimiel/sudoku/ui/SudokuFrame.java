package br.git.jesimiel.sudoku.ui;

import br.git.jesimiel.sudoku.model.SudokuCell;
import br.git.jesimiel.sudoku.model.SudokuGame;

import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

public class SudokuFrame extends JFrame {

    private final SudokuGame game;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuFrame() {
        this.game = new SudokuGame();
        setTitle("Sudoku Java");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int cellSize = getWidth() / 9;
                selectedCol = e.getX() / cellSize;
                selectedRow = e.getY() / cellSize;
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (selectedRow != -1 && selectedCol != -1) {
                    SudokuCell cell = game.getBoard().get(selectedRow).get(selectedCol);
                    if (!cell.isFixed()) {
                        if (Character.isDigit(e.getKeyChar())) {
                            int num = e.getKeyChar() - '0';
                            cell.setValue(num);
                        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                                e.getKeyCode() == KeyEvent.VK_DELETE) {
                            cell.setValue(0);
                        }
                        repaint();
                    }
                }
            }
        });

        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        List<List<SudokuCell>> b = game.getBoard();
        boolean[][] bad = game.getConflicts();

        int cellSize = getWidth() / 9;
        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int x = c * cellSize;
                int y = r * cellSize + 30; // compensar título
                SudokuCell sc = b.get(r).get(c);
                int v = sc.getValue();

                // Fundo da célula selecionada
                if (r == selectedRow && c == selectedCol) {
                    g2.setColor(new Color(200, 200, 255));
                    g2.fillRect(x, y, cellSize, cellSize);
                }

                // Valor
                if (v != 0) {
                    g2.setFont(g2.getFont().deriveFont(sc.isFixed() ? Font.BOLD : Font.PLAIN, cellSize * 0.6f));
                    String s = Integer.toString(v);
                    FontMetrics fm = g2.getFontMetrics();
                    int tx = x + (cellSize - fm.stringWidth(s)) / 2;
                    int ty = y + (cellSize + fm.getAscent()) / 2 - fm.getDescent();
                    g2.setColor(bad[r][c] ? Color.RED : Color.BLACK);
                    g2.drawString(s, tx, ty);
                }

                // Borda da célula
                g2.setColor(Color.GRAY);
                g2.drawRect(x, y, cellSize, cellSize);
            }
        }

        // Linhas grossas de bloco 3x3
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 9; i += 3) {
            g2.drawLine(0, i * cellSize + 30, getWidth(), i * cellSize + 30);
            g2.drawLine(i * cellSize, 30, i * cellSize, getHeight());
        }
    }
}
