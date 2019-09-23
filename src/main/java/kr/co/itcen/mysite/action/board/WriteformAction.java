package kr.co.itcen.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class WriteformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = Boolean.parseBoolean((request.getParameter("flag")));

		if (!flag) {
			request.setAttribute("flag", flag);
			WebUtils.forward(request, response, "/WEB-INF/views/board/writeform.jsp");
		} else {
			
			int oNo = Integer.parseInt(request.getParameter("oNo"));
			int gNo = Integer.parseInt(request.getParameter("gNo"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			
			request.setAttribute("flag", flag);
			request.setAttribute("oNo", oNo);
			request.setAttribute("gNo", gNo);
			request.setAttribute("depth", depth);
			WebUtils.forward(request, response, "/WEB-INF/views/board/writeform.jsp");
		}

	}

}
