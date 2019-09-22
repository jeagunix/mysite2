package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		/* index(list) */
		List<BoardVo> list = new BoardDao().getList();
		request.setAttribute("list", list);
		System.out.println(list);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}