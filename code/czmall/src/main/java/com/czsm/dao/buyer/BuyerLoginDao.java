package com.czsm.dao.buyer;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.czsm.entity.buyer.BuyerUserInfo;
public interface BuyerLoginDao {
	public BuyerUserInfo hasUserByAccont(String acccont);
	
	
	@Select("select * from userinfo")
	public List<BuyerUserInfo> findAllUser();


	public String usernameExise(String username);


	/**
	 * 买家登录
	 * @param info 传入用户名以及密码
	 * @return  返回null或者一个用户的基本信息
	 */
	public BuyerUserInfo findUserByUsername(BuyerUserInfo info);


	/**
	 * 买家注册
	 * @param phNum  传入注册的
	 * @param enPwd
	 */
	public int signup(BuyerUserInfo info);


	/**
	 * 买家注册时判断手机号是否已存在
	 * @param phone 传入买家输入的手机号码
	 * @return  返回一个手机号码或者空
	 */
	public String phoneExise(String phone);

	/**
	 * 买家注册时，判断买家输入的邮箱是否已存在
	 * @param mail  传入买家输入的邮箱
	 * @return  返回一个邮箱或空
	 */
	public String EmailExise(String mail);


}
