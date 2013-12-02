package dragni.tgb.thegamesdb.entity;

import java.util.ArrayList;

public class Game {
	private ArrayList genres;
	private int id;
	private String title, platform, releaseDate, 
			overview, ESRB, youtubeUrl, publisher,
			developer;
	private double rating;
	
	public Game() {
		genres = new ArrayList();
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
	
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	public void setRating(double Rating) {
		this.rating = rating;
	}
	
	/** getters **/
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public String getESRB() {
		return ESRB;
	}
	
	public ArrayList getGenres() {
		return genres;
	}
	
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String getDeveloper() {
		return developer;
	}
	
	public double getRating() {
		return rating;
	}
}
