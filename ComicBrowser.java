import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class ComicBrowser {
	JFrame frame;
	JPanel titlePanel;
	JLabel image, title;
	JScrollPane scroller;
	Comic comic;
	int index = 1;

	public static void main(String[] args) {
		new ComicBrowser().go();
	}

	public void go() {
		comic = getComic(index);
		
		frame = new JFrame("XKCD - " + index);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.addKeyListener(new FrameListener());

		draw(index);

		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void draw(int index) {
		titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		titlePanel.add(Box.createHorizontalGlue());
		Font font = new Font("SansSerif", Font.BOLD, 20);
		title = new JLabel(comic.getTitle());
		title.setFont(font);
		titlePanel.add(title);
		titlePanel.add(Box.createHorizontalGlue());

		image = new JLabel(new ImageIcon(comic.getImgFilename()));
		scroller = new JScrollPane(image);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		frame.getContentPane().add(scroller, BorderLayout.CENTER);

		frame.repaint();
	}

	public Comic getComic(int index) {
		Comic c = null;

		try {
			File file = new File("./Comics/" + Integer.toString(index) + ".comic");
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			c = (Comic) ois.readObject();
			ois.close();
		} catch(Exception ex) {}

		return c;
	}

	public class FrameListener implements KeyListener {
		public void keyReleased(KeyEvent ev) {
			if(ev.getKeyCode() == KeyEvent.VK_LEFT) {
				if(index != 1)
					index--;
			}

			if(ev.getKeyCode() == KeyEvent.VK_RIGHT) {
				index++;
				index = index % 1325;
				if(index == 0)
					index++;
			}

			comic = getComic(index);
			frame.setTitle("XKCD - " + Integer.toString(index));
			title.setText(comic.getTitle());
			image.setIcon(new ImageIcon(comic.getImgFilename())); 
			frame.repaint();
		}

		public void keyPressed(KeyEvent ev) {}

		public void keyTyped(KeyEvent ev) {}
	}
}
