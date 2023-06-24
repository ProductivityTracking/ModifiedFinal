package com.prodmon.pennant.spring.orm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prodmon.pennant.spring.orm.model.Role;
import com.prodmon.pennant.spring.orm.model.User;

@Controller
public class LoginController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex(Model model) {
		return "login";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verifyUser(@Validated User user, Model model, HttpSession session) {
		System.out.println("Users data");
		Role userRole = new Role();
		userRole.setRoleId((short) 1);
		user.setUserRole(userRole);
		session.setAttribute("user", user);
		return "productivity";
	}

	@RequestMapping(value = "/a", method = RequestMethod.GET)
	public String a(Model model, HttpSession session) {
		User user = new User();
		user.setUserEmployeeId("1");
		user.setUserDisplayName("bhargav");
		user.setUserPassword("hello");

		session.setAttribute("user", user);
		return "a";
	}

	@RequestMapping(value = "getEmail", method = RequestMethod.GET)
	public String getEmail(Model model, HttpSession session) {
		User user = new User();
		user.setUserEmployeeId("1");
		user.setUserDisplayName("bhargav");
		user.setUserPassword("hello");
		session.setAttribute("user", user);
		return "a";
	}

}
