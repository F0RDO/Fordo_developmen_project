package model;

public class Rank{
	private String username;
	private int score;
	private int rank;
	
	public Rank(String username, int score, int rank) {
		this.username = username;
		this.score = score;
		this.rank = rank;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
