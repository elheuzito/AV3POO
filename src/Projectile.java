import java.awt.*;

public class Projectile {
    private int x;
    private int y;
    private int speedX; // Velocidade em direção ao eixo X
    private int speedY; // Velocidade em direção ao eixo Y
    private Color color;

    public Projectile(int x, int y, int speedX, int speedY, Color color) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
    }

    public void update() {
        // Atualize a posição do projétil com base na velocidade
        x += speedX;
        y += speedY;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Outros métodos de atualização e movimento...

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 10, 10); // Desenhe um círculo como o projétil
    }
}





