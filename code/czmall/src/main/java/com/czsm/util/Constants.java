package com.czsm.util;

/**
 * 常量列表  所有字段定义为常量 \n 所有静态字段定义此处规范管理
 * 
 * @author Mac(刘平) 20180806
 *
 */
public class Constants {
	public static final String ACCOUNT_ERROR = "accountError"; //账号错误
	public static final String ACCOUNT_NO_EXIST = "accountNoExist";  //账号不存在
	public static final String ACCOUNT_EXIST = "accountExist";  //账号存在
	public static final String INPUT_VCODE = "inputVcode";//请输入验证码
	public static final String VCODE_ERROR = "vcodeError";//验证码错误
	public static final String VCODE_CORRECT = "vcodeCorrect";//验证码正确
	public static final String SUCCESS = "success";   //登录成功、注册成功
	public static final String FAIL = "fail";   //登录失败、注册失败
	public static final String PWD_ERROR = "passwordError";  //密码错误
	public static final String VCODE_SENT = "sent";  //验证码
	

	public static final String PHONE_EXISE = "phoneExise";//手机号已存在
	public static final String CAN_REGISTER = "canRegister";//手机号不存在、邮箱号不存在
	public static final String EMAIL_EXISE = "emailExise"; //邮箱已存在
	public static final String EMAIL_ERROR = "inputEmailError";//您输入的邮箱有误
	
	public static final int VCODE_LENGTH = 6; // 设置短信验证码和邮箱验证码均为6位

	public static final int LOGIN_COUNT = 3;//登录的次数
	public static final String LOGIN_COUNT_MSG = "loginCount";//登录的次数

}
