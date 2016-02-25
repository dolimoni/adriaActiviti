package com.adria.activiti;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/demande")
public class demande {
	
	 @Autowired
	    private MyService myService;

	    @RequestMapping(value="/demandes", method= RequestMethod.GET)
	    public String startProcessInstance(HttpServletRequest request) {
	    	return "demandes";
	    }

}
