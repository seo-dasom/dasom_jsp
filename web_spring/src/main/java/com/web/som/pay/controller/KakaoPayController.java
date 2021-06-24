package com.web.som.pay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/pay")
public class KakaoPayController {

	@RequestMapping(value = "")
	public String payment() {
		String forward = "kakaopay/payment";
		return forward;
	}
	
	@RequestMapping(value = "/payment")
	public String payment(@RequestParam String orderid,
			@RequestParam String pname,
			@RequestParam String qty,
			@RequestParam String amount,
			// 임시
			HttpServletRequest req) throws Exception {
		String forward = "";
		// 주문자가 결제 요청한 상품의 정보를 확인 후 카카오페이 서버에 상품관련 결제준비 정보 전송
		
		// 상품 정보를 확인하기 위한 코드 작성 시작
		
		// 상품 정보를 확인하기 위한 코드 작성 끝
		
		// 카카오페이 서버 결제준비 정보 전송 시작
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("cid", "TC0ONETIME");
		param.add("partner_order_id", orderid);
		param.add("partner_user_id", "test_user");
		param.add("item_name", pname);
		param.add("quantity", qty);
		param.add("total_amount", amount);
		int tax_free = (Integer.parseInt(amount) / 10 * 9);
		param.add("tax_free_amount", Integer.toString(tax_free));
		param.add("approval_url", "http://localhost/som/pay/success");
		param.add("cancel_url", "http://localhost/som/pay/cancel");
		param.add("fail_url", "http://localhost/som/pay/fail");
		
		System.out.println("요청 데이터 : " + param.toString());
		
		// REST API를 사용하여 카카오페이 서버에 위의 데이터 전송
		RestTemplate rest = new RestTemplate();
		rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 56f1d627f2cadda295fb593c8ea1e09f");
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
		
		String url = "https://kapi.kakao.com/v1/payment/ready";
		ResponseEntity<String> resp = rest.postForEntity(url, entity, String.class);
		
		System.out.println("응답 데이터 : " + resp.getBody());
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resp_data = mapper.readValue(resp.getBody(), HashMap.class);
		
		String tid = (String)resp_data.get("tid");	// 데이터 베이스에 저장해야 함
		String redirect_pc = (String)resp_data.get("next_redirect_pc_url");
		
		// 임시로 작성한 코드(WAS 전역 메모리에 저장) 반드시 데이터베이스로 저장하도록 해야함.
		req.getServletContext().setAttribute("tid", tid);
		
		forward = "redirect:" + redirect_pc;
		// 카카오페이 서버 결제준비 정보 전송 끝
		
		return forward;
	}
	
	@RequestMapping(value = "/success")
	public String success(@RequestParam String pg_token,
			HttpServletRequest req) throws Exception {
		String forward = "";
		
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("cid", "TC0ONETIME");
		param.add("tid", (String)req.getServletContext().getAttribute("tid"));
		param.add("partner_order_id", "123456789");
		param.add("partner_user_id", "test_user");
		param.add("pg_token", pg_token);
		
		RestTemplate rest = new RestTemplate();
		rest.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 56f1d627f2cadda295fb593c8ea1e09f");
		headers.set("Content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(param, headers);
		
		String url = "https://kapi.kakao.com/v1/payment/approve";
		ResponseEntity<String> resp = rest.postForEntity(url, entity, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resp_data = mapper.readValue(resp.getBody(), HashMap.class);
		
		if(resp.getStatusCode() == HttpStatus.OK) {
			// 데이터베이스 저장을 위한 추가 작업
			forward = "kakaopay/success";
		} else if(resp.getStatusCode() == HttpStatus.BAD_REQUEST) {
			forward = "kakaopay/fail";
		}
		
		System.out.println(resp.getBody());
		
		return forward;
	}
}
