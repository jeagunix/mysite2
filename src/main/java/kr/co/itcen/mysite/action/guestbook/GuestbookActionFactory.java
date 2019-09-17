package kr.co.itcen.mysite.action.guestbook;

import java.util.List;

import kr.co.itcen.mysite.action.main.MainAction;
import kr.co.itcen.mysite.action.user.JoinAction;
import kr.co.itcen.mysite.action.user.JoinFormAction;
import kr.co.itcen.mysite.action.user.JoinSuccessAction;
import kr.co.itcen.mysite.action.user.LoginAction;
import kr.co.itcen.mysite.action.user.LoginFormAction;
import kr.co.itcen.mysite.action.user.LogoutAction;
import kr.co.itcen.mysite.action.user.UpdateAction;
import kr.co.itcen.mysite.action.user.UpdateFormAction;
import kr.co.itcen.mysite.dao.GuestbookDao;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.web.WebUtils;
import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("add".equals(actionName)) {
				action = new AddAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteformAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new ListFormAction();
		}

		return action;
	}

}
