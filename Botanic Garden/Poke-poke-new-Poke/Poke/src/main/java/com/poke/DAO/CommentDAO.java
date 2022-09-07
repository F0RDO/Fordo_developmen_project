package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.CommentVO;

public class CommentDAO {
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
	public ArrayList<CommentVO> CommentLoad(int post_number){
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<CommentVO> result = (ArrayList)session.selectList("CommentLoad", post_number);
		session.close();
		return result;
	}
	
	public int CommentWrite(CommentVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.insert("CommentWrite", vo);
		session.close();
		return row;
	}
	
	public int CommentDelete(int comment_num) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int row = session.delete("CommentDelete", comment_num);
		session.close();
		return row;
	}
}
