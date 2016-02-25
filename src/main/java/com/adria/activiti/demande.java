package com.adria.activiti;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adria.activiti.service.MyService;

@Controller
@RequestMapping(value="/activiti/demande")
public class demande {
	
	    @Autowired
	    private MyService myService;

	    @RequestMapping(value="/add", method= RequestMethod.GET)
	    public String startProcessInstance(HttpServletRequest request) {
	    	return "demandes";
	    }

	    @RequestMapping(value="/create", method= RequestMethod.GET)
	    public String create(HttpServletRequest request) {
	    	return "Request";
	    }
}
