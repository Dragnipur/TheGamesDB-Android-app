package com.example.thegamesdb;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameListFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_game_list, container, false);		
		setContent(view);	
		return view;
	}
	
	private void setContent(View view) {
		
	}
}
