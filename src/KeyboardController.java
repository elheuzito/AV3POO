import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class KeyboardController {
    private Personagem character;
    private boolean moveLeftPressed;
    private boolean moveRightPressed;
    private boolean jumpPressed;

    public KeyboardController(Personagem character, JPanel panel) {
        this.character = character;
        moveLeftPressed = false;
        moveRightPressed = false;
        jumpPressed = false;

        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Configurar Key Bindings para a tecla esquerda (A)
        KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        inputMap.put(leftKey, "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                character.moveLeft();
                moveLeftPressed = true;
            }
        });

        KeyStroke leftReleaseKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
        inputMap.put(leftReleaseKey, "stopMoveLeft");
        actionMap.put("stopMoveLeft", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!moveRightPressed) {
                    character.stopMoving();
                }
                moveLeftPressed = false;
            }
        });

        // Configurar Key Bindings para a tecla direita (D)
        KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
        inputMap.put(rightKey, "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                character.moveRight();
                moveRightPressed = true;
            }
        });

        KeyStroke rightReleaseKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
        inputMap.put(rightReleaseKey, "stopMoveRight");
        actionMap.put("stopMoveRight", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!moveLeftPressed) {
                    character.stopMoving();
                }
                moveRightPressed = false;
            }
        });

        // Configurar Key Bindings para a tecla espaço
        KeyStroke spaceKey = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false);
        inputMap.put(spaceKey, "jump");
        actionMap.put("jump", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                character.jump();
                jumpPressed = true;
            }
        });

        KeyStroke spaceReleaseKey = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true);
        inputMap.put(spaceReleaseKey, "stopJump");
        actionMap.put("stopJump", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (jumpPressed) {
                    character.stopJump();
                }
                jumpPressed = false;
            }
        });
    }
}
