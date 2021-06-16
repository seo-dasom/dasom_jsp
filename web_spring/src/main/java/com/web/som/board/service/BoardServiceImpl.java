package com.web.som.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
    private BoardRepository dao;

	@Override
	public boolean add(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public boolean update(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public boolean remove(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public List<BoardDTO> findAll() throws Exception {
//		BoardDTO dto = new BoardDTO();
//		dto.setId(-1);		// 조회 조건으로 사용 안함
//		dto.setBtype(-1);	// 조회 조건으로 사용 안함
//		dto.setAid(-1);		// 조회 조건으로 사용 안함
//		dto.setTitle("");	// 조회 조건으로 사용 안함 또는 ""임.
		
		List<BoardDTO> data = dao.selectAll();
		return data;
	}

	@Override
	public BoardDTO findId(int id) throws Exception {
		return null;
	}

	@Override
	public List<BoardDTO> findType(int btype) throws Exception {
		return null;
	}

	@Override
	public List<BoardDTO> findTitle(String title) throws Exception {
		return null;
	}

	@Override
	public List<BoardDTO> findAuthor(int aid) throws Exception {
		return null;
	}

}