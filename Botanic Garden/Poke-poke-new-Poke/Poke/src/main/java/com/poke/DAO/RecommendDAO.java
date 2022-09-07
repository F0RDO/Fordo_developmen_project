package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.RecommendVO;

public class RecommendDAO {
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
	
	public ArrayList<RecommendVO> ViewFileLocation(String[] recommend_num) {
		RecommendVO vo = new RecommendVO();
		RecommendVO result = new RecommendVO();
		ArrayList<RecommendVO> list = new ArrayList<RecommendVO>();
		SqlSession session = sqlSessionFactory.openSession(true);
		for(int i =0; i<recommend_num.length; i++) {
			vo.setRecommend_num(Integer.parseInt(recommend_num[i]));
			result = (RecommendVO)session.selectOne("ViewFileLocation", vo);
			list.add(result);
		}
		session.close();
		return list;
	}
}
