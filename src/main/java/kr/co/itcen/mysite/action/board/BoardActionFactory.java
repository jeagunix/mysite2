package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			
		} else if("write".equals(actionName)) {
		
		} else {
			/* deafult(list) */
			action = new ListAction();
		}
		
		return action;
	}
}