package com.example.demo.Controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.EmploymentOppDaoImpl;

import edu.stayConnected.FBO.EmploymentOppForm;

@Scope("session")
@Controller
public class EmploymentOppController {
	
	@Autowired
	EmploymentOppDaoImpl employmentOpps;
	
	@RequestMapping(value="/postemploymentopp", method=RequestMethod.GET)
	public String showOppForm(EmploymentOppForm form, Model model) {
		model.addAttribute("type", getAllTypes());
		return "postEmploymentOpp";
	}
	
	private ArrayList<String> getAllTypes(){
		//populate radio buttons
		ArrayList<String> list = new ArrayList<>();
		list.add("full time");
		list.add("part time");
		list.add("internship");
		list.add("summer");
		return list;		
	}
	
	@RequestMapping(value="/postemploymentopp", method=RequestMethod.POST)
	public String addForm(@Valid EmploymentOppForm form, BindingResult bindingResult, Principal principal) {
		String userEmail = principal.getName();
		employmentOpps.addOpportunity(form, userEmail);
		return "home";
	}
}
