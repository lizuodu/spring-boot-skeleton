package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserQuery;

/**
 * User模型服务接口
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public interface UserService extends DataBaseService<User> {
	
	/**
	 * 增加或修改user
	 * @param userQuery
	 * @return
	 * @throws Exception
	 */
	User addOrModifyUser(UserQuery userQuery) throws Exception;	
	
}
