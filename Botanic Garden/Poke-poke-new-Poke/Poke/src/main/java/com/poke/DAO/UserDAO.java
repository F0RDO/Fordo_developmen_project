package com.poke.DAO;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.UserInfoVO;

public class UserDAO {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/poke/DAO/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public UserInfoVO UserLogin(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		UserInfoVO result = session.selectOne("UserLogin", vo);
		session.close();
		return result;
	}
	public int RegisterUser(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.insert("RegisterUser", vo);
		session.close();
		return row;
	}
	public int UserSecession(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.delete("UserSecession", vo);
		session.close();
		return row;
	}
	public int UpdateNickname(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("UpdateNickname", vo);
		session.close();
		return row;
	}
	public int UpdatePassowrd(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("UpdatePassword", vo);
		session.close();
		return row;
	}
	public int UpdateEmail(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("UpdateEmail", vo);
		session.close();
		return row;
	}
	public UserInfoVO FindId(String email) {
		SqlSession session = sqlSessionFactory.openSession(true);
		UserInfoVO vo = session.selectOne("FindId", email);
		session.close();
		return vo;
	}
	public UserInfoVO FindPasswordCheck(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		UserInfoVO result = session.selectOne("FindPasswordCheck", vo);
		session.close();
		return result;
	}
	public int ChangePassword(UserInfoVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("ChangePassword", vo);
		session.close();
		return row;
	}
}
