package dragni.tgb.thegamesdb.entity;

import java.util.ArrayList;

public class GameList extends ArrayList<Game> {

	private static final long serialVersionUID = -6940419301391447425L;

	public Game getLastGame() {
		int lastGameLocation = this.size();
		lastGameLocation--;
		Game game = this.get(lastGameLocation);
		return game;
	}
}
