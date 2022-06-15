package org.solman.graphics;

import org.solman.entity.ComputerPlayer;
import org.solman.entity.HumanPlayer;
import org.solman.entity.Player;
import org.solman.logic.util.CreateMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Board extends JPanel implements ActionListener, KeyListener {

    private final int DELAY = 25;
    private final int TILE_SIZE = 40;
    private static final long serialVersionUID = 490905409104883233L;
    private Timer timer;


    private Image star;
    private Thread animator;

    private final int OFFSET = 430;
    private ComputerPlayer cpu;
    private HumanPlayer humanPlayer;
    private CreateMap mapInfo;

    public Board() {
        setPreferredSize(new Dimension(TILE_SIZE * 21, TILE_SIZE * 13));
        mapInfo = new CreateMap(1);
        cpu = new ComputerPlayer(mapInfo.getStartNode(), "CPU", 3,
                mapInfo.getCpuShortestPath(mapInfo.getStartNode(), mapInfo.getEndNode(), mapInfo.getNodes()));
        humanPlayer = new HumanPlayer(mapInfo.getStartNode(), "Player1", 3);
        humanPlayer.createObstaclesList(mapInfo.getNodes());
        initBoard();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Where all updates regarding the game state is done.
    @Override
    public void actionPerformed(ActionEvent e) {
        //todo add collision detection for human player.
        humanPlayer.setNewLocation();
        cpu.setNewLocation();
        repaint();
    }

    private void initBoard() {
        setBackground(Color.BLACK);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawMap(graphics2D, 0);
        drawMap(graphics2D, OFFSET);
        drawHuman(graphics2D, OFFSET, humanPlayer);
        drawHuman(graphics2D, 0, cpu);
        if (humanPlayer.hasWon(mapInfo.getEndNode())){
            paintVictoryText("Human is VICTORIOUS!", graphics2D);
        }
        if (cpu.hasWon(mapInfo.getEndNode())){
            paintVictoryText("CPU is VICTORIOUS!", graphics2D);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void paintVictoryText(String s, Graphics2D graphics2D) {
        Font font = new Font("Monospaced", Font.BOLD, 70);
        graphics2D.setColor(Color.RED);
        graphics2D.setFont(font);
        graphics2D.drawString(s,10, 100);
    }

    private void drawHuman(Graphics2D graphics2D, int offset, Player player) {
        Rectangle2D.Double human = new Rectangle2D.Double(player.getPlayerPosition().getPosX() * TILE_SIZE + (offset), player.getPlayerPosition().getPosY() * TILE_SIZE, 40, 40);
        graphics2D.setColor(Color.BLUE);
        graphics2D.fill(human);
    }

    private void drawMap(Graphics2D graphics2D, int offset) {
        List<String> map = mapInfo.getMapAsString();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                if (map.get(i).charAt(j) == 'x') {
                    graphics2D.setColor(Color.CYAN);
                    graphics2D.fillRect((j * 40) + offset, i * 40, 40, 40);
                }
                if (map.get(i).charAt(j) == 'o') {
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.fillRect((j * 40) + offset, i * 40, 40, 40);
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        humanPlayer.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        humanPlayer.keyReleased(e);
    }
}


