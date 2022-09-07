package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.ChoiceVO;

public class ChoiceDAO {
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
	public ArrayList<ChoiceVO> ChoiceList1() {
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<ChoiceVO> result = (ArrayList)session.selectList("ChoiceList1");
		session.close();
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<ChoiceVO> ChoiceList2(){
		SqlSession session = sqlSessionFactory.openSession(true);
		ArrayList<ChoiceVO> result = (ArrayList)session.selectList("ChoiceList2");
		session.close();
		return result;
	}
}
