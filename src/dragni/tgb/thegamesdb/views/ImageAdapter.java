package dragni.tgb.thegamesdb.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import dragni.tgb.thegamesdb.entity.Image;
import dragni.tgb.thegamesdb.entity.ImageList;
import dragni.tgb.thegamesdb.util.UrlMaker;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ImageList images;
    private UrlMaker urlMaker;

    public ImageAdapter(Context c, ImageList images) {
        mContext = c;
        this.images = images;
        urlMaker = new UrlMaker();
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView ;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	imageView = new ImageView(mContext);
        	imageView.setLayoutParams(new GridView.LayoutParams(300, 300)); //TODO: betere params (groter)
        	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        	imageView.setPadding(2, 2, 2, 2);
        } else {
        	imageView = (ImageView) convertView;
        }
        
        System.out.println(images.get(position).getThumbNail());
        Image image = images.get(position);
        String thumbNailLocation = image.getThumbNail();
        String imageUrl = urlMaker.getGameImageUrl(thumbNailLocation);
		Picasso.with(mContext).load(imageUrl).into(imageView);
        return imageView;
    }
}