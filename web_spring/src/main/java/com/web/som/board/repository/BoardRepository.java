package com.web.som.board.repository;

import java.util.*;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.dto.BoardSearchDTO;
import com.web.som.board.dto.BoardTypeDTO;

public interface BoardRepository {
    public BoardDTO select(BoardDTO dto) throws Exception;
    public List<BoardDTO> selectAll() throws Exception;
    public List<BoardDTO> selectList(BoardDTO dto) throws Exception;
    public List<BoardDTO> selectList(BoardSearchDTO search) throws Exception;
    public boolean insert(BoardDTO dto) throws Exception;
    public boolean update(BoardDTO dto) throws Exception;
    public boolean delete(BoardDTO dto) throws Exception;
    public List<BoardTypeDTO> selectBoardTypes() throws Exception;
    public List<Map<String, String>> uploadfiles(int id) throws Exception;
}