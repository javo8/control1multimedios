package learn2crack.jsonparsing;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Item {
	
	private String title;
	private String image;
	private String points;
	private String link;
	private Bitmap foto;
	
	public Item() {}

	public Item(String title, String image, String points, String link) {
		this.title = title;
		this.image = image;
		this.points = points;
		this.link = link;
		downloadFile(image);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Bitmap getFoto() {
		return foto;
	}
	
	void downloadFile(String imageHttpAddress) {
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            foto = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
