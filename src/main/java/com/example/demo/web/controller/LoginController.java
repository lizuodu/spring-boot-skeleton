package com.example.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Login页面控制器
 * 
 * @author lizuodu
 * @date 2018/10/06
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/index");
		return mav;
	}

}
