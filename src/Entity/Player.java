
package Entity;

import KeyBoardInPut.KeyBoardInPut;
import Manager.GamePanel;
import Tool.Tool;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyBoardInPut keyBoardInPut = new KeyBoardInPut();
    
    private int energy;
    private int speed;
    
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
       speed = 4;
   }
   
   private void getPlayerImage(){
       try{
            left = ImageIO.read(getClass().getResourceAsStream("/Resource/PlayerImage/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Resource/PlayerImage/right.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
       
       left = scaleImg("left");
       right = scaleImg("right");
       
   }
   private BufferedImage scaleImg(String imageName){
       Tool tool = new Tool();
       BufferedImage image = null ;
       String path = "/Resource/PlayerImage/" + imageName + ".png";
       try{
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = tool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
       }catch(IOException e){
           e.fillInStackTrace();
       }
       return image;
   }
   
   public void update(){
       if(keyBoardInPut.getLeftPressed() == true || keyBoardInPut.getRightPressed() == true || keyBoardInPut.getSpacePressed() == true){
           if(keyBoardInPut.getLeftPressed() == true){
               direction = "left";
               mapX -= speed;
           }
           if(keyBoardInPut.getRightPressed() == true){
               direction = "right";
               mapX += speed;
           }
           if(keyBoardInPut.getSpacePressed() == true){
               if(energy < 60) {
                   energy++;
               }
           }
       }
           if(energy == 60 || keyBoardInPut.getSpacePressed() != true){
               mapY -= energy;
               energy = 0;
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
           case "left":
               image = left;
           case "right":
               image = right;
       }
       g2.drawImage(image, mapX, mapY,  null);
   }
}

