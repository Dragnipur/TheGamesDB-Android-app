package dragni.tgb.thegamesdb.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.example.thegamesdb.R;
import com.squareup.picasso.Picasso;

import dragni.tgb.thegamesdb.util.UrlMaker;

public class ImageZoomActivity extends Activity {
	private UrlMaker urlMaker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_zoom);
		urlMaker = new UrlMaker();
		
		setImage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.image_zoom, menu);
		return true;
	}
	
	private void setImage() {
		Intent i = getIntent();
		String imageUrl = i.getStringExtra("imageUrl");
		String url = urlMaker.getGameImageUrl(imageUrl);
		
		ImageView imageView = (ImageView) findViewById(R.id.zoomedImageView);
		Picasso.with(this).load(url).into(imageView);
	}
}
