package com.czsm.service.buyer;



import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.StringMsg;

public interface BuyerLoginService {
	public boolean hasUserByAccont(String accont);

	//查询用户名是否存在
	public StringMsg usernameExise(String username);

	//登录
	public BuyerUserInfo login(BuyerUserInfo info);

	//查询买家输入的手机号码或邮箱号码是否已存在
	public String accountExise(BuyerUserInfo info);
	
	//注册
	public String signup(BuyerUserInfo info);

	//获取邮箱验证码
	public String emailVcode(String email);

	//获取短信验证码
	public String smsVcode(String tel);

	
}
