package dragni.tgb.thegamesdb.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlMaker {
	private static String baseUrl = "http://thegamesdb.net/api/";
	private static String getGameBaseUrl = "GetGame.php";
	private static String baseImgUrl = "http://thegamesdb.net/banners/";

	public URL getGameByIdUrl(int id) {
		URL url = null;

		try {
			String link = baseUrl + getGameBaseUrl + "?id=" + id;
			url = new URL(link);
		} catch (MalformedURLException e) {
			// TODO: handle error
		}
		return url;
	}

	public URL getGameByNameUrl(String name) {
		URL url = null;
		name = name.replace(" ", "+");

		try {
			String link = baseUrl + getGameBaseUrl + "?name=" + name;
			url = new URL(link);
		} catch (MalformedURLException e) {
			// TODO: handle error
		}
		return url;
	}

	public URL getGameByNameAndPlatformUrl(String name, String platform) {
		URL url = null;
		name = name.replace(" ", "+");
		platform = platform.replace(" ", "+");

		try {
			String link = baseUrl + getGameBaseUrl + "?name=" + name
					+ "&platform=" + platform;
			url = new URL(link);
		} catch (MalformedURLException e) {
			// TODO: handle error
		}
		return url;
	}

	public String getGameThumbNailUrl(String relativePath) {
		String url = null;
		url = baseImgUrl + relativePath;
		return url;
	}
}
