package com.poke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.ReactDAO;
import com.poke.domain.ReactVO;

public class ViewPlantProblemController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReactDAO dao = new ReactDAO();
		ArrayList<ReactVO> list = dao.ReactChoiceView();
		request.setAttribute("list", list);
		return "PlantProblem";
	}

}