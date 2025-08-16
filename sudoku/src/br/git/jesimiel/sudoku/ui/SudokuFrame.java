package br.git.jesimiel.sudoku.ui;

import br.git.jesimiel.sudoku.model.SudokuCell;
import br.git.jesimiel.sudoku.model.SudokuGame;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

public class SudokuFrame extends JFrame {

    private SudokuPanel boardPanel;

    public SudokuFrame() {
        setTitle("Sudoku Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel do tabuleiro
        boardPanel = new SudokuPanel();
        add(boardPanel, BorderLayout.CENTER);

        // Criar Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu jogoMenu = new JMenu("Jogo");

        JMenuItem novoJogoItem = new JMenuItem("Novo Jogo");
        novoJogoItem.addActionListener(e -> boardPanel.novoJogo());

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(e -> System.exit(0));

        jogoMenu.add(novoJogoItem);
        jogoMenu.add(sairItem);
        menuBar.add(jogoMenu);

        setJMenuBar(menuBar);

        // Ajuste de tamanho inicial
        setSize(600, 650); // espaço extra por causa da barra
        setLocationRelativeTo(null); // centralizar
    }

}
