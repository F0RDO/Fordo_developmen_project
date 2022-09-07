package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.ViewRecommendVO;

public class ViewRecommendDAO {
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
	public ArrayList<ViewRecommendVO> ViewRecommendList(ViewRecommendVO vo) {
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<ViewRecommendVO> result = (ArrayList)session.selectList("ViewRecommendList", vo);
		session.close();
		return result;
	}
}
