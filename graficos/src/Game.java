import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    public static JFrame frame; //tela
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 240; //largura da tela
    private final int HEIGHT = 160;//altura da tela
    private final int SCALE = 3;//escala

    private BufferedImage image;

    private Spritesheet sheet;
    private BufferedImage[] player;// array para comportar as animaçoes do player
    private int frames = 0;
    private int maxFrames = 10;//quanto menor mais rapido a animação
    private int curAnimation = 0, maxAnimation = 3; //quantas animações vao ter

    //metodo construtor da classe principal(Game)
    public Game() {
        sheet = new Spritesheet("/spritesheet.png");//pegando a spritesheet inteira e trazendo pro jogo
        player = new BufferedImage[3];//aqui ta dizendo que vai haver dois playes(duas animações)
        player[0] = sheet.getSprite(0, 0, 16, 16);//recortando a spritesheet pra aparecer só o player
        player[1] = sheet.getSprite(15, 0, 16, 16);
        player[2] = sheet.getSprite(31, 0, 16, 16);
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
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
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }

    //atualizando o jogo(logica)
    public void tick() {
        frames++;
        if(frames > maxFrames){
            frames = 0;
            curAnimation++;
            if(curAnimation >= maxAnimation) {
                curAnimation = 0;
            }
        }
    }

    //renderizando o jogo(graficos)
    public void render() {
        //criando o fundo preto do jogo
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(Color.BLUE); //cor da tela
        g.fillRect(0, 0, WIDTH, HEIGHT); //formato e dimensoes da tela

        //renderizando outras imagens na tela(objetos)
//        g.setColor(Color.BLUE);
//        g.fillRect(20, 10, 80, 80);
//        g.setColor(Color.CYAN);
//        g.fillRect(0, 0, 20, 30);
//        g.setColor(Color.GREEN);
//        g.fillRect(80, 100, 60, 20);
//        g.setColor(Color.RED);
//        g.fillOval(105, 20, 50, 50);

        //adiciando palavras no jogo
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.drawString("hello world", 10, 20);


        //adicionando players ao jogo com sprites
        Graphics2D g2 = (Graphics2D) g; //criando animaçoes
//      g2.rotate(Math.toRadians(45), 90 + 8, 90 + 8);//animação
        g2.drawImage(player[curAnimation], 90, 90, null); //renderizando o player na tela

        //deixando a tela do jogo mais escura
//        g2.rotate(Math.toRadians(-45), 90 + 8, 90 + 8);
//        g2.setColor(new Color(0, 0, 0, 180));
//        g2.fillRect(0, 0, WIDTH, HEIGHT);



        g.dispose();//melhorar a performance
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); //colocando a imagem pra ocupar toda tela
        bs.show(); //mostrando as imagens na tela
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
        stop();
    }
}