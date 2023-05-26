import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private JMenuItem pauseMenuItem;
	private JMenuItem pauseMenuItem2;
	private JMenuItem resumeMenuItem;
	private Field field = new Field();
	
	public MainFrame() {
		super("Программирование и синхронизация потоков");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
		setExtendedState(MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu ballMenu = new JMenu("Мячи");
		Action addBallAction = new AbstractAction("Добавить мяч") {
			public void actionPerformed(ActionEvent event) {
				field.addBall();
				if (!pauseMenuItem.isEnabled() && !pauseMenuItem2.isEnabled()&& !resumeMenuItem.isEnabled())
					{
						pauseMenuItem.setEnabled(true);
						pauseMenuItem2.setEnabled(true);
					}
			}
		};
		menuBar.add(ballMenu);
		ballMenu.add(addBallAction);
		JMenu controlMenu = new JMenu("Управление");
		menuBar.add(controlMenu);
		
		Action pauseActionLittleRounds = new AbstractAction("Приостановить движение шаров малого радиуса"){
			public void actionPerformed(ActionEvent event) {
				field.pauseLittleRounds();
				pauseMenuItem2.setEnabled(false);
				resumeMenuItem.setEnabled(true);
			}
		};
		pauseMenuItem2 = controlMenu.add(pauseActionLittleRounds);
		pauseMenuItem2.setEnabled(false);
		
		Action pauseAction = new AbstractAction("Приостановить движение"){
			public void actionPerformed(ActionEvent event) {
				field.pause();
				pauseMenuItem.setEnabled(false);
				pauseMenuItem2.setEnabled(false);
				resumeMenuItem.setEnabled(true);
			}
		};
		pauseMenuItem = controlMenu.add(pauseAction);
		pauseMenuItem.setEnabled(false);
		
		Action resumeAction = new AbstractAction("Возобновить движение") {
			public void actionPerformed(ActionEvent event) {
				field.resume();
				pauseMenuItem.setEnabled(true);
				pauseMenuItem2.setEnabled(true);
				resumeMenuItem.setEnabled(false);
			}
		};
		resumeMenuItem = controlMenu.add(resumeAction);
		resumeMenuItem.setEnabled(false);
		getContentPane().add(field, BorderLayout.CENTER);
	}
	
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}