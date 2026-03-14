import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	private int lengthOfSnake = 3;

	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;

	private int move = 0;
	private int score = 0;
	private boolean gameOver = false;

	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625,650,675,700,725,750,775,800,825,850};

	private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,
			600,625};

	private Random random = new Random();
	private int enemyX,enemyY;
	//private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
	private ImageIcon snaketitle = new ImageIcon("snaketitle.jpg");
	private ImageIcon leftmouth = new ImageIcon("leftmouth.png");
	private ImageIcon rightmouth = new ImageIcon("rightmouth.png");
	private ImageIcon upmouth = new ImageIcon("upmouth.png");
	private ImageIcon downmouth = new ImageIcon("downmouth.png");
	private ImageIcon snakeimage = new ImageIcon("snakeimage.png");
	private ImageIcon enemy = new ImageIcon("enemy.png");
	private Timer timer;
	private int delay = 100;

	public GamePanel() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay, this);
		timer.start();

		newEnemy();
	}

	private void newEnemy() {
		enemyX = enemyxpos[random.nextInt(34)];
		enemyY = enemyypos[random.nextInt(23)];

		for (int i = lengthOfSnake-1; i > 0; i--) {
			if (snakexlength[i] == enemyX && snakeylength[i] == enemyY) {
				newEnemy();
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		// border of title image
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);

		snaketitle.paintIcon(this, g, 25, 11);

		// border of gameplay
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);

		if (move == 0) {
			snakexlength[0] = 100;
			snakexlength[1] = 75;
			snakexlength[2] = 50;

			snakeylength[0] = 100;
			snakeylength[1] = 100;
			snakeylength[2] = 100;


		}

		if (left) {
			leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if (right) {
			rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if (up) {
			upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		if (down) {
			downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}

		for (int i = 1; i < lengthOfSnake; i++) {
			snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		}

		enemy.paintIcon(this,g,enemyX,enemyY);

		if (gameOver) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Game Over", 300,300);

			g.setFont(new Font("Arial", Font.PLAIN,20));
			g.drawString("Press SPACE to Start", 320,350);
		}
		g.setColor(Color.GREEN);
		g.setFont(new Font ("Arial", Font.BOLD,14));
		g.drawString("SCORE : " + score, 750,30);
		g.drawString("LENGTH : " + lengthOfSnake, 750,50);
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = lengthOfSnake - 1; i > 0; i--) {
			snakexlength[i] = snakexlength[i - 1];
			snakeylength[i] = snakeylength[i - 1];
		}
		if (left) {
			snakexlength[0] = snakexlength[0] - 25;
		}
		if (right) {
			snakexlength[0] = snakexlength[0] + 25;
		}
		if (up) {
			snakeylength[0] = snakeylength[0] - 25;
		}
		if (down) {
			snakeylength[0] = snakeylength[0] + 25;
		}

		if(snakexlength[0] > 850) {
			snakexlength[0] = 25;
		}
		if (snakexlength[0] < 25) {
			snakexlength[0] = 850;
		}

		if(snakeylength[0] > 625) {
			snakeylength[0] = 75;
		}
		if (snakeylength[0] < 75) {
			snakeylength[0] = 625;
		}
		collidesWithEnemy();
		collidesWithBody();
		repaint();
	}

	private void collidesWithBody() {
		for (int i = lengthOfSnake-1; i > 0; i--) {
			if (snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0]) {
				timer.stop();
				 gameOver = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && (!right)) {
			left = true;
			right = false;
			up = false;
			down = false;
			move++;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT && (!left)) {
			left = false;
			right = true;
			up = false;
			down = false;
			move++;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP && (!down)) {
			left = false;
			right = false;
			up = true;
			down = false;
			move++;
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN && (!up)) {
			left = false;
			right = false;
			up = false;
			down = true;
			move++;
		}


	}

	private void restart() {
		gameOver = false;
		move = 0;
		score = 0;
		lengthOfSnake = 3;
		left = false;
		right = true;
		up = false;
		down = false;
		timer.start();
		repaint();
	}

	private void collidesWithEnemy() {
		if (snakexlength[0] == enemyX && snakeylength[0] == enemyY) {
			newEnemy();
			lengthOfSnake++;
			score++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
