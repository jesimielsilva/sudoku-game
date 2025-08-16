package br.git.jesimiel.sudoku.ui;

import br.git.jesimiel.sudoku.model.SudokuGame;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {

    private SudokuGame game = new SudokuGame();
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuPanel() {
        setPreferredSize(new Dimension(600, 600));

        // Clique do mouse seleciona a célula
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int cellSize = getWidth() / 9;
                selectedRow = e.getY() / cellSize;
                selectedCol = e.getX() / cellSize;
                repaint();
            }
        });

        // Digitar número insere valor na célula
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (selectedRow != -1 && selectedCol != -1) {
                    int num = e.getKeyChar() - '0';
                    if (num >= 1 && num <= 9) {
                        if (isValidMove(selectedRow, selectedCol, num)) {
                            game.getBoard().get(selectedRow).get(selectedCol).setValue(num);
                        } else {
                            // Mostra erro visual
                            JOptionPane.showMessageDialog(SudokuPanel.this,
                                    "Número inválido nessa posição!",
                                    "Movimento inválido",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        repaint();
                    } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE ||
                            e.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                        game.getBoard().get(selectedRow).get(selectedCol).setValue(0);
                        repaint();
                    }
                }
            }
        });
    }

    public void novoJogo() {
        game = new SudokuGame();
        selectedRow = -1;
        selectedCol = -1;
        repaint();
    }

    /**
     * Valida se o número pode ser colocado em (row, col)
     */
    private boolean isValidMove(int row, int col, int num) {
        // Linha
        for (int c = 0; c < 9; c++) {
            if (c != col && game.getBoard().get(row).get(c).getValue() == num) {
                return false;
            }
        }

        // Coluna
        for (int r = 0; r < 9; r++) {
            if (r != row && game.getBoard().get(r).get(col).getValue() == num) {
                return false;
            }
        }

        // Quadrante 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (!(r == row && c == col) &&
                        game.getBoard().get(r).get(c).getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = getWidth() / 9;

        Graphics2D g2 = (Graphics2D) g;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int x = c * cellSize;
                int y = r * cellSize;
                int v = game.getBoard().get(r).get(c).getValue();

                // Fundo
                if (r == selectedRow && c == selectedCol) {
                    g2.setColor(new Color(200, 220, 255)); // célula selecionada
                } else {
                    g2.setColor(Color.WHITE);
                }
                g2.fillRect(x, y, cellSize, cellSize);

                // Valor
                if (v != 0) {
                    g2.setFont(new Font("Arial", Font.BOLD, (int)(cellSize * 0.6)));
                    String s = Integer.toString(v);
                    FontMetrics fm = g2.getFontMetrics();
                    int tx = x + (cellSize - fm.stringWidth(s)) / 2;
                    int ty = y + (cellSize + fm.getAscent()) / 2 - fm.getDescent();
                    g2.setColor(Color.BLACK);
                    g2.drawString(s, tx, ty);
                }

                // Bordas
                g2.setColor(Color.GRAY);
                g2.drawRect(x, y, cellSize, cellSize);
            }
        }

        // Linhas grossas (3x3)
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 9; i += 3) {
            g2.drawLine(0, i * cellSize, getWidth(), i * cellSize);
            g2.drawLine(i * cellSize, 0, i * cellSize, getHeight());
        }
    }
}
