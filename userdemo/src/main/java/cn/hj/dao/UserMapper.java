package cn.hj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hj.entity.User;

public interface UserMapper {
	public void add(User user);

	public void delete(int id);

	public void update(User user);

	public User getAllById(int id);

	
	/**
     * 获取用户所有信息
     * @return
     */
    List<User> getAllUser();
	/**
	 * 根据用户名和密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	public List<User> findNamePwdByUser(@Param("username") String username,@Param("password") String password);
	
	 /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 根据id更新信息
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);
    
    List<User> findUserByName(String username);

}
