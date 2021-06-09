package com.web.som.controller.sp2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/multi")
public class MultipleController {
	
	@RequestMapping(value = "/first")
	public ModelAndView first(HttpServletRequest req, HttpServletResponse rsp) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sp2/first");
		mv.addObject("name", "첫번째 페이지");
		
		return mv;
	}
	
	@RequestMapping(value = "/second")
	public String second() {
		return "sp2/second";
	}
	
	@RequestMapping(value = "/third")
	public String third(Model m) {
		m.addAttribute("data", "jsp에 데이터를 넘겨 처리할 작업이 필요할 때");
		return "sp2/third";
	}
}
