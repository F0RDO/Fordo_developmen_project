package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.CommunityVO;

public class CommunityDAO {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<CommunityVO> ViewCommunityAll() {
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<CommunityVO> list = (ArrayList)session.selectList("ViewCommunityAll");
		session.close();
		return list;
	}
	public CommunityVO ViewCommunityOne(int post_number) {
		SqlSession session = sqlSessionFactory.openSession(true);
		CommunityVO result = (CommunityVO)session.selectOne("ViewCommunityOne", post_number);
		session.close();
		return result;
	}
	public int CommunityUpdate(CommunityVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("CommunityUpdate", vo);
		session.close();
		return row;
	}
	public int CommunityDelete(int post_number) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.update("CommunityDelete", post_number);
		session.close();
		return row;
	}
	public int CommunityInsert(CommunityVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.insert("CommunityInsert", vo);
		session.close();
		return row;
	}
	public int CommunityCount(int post_number) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.insert("CommunityCount", post_number);
		session.close();
		return row;
	}
	
}
