import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static JFrame frame;
    private final int WIDTH = 160; //largura da tela
    private final int HEIGHT = 120;//altura da tela
    private final int SCALE = 3;//escala

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        frame = new JFrame("Primeira tela");
        frame.add(this);
        frame.setResizable(false); //o usuario nao vai poder aumentar a tela do jogo
        frame.pack();
        frame.setLocationRelativeTo(null); //o jogo sempre vai ficar posicionado no centro
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ao clicar em fechar o programa é encerrado
        frame.setVisible(true);//quando rodar a tela vai ficar vizivel

    }
    public static void main(String[] args) {
        Game game = new Game();

    }

    public void run() {

    }
}