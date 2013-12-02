package dragni.tgb.thegamesdb.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thegamesdb.R;

import dragni.tgb.thegamesdb.util.ImageLoader;
 
public class ListAdapter extends BaseAdapter {
 
    private Activity activity;
    private String[][] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
 
    public ListAdapter(Activity a, String[][] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
 
    public int getCount() {
        return data.length;
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null)
            vi = inflater.inflate(R.layout.game_list_row, null);
 
        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
 
        String[] game = data[position];
 
        // Setting all values in listview
        title.setText(game[1]);
        artist.setText(game[2]);
        duration.setText(game[3]);
        //imageLoader.DisplayImage(game[4], thumb_image);
        return vi;
    }
}