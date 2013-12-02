package dragni.tgb.thegamesdb.entity;

import java.util.ArrayList;

public class SearchList extends ArrayList<Game> {
	
	public Game getLastGame() {
		int lastGameLocation = this.size();
		lastGameLocation--;
		Game game = this.get(lastGameLocation);
		return game;
	}
}
