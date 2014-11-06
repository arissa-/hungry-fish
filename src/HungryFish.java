import java.awt.*;
import java.awt.event.*;
import java.net.URL;
    
  
  
import javax.swing.ImageIcon;
    
    
public class HungryFish{
    
    Image hungryFishLeftImage;
    Image hungryFishRightImage;
    private int xPos; // x position of the hungry fish
    private int yPos; // y position of the hungry fish
    private int dx;
    private int dy;
    private int width; // width of fish
    private int height; // height of fish
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
        
    private ImageIcon hungryFishLeftIcon = new ImageIcon("grayfishleft.png");
    private ImageIcon hungryFishRightIcon = new ImageIcon("grayfishright.png");
        
    public HungryFish() {
        xPos = 100;
        yPos = 100;
        width = 217/2;
        height = 108/2;
        hungryFishLeftImage = hungryFishLeftIcon.getImage();
        hungryFishRightImage = hungryFishRightIcon.getImage();
    }
    
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.drawImage(getImage(), x, y, width, height, null);
    }
      
    public Image getImage(){
        if( dx > 0){
            return hungryFishRightImage;
        }
        else{
            return hungryFishLeftImage;
        }
          
    }
      
    public Rectangle getBounds(){
        return new Rectangle(xPos,yPos,width, height);
    }
      
        
    public void move() {
        yPos += dy;
        xPos += dx;
    }
    
    public void changeDirection(int direction) {
        if (direction == UP)
            dy = -1;
        if (direction == DOWN)
            dy = 1;
        if (direction == RIGHT)
            dx = 1;
        if (direction == LEFT)
            dx = -1;
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
        
    public int getDX(){
        return dx;
    }
    public int getDY() {
        return dy;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void grow(int deltaSize){
        width += deltaSize;
        height += deltaSize;
    }
    public void setXPos(int x) {
        xPos = x;
    }
    public void setYPos(int y) {
        yPos = y;
    }
    public void setDX(int x) {
        dx = x;
    }
    public void setDY(int y) {
        dy = y;
    }
        
        
    
}