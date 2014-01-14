package dragni.tgb.thegamesdb.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.thegamesdb.R;

import dragni.tgb.thegamesdb.entity.Game;

public class FragmentGameInformation extends SherlockFragment {

	private Game game;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_game_information,
				container, false);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	public void setGameData(Game game) {
		this.game = game;
	}
}
