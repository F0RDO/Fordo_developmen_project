package com.poke.DAO;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.Plant_care;
import com.poke.domain.PlantInsert;
import com.poke.domain.Plant_Garden;

public class Plant_careDAO {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "com/poke/DAO/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// plant_Care last_date 가져오기
	public Plant_care plant_CareSelcet(int plant_seq){
		SqlSession session = sqlSessionFactory.openSession();
		Plant_care plant_care = session.selectOne("plant_CareSelcet",plant_seq);
		session.close();
		return plant_care;
	}
	
	// 다음 식물 줄 날 계산
	public String waterNextDay(Plant_Garden plant_garden) {
		SqlSession session = sqlSessionFactory.openSession();
		String nextDate = session.selectOne("waterNextDay", plant_garden);
		session.close();
		return nextDate; 
	}

	public void plant_careInsert(Plant_care plant_care) {
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("plant_careInsert", plant_care);
		session.commit();
		session.close();
	}

	// plant_care 정보 업데이트하기 
	
	public void plant_careUpdate(Plant_care vo) {
		SqlSession session = sqlSessionFactory.openSession();
		session.update("plant_careUpdate", vo);
		session.commit();
		session.close();
	}
	
	// plant_care plantcare_Seq로 diary 와 height 값 가져오기
	public Plant_care plant_careView(int plantcare_seq) {
		SqlSession session = sqlSessionFactory.openSession();
		Plant_care plant_care = session.selectOne("plant_careView",plantcare_seq);
		session.close();
		return plant_care;
	}
	
	public void plantCareFirstInsert(PlantInsert plant) {
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("plantCareFirstInsert", plant);
		session.commit();
		session.close();
	}
	
	
	
}
