package cn.hj.service;

import java.util.List;

import cn.hj.entity.User;

public interface UserService {
	/**
	 * 增加用户
	 * @param user
	 */
	public void add(User user);
	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> userLogin(String username,String password);
	
	/**
	 * 获取所有用户的信息
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	public String deleteUser(int id);
	/**
	 * 根据id更新信息
	 * @param user
	 * @return
	 */
	int updateByPrimaryKeySelective(User user);
	/**
	 * 
	 * @param username
	 * @return
	 */
	List<User> findUserByName(User user);
}
