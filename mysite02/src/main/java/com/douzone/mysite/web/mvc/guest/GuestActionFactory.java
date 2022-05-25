package com.douzone.mysite.web.mvc.guest;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.mysite.web.mvc.user.JoinAction;
import com.douzone.mysite.web.mvc.user.JoinFormAction;
import com.douzone.mysite.web.mvc.user.JoinSuccess;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("deleteform".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccess();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
