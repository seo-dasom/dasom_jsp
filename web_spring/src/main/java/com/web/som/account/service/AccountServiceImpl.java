package com.web.som.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.som.account.dto.AccountDTO;
import com.web.som.account.repository.AccountRepositoryImpl;

/**
 *	DAO에 데이터를 전달하기 전에 필요한 비지니스 로직을 동작시키기 위해 사용
 */
@Service	// bean에 등록
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepositoryImpl dao;
	
	@Override
	public boolean join(AccountDTO dto) throws Exception {
		return dao.insert(dto);
	}
	
	@Override
	public boolean checkNickname(String nickname) throws Exception {
		// 사용중인 닉네임을 찾는 기능
		// 사용중이면 true, 사용하지 않으면 false 반환
		boolean result = true;
		int rs = dao.usedNickname(nickname);
		if(rs == 0) {
			result = false;
		}
		return result;
	}
	
	@Override
	public boolean checkEmail(String email) throws Exception {
		// 사용중인 이메일을 찾는 기능
		// 사용중이면 true, 사용하지 않으면 false 반환
		boolean result = true;
		int rs = dao.usedEmail(email);
		if(rs == 0) {
			result = false;
		}
		return result;
	}

	@Override
	public void login(AccountDTO dto) throws Exception {
		AccountDTO data = dao.checkUser(dto);
		if(data != null) {
			// 로그인 성공
			dto.setId(data.getId());
			dto.setUsername(data.getUsername());
			dto.setNickname(data.getNickname());
			dto.setEmail(data.getEmail());
			dto.setPassword("");
			dto.setGender(data.getGender());
			dto.setAge(data.getAge());
			dto.setLogindate(data.getLogindate());
			dto.setJoindate(data.getJoindate());
		} else {
			// 로그인 실패
			dto.setId(-1);
			dto.setPassword("");
		}
	}
	
	@Override
	public AccountDTO accountInfoDetail(AccountDTO dto) throws Exception {
		// 비지니스 로직
		return dao.select(dto);
	}

	@Override
	public List<AccountDTO> accountInfoList() throws Exception {
		// 비지니스 로직
		return dao.selectList();
	}

}
