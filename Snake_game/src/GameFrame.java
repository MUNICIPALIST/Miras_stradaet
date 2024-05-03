import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private GamePanel gamePanel;
    private JButton restartButton;

    GameFrame() {
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        restartButton = new JButton("RESTART");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        southPanel.add(restartButton);
        this.add(southPanel, BorderLayout.SOUTH);

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    void restartGame() {
        this.remove(gamePanel);
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        this.validate();
    }
}
