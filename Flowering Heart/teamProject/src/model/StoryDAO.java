package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StoryDAO {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	Scanner sc = new Scanner(System.in);

	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "cgi_1_0418_4";
			String password = "smhrd4";

			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	public void getClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("자원 반납시 생긴 오류!");
		}
	}

	public int insert(Member m1) {
		int row = 0;

		try {
			getConn();

			String sql = "insert into member values(m_seq.nextval,?,?,?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, m1.getId());
			psmt.setString(2, m1.getPw());
			psmt.setString(3, m1.getUsername());

			row = psmt.executeUpdate();

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}

		return row;
	}

	public Member selectOne(Member m1) {
		Member s2 = null;
		try {
			getConn();

			String sql = "select * from member where id=? and password=? ";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, m1.getId());
			psmt.setString(2, m1.getPw());

			rs = psmt.executeQuery();

			if (rs.next()) {
				String nick = rs.getString("username");
				s2 = new Member(nick);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}
		return s2;

	}

	public ArrayList<Rank> ranking() {
		ArrayList<Rank> list = new ArrayList<Rank>();
		try {
			getConn();

			String sql = "select id, score ,rank() over (order by score desc) AS R from ranking";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				int score = rs.getInt("score");
				int rank = rs.getInt("r");

				// 하나의 데이터로 묶기!
				Rank r = new Rank(id, score, rank);
				list.add(r);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println("데이터베이스 연결 실패");
		} finally {
			getClose();
		}
		return list;
	}

	
	public int save(int index, Member m, int gender, int score) {
		// int result = 0;
		int row = 0;
		String id = m.getId();
		try {
			getConn();

			String sql = "select * from save where id=?";
			psmt = conn.prepareStatement(sql);

			// 물음표인자는 get.username
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next() == false) {
				sql = "insert into save values (?,?,?,?)";
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, id);
				// 5의 자리에 현재 진행중인 스토리 체크!
				psmt.setInt(2, index);
				psmt.setInt(3, gender);
				psmt.setInt(4, score);
				row = psmt.executeUpdate();
			} else {
				sql = "update save set save_point = ?, score = ? where id=?";
				psmt = conn.prepareStatement(sql);
				// 5의 자리에는 현재 진행중인 스토리 체크!
				psmt.setInt(1, index);
				psmt.setInt(2, score);
				psmt.setString(3, id);
				row = psmt.executeUpdate();
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}
	public int inputRank(Member m, int score) {
		int row = 0;
		String id = m.getId();
		try {
			getConn();

			String sql = "select * from ranking where id=?";
			psmt = conn.prepareStatement(sql);

			// 물음표인자는 get.username
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next() == false) {
				sql = "insert into ranking values (?,?,?)";
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, id);
				// 5의 자리에 현재 진행중인 스토리 체크!
				psmt.setInt(2, score);
				psmt.setInt(3, 0);
				row = psmt.executeUpdate();
			} else {
				sql = "update ranking set score = ? where id=?";
				score += rs.getInt("score");
				psmt = conn.prepareStatement(sql);
				// 5의 자리에는 현재 진행중인 스토리 체크!
				psmt.setInt(1, score);
				psmt.setString(2, id);
				row = psmt.executeUpdate();
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}
		return row;
	}
	public int eventGame1() {
		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
		int answer = rd.nextInt(2) + 1;
		int result = 0;
		System.out.println("=== 이벤트 게임(홀짝 게임) ===");
		System.out.print("정답 입력 >> ");
		String input = sc.next();

		if (answer % 2 == 0) {
			if (input == "짝") {
				System.out.println("성공입니다.");
				result = 1;
			} else {
				System.out.println("실패입니다.");
				result = 0;
			}
		} else {
			if (input == "홀") {
				System.out.println("실패입니다.");
				result = 0;
			} else {
				System.out.println("성공입니다.");
				result = 1;
			}
		}
		return result;

	}

	public boolean eventGame2() {
		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
		int answer = rd.nextInt(10) + 1;
		boolean result = false;
		System.out.println("=== 이벤트 게임(업다운 게임) ===");

		int cnt = 0;

		while (true) {

			if (cnt == 3) {
				System.out.println("실패입니다");
				break;
			}
			System.out.println("1~10 사이의 숫자를 입력하세요, 기회는 3번입니다.");
			System.out.print("숫자 입력 >> ");
			int num = sc.nextInt();

			if (answer == num) {
				System.out.println("성공입니다.");
				result = true;
				break;
			} else if (answer > num) {
				System.out.println("더 큰 수를 입력하세요");
			} else {
				System.out.println("더 작은 수를 입력하세요");
			}

			cnt++;
		}
		return result;
		
		
		
	}

	public Member callIndex(Member m) {
		int save_point = 0;
		int gender = 0;
		Member m1 = null;
		try {
			getConn();

			String sql = "select save_point, gender from save where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.getId());

			rs = psmt.executeQuery();

			if (rs.next()) {
				save_point = rs.getInt("save_point");
				gender = rs.getInt("gender");
				m1 = new Member(save_point, gender);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}
		return m1;

	}
	
	
	public boolean userCheck(Member m) {
		boolean result = false;
		try {
			getConn();

			String sql = "select * from save where id=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, m.getId());

			rs = psmt.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			getClose();
		}
		return result;
	}
	
	
	public Story WomanStory(Story s) {

		Story s2 = null;
		try {
			getConn();
			String sql = "select story from woman_story where woman_index = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, s.getIndex());

			rs = psmt.executeQuery();

			if (rs.next()) {
				String story = rs.getString("story");
				s2 = new Story(story);
			}
		} catch (SQLException e) {

			//e.printStackTrace();
		} finally {
			getClose();
		}
		return s2;

	}
	
	public Story ManStory(Story s) {
		Story s2 = null;
		try {
			getConn();
			String sql = "select story from man_story where man_index = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, s.getIndex());

			rs = psmt.executeQuery();

			if (rs.next()) {
				String story = rs.getString("story");
				s2 = new Story(story);
			}
		} catch (SQLException e) {

			//e.printStackTrace();
		} finally {
			getClose();
		}
		return s2;
	}

	public Story ManAnswer(Story s) {
		Story s2 = null;
	      try {
	         getConn();
	         String sql = "select * from man_story where man_index = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, s.getIndex());

	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String answer = rs.getString("answer");
	            String wrong = rs.getString("wrong");
	            String man_answer = rs.getString("man_answer");
	            String man_wrong = rs.getString("man_wrong");
	            //1. woman_wrong, woman_answer 가져오기
	            s2 = new Story(answer , wrong, man_answer, man_wrong);
	            
	            
	         }
	      } catch (SQLException e) {

	         //e.printStackTrace();
	      } finally {
	         getClose();
	      }
	      return s2;
	}
	
	 public Story answer(Story s){
	      Story s2 = null;
	      try {
	         getConn();
	         String sql = "select * from woman_story where woman_index = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, s.getIndex());

	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String answer = rs.getString("answer");
	            String wrong = rs.getString("wrong");
	            String woman_answer = rs.getString("woman_answer");
	            String woman_wrong = rs.getString("woman_wrong");
	            //1. woman_wrong, woman_answer 가져오기
	            s2 = new Story(answer , wrong, woman_answer, woman_wrong);
	            
	            
	         }
	      } catch (SQLException e) {

	        // e.printStackTrace();
	      } finally {
	         getClose();
	      }
	      return s2;
	   }

	 public Story WA(Story s , int num){
	      Story s2 = null;
	      try {
	         getConn();
	         String sql = "select * from woman_story where woman_index = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, s.getIndex());

	         rs = psmt.executeQuery();

	         if (rs.next()) {
	            String answer = rs.getString("answer");
	            String wrong = rs.getString("wrong");
	             s2 = new Story(answer , wrong);
	             
	         }
	      } catch (SQLException e) {

	         //e.printStackTrace();
	      } finally {
	         getClose();
	      }
	      return s2;
	 }
	 
	 public int gender() {
		 System.out.println("성별을 선택해주세요 [1] 남 : [2] 여");
	      
	      int select = sc.nextInt();
	      
	      if(select == 1) {
	           System.out.println("남성을 선택했습니다.");
	        }else {
	           System.out.println("여성을 선택했습니다.");
	        }
	      return select;
	 }
}
