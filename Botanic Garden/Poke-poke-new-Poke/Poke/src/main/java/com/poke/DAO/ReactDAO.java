package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.ReactVO;

public class ReactDAO {
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
	public ArrayList<ReactVO> ReactChoiceView() {
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<ReactVO> list = (ArrayList)session.selectList("ReactChoiceView");
		session.close();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<ReactVO> ReactAnswerView(int categorize_num) {
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<ReactVO> list = (ArrayList)session.selectList("ReactAnswerView", categorize_num);
		session.close();
		return list;
	}
}
