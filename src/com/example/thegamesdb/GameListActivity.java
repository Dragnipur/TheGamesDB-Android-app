package com.example.thegamesdb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class GameListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		
		//get the search query.
		Intent intent = getIntent();
		String searchQuery = intent.getStringExtra("searchQuery");
		
		//retrieve a list of games.
		DataHandler dataHandler = new DataHandler();
		
		//test
		TextView test = (TextView) findViewById(R.id.test);
		test.setText(searchQuery);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

}
