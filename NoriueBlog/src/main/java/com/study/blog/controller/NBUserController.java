package com.study.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.blog.exception.UserAlreadyExistException;
import com.study.blog.model.UserDto;
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
	
	@GetMapping("/user/registration")
	public String showSignUpForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		
		return "registration";
	}
	
	@PostMapping("/user/registration")
	public String registerUserAccount(@Valid @ModelAttribute(name = "user") UserDto user, BindingResult bindingResult,
			Model model) {
		
		// When error occur, must return view not redirect
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		
        try {
        	userService.registerNewUserAccount(user);
        } catch(UserAlreadyExistException e) {
        	model.addAttribute("userAlreadyExist", e.getMessage());
        	return "registration";
        }
        
        return "redirect:/";
	}
}
