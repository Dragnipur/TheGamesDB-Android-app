package dragni.tgb.thegamesdb.views;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.thegamesdb.R;

import dragni.tgb.thegamesdb.logic.GameSearcher;

public class GameListActivity extends Activity {
	private GameSearcher gameManager;
	public static String[][] gamesList;
	
	private static String baseUrl = "http://thegamesdb.net/api/";
	private static String getGameUrl = "GetGame.php?id=";
	private static String searchGamesUrl = "GetGame.php?name=";
	
	private ListView gameListView;
	private ListAdapter gameListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		
		//get the search query.
		Intent intent = getIntent();
		String searchQuery = intent.getStringExtra("searchQuery");
		
		//setup the manager and send the search request.
		gameManager = new GameSearcher();	
		sendSearchRequest(searchQuery);
	}
	
	
	private void sendSearchRequest(String searchQuery) {
		try {
			URL url = new URL(baseUrl + searchGamesUrl + searchQuery);
			DownloadGamesList asyncDownload = new DownloadGamesList();
			asyncDownload.execute(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void showGamesList(String[][] gamesList) {
		gameListView = (ListView) findViewById(R.id.gameListLayout);
		gameListAdapter = new ListAdapter(this, gamesList);
        gameListView.setAdapter(gameListAdapter);
        
        // Click event for single list row
        gameListView.setOnItemClickListener(new OnItemClickListener() {
 
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				// onclick thingy
			}
        });	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}
	  
	 private class DownloadGamesList extends AsyncTask<URL, Integer, String[][]> {
	     protected String[][] doInBackground(URL... url) {
	    	 
	    	 try {
				gameManager.parseXml(url[0]);
				String[][] searchResults = gameManager.getSearchResults();
				return searchResults;
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      return null;
	     }
	     
	     protected void onProgressUpdate(Integer... progress) {
	    	 //show progress or something.
	     }

	     protected void onPostExecute(String[][] gamesList) {
	    	 showGamesList(gamesList);
	     }
	 }
}
