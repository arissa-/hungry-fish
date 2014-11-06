import javax.swing.*;
    
    
public class EatingFishApp {
     public static void main(String[] args) {
            JFrame window = new JFrame("Eating Fish Game");
            window.setContentPane(new EatingFishPanel());
  
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocation(0, 0);
            window.setSize(700,600);
            window.setVisible(true);
        }
              
      
}