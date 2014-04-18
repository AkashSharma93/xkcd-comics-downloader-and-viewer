import java.io.*;

public class XkcdImageParser {
	public static void main(String[] args) {
		new XkcdImageParser().go();
	}

	public void go() {
		
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream("xkcdImages.txt"));
	
			for(int i = 1; i <= 1330; i++) {
				if(i == 100 || i == 404 || i == 1037) {
					System.out.println("Skipping " + Integer.toString(i));
					continue;
				}

				File file = new File("XKCD/" + Integer.toString(i) + ".html");

				String temp = "";

				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

				while((temp = reader.readLine()) != null) {
					String r = "hotlink";
				
					if(temp.contains(r) && i != 1037) {
						r = r + ".*\\s";
						String[] image = temp.split(r);

						writer.write(image[1] + "\n");
						writer.flush();
					}
				}

				reader.close();
			}

			writer.close();
		} catch(Exception ex) {ex.printStackTrace();}
	}
}
