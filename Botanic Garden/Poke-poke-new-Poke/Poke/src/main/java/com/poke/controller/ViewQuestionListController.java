package com.poke.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.poke.DAO.QuestionDAO;
import com.poke.domain.QuestionVO;

public class ViewQuestionListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int Question_num = Integer.parseInt(request.getParameter("Question_num"));
		QuestionDAO dao = new QuestionDAO();
		ArrayList<QuestionVO> list = dao.ViewQuestionList(Question_num);
		Gson g = new Gson();
		String json = g.toJson(list);
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		return "NotPageMove";
	}

}
