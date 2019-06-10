package com.cafe24.mysite.action.user;

import com.cafe24.mysite.action.main.MainAction;
import com.cafe24.web.mvc.Action;
import com.cafe24.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	// String s = "JoinAction";  -> string으로 create class 이름 만들기.?
	
	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if("join".equals(actionName)) {
			action = new JoinAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if("login".equals(actionName)) {
			action = new LoginAction();
		} else if("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();			
		} else if("update".equals(actionName)) {
			action = new UpdateAction();	
		} else if("updatesuccess".equals(actionName)) {
			action = new UpdateSuccessAction();
		} else {
			action = new MainAction();
		}
		return action;
	}

}
