package cn.hj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hj.dao.UserMapper;
import cn.hj.entity.User;
import cn.hj.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	private final static String DELE_SUCCESS = "删除成功";
	private final static String DELE_FAIL = "删除失败";
	@Autowired
	private UserMapper userMapper;
	
	public void add(User user) {
		userMapper.add(user);
		
	}

	public List<User> userLogin(String username, String password) {
		return userMapper.findNamePwdByUser(username, password);
	}
	
	/**
	 * 获取所有用户的信息
	 */
	public List<User> getAllUser() {
		List<User> list = userMapper.getAllUser();
		return list;
	}
	
	/**
	 * 根据id删除用户
	 */
	public String deleteUser(int id) {
		if(userMapper.deleteByPrimaryKey(id) == 1) {
			return "DELE_SUCCESS";
		}else {
			return "DELE_FAIL";
		}
		
	}
	/**
	 * 根据id更新信息
	 */
	public int updateByPrimaryKeySelective(User user) {
		int flag = 0;
		if(user != null) {
			flag = userMapper.updateByPrimaryKeySelective(user);
		}
		return flag;
	}
	/**
	 * 
	 */
	public List<User> findUserByName(User user) {
		
		String username = user.getUsername();
		List<User> list = new ArrayList<User>();
		list = userMapper.findUserByName(username);		
		return list;
	}

}
