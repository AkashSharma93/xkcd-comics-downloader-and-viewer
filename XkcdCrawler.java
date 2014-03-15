import java.io.*;
import java.net.URL;

public class XkcdCrawler {

	public static void main(String[] args) {
		new XkcdCrawler().go();
	}

	public void go() {
		int index = 0;
				
		try {
			while(index < 1330) {
				new Thread(new Crawler(index)).start();
				index++;
			}

		} catch(Exception ex) {}

	}

	public class Crawler implements Runnable {
		int index;

		public Crawler(int i) {
			index = i;	
		}

		public void run() {
			try {
				String link = "http://xkcd.com/" + Integer.toString(index);
				File file = new File("XKCD/" + Integer.toString(index) + ".html");

				if(file.exists())
					return;

				URL xkcd = new URL(link);

				BufferedReader reader = new BufferedReader(new InputStreamReader(xkcd.openStream()));
				PrintWriter writer = new PrintWriter(new FileOutputStream(file));

				String str = "";
	
				while((str = reader.readLine()) != null) {
					writer.write(str + "\n");
					writer.flush();
				}

			} catch(Exception ex) {}

			System.out.println("Saved " + Integer.toString(index));
		}
	}
}
