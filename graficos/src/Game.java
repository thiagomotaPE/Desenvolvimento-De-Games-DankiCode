import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static JFrame frame; //tela
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 160; //largura da tela
    private final int HEIGHT = 120;//altura da tela
    private final int SCALE = 3;//escala

    public Game() {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
    }

    //metodo que inicia a tela do jogo
    public void initFrame() {
        frame = new JFrame("Primeira tela"); //tela iniciada
        frame.add(this);
        frame.setResizable(false); //o usuario nao vai poder aumentar a tela do jogo
        frame.pack();
        frame.setLocationRelativeTo(null); //o jogo sempre vai ficar posicionado no centro
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ao clicar em fechar o programa é encerrado
        frame.setVisible(true);//quando rodar a tela vai ficar vizivel
    }

    //iniciando o jogo
    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    //desligando o jogo
    public synchronized void stop() {

    }
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }

    //atualizando o jogo(logica)
    public void tick() {

    }

    //renderizando o jogo(graficos)
    public void render() {

    }

    //loop principal do game
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            //contador para mostra o FPS no console
            if(System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
    }
}