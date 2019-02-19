package java;

public class Player {

	public String name;
	private int totalScore;

	public Player() {
	}

	public Player(String playerName) {
		name = playerName;
		totalScore = 0;
	}

	public String getPlayerName() {
		return name;
	}

	public int getPlayerScore() {
		return totalScore;
	}

	public void updatePlayerName(String playerName) {
		name = playerName;
	}

	public void updatePlayerScore(int num) {
		totalScore = totalScore + num;
	}

}
