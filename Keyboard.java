import java.awt.event.KeyEvent;  // Required for handling key events
import java.awt.event.KeyListener;  // Required for implementing key listener

public class Keyboard implements KeyListener {

    private static Keyboard instance;
    private boolean[] keys;
    
    private Keyboard() {
        keys = new boolean[256];
    }

    /**
     * Returns the singleton instance of the Keyboard class.
     *
     * @return the singleton instance of Keyboard
     */
    public static Keyboard getInstance() {

        if (instance == null) {
            instance = new Keyboard();
        }
        
        return instance;
    }

    /**
     * Called when a key is pressed.
     *
     * @param e the KeyEvent representing the key press event
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }
    
    /**
     * Called when a key is released.
     *
     * @param e the KeyEvent representing the key release event
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }

    /**
     * Not used in this implementation.
     *
     * @param e the KeyEvent representing the key typed event
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * Returns the current state of the specified key.
     *
     * @param key the integer value representing the key code
     * @return true if the key is currently pressed, false otherwise
     */
    public boolean isDown(int key) {

        if (key >= 0 && key < keys.length) {
            return keys[key];
        }
        
        return false;
    }
}
