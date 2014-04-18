import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class XkcdImageDownloader {
	public static void main(String[] args) {
		new XkcdImageDownloader().download();
	}

	public void download() {
		try {
			int i = 1;
			
			File file = new File("xkcdImages.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String link = "";
			while((link = reader.readLine()) != null) {
				if(i == 100 || i == 1037 || i == 404) {
					System.out.println("Skipping " + Integer.toString(i));
					i++;
				}

				File file2 = new File("./Images/" + Integer.toString(i) + ".png");
				
				if(!file2.exists()) 
					new Thread(new Downloader(link, i)).start();
				else
					System.out.println(i);

				i++;
			}
		} catch(Exception ex) {}
	}

	public class Downloader implements Runnable {
		String link, filename;

		public Downloader(String l, int i) {
			link = l;
			filename = "./Images/" + Integer.toString(i) + ".png";
		}

		public void run() {
			BufferedImage image = null;

			
			try {
				URL imgUrl = new URL(link);
				image = ImageIO.read(imgUrl);
				ImageIO.write(image, "png", new File(filename));
			} catch(Exception ex) {}

			System.out.println("Downloaded to " + filename);
		}
	}
}
