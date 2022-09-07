package controller;

import java.util.ArrayList;

import model.Member;
import model.Rank;
import model.Story;
import model.StoryDAO;
import view.Main;

public class Controller {
	Main m = new Main();
	StoryDAO dao = new StoryDAO();
	Story s = null;
	Member m2 = null;

	public void insert(Member m1) {

		int row = dao.insert(m1);

		if (row > 0) {
			System.out.println("회원가입 완료");
		} else {
			System.out.println("회원가입 오류(중복)");
		}

	}

	public void selectOne(Member m1) {

		Member m3 = dao.selectOne(m1);

		if (m3 == null) {
			System.out.println("로그인실패");
		} else {
			System.out.println(m3.getUsername() + "님 환영합니다.");
		}

	}

	public void save(int index, Member m, int gender, int score) {
		int row = dao.save(index, m, gender, score);
	}
	
	public void inputRank(Member m, int score) {
		int row = dao.inputRank(m, score);
	}
	public ArrayList<Rank> ranking(){
		
		ArrayList<Rank> list =  dao.ranking();
		for(Rank s : list) {
			System.out.println(s.getRank()+"\t"+s.getUsername()+"\t"+s.getScore());
		}
		return list;
	}

}
