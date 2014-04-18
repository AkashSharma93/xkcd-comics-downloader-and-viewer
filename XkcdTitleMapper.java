import java.io.*;

public class XkcdTitleMapper {
	public static void main(String[] args) {
		new XkcdTitleMapper().mapTitle();
	}

	public void mapTitle() {
		int i = 1;

		try {
			File titleFile = new File("xkcdTitles.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(titleFile)));
			String title = "";

			while((title = reader.readLine()) != null) {
				if(i == 100 || i == 404 || i == 1037) {
					System.out.println("Skipping " + Integer.toString(i));
					i++;
				}

				File file = new File("./Comics/" + Integer.toString(i) + ".comic");
				if(!file.exists())
					new Thread(new Mapper(title, i)).start();
				else
					System.out.println(i);

				i++;
			}
		} catch(Exception ex) {}
	}

	public class Mapper implements Runnable {
		String title, imgFilename, comicFilename;

		public Mapper(String t, int i) {
			title = t;
			imgFilename = "./Images/" + Integer.toString(i) + ".png";
			comicFilename = "./Comics/" + Integer.toString(i) + ".comic";
		}

		public void run() {
			Comic comic = new Comic(title, imgFilename);
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(comicFilename));
				oos.writeObject(comic);
				oos.close();
			} catch(Exception ex) {}

			System.out.println("Saved " + comicFilename);
		}
	}
}
