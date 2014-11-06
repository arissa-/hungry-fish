import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
    
  
import javax.swing.ImageIcon;
        
        
        
public class EdibleFish{
            
    Image edibleFishLeftImage;
    Image edibleFishRightImage;
  
    private int xPos;
    private int yPos;
    private int dx;
    private int dy;
    private int width = 1486/20;
    private int height = 538/20;
    private ImageIcon edibleFishLeftIcon = new ImageIcon("greenfishleft.png");
    private ImageIcon edibleFishRightIcon = new ImageIcon("greenfishright.png");
  
            
    public EdibleFish(int xPos, int yPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        edibleFishLeftImage = edibleFishLeftIcon.getImage();
        edibleFishRightImage = edibleFishRightIcon.getImage();
  
    }
            
    public void draw(Graphics g) {
        if (dx < 0){
            g.drawImage(edibleFishLeftImage, xPos, yPos, width, height, null);
        }
          
        else {
            g.drawImage(edibleFishRightImage, xPos, yPos, width, height, null);
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(xPos,yPos,width, height);
    }
      
            
    public void move(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
        xPos += dx;
        yPos += dy;
    }
            
         
    public int getSize() {
        return width * height;
    }
    
            
    public int getXPos() {
        return xPos;
    }
          
    public int getYPos(){
        return yPos;
    }    
   
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void setXPos(int x) {
        xPos = x;
    }
    public void setYPos(int y) {
        yPos = y;
    }
   
            
            
            
}