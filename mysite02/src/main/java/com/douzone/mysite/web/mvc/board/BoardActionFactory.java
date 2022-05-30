package com.douzone.mysite.web.mvc.board;



import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("write".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("view".equals(actionName)) {
			action = new ViewFormAction();
		} else {
			action = new IndexAction();
		} 
		return action;
	}

}
