package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");

		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));

		new BoardDao().delete(vo);
		WebUtils.forward(request, response, "/WEB-INF/views/board/deletecheckform.jsp");

	}

}
