package com.example.thegamesdb;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class GameListActivity extends Activity {
	private FragmentManager fragmentManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		
		//get the search query.
		Intent intent = getIntent();
		String searchQuery = intent.getStringExtra("searchQuery");
		
		//retrieve a list of games.
		DataHandler dataHandler = new DataHandler();
		fragmentManager = getFragmentManager();
		
		addList();
		addList();
		addList();
		addList();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}
	
	private void addList() {
        GameListFragment fragment = new GameListFragment();
        
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        fragTransaction.add(R.id.gameListLayout, fragment);
        fragTransaction.commit();
	}

}
