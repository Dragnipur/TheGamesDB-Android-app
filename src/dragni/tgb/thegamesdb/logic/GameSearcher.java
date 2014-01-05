package dragni.tgb.thegamesdb.logic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.GameList;

public class GameSearcher {

	private GameList searchList;
	private int searchType;
	private static int MULTIPLEGAMES = 0;
	private static int SINGLEGAME = 1;

	private enum XmlTag {
		Data, baseImgUrl, Game, id, GameTitle, AlternateTitles, title, ReleaseDate, PlatformId, Platform, Overview, ESRB, Genres, genre, Players, Coop, Youtube, Publisher, Developer, Rating, Images, fanart, original, thumb, boxart, banner, screenshot, clearlogo, error;
	}

	private String currentTag;
	private XmlPullParser xpp;

	public GameSearcher() {
		searchList = new GameList();
	}

	public GameList getSearchResults() {
		return searchList;
	}

	public void parseXml(URL url, int type) throws XmlPullParserException,
			IOException {
		searchType = type;
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		xpp = factory.newPullParser();
		InputStream stream = url.openStream();
		xpp.setInput(stream, null);
		int eventType = xpp.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				handleStartTag();
			} else if (eventType == XmlPullParser.TEXT) {
				handleTextTag();
			} else if (eventType == XmlPullParser.END_TAG) {
				handleEndTag();
			}

			eventType = xpp.next();
		}
	}

	private void handleStartTag() {
		currentTag = (xpp.getName());
		if (currentTag.equals("boxart"))
			handleBoxArtTag();
	}

	private void handleBoxArtTag() {
		String side = xpp.getAttributeValue(null, "side");

		if (side.equals("front")) {
			String thumbNail = xpp.getAttributeValue(null, "thumb");
			searchList.getLastGame().setThumbNailLocation(thumbNail);
		}
	}

	private void handleTextTag() {
		if (currentTag != null) {
			String tag = currentTag.replace("-", "");
			String value = xpp.getText();

			if (searchType == MULTIPLEGAMES) {
				handleMultipleGames(tag, value);
			} else if (searchType == SINGLEGAME) {
				handleSingleGame(tag, value);
			}
		}
	}

	private void handleMultipleGames(String tag, String value) {
		try {
			switch (XmlTag.valueOf(tag)) {
			case Game:
				searchList.add(new Game());
				break;
			case id:
				int id = Integer.parseInt(value);
				searchList.getLastGame().setId(id);
				break;
			case GameTitle:
				searchList.getLastGame().setTitle(value);
				break;
			case ReleaseDate:
				searchList.getLastGame().setReleaseDate(value);
				break;
			case Platform:
				searchList.getLastGame().setPlatform(value);
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private void handleSingleGame(String tag, String value) {
		try {
			switch (XmlTag.valueOf(tag)) {
			case Game:
				searchList.add(new Game());
				break;
			case id:
				int id = Integer.parseInt(value);
				searchList.getLastGame().setId(id);
				break;
			case GameTitle:
				searchList.getLastGame().setTitle(value);
				break;
			case ReleaseDate:
				searchList.getLastGame().setReleaseDate(value);
				break;
			case Platform:
				searchList.getLastGame().setPlatform(value);
				break;
			case Overview:
				searchList.getLastGame().setOverview(value);
				break;
			case genre:
				searchList.getLastGame().addGenre(value);
				break;
			case Players:
				searchList.getLastGame().setPlayers(value);
				break;
			case Coop:
				searchList.getLastGame().setCoop(value);
				break;
			case Youtube:
				searchList.getLastGame().setYoutubeUrl(value);
				break;
			case Publisher:
				searchList.getLastGame().setPublisher(value);
				break;
			case Developer:
				searchList.getLastGame().setDeveloper(value);
				break;
			case original:
				//searchList.getLastGame().addImage(value);
				break;
			default:
				break;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private void handleEndTag() {
		currentTag = null;
	}
}
