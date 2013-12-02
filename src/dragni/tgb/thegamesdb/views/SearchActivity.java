package dragni.tgb.thegamesdb.views;

import com.example.thegamesdb.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SearchActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);        
        setSearchBarEnterAction();        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }
    
    private void setSearchBarEnterAction() {
		final EditText searchBar = (EditText) findViewById(R.id.searchBar);
		searchBar.setOnEditorActionListener(new OnEditorActionListener() {

			  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    if (actionId == EditorInfo.IME_ACTION_DONE) {
			      String searchQuery = searchBar.getText().toString();
			      sendSearchRequest(searchQuery);
			      return true;
			    } else {
			      return false;
			    }
			 }
		});
    }
    
    private void sendSearchRequest(String searchQuery) {
    	Intent intent = new Intent(this, GameListActivity.class);
    	intent.putExtra("searchQuery", searchQuery);
    	startActivity(intent);
    }
    
}
