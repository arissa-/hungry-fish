import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
  
import javax.swing.*;
import javax.swing.Timer;
  
public class EatingFishPanel extends JPanel implements ActionListener  {
  
    ArrayList<EdibleFish> listOfEdibleFish = new ArrayList<EdibleFish>();
    ArrayList<Integer> listOfEdibleFishXSpeeds = new ArrayList<>();
    ArrayList<Integer> listOfEdibleFishYSpeeds = new ArrayList<>();
    HungryFish userFish = new HungryFish();
    private Timer timer;
    int numFish = 10;
    int newXPos;
    int panelWidth = 700;
    int panelHeight = 600;
    boolean isPaused = true;
    boolean gameHasStarted;
    boolean gameOver, youWin;
  
    public EatingFishPanel() {
        Color backgroundColor = new Color(175, 238, 238);
          
        this.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent evt) {
                 isPaused = false;
                 gameHasStarted = false;
                  
                 if (gameOver) {
                    numFish = 10;
                    listOfEdibleFish.clear();
                    listOfEdibleFishXSpeeds.clear();
                    listOfEdibleFishYSpeeds.clear();
                          
                        for (int i = 0; i < numFish; i++) {
                            int width = (int) (Math.random() * 150) + 30;
                            int edibleFishXPos;
                            int edibleFishYPos;
                            int edibleFishXSpeed;
                            int edibleFishYSpeed;
  
                            do {
                                edibleFishXPos = (int) (Math.random() * panelWidth);
                            } while ((Math.abs(userFish.getXPos() - edibleFishXPos) <= 50));
  
                            do {
                                edibleFishYPos = (int) (Math.random() * panelHeight);
                            } while ((Math.abs(userFish.getYPos() - edibleFishYPos) <= 50));
  
                            listOfEdibleFish.add(new EdibleFish(edibleFishXPos, edibleFishYPos,
                                    width, (int) (width / 2.5)));
  
                            do {
                                edibleFishXSpeed = (int) (Math.random() * 4) - 2;
                                edibleFishYSpeed = (int) (Math.random() * 4) - 2;
                            } while (edibleFishXSpeed == 0 || edibleFishYSpeed == 0);
  
                            listOfEdibleFishXSpeeds.add(edibleFishXSpeed);
                            listOfEdibleFishYSpeeds.add(edibleFishYSpeed);
                        }
                 }
            }
        });
        for (int i = 0; i < numFish; i++) {
            int width = (int) (Math.random() * 250) + 30;
            int edibleFishXPos;
            int edibleFishYPos;
            int edibleFishXSpeed;
            int edibleFishYSpeed;
  
            do {
                edibleFishXPos = (int) (Math.random() * panelWidth);
            } while ((Math.abs(userFish.getXPos() - edibleFishXPos) <= 50));
  
            do {
                edibleFishYPos = (int) (Math.random() * panelHeight);
            } while ((Math.abs(userFish.getYPos() - edibleFishYPos) <= 50));
  
            listOfEdibleFish.add(new EdibleFish(edibleFishXPos, edibleFishYPos,
                    width, (int) (width / 2.5)));
  
            do {
                edibleFishXSpeed = (int) (Math.random() * 4) - 2;
                edibleFishYSpeed = (int) (Math.random() * 4) - 2;
            } while (edibleFishXSpeed == 0 || edibleFishYSpeed == 0);
  
            listOfEdibleFishXSpeeds.add(edibleFishXSpeed);
            listOfEdibleFishYSpeeds.add(edibleFishYSpeed);
        }
        this.setBackground(backgroundColor);
        this.addKeyListener(new KeyListener());
          
        setFocusable(true);
        timer = new Timer(10, this);
        timer.start();
  
    }
  
  
    public void paintComponent(Graphics g) {
        if (!gameHasStarted){
            intro(g);
            repaint();
        }
        if (!isPaused) {
            gameHasStarted = true;
            super.paintComponent(g);
            for (int i = 0; i < numFish; i++) {
                listOfEdibleFish.get(i).draw(g);
                EdibleFish edibleFish = listOfEdibleFish.get(i);
                int edibleFishSize = edibleFish.getSize();
                Rectangle edibleFishBounds = edibleFish.getBounds();
                Rectangle userFishBounds = userFish.getBounds();
  
                try {
                    edibleFish.move(listOfEdibleFishXSpeeds.get(i),
                            listOfEdibleFishYSpeeds.get(i));
                } catch (IndexOutOfBoundsException e) {
                }
  
                if (edibleFish.getXPos() > panelWidth || edibleFish.getXPos() < (-edibleFish.getWidth())
                        || edibleFish.getYPos() > panelHeight || edibleFish.getYPos() < (-edibleFish.getHeight())) {
  
                    newXPos = (int) (Math.random() * 2);
                    if (newXPos == 0) {
                        edibleFish.setXPos(0);
                        listOfEdibleFishXSpeeds.set(i, Math.abs(listOfEdibleFishXSpeeds.get(i)));
                    } else {
                        edibleFish.setXPos(panelWidth);
                        listOfEdibleFishXSpeeds.set(i,(-1)* Math.abs(listOfEdibleFishXSpeeds.get(i)));
                    }
                    edibleFish.setYPos((int) (Math.random() * panelHeight));
                }
  
                if (edibleFishBounds.intersects(userFishBounds)
                        && edibleFishSize < userFish.getSize()) {
                    eat(i);
                    userFish.grow(6);
                }
                if (edibleFishBounds.intersects(userFishBounds)
                        && edibleFishSize > userFish.getSize()) {
                    gameOver(g);
                      
                    break;
                }
            }
              
              
            userFish.draw(g, userFish.getXPos(), userFish.getYPos(),
                    userFish.getWidth(), userFish.getHeight());
            if(userFish.getXPos() > panelWidth || userFish.getXPos() < 0
                    || userFish.getYPos() > panelHeight || userFish.getYPos() < 0){
                userFish.setDX(0);
                userFish.setDY(0);
            }
                  
            if(userFish.getWidth() > panelWidth/2){
                userWins(g);
            }
              
            if (numFish < 10) {
                  
                for (; numFish <= 10; numFish++) {
                    int width = (int) (Math.random() * 150) + 30;
                    int randNumX;
                    int randNumY;
  
                    do {
                        randNumX = (int) (Math.random() * panelWidth);
                    } while ((Math.abs(userFish.getXPos() - randNumX) <= 50));
  
                    do {
                        randNumY = (int) (Math.random() * panelHeight);
                    } while ((Math.abs(userFish.getYPos() - randNumY) <= 50));
  
                    listOfEdibleFish.add(new EdibleFish(randNumX, randNumY,
                            width, (int) (width / 2.5)));
                }
            }
        }
    }
  
    public void intro(Graphics g){
      
            g.setColor(new Color(0,0,0));
            g.setFont(new Font("Sans Serif", Font.BOLD, 40));
            g.drawString("Welcome to the Hungry Fish Game!", panelWidth/2 -340, 200);
            g.setFont(new Font("Sans Serif", Font.PLAIN, 25));
            g.drawString("You are the hungry gray fish.", panelWidth/2 - 180, 250);
            g.drawString("Your goal is to eat green fishes until you are full.", panelWidth/2 -290, 295);
            g.drawString("You can only eat fish that are smaller than you.", panelWidth/2 -290, 320);
            g.drawString("If you touch a larger fish you will be eaten.", panelWidth/2 -260, 345);
            g.drawString("Use the arrow keys to control your fish.", panelWidth/2 -245, 370);
            g.drawString("Click anywhere to begin.", panelWidth/2 -150, 500);
          
  
    }
      
    public void userWins(Graphics g) {
        g.setFont(new Font("Sans Serif", Font.BOLD, 100));
        g.drawString("You win!", panelWidth/2 - 350, panelHeight/2);
        userFish.setDX(0);
        userFish.setDY(0);
  
        isPaused = true;
        youWin = true;
        gameOver = true;
    }
    public void gameOver(Graphics g) {
        g.setFont(new Font("Sans Serif", Font.BOLD, 100));
        g.drawString("You got eaten!", panelWidth/2 - 350, panelHeight/2);
        g.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        g.drawString("Click to play again.", panelWidth/2 - 350, panelHeight/2 + 200);
        userFish.setDX(0);
        userFish.setDY(0);
        isPaused = true;
        gameOver = true;
    }
  
    public void eat(int i) {
        listOfEdibleFish.remove(i);
        numFish--;
  
    }
  
    public void actionPerformed(ActionEvent evt) {
        userFish.move();
        repaint();
          
    }
  
    public class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent evt) {
            int keyCode = evt.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT) {
                userFish.setDX(-3);
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                userFish.setDX(3);
            }
            if (keyCode == KeyEvent.VK_UP) {
                userFish.setDY(-3);
            }
            if (keyCode == KeyEvent.VK_DOWN) {
                userFish.setDY(3);
            }
              
          
        }
  
        public void keyReleased(KeyEvent evt) {
            int key = evt.getKeyCode();
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
                userFish.setDX(0);
  
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)
                userFish.setDY(0);
        }
  
    }
}