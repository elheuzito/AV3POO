import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class ProjectileController implements ActionListener {
    private List<Projectile> projectiles;
    private Random random;
    private Personagem character;

    public ProjectileController(List<Projectile> projectiles, Personagem character) {
        this.projectiles = projectiles;
        random = new Random();
        this.character = character;


        Timer timer = new Timer(5000, this); // 10 segundos em milissegundos
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawnRandomProjectile();
        spawnRandomProjectile();
        spawnRandomProjectile();
    }

    public void spawnRandomProjectile() {
        int startX, startY;
        int screenWidth = 800; // Largura da tela
        int screenHeight = 600; // Altura da tela

        int side = random.nextInt(3); // Gere um número aleatório de 0 a 3 para escolher o lado

        switch (side) {
            case 0: // Superior
                startX = random.nextInt(screenWidth);
                startY = 0;
                break;
            case 1: // Esquerda
                startX = 0;
                startY = random.nextInt(screenHeight);
                break;
            case 2: // Direita
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
        int speedX = 6; // Velocidade em direção ao eixo X
        int speedY = 6; // Velocidade em direção ao eixo Y

        Projectile projectile = new Projectile(startX, startY, speedX, speedY, Color.RED);
        projectiles.add(projectile);
    }
}
