package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * User页面控制器
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Controller
@RequestMapping("/user")
public class UserController {
		
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("controller", "user");
		mav.setViewName("user/index");
		return mav;
	}

}
