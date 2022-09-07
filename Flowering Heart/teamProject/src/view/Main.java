package view;

import java.util.ArrayList;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;
import controller.Controller;
import model.Member;
import model.Story;
import model.StoryDAO;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StoryDAO dao = new StoryDAO();
		int score = 0;
		int cnt = 20;
		int index = 1;
		int check = 0;
		int input2 = 0;
		int gender = 0;
		boolean checkc = false;
		Controller c = new Controller();
		Member m = null;
		Story s3 = null;
		MP3Player mp3 = new MP3Player();
		while (true) {
			System.out.println("[1]회원가입 [2]로그인 [3] 게임시작 [4]랭킹조회 [5]종료");
			int input = sc.nextInt();
			if (input == 1) {

				System.out.println("=====회원가입=====");
				System.out.print("ID를 입력하세요 : ");
				String id = sc.next();

				System.out.print("패스워드를 입력하세요 : ");
				String pw = sc.next();

				System.out.print("사용하실 닉네임을 입력하세요 : ");
				String username = sc.next();

				m = new Member(id, pw, username);

				c.insert(m);
				continue;
			} else if (input == 2) {
				System.out.println("=====로그인=====");
				System.out.print("ID : ");
				String id = sc.next();
				System.out.print("PW : ");
				String pw = sc.next();

				m = new Member(id, pw);

				c.selectOne(m);
				continue;

			} else if (input == 3) {
				checkc = dao.userCheck(m);
				if(checkc) {
					System.out.println("저장된 게임을 불러오시겠습니까?");
					System.out.println("[1] 네 [2] 아니요");
					input2 = sc.nextInt();
					if(input2 == 1) {
						Member m4 = dao.callIndex(m);
						index = m4.getSave_point();
						gender = m4.getGender();
					}else {
						gender = dao.gender();
					}
				}else {
					gender = dao.gender();
				}
				
				mp3.play("C:\\Users\\smhrd\\Desktop\\Java\\teamProject\\player\\우연히 봄 - 로꼬, 유주 (여자친구)   가사 (Lyrics).mp3");
				System.out.println("게임 시작전에 알립니다. 저장을 하고 싶으시면 9번을 입력해주세요!!");
				
				if (gender == 1) {
					while (true) {
						// 1 . Story를 가지고 오기
						Story s = new Story(index);
						Story s2 = dao.WomanStory(s);
							System.out.println(s2.getAnswer());

							s3 = dao.answer(s);
							System.out.println(s3.getAnswer() + "\t" + s3.getWrong());
						

						// 2. Stroy에 해당하는 Answer 가지고 오기
						if (index != 2 && index != 12) {
							System.out.print("[1] or [2] 선택하세요 >> ");
							input = sc.nextInt(); // 답을 입력
							System.out.println();
						}

						// 3-1. input == 1 --> woman_answer가 출력
						// 3-2. input ==2 --> woman_wrong이 출력
						if (input == 1) {
							if(index != 2) {
								System.out.println(s3.getWoman_Answer());
							}
							
							if (index == 1) {
								cnt += 5;
							}
							if (index == 2) {
								check = dao.eventGame1();
								if (check == 1) {
									cnt += 10;
								} else {
									cnt -= 20;
								}
								if(check==1) {
									System.out.println(s3.getWoman_Answer());
								}else {
									System.out.println(s3.getWoman_Wrong());
								}
								
							}
							if (index == 3) {
								cnt += 10;
							}
							if (index == 4) {
								cnt -= 10;
							}
							if (index == 5) {
								cnt += 10;
							}
							if (index == 6) {
								cnt += 10;
							}
							if (index == 7) {
								cnt += 10;
							}
							if (index == 8) {
								cnt -= 10;
							}
							if (index == 9) {
								cnt -= 10;
							}
							if (index == 10) {
								cnt += 10;
							}
							if (index == 11) {
								cnt -= 10;
							}
							if (index == 12) {
								checkc = dao.eventGame2();
								if(checkc) {
									cnt += 20;
								}else {
									cnt -= 100;
								}
								break;
							}
						} else if (input == 9) {
							// save
							c.save(index, m, gender,cnt);
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							System.exit(0);
							break;
						} else {
							if(index != 2) {
								System.out.println(s3.getWoman_Wrong());
							}
							if (index == 1) {
								cnt += 10;
							}
							if (index == 2) {
								check = dao.eventGame1();
								if (check == 1) {
									cnt += 10;
								} else {
									cnt -= 20;
								}
								if(check==1) {
									System.out.println(s3.getWoman_Answer());
								}else {
									System.out.println(s3.getWoman_Wrong());
								}
							}
							if (index == 3) {
								cnt -= 10;
							}
							if (index == 4) {
								cnt += 10;
							}
							if (index == 5) {
								cnt += 10;
							}
							if (index == 6) {
								cnt -= 10;
							}
							if (index == 7) {
								cnt -= 10;
							}
							if (index == 8) {
								cnt += 10;
							}
							if (index == 9) {
								cnt += 10;
							}
							if (index == 10) {
								cnt -= 10;
							}
							if (index == 11) {
								cnt += 10;
							}
							if (index == 12) {
								checkc = dao.eventGame2();
								if(checkc) {
									cnt += 20;
								}else {
									cnt -= 100;
								}
								break;
							}
						}
						// 총 스코어
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							Thread.sleep(2000);
							System.out.println("♡");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(1000);
							System.out.println("♥  ♡   ♡   ♡   ♡   ♡   Next Stage ♡   ♡   ♡   ♡   ♥");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(700);
							System.out.println("♥  ♡   ♡   ♡   ♡   ♡   ♡  ♡   ♡  ♡   ♡   ♡   ♡   ♥ ");
							System.out.println();
							System.out.println();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 다음 스토리로 넘어가겠다.
						index++;
					}
					if (cnt >= 80) {
						index = 21;
						Story s = new Story(index);
						Story s2 = dao.WomanStory(s);
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							mp3.play("C:\\Users\\smhrd\\Desktop\\Java\\teamProject\\player\\Urban Zakapa - Beautiful Day.mp3");
							Thread.sleep(2000);
							System.out.println(".----------------.  .----------------.  .----------------.  .----------------. \r\n" + 
									"| .--------------. || .--------------. || .--------------. || .--------------. |\r\n" + 
									"| |   _____      | || |     ____     | || | ____   ____  | || |  _________   | |\r\n" + 
									"| |  |_   _|     | || |   .'    `.   | || ||_  _| |_  _| | || | |_   ___  |  | |\r\n" + 
									"| |    | |       | || |  /  .--.  \\  | || |  \\ \\   / /   | || |   | |_  \\_|  | |\r\n" + 
									"| |    | |   _   | || |  | |    | |  | || |   \\ \\ / /    | || |   |  _|  _   | |\r\n" + 
									"| |   _| |__/ |  | || |  \\  `--'  /  | || |    \\ ' /     | || |  _| |___/ |  | |\r\n" + 
									"| |  |________|  | || |   `.____.'   | || |     \\_/      | || | |_________|  | |\r\n" + 
									"| |              | || |              | || |              | || |              | |\r\n" + 
									"| '--------------' || '--------------' || '--------------' || '--------------' |\r\n" + 
									" '----------------'  '----------------'  '----------------'  '----------------'");
							System.out.println();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(s2.getAnswer());
					} else if (cnt < 80) {
						index = 22;
						Story s = new Story(index);
						Story s2 = dao.WomanStory(s);
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							mp3.play("C:\\Users\\smhrd\\Desktop\\Java\\teamProject\\player\\윤하 - 오늘 헤어졌어요.mp3");
							Thread.sleep(2000);
							System.out.println("                             \r\n" + 
									".-----.-----.----.----.--.--.\r\n" + 
									"|__ --|  _  |   _|   _|  |  |\r\n" + 
									"|_____|_____|__| |__| |___  |\r\n" + 
									"                      |_____|");
							System.out.println();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(s2.getAnswer());
					}
					
					try {
						c.inputRank(m, cnt);
						System.out.println();
						Thread.sleep(1000);
						System.out.println("10초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("9초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("8초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("7초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("6초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("5초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("4초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("3초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("2초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("1초후에 종료됩니다.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(mp3.isPlaying()) {
						mp3.stop();
					}
					System.exit(0);
				} else if (gender == 2) {
					while (true) {
						// 1 . Story를 가지고 오기
						Story s = new Story(index);
						Story s2 = dao.ManStory(s);
						System.out.println(s2.getAnswer());

						// 2. Stroy에 해당하는 Answer 가지고 오기
						s3 = dao.ManAnswer(s);
						System.out.println(s3.getAnswer() + "\t" + s3.getWrong());

						if (index != 2 && index != 12) {
							System.out.print("[1] or [2] 선택하세요 >> ");
							input = sc.nextInt(); // 답을 입력
							System.out.println();
						}

						// 3-1. input == 1 --> woman_answer가 출력
						// 3-2. input ==2 --> woman_wrong이 출력
						if (input == 1) {
							if(index!=2) {
								System.out.println(s3.getWoman_Answer());
							}
							if (index == 1) {
								cnt += 10;
							}
							if (index == 2) {
								check = dao.eventGame1();
								if (check == 1) {
									cnt += 10;
								} else {
									cnt -= 20;
								}
								if(check==1) {
									System.out.println(s3.getWoman_Answer());
								}else {
									System.out.println(s3.getWoman_Wrong());
								}
							}
							if (index == 3) {
								cnt += 10;
							}
							if (index == 4) {
								cnt -= 10;
							}
							if (index == 5) {
								cnt += 10;
							}
							if (index == 6) {
								cnt += 10;
							}
							if (index == 7) {
								cnt += 10;
							}
							if (index == 8) {
								cnt += 10;
							}
							if (index == 9) {
								cnt -= 10;
							}
							if (index == 10) {
								cnt += 10;
							}
							if (index == 11) {
								cnt -= 10;
							}
							if (index == 12) {
								checkc = dao.eventGame2();
								if(checkc) {
									cnt += 20;
								}else {
									cnt -= 100;
								}
								break;
							}
						} else if (input == 9) {
							c.save(index, m,gender,cnt);
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							System.exit(0);
						} else {
							if(index!=2) {
								System.out.println(s3.getWoman_Wrong());
							}
							
							if (index == 1) {
								cnt -= 10;
							}
							if (index == 2) {
								check = dao.eventGame1();
								if (check == 1) {
									cnt += 5;
								} else {
									cnt -= 20;
								}
								if(check==1) {
									System.out.println(s3.getWoman_Answer());
								}else {
									System.out.println(s3.getWoman_Wrong());
								}
							}
							if (index == 3) {
								cnt -= 10;
							}
							if (index == 4) {
								cnt += 10;
							}
							if (index == 5) {
								cnt -= 10;
							}
							if (index == 6) {
								cnt -= 10;
							}
							if (index == 7) {
								cnt -= 10;
							}
							if (index == 8) {
								cnt -= 10;
							}
							if (index == 9) {
								cnt += 10;
							}
							if (index == 10) {
								cnt -= 10;
							}
							if (index == 11) {
								cnt += 10;
							}
							if (index == 12) {
								checkc = dao.eventGame2();
								if(checkc) {
									cnt += 20;
								}else {
									cnt -= 100;
								}
								break;
							}
						}
						// 총 스코어
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							Thread.sleep(2000);
							System.out.println("♡");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(1000);
							System.out.println("♥  ♡   ♡   ♡   ♡   ♡   Next Stage ♡   ♡   ♡   ♡   ♥");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(300);
							System.out.println("♡");
							Thread.sleep(700);
							System.out.println("♥  ♡   ♡   ♡   ♡   ♡   ♡  ♡   ♡  ♡   ♡   ♡   ♡   ♥ ");
							System.out.println();
							System.out.println();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 다음 스토리로 넘어가겠다.
						index++;
					}
					if (cnt >= 80) {
						index = 21;
						Story s = new Story(index);
						Story s2 = dao.ManStory(s);
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							mp3.play("C:\\Users\\smhrd\\Desktop\\Java\\teamProject\\player\\Urban Zakapa - Beautiful Day.mp3");
							Thread.sleep(2000);
							System.out.println(".----------------.  .----------------.  .----------------.  .----------------. \r\n" + 
									"| .--------------. || .--------------. || .--------------. || .--------------. |\r\n" + 
									"| |   _____      | || |     ____     | || | ____   ____  | || |  _________   | |\r\n" + 
									"| |  |_   _|     | || |   .'    `.   | || ||_  _| |_  _| | || | |_   ___  |  | |\r\n" + 
									"| |    | |       | || |  /  .--.  \\  | || |  \\ \\   / /   | || |   | |_  \\_|  | |\r\n" + 
									"| |    | |   _   | || |  | |    | |  | || |   \\ \\ / /    | || |   |  _|  _   | |\r\n" + 
									"| |   _| |__/ |  | || |  \\  `--'  /  | || |    \\ ' /     | || |  _| |___/ |  | |\r\n" + 
									"| |  |________|  | || |   `.____.'   | || |     \\_/      | || | |_________|  | |\r\n" + 
									"| |              | || |              | || |              | || |              | |\r\n" + 
									"| '--------------' || '--------------' || '--------------' || '--------------' |\r\n" + 
									" '----------------'  '----------------'  '----------------'  '----------------'");
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(s2.getAnswer());
					} else if (cnt < 80) {
						index = 22;
						Story s = new Story(index);
						Story s2 = dao.ManStory(s);
						try {
							System.out.println(" ♡ 현재 당신에게 호감도는 ♡  " + cnt + "점입니다");
							if(mp3.isPlaying()) {
								mp3.stop();
							}
							mp3.play("C:\\Users\\smhrd\\Desktop\\Java\\teamProject\\player\\윤하 - 오늘 헤어졌어요.mp3");
							Thread.sleep(2000);
							System.out.println("                             \r\n" + 
									".-----.-----.----.----.--.--.\r\n" + 
									"|__ --|  _  |   _|   _|  |  |\r\n" + 
									"|_____|_____|__| |__| |___  |\r\n" + 
									"                      |_____|");
							System.out.println();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(s2.getAnswer());
					}
					try {
						c.inputRank(m, cnt);
						System.out.println();
						Thread.sleep(1000);
						System.out.println("10초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("9초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("8초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("7초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("6초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("5초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("4초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("3초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("2초후에 종료됩니다.");
						Thread.sleep(1000);
						System.out.println("1초후에 종료됩니다.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if(mp3.isPlaying()) {
						mp3.stop();
					}
				
					System.exit(0);
				}

			} else if (input == 4) {
				c.ranking();
				continue;
			} else if (input == 5) {
				System.exit(0);
			} else {
				break;
			}

		}

		
	}

}
