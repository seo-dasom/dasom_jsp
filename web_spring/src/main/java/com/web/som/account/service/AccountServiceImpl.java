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
