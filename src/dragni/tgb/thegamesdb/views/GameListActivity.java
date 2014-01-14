package dragni.tgb.thegamesdb.views;

import java.io.IOException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.thegamesdb.R;

import dragni.tgb.thegamesdb.entity.GameList;
import dragni.tgb.thegamesdb.logic.GameSearcher;
import dragni.tgb.thegamesdb.util.GameListSorter;
import dragni.tgb.thegamesdb.util.GameListSorter.SortType;
import dragni.tgb.thegamesdb.util.SearchType;
import dragni.tgb.thegamesdb.util.UrlMaker;

public class GameListActivity extends SherlockActivity {
	private GameSearcher gameSearcher;
	private ListView gameListView;
	private GameList games;
	private GameListSorter gameListSorter;
	private ListAdapter gameListAdapter;
	private UrlMaker urlMaker;
	private boolean menuChanged = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_layout);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		// get the search query.
		Intent intent = getIntent();
		String searchQuery = intent.getStringExtra("searchQuery");

		// setup the required manager and util objects.
		gameSearcher = new GameSearcher();
		urlMaker = new UrlMaker();
		gameListSorter = new GameListSorter();

		sendSearchRequest(searchQuery);
	}

	private void sendSearchRequest(String searchQuery) {
		URL searchUrl = urlMaker.getGameByNameUrl(searchQuery);
		DownloadGamesList asyncDownload = new DownloadGamesList();
		asyncDownload.execute(searchUrl);
	}

	private void showGamesList(GameList games) {
		this.games = games;
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
		getSupportMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		getSupportMenuInflater().inflate(R.menu.game_list, menu);

		if (menuChanged) {
			setSortMenuIcons(menu);
			sortGameList();
			menuChanged = false;
		}

		return super.onPrepareOptionsMenu(menu);
	}

	private void setSortMenuIcons(Menu menu) {
		SortType sortType = gameListSorter.getSortType();
		MenuItem item;

		resetSortMenuIcons(menu);

		switch (sortType) {
		case nameAsc:
			item = menu.findItem(R.id.sortByName);
			item.setIcon(R.drawable.up);
			break;
		case nameDesc:
			item = menu.findItem(R.id.sortByName);
			item.setIcon(R.drawable.down);
			break;
		case platformAsc:
			item = menu.findItem(R.id.sortByPlatform);
			item.setIcon(R.drawable.up);
			break;
		case platformDesc:
			item = menu.findItem(R.id.sortByPlatform);
			item.setIcon(R.drawable.down);
			break;
		case releaseAsc:
			item = menu.findItem(R.id.sortByRelease);
			item.setIcon(R.drawable.up);
			break;
		case releaseDesc:
			item = menu.findItem(R.id.sortByRelease);
			item.setIcon(R.drawable.down);
			break;
		}
	}

	private void resetSortMenuIcons(Menu menu) {
		MenuItem nameItem = menu.findItem(R.id.sortByName);
		nameItem.setIcon(R.drawable.none);
		MenuItem platformItem = menu.findItem(R.id.sortByName);
		platformItem.setIcon(R.drawable.none);
		MenuItem releaseItem = menu.findItem(R.id.sortByName);
		releaseItem.setIcon(R.drawable.none);
	}

	private void sortGameList() {
		games = gameListSorter.sortGamesList(games);
		showGamesList(games);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.sortByName:
			menuChanged = true;
			gameListSorter.setSortType(GameListSorter.NAME);
			invalidateOptionsMenu();
			break;
		case R.id.sortByPlatform:
			menuChanged = true;
			gameListSorter.setSortType(GameListSorter.PLATFORM);
			invalidateOptionsMenu();
			break;
		case R.id.sortByRelease:
			menuChanged = true;
			gameListSorter.setSortType(GameListSorter.RELEASE);
			invalidateOptionsMenu();
			break;
		}
		return super.onOptionsItemSelected(menuItem);
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
			if (gamesList != null) {
				if (gamesList.size() > 0) {
					setContentView(R.layout.activity_game_list);
					gamesList = gameListSorter.sortByNameAsc(gamesList);
					showGamesList(gamesList);
				} else {

					Context context = getApplicationContext();
					CharSequence text = "No games found";
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

					Intent i = new Intent(context, SearchActivity.class);
					startActivity(i);

				}
			}
			else
			{
				Context context = getApplicationContext();
				Intent i = new Intent(context, SearchActivity.class);
				startActivity(i);
			}
		}
	}
}
