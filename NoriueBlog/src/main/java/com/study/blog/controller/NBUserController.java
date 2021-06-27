package com.study.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.blog.exception.UserAlreadyExistException;
import com.study.blog.model.UserDto;
import com.study.blog.model.UserEntity;
import com.study.blog.service.NBUserService;

@Controller
public class NBUserController {
	
	@Autowired
	NBUserService userService;
	
	@GetMapping("/")
	public String viewHome(Model model) {
		
		
		return "home";
	}
	
	@GetMapping("/user/login")
	public String showlogInForm(Model model) {
		
		return "login";
	}
	
//	@PostMapping("/user/login")
//	public String login(Model model) {
//		
//		return "home";
//	}
	
	@GetMapping("/user/registration")
	public String showSignUpForm(Model model) {
		UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
		
		return "registration";
	}
	
	@PostMapping("/user/registration")
	public ModelAndView registerUserAccount(@ModelAttribute(name = "user") @Valid UserDto userDto,
			HttpServletRequest request, Errors errors) {
		try {
	        UserEntity registered = userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistException uaeEx) {
	    	ModelAndView mav = new ModelAndView();
	        mav.addObject("message", uaeEx.getMessage());
	        return mav;
	    }

	    return new ModelAndView("home", "user", userDto);
	}
}
