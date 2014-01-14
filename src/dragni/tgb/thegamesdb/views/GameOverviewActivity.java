package dragni.tgb.thegamesdb.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.thegamesdb.R;
import com.squareup.picasso.Picasso;

import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.GameList;
import dragni.tgb.thegamesdb.entity.Image;
import dragni.tgb.thegamesdb.logic.GameSearcher;
import dragni.tgb.thegamesdb.util.SearchType;
import dragni.tgb.thegamesdb.util.UrlMaker;

public class GameOverviewActivity extends SherlockFragmentActivity {

	private int gameId;
	private UrlMaker urlMaker;
	private GameSearcher gameSearcher;
	private GameList gamesList;
	private ActionBar actionBar;
	private Tab tab;
	private ViewPager pager;

	private static final int INFORMATION = 0;
	private static final int IMAGES = 1;
	private static final int VIDEOS = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_layout);

		TextView loadText = (TextView) findViewById(R.id.progressText);
		loadText.setText(R.string.load_game);

		urlMaker = new UrlMaker();
		gameSearcher = new GameSearcher();

		Intent intent = getIntent();
		gameId = intent.getIntExtra("id", -1);

		loadGameData();
	}

	private void initGameOverview() {
		setContentView(R.layout.activity_game_overview);
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		pager = (ViewPager) findViewById(R.id.pager);
		FragmentManager fm = getSupportFragmentManager();

		ViewPager.SimpleOnPageChangeListener ViewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				actionBar.setSelectedNavigationItem(position);
			}
		};

		pager.setOnPageChangeListener(ViewPagerListener);
		ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(fm);
		pager.setAdapter(viewpageradapter);

		generateActionBar(actionBar);
	}

	private void generateActionBar(ActionBar actionBar) {

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				pager.setCurrentItem(tab.getPosition());

				int currentTab = tab.getPosition();

				switch (currentTab) {
				case INFORMATION:
					showGame();
					break;
				case IMAGES:
					showImages();
					break;
				case VIDEOS:
					break;
				}
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// do nothing.
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// do nothing.
			}
		};

		createTabs(actionBar, tabListener);
	}

	private void createTabs(ActionBar actionBar, TabListener tabListener) {
		// Create information tab.
		tab = actionBar.newTab().setText("information").setTabListener(tabListener);
		actionBar.addTab(tab);

		// Create images tab.
		tab = actionBar.newTab().setText("images").setTabListener(tabListener);
		actionBar.addTab(tab);

		// Create videos tab.
		tab = actionBar.newTab().setText("videos").setTabListener(tabListener);
		actionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(menuItem);
	}

	private void loadGameData() {
		URL gameUrl = urlMaker.getGameByIdUrl(gameId);
		DownloadGame asyncDownload = new DownloadGame();
		asyncDownload.execute(gameUrl);
	}

	private class DownloadGame extends AsyncTask<URL, Integer, GameList> {
		protected GameList doInBackground(URL... url) {

			try {
				gameSearcher.parseXml(url[0], SearchType.SINGLE_SEARCH);
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
			setGamesList(gamesList);
			initGameOverview();
		}
	}

	private void setGamesList(GameList gamesList) {
		this.gamesList = gamesList;
	}

	private void showGame() {
		Game game = gamesList.get(0);

		TextView name = (TextView) findViewById(R.id.gameName);
		TextView platform = (TextView) findViewById(R.id.gamePlatform);
		TextView players = (TextView) findViewById(R.id.gamePlayersTxt);
		TextView coop = (TextView) findViewById(R.id.gameCoopTxt);
		TextView genres = (TextView) findViewById(R.id.gameGenresTxt);
		TextView releaseDate = (TextView) findViewById(R.id.gameReleaseTxt);
		TextView publisher = (TextView) findViewById(R.id.gamePublisherTxt);
		TextView developer = (TextView) findViewById(R.id.gameDeveloperTxt);
		TextView overview = (TextView) findViewById(R.id.overviewTxt);
		ImageView thumbImage = (ImageView) findViewById(R.id.gameThumb);

		name.setText(game.getTitle());
		platform.setText(game.getPlatform());
		players.setText(game.getPlayers());
		coop.setText(game.getCoop());
		genres.setText(handleGenres(game));
		releaseDate.setText(game.getReleaseDate());
		publisher.setText(game.getPublisher());
		developer.setText(game.getDeveloper());
		overview.setText(game.getOverview());

		Image thumbNail = game.getImages().getThumbNail();
		String thumbNailLocation = thumbNail.getThumbNail();
		String imageUrl = urlMaker.getGameImageUrl(thumbNailLocation);
		Picasso.with(this).load(imageUrl).into(thumbImage);
	}

	private void showImages() {
		GridView gridView = (GridView) findViewById(R.id.imagesview);
		gridView.setAdapter(new ImageAdapter(this, gamesList.get(0).getImages()));

		gridView.setOnItemClickListener(new OnItemClickListener() {			
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				handleImageClick(position);
			}
		});
	}
	
	private void handleImageClick(int position) {
		Image image = gamesList.get(0).getImage(position);
		String imageUrl = image.getUrl();
    	Intent intent = new Intent(this, ImageZoomActivity.class);
    	intent.putExtra("imageUrl", imageUrl);
    	startActivity(intent);
	}

	private void showVideos() {

	}

	private String handleGenres(Game game) {
		ArrayList<String> genres = game.getGenres();
		String genresTxt = StringUtils.join(genres, ',');
		return genresTxt;
	}
}
