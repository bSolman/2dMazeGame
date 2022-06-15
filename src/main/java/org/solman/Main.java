package org.solman;

import org.solman.graphics.Board;

import javax.swing.*;

public class Main {


    private static void initWindow() {
        JFrame window = new JFrame("Party");
        Board board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initWindow();
            }
        });
    }
}