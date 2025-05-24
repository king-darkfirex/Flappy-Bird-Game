import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartMenu extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;

    JFrame frame;
    JButton startButton = new JButton();

    //Draw Bird
    Image birdImg;
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 68;
    int birdHeight = 48;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    StartMenu(JFrame frame){
        this.frame = frame;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        setLayout(null);
        //addKeyListener(this);

        //load background image
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        
        int buttonWidth = 150;
        int buttonHeight = 60;
        int buttonX = (boardWidth - buttonWidth) / 2;
        int buttonY = (boardHeight / 2) + 10;

        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.setText("START");
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.setBackground(Color.ORANGE);
        startButton.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.HANGING_BASELINE | Font.ITALIC, 20));
        startButton.setForeground(Color.WHITE);

        addKeyListener(this);

        startButton.setRolloverEnabled(false); 

        add(startButton);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, this.boardWidth, this.boardHeight, null);
        g.setColor(Color.YELLOW);
        Font font = new Font("Segoe UI Emoji", Font.BOLD, 50);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth("FLAPPY BIRD")) / 2;
        int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent() - 120;
        g.drawString("FLAPPY BIRD", x, y);

        int BirdY = y + 20;
        int BirdX = (getWidth() - birdWidth) / 2;
        g.drawImage(birdImg, BirdX,BirdY, birdWidth, birdHeight, null);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == startButton){
            frame.getContentPane().remove(this);

            FlappyBird flappyBird = new FlappyBird();
            frame.add(flappyBird);
            frame.pack();
            frame.revalidate();
            frame.repaint();
        
            flappyBird.requestFocusInWindow();

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
            ActionEvent ae = new ActionEvent(startButton, ActionEvent.ACTION_PERFORMED, 
            startButton.getActionCommand());
            actionPerformed(ae);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}  
}
