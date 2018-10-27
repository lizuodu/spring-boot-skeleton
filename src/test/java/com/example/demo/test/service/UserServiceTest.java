package com.example.demo.test.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.demo.Application;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.test.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest extends BaseTest {
	
	@Autowired private UserService userService;
	
	@Test
	public void testFindAll() {
		List<User> userList = this.userService.findAll();
		assertNotNull(userList);
		dumpResult(userList);
	}

}
