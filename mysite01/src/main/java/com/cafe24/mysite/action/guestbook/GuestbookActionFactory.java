package com.cafe24.mysite.action.guestbook;
import com.cafe24.web.mvc.Action;
import com.cafe24.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("add".equals(actionName)) {
			action = new AddAction();
		} else if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else{
			action = new ListAction();
		}
		
		return action;
	}

}
