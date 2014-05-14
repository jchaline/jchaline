package battle.csn.lucette.service;

public final class InitGameHelper {
	private InitGameHelper() {

	}

	public static String getGameId(String team) {
		return new RestClientAPI().getGameId(team);
	}

	public static String getTrainingGameId(String team, Integer level) {
		return new RestClientAPI().getNewPracticeGame(team, level);
	}
}
