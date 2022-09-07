package com.poke.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poke.DAO.ReactDAO;
import com.poke.domain.ReactVO;

public class ViewReactAnswerTestController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int categorize_num = Integer.parseInt(request.getParameter("categorize_num"));
		ReactDAO dao = new ReactDAO();
		ArrayList<ReactVO> list = dao.ReactAnswerView(categorize_num);
		request.setAttribute("list", list);
		return "ReactAnswerTest";
	}

}
