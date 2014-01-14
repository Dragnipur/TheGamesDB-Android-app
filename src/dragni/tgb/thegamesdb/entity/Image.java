package dragni.tgb.thegamesdb.entity;

public class Image {
	private String thumbNail, url;
	private int width, height;
	
	public String getUrl() {
		return url;
	}
	
	public String getThumbNail() {
		return thumbNail;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setThumbNail(String thumbNail) {
		this.thumbNail = thumbNail;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}
