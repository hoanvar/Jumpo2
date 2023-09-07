package KeyBoardInPut;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInPut implements KeyListener{
    private boolean spacePressed, leftPressed, rightPressed, upPressed;
    
    public boolean getSpacePressed(){
        return spacePressed;
    }
    public boolean getLeftPressed(){
        return leftPressed;
    }
    public boolean getRightPressed(){
        return rightPressed;
    }
    public boolean getUpPressed(){
        return upPressed;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
    }
    
}
