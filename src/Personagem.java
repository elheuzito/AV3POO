import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Personagem {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private boolean jumping;
    private Image characterImage;
    private Image groundImage;

    public Personagem(int initialX, int initialY) {
        x = initialX;
        y = initialY;
        xVelocity = 0;
        yVelocity = 0;
        jumping = false;
        try {
            characterImage = ImageIO.read(new File("C:\\Users\\PICHAU\\IdeaProjects\\ProjetoAV3LUCAS\\src\\personagem.png"));
            groundImage = ImageIO.read(new File("C:\\Users\\PICHAU\\IdeaProjects\\ProjetoAV3LUCAS\\src\\tiles10050.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getGroundImage() {
        return groundImage;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;

        if (y < 425) {
            yVelocity += 1;
        } else {
            y = 425;
            yVelocity = 0;
            jumping = false;
        }
        if (x < 0 ){
            x = 0;
            xVelocity = 0;
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void moveLeft() {
        xVelocity = -5;
    }

    public void moveRight() {
        xVelocity = 5;
    }

    public void jump() {
        if (!jumping) {
            yVelocity = -15;
            jumping = true;
        }
    }

    public void stopMoving() {
        xVelocity = 0;
    }

    public void stopJump() {
        if (yVelocity < -8) {
            yVelocity = -8; // Define uma velocidade vertical mínima
        }
    }

    public void draw(Graphics g) {
        g.drawImage(characterImage, x, y, 128,128,null);
    }
}

