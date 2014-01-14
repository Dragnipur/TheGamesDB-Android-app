package dragni.tgb.thegamesdb.util;

import java.util.Collections;

import dragni.tgb.thegamesdb.entity.Game;
import dragni.tgb.thegamesdb.entity.GameList;

public class GameListSorter {

	public enum SortType {
		nameAsc, nameDesc, platformAsc, platformDesc, releaseAsc, releaseDesc
	};

	public final static int NAME = 0;
	public final static int PLATFORM = 1;
	public final static int RELEASE = 2;

	private SortType sortType;

	// TODO: move to gamelistsorter.
	public void setSortType(int type) {
		switch (type) {
		case NAME:
			if (sortType == SortType.nameAsc) {
				sortType = SortType.nameDesc;
			} else {
				sortType = SortType.nameAsc;
			}
			break;
		case PLATFORM:
			if (sortType == SortType.platformAsc) {
				sortType = SortType.platformDesc;
			} else {
				sortType = SortType.platformAsc;
			}
			break;
		case RELEASE:
			if (sortType == SortType.releaseAsc) {
				sortType = SortType.releaseDesc;
			} else {
				sortType = SortType.releaseAsc;
			}
			break;
		}
	}

	public GameList sortGamesList(GameList games) {

		switch (sortType) {
		case nameAsc:
			games = sortByNameAsc(games);
			return games;
		case nameDesc:
			games = sortByNameDesc(games);
			return games;
		case platformAsc:
			games = sortByPlatformAsc(games);
			return games;
		case platformDesc:
			games = sortByPlatformDesc(games);
			return games;
		case releaseAsc:
			games = sortByReleaseAsc(games);
			return games;
		case releaseDesc:
			games = sortByReleaseDesc(games);
			return games;
		default:
			return games;
		}
	}

	public SortType getSortType() {
		return sortType;
	}

	public GameList sortByNameAsc(GameList games) {

		for (Game game : games) {
			game.setSortType(Game.SortType.TITLE);
		}

		Collections.sort(games);
		return games;
	}

	public GameList sortByNameDesc(GameList games) {
		for (Game game : games) {
			game.setSortType(Game.SortType.TITLE);
		}

		Collections.sort(games, Collections.reverseOrder());
		return games;
	}

	public GameList sortByPlatformAsc(GameList games) {
		for (Game game : games) {
			game.setSortType(Game.SortType.PLATFORM);
		}

		Collections.sort(games);
		return games;
	}

	public GameList sortByPlatformDesc(GameList games) {
		for (Game game : games) {
			game.setSortType(Game.SortType.PLATFORM);
		}

		Collections.sort(games, Collections.reverseOrder());
		return games;
	}

	public GameList sortByReleaseAsc(GameList games) {
		for (Game game : games) {
			game.setSortType(Game.SortType.RELEASE);
		}

		Collections.sort(games);
		return games;
	}

	public GameList sortByReleaseDesc(GameList games) {
		for (Game game : games) {
			game.setSortType(Game.SortType.RELEASE);
		}

		Collections.sort(games, Collections.reverseOrder());
		return games;
	}
}
