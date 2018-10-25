package com.example.demo.mapper.master;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.mapper.BaseMapper;
import com.example.demo.model.User;

/**
 * User增删改查
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
