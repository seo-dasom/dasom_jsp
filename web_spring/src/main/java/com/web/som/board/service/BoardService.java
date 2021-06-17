package com.web.som.board.service;

import java.util.List;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.dto.BoardTypeDTO;

public interface BoardService {
    public boolean add(BoardDTO dto) throws Exception;
    public boolean update(BoardDTO dto) throws Exception;
    public boolean remove(BoardDTO dto) throws Exception;
    public List<BoardDTO> findAll() throws Exception;
    public BoardDTO findId(int id) throws Exception;
    public List<BoardDTO> findType(int btype) throws Exception;
    public List<BoardDTO> findTitle(String title) throws Exception;
    public List<BoardDTO> findAuthor(int aid) throws Exception;
    public List<BoardTypeDTO> getBoardTypes() throws Exception;
}