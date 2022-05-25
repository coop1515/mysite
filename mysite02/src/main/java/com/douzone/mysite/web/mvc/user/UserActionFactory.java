package com.douzone.mysite.web.mvc.user;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
		}else if("".equals(actionName)) {
//			action 
		} else {
			action = new DefaultAction();
		}
		return action;
	}

}
