package dragni.tgb.thegamesdb.views;

import com.example.thegamesdb.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameOverviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_overview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_overview, menu);
		return true;
	}

}
