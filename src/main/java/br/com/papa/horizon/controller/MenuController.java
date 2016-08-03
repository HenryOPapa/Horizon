package br.com.papa.horizon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * 
 * @author henry.papa
 *
 */


@Controller
@RequestMapping("menu")
public class MenuController {
	
	@RequestMapping
	public ModelAndView menu(HttpSession httpSession) throws Exception {
		Gson gson = new Gson();
		Map<String, Object> retorno = new HashMap<String, Object>();
		return new ModelAndView("menu").addObject("result",
				gson.toJson(retorno));
	}

	

}
