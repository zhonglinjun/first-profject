package com.yr.test;

import java.util.List;
import java.util.Map;

import com.yr.bean.Classes;
import com.yr.bean.User;

/**
 * 这里面的方法都需要在配置文件中与sql对应
 * 
 * @author liucong
 *
 * @date 2017年7月24日
 */
public interface UserMapper {

	public Map<String, Integer> getCount(Map<String, Integer> map);

	// 查询
	public List<User> getAll(User user);

	// 添加
	public Integer addUser(User user);

	// 修改
	public Integer updateUser(User user);

	// 删除
	public Integer deleteUser(Integer id);

	// 查询
	public List<Classes> getAllClass();

	public Classes getOneClass(Integer id);

	// 添加
	public Integer addClasses(Classes user);

	// 修改
	public Integer updateClasses(Classes user);

	// 删除
	public Integer deleteClasses(Integer id);

	public Classes getOneClasses(Integer id);

}