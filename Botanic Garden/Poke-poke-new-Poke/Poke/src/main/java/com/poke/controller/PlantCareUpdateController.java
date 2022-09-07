package com.poke.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.Plant_careDAO;
import com.poke.domain.Plant_care;

public class PlantCareUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 날짜와 nickname, 수정된 diary, height 값 가져오기
		int plantcare_seq = Integer.parseInt(request.getParameter("plantcare_seq"));
		String diary = request.getParameter("plant_diary");


		
		Plant_care vo = new Plant_care();
		vo.setPlantcare_seq(plantcare_seq);
		vo.setPlant_diary(diary);
		
		Plant_careDAO dao = new Plant_careDAO();
		dao.plant_careUpdate(vo);
		
		
		
		
		return "NotPageMove";
	}

}
