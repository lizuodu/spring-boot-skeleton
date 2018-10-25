package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.mapper.master.UserMapper;
import com.example.demo.model.User;
import com.example.demo.model.UserQuery;
import com.example.demo.service.UserService;
import com.example.demo.util.BeanUtil;

/**
 * User模型服务实现类
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Service("userService")
public class UserServiceImpl extends AbstractDataBaseServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public BaseMapper<User> mapperInstance() {
		return this.userMapper;
	}

	/**
	 * 增加或修改user
	 * @param userQuery
	 * @throws Exception
	 */
	@Override
	public User addOrModifyUser(UserQuery userQuery) throws Exception {
		User user = BeanUtil.map(userQuery, User.class);
		if (user.getId() == null) {
			super.insert(user);
		} else {
			super.update(user);
		}
		return super.findByPrimaryKey(user.getId());
	}

	
}
