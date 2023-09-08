
package Entity;

import KeyBoardInPut.KeyBoardInPut;
import Manager.GamePanel;
import Tool.Tool;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    
    private int energy;
    private int jumpSpeed;
    private BufferedImage lastImage;
    private boolean isJumping;
    
    public Player (GamePanel gamePanel, KeyBoardInPut keyBoardInPut){
        this.gamePanel = gamePanel;
        this.keyBoardInPut = keyBoardInPut;
        setDefaultValue();
        getPlayerImage();
        
    }   
   private void setDefaultValue(){
       mapX = 10 * gamePanel.tileSize;
       mapY = 10 * gamePanel.tileSize;
       direction = "right";
       energy = 0;
       entityWalkSpeed = 2;
       jumpSpeed = 3;
       isJumping = false;
   }
   
   private void getPlayerImage(){
       try{
            left = ImageIO.read(getClass().getResourceAsStream("/Res/PlayerImage/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Res/PlayerImage/right.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
       
       left = scaleImg("left");
       right = scaleImg("right");
       
   }
   private BufferedImage scaleImg(String imageName){
       BufferedImage image = null ;
       String path = "/Res/PlayerImage/" + imageName + ".png";
       try{
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = Tool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
       }catch(IOException e){
           e.fillInStackTrace();
       }
       return image;
   }
   
   public void update(){
       if(isJumping == false){
           if(keyBoardInPut.getSpacePressed() == true){
           if(energy < 60) {
                   energy++;
               }else{
               isJumping = true;
           }
            }else {
                if(keyBoardInPut.getLeftPressed() == true){
                   direction = "left";
                   mapX -= entityWalkSpeed;
               }
               if(keyBoardInPut.getRightPressed() == true){
                   direction = "right";
                   mapX += entityWalkSpeed;
               }
               if(keyBoardInPut.getUpPressed() == true){
                   direction = "up";
               }
           }
       }
           if(energy > 0 && keyBoardInPut.getSpacePressed() == false){
               isJumping = true;
           }
           if(isJumping == true ){
               switch(direction){
                   case "left":
                       mapX -= jumpSpeed;
                       break;
                   case "right":
                       mapX += entityWalkSpeed;
                       break;
               }
                   mapY -= jumpSpeed;
                   energy -= 1.5;
                   if(energy < 0){
                       energy = 0;
                       isJumping = false;
                   }
           }
       
        spriteCounter++;
            if(spriteCounter > 12 ){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        System.out.println(energy);
   }
   
   public void draw(Graphics g2){
       BufferedImage image = null;
       switch(direction){
           case "up":
               image = lastImage;
               break;
           case "left":
               image = left;
               lastImage = left;
               break;
           case "right":
               image = right;
               lastImage = right;
               break;
       }
       
            g2.drawImage(image, mapX, mapY,  null);
      
   }
   private boolean isGrounded(){
       int bns=1231221;
       return true;
   }
}

