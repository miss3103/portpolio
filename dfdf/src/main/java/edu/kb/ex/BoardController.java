package edu.kb.ex;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.kb.ex.dao.BoardDao;

@Controller
public class BoardController {
	
	@Autowired
	private SqlSession sqlSession;

	
	@RequestMapping("/*_list")
	public String list(Model model , HttpServletRequest request) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String uri = request.getRequestURI();
		String context= request.getContextPath();
		String reqPath = uri.substring(context.length());
		int page;
		System.out.println(reqPath);
	
		switch(reqPath) {
		case "/notify_list":
			page = Integer.parseInt(request.getParameter("page"));
			model.addAttribute("list", dao.boardList(1,page));
			model.addAttribute("count",dao.boardCount(1));
			model.addAttribute("page",page);
			return "boards/notifyBoard/list";
		case "/review_list":
			page = Integer.parseInt(request.getParameter("page"));
			model.addAttribute("review", dao.boardList(2,page));
			model.addAttribute("count",dao.boardCount(2));
			return "boards/reviewBoard/list";
		}
		return "home";
	}
	
	@RequestMapping("/*_content")
	public String contentView(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String uri = request.getRequestURI();
		String context= request.getContextPath();
		String reqPath = uri.substring(context.length());

		System.out.println(reqPath);
		
		switch(reqPath) {
		case "/notify_content":
			model.addAttribute("content", dao.boardContent(Integer.parseInt(request.getParameter("bIndent"))));
			return "boards/notifyBoard/content";
		case "/review_content":
			break;
		}
		return "home";
	}

	
//	@RequestMapping("/review_list")
//	public String review(Model model) {
//		BoardDao dao = sqlSession.getMapper(BoardDao.class);
//		model.addAttribute("review", dao.reviewDao());
//		
//		return "boards/reviewBoard/list";
//	}
	
	@RequestMapping("/write")
	public String write_view(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.writeDao(request.getParameter("bName"),request.getParameter("bTitle"),request.getParameter("bContent"));
		
		return "redirect:review";
	}
	
	
	@RequestMapping("/c_content")
	public String c_contentView(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		model.addAttribute("c_content", dao.c_contentDao(Integer.parseInt(request.getParameter("bId"))));
		
		return "c_content";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.modifyDao(request.getParameter("bName"),request.getParameter("bTitle"),request.getParameter("bContent"),Integer.parseInt(request.getParameter("bId")));
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.deleteDao(request.getParameterValues("lists"));
		
		return "redirect:list";
	}

//	@RequestMapping("/replyView")
//	public String replyView(HttpServletRequest request, Model model) {
//		BoardDao dao = sqlSession.getMapper(BoardDao.class);
//		model.addAttribute("content", dao.contentDao(Integer.parseInt(request.getParameter("bId"))));
//
//		return "reply_view";
//	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.modifyDao(request.getParameter("bName"),request.getParameter("bTitle"),request.getParameter("bContent"),Integer.parseInt(request.getParameter("bId")));
		
		return "redirect:list";
	}
}

