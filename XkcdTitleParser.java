import java.io.*;

public class XkcdTitleParser {
	public static void main(String[] args) {
		new XkcdTitleParser().go();
	}

	public void go() {
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream("xkcdTitles.txt"));
			for(int i = 1; i <= 1330; i++) {
				if(i == 100 || i == 404 || i == 1037) {
					System.out.println("Skipping " + Integer.toString(i));
					continue;
				}

				File file = new File("XKCD/" + Integer.toString(i) + ".html");

				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));


				String temp = "";

				while((temp = reader.readLine()) != null) {
					String r = "<title>xkcd: ";
					if(temp.contains(r)) {
						String[] title = temp.split(r);
						title = title[1].split("</title>");

						writer.write(title[0] + "\n");
						writer.flush();
					}
				}

				reader.close();
			}
			
			writer.close();
		} catch(Exception ex) {ex.printStackTrace();}
	}
}
