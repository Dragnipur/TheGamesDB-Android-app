package dragni.tgb.thegamesdb.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import dragni.tgb.thegamesdb.entity.Game;
 
public class ViewPagerAdapter extends FragmentPagerAdapter {
 
    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 3;
    private Game game;
 
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int arg0) {
    	
        switch (arg0) {
        case 0:
            FragmentGameInformation informationFragment = new FragmentGameInformation();
            return informationFragment;
        case 1:
            FragmentGameImages imagesFragment = new FragmentGameImages();
            return imagesFragment;
        case 2:
            FragmentGameVideos videosFragment = new FragmentGameVideos();
            return videosFragment;
        }
        return null;
    }
 
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
 
}