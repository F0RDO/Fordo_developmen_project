package model;

public class Member {

	private int m_seq;
	private String id = null;
	private String pw = null;
	private String username = null;
	private int gender = 0;
	private int save_point = 0;
	private int score;
	
	
	
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public Member(String id, String pw, String username) {
		this.id = id;
		this.pw = pw;
		this.username = username;
	}
	

	public Member(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public Member(String nick) {
		this.username = nick;

	}
	
	public void setId(String id) {
		this.id = id;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}


	public int getSave_point() {
		return save_point;
	}


	public void setSave_point(int save_point) {
		this.save_point = save_point;
	}


	public Member(int m_seq, String id, String pw, String username) {
		this.m_seq = m_seq;
		this.id = id;
		this.pw = pw;
		this.username = username;

	}
	
	public Member(int save_point, int gender) {
		this.save_point = save_point;
		this.gender = gender;
	}


	public int getm_seq() {
		return m_seq;

	}

	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return pw;
	}

	public String getPw() {
		return pw;
	}
	
	public String getUsername() {
		return username;
	}
}
