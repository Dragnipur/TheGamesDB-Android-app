package dragni.tgb.thegamesdb.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Game implements Comparable<Game> {
	private ArrayList<String> genres;
	private ImageList images;
	private int id;
	private String title, platform, releaseDate, overview, ESRB, players, coop,
			youtubeUrl, publisher, developer;
	private double rating;

	public enum SortType {
		TITLE, PLATFORM, RELEASE
	};

	private SortType sortType;

	public Game() {
		genres = new ArrayList<String>();
		images = new ImageList();
	}

	public int compareTo(Game comparisonGame) {

		switch (sortType) {
		case TITLE:
			String comparisonTitle = comparisonGame.getTitle();
			return title.compareTo(comparisonTitle);
		case PLATFORM:
			String comparisonPlatform = comparisonGame.getPlatform();
			return platform.compareTo(comparisonPlatform);
		case RELEASE:
			return compareReleaseDates(comparisonGame);
		default:
			// TODO: throw exception.
			return -1;
		}
	}

	// TODO: clean this shit up
	private int compareReleaseDates(Game comparisonGame) {
		try {
			String release = getReleaseDate();
			String comparisonRelease = comparisonGame.getReleaseDate();

			if (comparisonRelease.equals("N/A")) {
				comparisonRelease = "01/01/1900";
			}

			if (release.equals("N/A")) {
				release = "01/01/1900";
			}

			//TODO: change release date from string to date format.
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date startReleaseDate = dateFormat.parse(release);
			Date comparisonReleaseDate = dateFormat.parse(comparisonRelease);
			
			return startReleaseDate.compareTo(comparisonReleaseDate);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
			// TODO: do something useful
		}
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

	public void addImage(Image image) {
		images.add(image);
	}
	
	public Image getLastImage() {
		Image image = images.getLastImage();
		return image;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
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
		if (genres.size() == 0) {
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

	public ImageList getImages() {
		return images;
	}
	
	public Image getImage(int position) {
		return images.get(position);
	}
	
	public boolean hasImages() {
		if(images.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
