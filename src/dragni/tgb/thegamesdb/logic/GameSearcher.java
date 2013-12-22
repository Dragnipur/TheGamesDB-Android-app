package dragni.tgb.thegamesdb.logic;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.SearchList;

public class GameSearcher {
	
	private SearchList searchList;
	
	private enum XmlTag {
		Data, Game, id, GameTitle, ReleaseDate,
		Platform, Overview, ESRB, Genres, Genre,
		Youtube, Publisher, Developer, Rating,
		Images, fanart, original, thumb, boxart, 
		banner, screenshot, clearlogo, error;
	}
	
	public GameSearcher() {
		searchList = new SearchList();
	}
	
	public String[][] getSearchResults() {
		String[][] searchResults = new String[searchList.size()][4];
		for(int i = 0; i < searchList.size(); i++) {
			searchResults[i][0] = searchList.get(i).getId() + "";
			searchResults[i][1] = searchList.get(i).getTitle();
			searchResults[i][2] = searchList.get(i).getPlatform();
			searchResults[i][3] = searchList.get(i).getReleaseDate();
		}
		
		return searchResults;
	}
	
	public void parseXml(URL url) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        InputStream stream = url.openStream();
        xpp.setInput(stream, null);
        int eventType = xpp.getEventType();
        String currentTag = null;
        
        while (eventType != XmlPullParser.END_DOCUMENT) {
        	if(eventType == XmlPullParser.START_TAG) {
        		currentTag = (xpp.getName());
        	} else if(eventType == XmlPullParser.TEXT) {
        		if(currentTag != null) {
            		handleXmlTag(currentTag, xpp.getText());
        		}
        	} else if(eventType == XmlPullParser.END_TAG) {
        		currentTag = null;
        	}
        	
         eventType = xpp.next();
        }
	}
	
	public void handleXmlTag(String tag, String value) {
        switch( XmlTag.valueOf( tag ) ) {
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
        case ESRB:
        	searchList.getLastGame().setESRB(value);
        	break;
        case Genre:
        	searchList.getLastGame().addGenre(value);
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
        case Rating:
        	double rating = Double.parseDouble(value);
        	searchList.getLastGame().setRating(rating);
        	break;
        case fanart:
        	break;
        case original:
        	break;
        case thumb:
        	break;
        case boxart:
        	break;
        case banner:
        	break;
        case screenshot:
        	break;
        case clearlogo:
        	break;
        case error:
        	//throw exception.
        default:
        	break;
        }
	}
}
