package com.czsm.service.impl.buyer;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.czsm.dao.buyer.BuyerLoginDao;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.entity.StringMsg;
import com.czsm.service.buyer.BuyerLoginService;
import com.czsm.util.MD5Util;
import com.czsm.util.MailUtil;
import com.czsm.util.Constants;
import com.czsm.util.NumberUtil;
import com.czsm.util.SmsUtil;

@Service("LoginService")
public class BuyerLoginServiceImpl implements BuyerLoginService{
	@Resource
	private BuyerLoginDao loginDao;

	@Override
	public boolean hasUserByAccont(String accont) {
		System.out.println("测试service   "+accont);
		BuyerUserInfo info=loginDao.hasUserByAccont("ss");
		System.out.println("用户信息：" +info.getUsername()+"========"+info.getUserid());
		List<BuyerUserInfo> lists=loginDao.findAllUser();
		for (BuyerUserInfo user : lists) {
			System.out.println(user.getUsername()+"---------"+user.getUserid());
		}
		return false;
	}

	/**
	 * 买家登录判断用户名是否存在
	 */
	@Override
	public StringMsg usernameExise(String username) {
		String msg =Constants.ACCOUNT_EXIST; //返回给controller层的结果  用户名存在
		String result = loginDao.usernameExise(username);
		//System.out.println(result);
		if(result==null) { //查询的结果为空
			msg=Constants.ACCOUNT_NO_EXIST;  //返回“用户名不存在”
		}
		return StringMsg.setMsgs(msg);
	}

	/**
	 * 登录
	 */
	@Override
	public BuyerUserInfo login(BuyerUserInfo info  ) {
					
		//获得数据密码
		return loginDao.findUserByUsername(info);//查出该用户的所有信息
		 
	}
	
	/**
	 * 查询买家输入的手机号码或者邮箱是否已存在
	 */
	@Override
	public String accountExise(BuyerUserInfo info) {
		String result="";
		String phone=info.getuTel();  //获取用户输入的手机号码
		
		if(phone==null) {  //判断手机号是否为空，为空用户则使用邮箱注册
			
			String mail=info.getEmail();  //获取用户输入的邮箱
			result=loginDao.EmailExise(mail); //返回查询的结果
			if(result==null) {
				return Constants.CAN_REGISTER;
			}else {
				return Constants.EMAIL_EXISE; //邮箱号已存在
			}
		}else {	//用户使用手机号注册
						
			result=loginDao.phoneExise(phone);   //返回查询的结果
			if(result==null) {
				return Constants.CAN_REGISTER;   //可注册
			}else {
				return Constants.PHONE_EXISE;  //手机号码已存在
			}
		}		
	}

	/**
	 * 注册
	 */
	@Override
	public String signup(BuyerUserInfo info) {
		//将用户输入的密码进行MD5加密处理
		String enPwd=MD5Util.EncoderByMd5(info.getPwd());
		info.setPwd(enPwd); //将密码进行MD5加密
		
		if(info.getuTel()==null) {  //如果用户电话号码为空，将用户名设置为邮箱			
			info.setUsername(info.getEmail());
			info.setuTel("");
			
		}else {  //电话号码不为空，用户名为手机号码
			info.setUsername(info.getuTel());
			info.setEmail("");
		}
		int row = loginDao.signup(info);  //返回影响的行数
		if(row>0) {   
			return Constants.SUCCESS;  //注册成功
		}else {
			return Constants.FAIL;  //添加失败
		}
		
	}

	/**
	 * 使用邮件发送验证码
	 */
	@Override
	public String emailVcode(String email) {		
		String num=NumberUtil.getRandomNum(Constants.VCODE_LENGTH);//获取系统生成的邮箱验证码
		MailUtil.sendMail(email, num);  //向指定邮箱中发送验证码
		return num;
	}

	/**
	 * 使用手机号码发送验证码
	 */
	@Override
	public String smsVcode(String tel) {
		String num=NumberUtil.getRandomNum(Constants.VCODE_LENGTH);//获取系统生成的短信验证码
		SmsUtil.sendSms(tel, num, tel);   //向指定手机号发送验证码
		return num;
	}

	

}
