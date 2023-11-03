import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Nivel {

        private int numeroNivel;
        private Image background;
        private Image background2;
        private Image background3;

        private Image floor_tiles;

        public Nivel(int numeroNivel) {
            this.numeroNivel = numeroNivel;
            try {
                background = ImageIO.read(new File("C:\\Users\\PICHAU\\Pictures\\background_layer_1.png"));
                background2 = ImageIO.read(new File("C:\\Users\\PICHAU\\Pictures\\background_layer_2.png"));
                background3 = ImageIO.read(new File("C:\\Users\\PICHAU\\Pictures\\background_layer_3.png"));
                floor_tiles = ImageIO.read(new File("C:\\Users\\PICHAU\\Pictures\\tiles_teste_wood.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void desenharNivel(Graphics g) {

            g.drawImage(background, 0 , 0 , 800, 600, null);
            g.drawImage(background2, 0 , 0 , 800, 600, null);
            g.drawImage(background3, 0 , 0 , 800, 600, null);


        }
    }

