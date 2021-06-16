package com.web.som.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.som.board.dto.BoardDTO;
import com.web.som.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	/**
	 * 빈 객체를 사용하여 BoardService 생성
	 */
	@Autowired
    private BoardService board;

	/**
	 * 게시판 메인 화면을 생성하기 위한 메서드
	 * /board 주소로 요청할 때 기능 동작
	 * /WEB-INF/views/board/main.jsp 로 포워딩 시킴
	 * @return
	 */
	@RequestMapping(value = "")
    public ModelAndView main() throws Exception {
        ModelAndView mv = new ModelAndView();
        
        List<BoardDTO> boardlist = board.findAll();
        
        mv.setViewName("board/main");
        mv.addObject("boardlist", boardlist);
		return mv;
    }

    public ModelAndView write() {
        return null;
    }

    public ModelAndView modify(int id) {
        return null;
    }

    public ModelAndView delete(int id) {
        return null;
    }

    public ModelAndView search(String title, int btype, int aid) {
        return null;
    }

}