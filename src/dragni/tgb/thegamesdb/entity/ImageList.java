package dragni.tgb.thegamesdb.entity;

import java.util.ArrayList;

public class ImageList extends ArrayList<Image> {

	private static final long serialVersionUID = -2268633744094862333L;
	private int thumbNailIndex;
	
	public Image getLastImage() {
		int lastImageLocation = this.size();
		lastImageLocation--;
		Image image = this.get(lastImageLocation);
		return image;
	}
	
	public void setThumbNailIndex() {
		int lastImageIndex = this.size();
		lastImageIndex--;
		
		thumbNailIndex = lastImageIndex;
	}
	
	public Image getThumbNail() {
		Image thumbNail = this.get(thumbNailIndex);
		return thumbNail;
	}
}
