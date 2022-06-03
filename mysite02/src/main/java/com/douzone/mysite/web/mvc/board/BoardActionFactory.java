package com.douzone.mysite.web.mvc.board;



import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("write".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("view".equals(actionName)) {
			action = new ViewFormAction();
		} else if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("search".equals(actionName)) {
			action = new SearchAction();
		} else {
			action = new IndexAction();
		} 
		return action;
	}

}
