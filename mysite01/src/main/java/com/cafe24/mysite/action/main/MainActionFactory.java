package com.cafe24.mysite.action.main;

import com.cafe24.web.mvc.Action;
import com.cafe24.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
