package dragni.tgb.thegamesdb.views;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParserException;

import com.example.thegamesdb.R;
import com.squareup.picasso.Picasso;

import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.GameList;
import dragni.tgb.thegamesdb.logic.GameSearcher;
import dragni.tgb.thegamesdb.util.SearchType;
import dragni.tgb.thegamesdb.util.UrlMaker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class GameOverviewActivity extends Activity {

	private int gameId;
	private UrlMaker urlMaker;
	private GameSearcher gameSearcher;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_overview, menu);
		return true;
	}

	private void loadGameData() {
		URL gameUrl = urlMaker.getGameByIdUrl(gameId);
		DownloadGame asyncDownload = new DownloadGame();
		asyncDownload.execute(gameUrl);
	}

	private void showGame(GameList gameList) {
		Game game = gameList.get(0);

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
		
        String imageUrl = urlMaker.getGameThumbNailUrl(game.getThumbNailLocation());
        Picasso.with(this).load(imageUrl).into(thumbImage);
	}

	private String handleGenres(Game game) {
		ArrayList<String> genres = game.getGenres();
		String genresTxt = StringUtils.join(genres, ',');
		return genresTxt;
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
			setContentView(R.layout.activity_game_overview);
			showGame(gamesList);
		}
	}

}
