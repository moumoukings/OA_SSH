package com.jboa.action.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;

@Controller("exitAction")
@Scope("prototype")
public class ExitAction extends BaseAction{

	private static final long serialVersionUID = -7497654782394102554L;
	
	
	@Override
	public String execute() throws Exception {
		getSession().setAttribute("SYS_EMP", null);
		return SUCCESS;
	}

}
