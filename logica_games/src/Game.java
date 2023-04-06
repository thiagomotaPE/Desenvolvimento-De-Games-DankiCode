import java.util.ArrayList;

//neste arquivo tem um loop de game basico que pode ser usado como exemplo para outros loops
public class Game implements Runnable{ //implementei a interface runnable para fazer o loop do game

    private boolean isRunning; // variavel que vai ser usada pra dizer se o loop vai parar ou não
    private Thread thread;

    //essa array guarda as entidades do meu game(personagens, objetos...)
    private ArrayList<Entidade> entidades = new ArrayList<>();

    public Game() {
        //adicionei 5 entidades ao meu jogo
        entidades.add(new Entidade());
        entidades.add(new Entidade());
        entidades.add(new Entidade());
        entidades.add(new Entidade());
        entidades.add(new Entidade());

        //este loop mostra quantas entidades existem
        for (int i = 0; i < entidades.size(); i++) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        Game game = new Game(); //criando o game
        game.start(); //iniciando o game

    }

    //metodo que inicia o game
    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    //metodo que vai atualizar o jogo
    public void tick() {
        //atualizar jogo
        //System.out.println("tick");
    }

    //metodo que vai renderizar o jogo(imagens)
    public void render() {
        //renderizar jogo
        //System.out.println("render");
    }
    @Override //a interface runnable tras este metodo run
    public void run() {
        while(isRunning) {
            tick();
            render();
            //como deixar o jogo um pouco mais lendo, neste caso com 60 fps(este não é o melhor jeito de ser feito)
//            try {
//                Thread.sleep(1000/60);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

    }
}