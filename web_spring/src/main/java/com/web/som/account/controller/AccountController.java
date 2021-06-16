package com.web.som.account.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.som.account.dto.AccountDTO;
import com.web.som.account.service.AccountService;

@Controller		// bean 객체로 등록 (사용자 요청에 대한 제어(views, model 사용에 대한..)를 담당
@RequestMapping(value = "/account")	// URL 주소 맵핑
public class AccountController {
	
	@Autowired
	private AccountService account;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "account/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model m, @ModelAttribute AccountDTO dto) throws Exception {
		String forward = "";
		
		// 서비스의 join 메서드 호출
		boolean result = account.join(dto);
		
		if(result) {
			// 가입 성공 했을 때 로그인 페이지로 리다이렉트
			forward = "redirect:/account/login";
		} else {
			// 가입 실패 했을 때 회원가입 페이지 재전송
			m.addAttribute("data", dto);
			m.addAttribute(forward);
			forward = "account/join";
		}
		return forward;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "account/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model m, @ModelAttribute AccountDTO dto
			, HttpServletRequest req) throws Exception {
		String forward = "";
		account.login(dto);
		
		if(dto.getId() > 0) {
			// dto.getId() 값이 0 보다 큰경우 로그인 성공
			HttpSession session = req.getSession();
			session.setAttribute("account", dto);
			session.setAttribute("logined", true);
			
			// System.out.println(((AccountDTO)session.getAttribute("account")).getUsername());
			
			forward = "redirect:/";
		} else {
			// dto.getId() 값이 0 보다 작은 경우 로그인 실패
			m.addAttribute("data", dto);
			m.addAttribute("error", "로그인 실패");
			forward = "account/login";
		}
		
		return forward;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userList(Model m) throws Exception {
		List<AccountDTO> datas = account.accountInfoList();
		m.addAttribute("datas", datas);
		System.out.println(datas.get(0).toString());
		return "user/list";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String userDetail(Model m, @ModelAttribute AccountDTO dto) throws Exception {
		AccountDTO data = account.accountInfoDetail(dto);
		m.addAttribute("data", data);
		System.out.println(data.toString());
		return "user/detail";
	}
}
