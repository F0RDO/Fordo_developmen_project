package model;

public class Story {

	private int index;
	private String story;
	private String answer;
	private String wrong;
	private String woman_Answer;
	private String woman_Wrong;
	private String man_Answer;
	private String man_Wrong;

	public String getMan_Answer() {
		return man_Answer;
	}

	public void setMan_Answer(String man_Answer) {
		this.man_Answer = man_Answer;
	}

	public String getMan_Wrong() {
		return man_Wrong;
	}

	public void setMan_Wrong(String man_Wrong) {
		this.man_Wrong = man_Wrong;
	}

	public Story(String answer, String wrong, String woman_Answer, String woman_Wrong) {
		this.answer = answer;
		this.wrong = wrong;
		this.woman_Answer = woman_Answer;
		this.woman_Wrong = woman_Wrong;
	}

	public Story(String story, int index, String answer, String wrong, String woman_Answer, String woman_Wrong) {
		this.story = story;
		this.index = index;
		this.answer = answer;
		this.wrong = wrong;
		this.woman_Answer = woman_Answer;
		this.woman_Wrong = woman_Wrong;
	}

	// String 하나 매개변수로 사용하는 생성자
	// answer story..

	public Story(String story, String answer, String wrong) {
		this.story = story;
		this.answer = answer;
		this.wrong = wrong;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getWrong() {
		return wrong;
	}

	public void setWrong(String wrong) {
		this.wrong = wrong;
	}

	public String getWoman_Answer() {
		return woman_Answer;
	}

	public void setWoman_Answer(String woman_Answer) {
		this.woman_Answer = woman_Answer;
	}

	public String getWoman_Wrong() {
		return woman_Wrong;
	}

	public void setWoman_Wrong(String woman_Wrong) {
		this.woman_Wrong = woman_Wrong;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public Story(String answer) {
		this.answer = answer;
	}

	public Story(int index) {
		this.index = index;
	}

	public Story(String answer, String wrong) {

		this.answer = answer;
		this.wrong = wrong;

	}

	public String getStory() {
		return story;
	}

}
