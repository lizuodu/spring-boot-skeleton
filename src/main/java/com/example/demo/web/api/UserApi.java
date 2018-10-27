package com.example.demo.web.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.annotation.Dto;
import com.example.demo.dto.PageBean;
import com.example.demo.dto.Result;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.model.UserQuery;
import com.example.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 提供restful风格的User操作api
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@RestController
@RequestMapping(value = "/api")
public class UserApi {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private UserService userService;
	
	/**
	 * 获取用户列表
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Dto(toType = UserDto.class)
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	public Result<Object> getAllV1() 
			throws InstantiationException, IllegalAccessException {
		List<User> listUser = this.userService.findAll();
		//List<UserDto> listUserDto = BeanUtil.mapList(listUser, UserDto.class);
		return Result.ok(listUser);
	}

	/**
	 * 获取用户列表
	 * @param pageNum 页码
	 * @param pageSize 每页记录数
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Dto(toType = UserDto.class)
	@RequestMapping(value = "/v2/users", method = RequestMethod.GET)
	public Result<Object> getAllV2(
			@RequestParam(required=true,defaultValue="1") Integer pageNum,
			@RequestParam(defaultValue="10") Integer pageSize)
			throws InstantiationException, IllegalAccessException {
		LOGGER.debug(pageNum + " " + pageSize);
		Page<User> page = PageHelper.startPage(pageNum, pageSize);
		this.userService.findAll();
		PageBean<User> pageBean = new PageBean<>(page);
		//PageBean<UserDto> pageBeanDto = BeanUtil.mapPage(pageBean, UserDto.class);
		LOGGER.debug(JSON.toJSONString(pageBean));
		return Result.ok(pageBean);
	}
	
	/**
	 * 获取一个用户
	 * @param userId
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET)
	public Result<Object> getByIdV1(@PathVariable("id") Long userId) 
			throws InstantiationException, IllegalAccessException {
		User user = this.userService.findByPrimaryKey(userId);
		return Result.ok(user);
	}
	
	/**
	 * 获取一个用户
	 * @param userId
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Dto(toType = UserDto.class)
	@RequestMapping(value = "/v2/user/{id}", method = RequestMethod.GET)
	public Result<Object> getByIdV2(@PathVariable("id") Long userId) 
			throws InstantiationException, IllegalAccessException {
		User user = this.userService.findByPrimaryKey(userId);
		return Result.ok(user);
	}
	
	/**
	 * 增加修改一个用户
	 * @param user
	 * @param bindResult
	 * @return
	 * @throws Exception
	 */
	@Dto(toType = UserDto.class)
	@RequestMapping(value = "/v1/user", method = RequestMethod.POST)
	public Result<Object> addOrModifyUserV1(@RequestBody @Valid UserQuery userQuery, BindingResult bindResult){
		try {
			User user = this.userService.addOrModifyUser(userQuery);
			return Result.ok("操作完成", user);
		} catch (Exception ex) {
			return Result.fail(ex);
		}
	}

}
