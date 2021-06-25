package com.web.som.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.dto.BoardSearchDTO;
import com.web.som.board.dto.BoardTypeDTO;
import com.web.som.board.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
    private BoardRepository dao;

	@Override
	public boolean add(BoardDTO dto) throws Exception {
		boolean res = dao.insert(dto);
		return res;
	}

	@Override
	public boolean update(BoardDTO dto) throws Exception {
		boolean res = dao.update(dto);
		return res;
	}

	@Override
	public boolean remove(BoardDTO dto) throws Exception {
		return false;
	}

	@Override
	public List<BoardDTO> findAll() throws Exception {
		List<BoardDTO> data = dao.selectAll();
		return data;
	}

	/**
	 * @param id: 게시물 식별 번호
	 * @return BoardDTO: 게시물 식별 번호로 조회된 정보를 반환
	 */
	@Override
	public BoardDTO findId(int id) throws Exception {
		BoardDTO data = new BoardDTO();
		data.setId(id);
		return dao.select(data);
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

	@Override
	public List<BoardTypeDTO> getBoardTypes() throws Exception {
		List<BoardTypeDTO> data = dao.selectBoardTypes();
		return data;
	}

	@Override
	public List<BoardDTO> findList(BoardSearchDTO search)
			throws Exception {
		List<BoardDTO> data = dao.selectList(search);
		return data;
	}

	@Override
	public List<Map<String, String>> uploadfiles(int id) throws Exception {
		return dao.uploadfiles(id);
	}

}