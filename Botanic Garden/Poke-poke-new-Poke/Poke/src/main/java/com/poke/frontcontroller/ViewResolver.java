package com.poke.frontcontroller;

public class ViewResolver {
	public static String makeView(String viewname) {
		return "/WEB-INF/BotanicGarden/"+viewname+".jsp";
	}
}
