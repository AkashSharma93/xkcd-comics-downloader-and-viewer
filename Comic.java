import java.io.*;

public class Comic implements Serializable {
	String title, imgFilename;

	public Comic(String t, String f) {
		title = t;
		imgFilename = f;
	}

	public String getTitle() {
		return title;
	}

	public String getImgFilename() {
		return imgFilename;
	}
}
