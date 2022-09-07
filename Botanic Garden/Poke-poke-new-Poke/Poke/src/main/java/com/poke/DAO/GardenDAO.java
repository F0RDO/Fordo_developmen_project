package com.poke.DAO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.poke.domain.Garden;
import com.poke.domain.Plant;
import com.poke.domain.PlantInsert;
import com.poke.domain.Plant_Garden;
import com.poke.domain.Plant_care;

public class GardenDAO {
	private static SqlSessionFactory sqlSessionFactory;
	PlantDAO Plant = new PlantDAO();
	Plant_careDAO Plant_care = new Plant_careDAO();
	static {
		try {
			String resource = "com/poke/DAO/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// garden 한 명 정보 가져오기
	public List<Garden> gardenSelect(String id){
		SqlSession session = sqlSessionFactory.openSession();
		List<Garden> garden= session.selectList("gardenSelect", id);
		session.close();
		return garden;
	}
	
	// plant_seq를 가지고 plant_number 가져오기
	
	public Garden gardenSelectOne(int plant_seq) {
		SqlSession session = sqlSessionFactory.openSession();
		Garden gardenOne = session.selectOne("gardenSelectOne",plant_seq);
		session.close();
		return gardenOne;
		
	}
	
	// 사용자가 키우는 식물의 plant_care 정보 모두 가져오기
	
	public List<List> getAllPlantList(String id){
		SqlSession session = sqlSessionFactory.openSession();
		List<Garden> list = gardenSelect(id);
		
		List<Plant_care> calendarList = null;
		List<List> result = new ArrayList<List>();
		for(Garden garden : list) {
			calendarList = session.selectList("getAllPlantList",garden.getPlant_seq());
			result.add(calendarList);
		}
		session.close();
		return result;
	}
	

	
	// 사용자별 키우는 식물 기르기 last_date와 사용자가 키우는 식물 종류 데이터 가져오기
	public ArrayList<Plant_care> waterNext(String id) {
		ArrayList<Plant_care> plant_care = new ArrayList<Plant_care>();
		Plant_Garden plant_garden = new Plant_Garden();
		Plant_care plantCare = new Plant_care();
		PlantDAO dao = new PlantDAO();
		Plant_careDAO caredao = new Plant_careDAO();
		Plant plant;
		String date;
		int plant_number;
		String plant_nickname;
		int plant_seq;
		int cycle;
		
		
		List<Garden> garden_list = gardenSelect(id);
		for(Garden garden : garden_list) {
			plant_number=garden.getPlant_number();
			plant_seq=garden.getPlant_seq();
			
			// 식물에 물주기  가져오기
			plant = dao.plantSelectOne(plant_number);
			cycle = plant.getCycle();
			plant_garden.setCycle(cycle);
			plant_garden.setPlant_seq(plant_seq);
			
			
			// 식물 마지막 물 주는 날 
			date = caredao.waterNextDay(plant_garden);
			
			// data 가져오기 및 입력
			plantCare = new Plant_care(); // 절대 빼지 말 것
			plantCare.setPlant_nickname(garden.getPlant_nickname());
			plantCare.setLast_date(date);
			plant_care.add(plantCare);
			

		}
		return plant_care;
		
	}

	
	// 식물 등록
	public void plantInsert(PlantInsert plant) {
		GardenInsert(plant);
		Plant_care.plantCareFirstInsert(plant);
	}
	
	// Gardeninsert 
	public void GardenInsert(PlantInsert plant) {
		SqlSession session = sqlSessionFactory.openSession();
		session.insert("GardenInsert",plant);
		session.commit();
		session.close();
	}
	
	// GardenDelete
	public void GardenDelete(String plant_seq) {
		SqlSession session = sqlSessionFactory.openSession();
		session.delete("GardenDelete", plant_seq);
		session.commit();
		session.close();
		
	}
	
	
		
	}

	

