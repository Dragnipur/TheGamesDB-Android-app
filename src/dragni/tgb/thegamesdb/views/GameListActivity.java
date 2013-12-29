package dragni.tgb.thegamesdb.views;

import java.io.IOException;
import java.net.URL;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thegamesdb.R;

import dragni.tgb.thegamesdb.entity.GameList;
import dragni.tgb.thegamesdb.logic.GameSearcher;
import dragni.tgb.thegamesdb.util.SearchType;
import dragni.tgb.thegamesdb.util.UrlMaker;

public class GameListActivity extends Activity {
	private GameSearcher gameSearcher;
	private ListView gameListView;
	private ListAdapter gameListAdapter;
	private UrlMaker urlMaker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_layout);

		// get the search query.
		Intent intent = getIntent();
		String searchQuery = intent.getStringExtra("searchQuery");

		// setup the managers and send the search request.
		gameSearcher = new GameSearcher();
		urlMaker = new UrlMaker();
		sendSearchRequest(searchQuery);
	}

	private void sendSearchRequest(String searchQuery) {
		URL searchUrl = urlMaker.getGameByNameUrl(searchQuery);
		DownloadGamesList asyncDownload = new DownloadGamesList();
		asyncDownload.execute(searchUrl);
	}

	private void showGamesList(GameList games) {
		gameListView = (ListView) findViewById(R.id.gameListLayout);
		gameListAdapter = new ListAdapter(this, games);
		gameListView.setAdapter(gameListAdapter);

		// Click event for single list row
		gameListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				String idTxt = view.findViewById(R.id.title).getTag()
						.toString();
				int gameId = Integer.parseInt(idTxt);
				gameInformationClick(gameId);
			}
		});
	}

	private void gameInformationClick(int gameId) {
		Intent intent = new Intent(this, GameOverviewActivity.class);
		intent.putExtra("id", gameId);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	private class DownloadGamesList extends AsyncTask<URL, Integer, GameList> {
		protected GameList doInBackground(URL... url) {

			try {
				gameSearcher.parseXml(url[0], SearchType.MULTIPLE_SEARCH);
				GameList searchResults = gameSearcher.getSearchResults();
				return searchResults;
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			// show progress or something.
		}

		protected void onPostExecute(GameList gamesList) {

			if (gamesList.size() > 0) {
				setContentView(R.layout.activity_game_list);
				showGamesList(gamesList);
			} else {

				Context context = getApplicationContext();
				CharSequence text = "I couldn't find any games by this term.";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();

				Intent i = new Intent(context, SearchActivity.class);
				startActivity(i);

			}
		}
	}
}
