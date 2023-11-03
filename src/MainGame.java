import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List; // Importe a classe java.util.List
import java.util.Random;


public class MainGame extends JPanel implements ActionListener {
    private Personagem character;
    private List<Projectile> projectiles;
    private KeyboardController keyboardController;
    private ArrayList<Nivel> niveis;
    private int nivelAtual;
    private Timer projectileTimer;
    private Random random;
    private boolean moveLeftPressed;
    private boolean moveRightPressed;
    private boolean jumpPressed;


    public MainGame() {
        character = new Personagem(50, 250);
        random = new Random();
        projectiles = new ArrayList<>();
        keyboardController = new KeyboardController(character, this);
        niveis = new ArrayList<Nivel>();
        niveis.add(new Nivel(1)); // Crie 3 níveis
        niveis.add(new Nivel(2));
        niveis.add(new Nivel(3));
        nivelAtual = 0; // Comece no primeiro nível
        // Configurar o JFrame
        JFrame frame = new JFrame("Jogo do Personagem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);
        frame.setVisible(true);
        ProjectileController projectileController = new ProjectileController(projectiles, character);

        // Iniciar o loop do jogo com um Timer de atualização mais rápido
        Timer timer = new Timer(10, this); // Usando 16 milissegundos para atualização (aproximadamente 60 FPS)
        timer.start();
    }
    public void spawnRandomProjectile() {
        int startX, startY;
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        int side = random.nextInt(4); // Gere um número aleatório de 0 a 3 para escolher o lado

        switch (side) {
            case 0: // Superior
                startX = random.nextInt(screenWidth);
                startY = 0;
                break;
            case 1: // Inferior
                startX = random.nextInt(screenWidth);
                startY = screenHeight;
                break;
            case 2: // Esquerda
                startX = 0;
                startY = random.nextInt(screenHeight);
                break;
            case 3: // Direita
                startX = screenWidth;
                startY = random.nextInt(screenHeight);
                break;
            default:
                startX = 0;
                startY = 0;
        }

        // Direcione o projétil para o centro do personagem
        int deltaX = character.getX() - startX;
        int deltaY = character.getY() - startY;
        double angle = Math.atan2(deltaY, deltaX);
        int speedX = 5; // Velocidade em direção ao eixo X
        int speedY = 5; // Velocidade em direção ao eixo Y

        Projectile projectile = new Projectile(startX, startY, speedX, speedY, Color.RED);
        projectiles.add(projectile);
    }
    public void actionPerformed(ActionEvent e) {
        for (Projectile projectile : projectiles) {
            projectile.update();
        }
        character.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Nivel nivel = niveis.get(nivelAtual);
        nivel.desenharNivel(g);

        character.draw(g);
        int groundWidth = character.getGroundImage().getWidth(this);
        int screenWidth = getWidth();

        for (Projectile projectile : projectiles) {
            g.setColor(projectile.getColor());
            g.fillOval(projectile.getX(), projectile.getY(), 20, 20); // Ajuste o tamanho e estilo conforme necessário
        }

        int numCopies = (screenWidth / groundWidth) + 2;

        for (int i = 0; i < numCopies; i++) {
            int x = i * groundWidth;
            g.drawImage(character.getGroundImage(), x, getHeight() - character.getGroundImage().getHeight(this), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGame();
            }
        });
    }
}