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
import com.squareup.picasso.Picasso;

import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.GameList;
import dragni.tgb.thegamesdb.entity.Image;
import dragni.tgb.thegamesdb.util.UrlMaker;
 
public class ListAdapter extends BaseAdapter {
 
    private GameList games;
    private static LayoutInflater inflater=null;
    private UrlMaker urlMaker;
 
    public ListAdapter(Activity activ, GameList games) {
        this.games = games;
        inflater = (LayoutInflater) activ.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        urlMaker = new UrlMaker();
    }
 
    public int getCount() {
        return games.size();
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
 
        TextView title = (TextView)vi.findViewById(R.id.title);
        TextView platform = (TextView)vi.findViewById(R.id.platform);
        TextView releaseDate = (TextView)vi.findViewById(R.id.releaseDate);
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image);
 
        Game game = games.get(position);
        
        String gameTitle = game.getTitle();
 
        // Setting all values in listview
        title.setTag(game.getId());
        title.setText(gameTitle);
        platform.setText(game.getPlatform());
        releaseDate.setText(game.getReleaseDate());
        Context context = vi.getContext();
        
        if(game.hasImages()) {
            Image thumbNail = game.getImages().get(0);
            String thumbNailUrl = thumbNail.getThumbNail();
            String imageUrl = urlMaker.getGameImageUrl(thumbNailUrl);
            Picasso.with(context).load(imageUrl).into(thumb_image);
        }
        
        return vi;
    }
}