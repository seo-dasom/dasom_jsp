package com.web.som;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 어노테이션
@Controller
public class HomeController {
	
	// 로그 기록
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//                url 패턴
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// logger 출력 메세지 작성
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// request.setAttribute("serverTime", formattedDate);
		model.addAttribute("serverTime", formattedDate );
		
		// RequestDispatcher dp =request.getRequestDispatcher("/WEB-INF/home.jsp");
		// dp.forward(request, response);
		return "home";
	}
	
}
