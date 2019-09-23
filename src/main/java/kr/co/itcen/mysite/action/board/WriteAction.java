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
		int hit = 0;
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
		int oNo;
		int gNo;
		int depth;
		
		if(!flag) {
			oNo = 1;
			gNo = 0;
			depth = 0;
		
			BoardVo vo = new BoardVo();
			vo.setUserNo(userNo);
			vo.setTitle(title);
			vo.setContents(content);
			vo.setHit(hit);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);

			new BoardDao().insert(vo, flag);
			
		} else {
			oNo = Integer.parseInt(request.getParameter("oNo")) +1;
			gNo = Integer.parseInt(request.getParameter("gNo"));
			depth = Integer.parseInt(request.getParameter("depth")) +1;
			
			BoardVo vo = new BoardVo();
			vo.setUserNo(userNo);
			vo.setTitle(title);
			vo.setContents(content);
			vo.setHit(hit);
			vo.setgNo(gNo);
			vo.setoNo(oNo);
			vo.setDepth(depth);
			
			new BoardDao().replyUpdate(vo);
			new BoardDao().insert(vo, flag);
			
		}
		
	
		
		response.sendRedirect(request.getContextPath() + "/board?kwd=");
	}

}
