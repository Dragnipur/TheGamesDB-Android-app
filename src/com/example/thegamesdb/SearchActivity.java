package com.example.thegamesdb;

import com.AridRayne.thegamesdb.lib.Data;
import com.AridRayne.thegamesdb.lib.GameItem;
import com.AridRayne.thegamesdb.lib.Utilities;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        Utilities util = new Utilities();
        Data<GameItem> game = util.GameFromID(1);
        Log.d("game", game.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }
    
}
