import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Field extends JPanel {
	private boolean paused;
	private boolean pausedLittle;
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	private Timer repaintTimer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent ev) { repaint();}
		});
	
	public Field() {
		setBackground(Color.WHITE);
		repaintTimer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		for (BouncingBall ball: balls)
			ball.paint(canvas);
	}
	public void addBall() {
		balls.add(new BouncingBall(this));
	}
	public synchronized void pause() {
		paused = true;
	}
	public synchronized void pauseLittleRounds() {
		pausedLittle = true;
	}
	public synchronized void resume() {
		paused = false;
		pausedLittle = false;
		notifyAll();
	}
	public synchronized void canMove(BouncingBall ball) throws InterruptedException {
		if (paused)
			wait();
		if (pausedLittle && ball.GetRadius() <= 10)
			wait();
	}
}