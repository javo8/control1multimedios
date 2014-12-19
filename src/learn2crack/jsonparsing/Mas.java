package learn2crack.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class Mas extends Activity {
	private ImageView imageView;
    private Bitmap loadedImage;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mas);
		
		imageView = (ImageView) findViewById(R.id.image_view);
		
		Intent intent = getIntent();
		loadedImage = (Bitmap) intent.getParcelableExtra("BitmapImage");
		
		imageView.setImageBitmap(loadedImage);
	}
}
