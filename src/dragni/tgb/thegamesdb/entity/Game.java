package dragni.tgb.thegamesdb.entity;

import java.util.ArrayList;

public class Game implements Comparable<Game>{
	private ArrayList<String> genres, images;
	private int id;
	private String title, platform, releaseDate, overview, ESRB, players, coop,
			youtubeUrl, publisher, developer, thumbNailLocation;
	private double rating;

	public Game() {
		genres = new ArrayList<String>();
		images = new ArrayList<String>();
	}
	
	public int compareTo(Game comparisonGame) {
		String comparisonTitle = comparisonGame.getTitle();
		return title.compareTo(comparisonTitle);
	}

	/** setters **/
	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setESRB(String ESRB) {
		this.ESRB = ESRB;
	}

	public void addGenre(String genre) {
		genres.add(genre);
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public void setCoop(String coop) {
		this.coop = coop;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public void setThumbNailLocation(String thumbNailLocation) {
		this.thumbNailLocation = thumbNailLocation;
	}

	public void addImage(String imageLocation) {
		images.add(imageLocation);
	}

	/** getters **/
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getPlatform() {
		if (platform == null) {
			return "N/A";
		}
		return platform;
	}

	public String getReleaseDate() {
		if (releaseDate == null) {
			return "N/A";
		}
		return releaseDate;
	}

	public String getOverview() {
		if (overview == null) {
			return "N/A";
		}
		return overview;
	}

	public String getESRB() {
		if (ESRB == null) {
			return "N/A";
		}
		return ESRB;
	}

	public String getPlayers() {
		if (players == null) {
			return "1";
		}
		return players;
	}

	public String getCoop() {
		if (coop == null) {
			return "N/A";
		}
		return coop;
	}

	public ArrayList<String> getGenres() {
		if(genres.size() == 0) {
			genres.add("N/A");
		}
		return genres;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public String getPublisher() {
		if (publisher == null) {
			return "N/A";
		}
		return publisher;
	}

	public String getDeveloper() {
		if (developer == null) {
			return "N/A";
		}
		return developer;
	}

	public double getRating() {
		return rating;
	}

	public String getThumbNailLocation() {
		return thumbNailLocation;
	}

	public ArrayList<String> getImages() {
		return images;
	}
}
