package kr.co.itcen.mysite.action.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.itcen.mysite.dao.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;
import kr.co.itcen.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true); // 없으면 만들어서 줘~
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		Long userNo = authUser.getNo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int hit = 0;
		int oNo = 1;
		int depth = 0;

		BoardVo vo = new BoardVo();
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(hit);
		vo.setoNo(oNo);
		vo.setDepth(depth);

		new BoardDao().insert(vo);

		response.sendRedirect(request.getContextPath() + "/board");
	}

}
