package kr.co.itcen.mysite.action.board;

import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("writeform".equals(actionName)) {
			action = new WriteformAction();
		} else if ("write".equals(actionName)) {
			action = new WriteAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("deletecheckform".equals(actionName)) {
			action = new DeleteCheckFormAction();
		} else if ("view".equals(actionName)) {
			action = new ViewFormAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else {
			/* deafult(list) */
			action = new ListAction();
		}

		return action;
	}
}