package com.douzone.mysite.web.mvc.guest;


import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("write".equals(actionName)) {
			action = new GuestBookAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
