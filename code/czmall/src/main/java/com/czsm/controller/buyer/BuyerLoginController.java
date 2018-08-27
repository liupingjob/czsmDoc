package com.czsm.controller.buyer;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czsm.entity.StringMsg;
import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.buyer.BuyerLoginService;
import com.czsm.util.MD5Util;
import com.czsm.util.Constants;


/**
 * 买家登录注册相关功能
 * 
 * @author Mac(刘平) 20180804
 */
@Controller
public class BuyerLoginController {
	@Autowired
	private BuyerLoginService loginService;

	/**
	 * 去买家登录页面
	 * 
	 * @param model 传值给页面
	 * @return 页面路径
	 */
	@RequestMapping("buyerToLogin")
	public String toLogin(Model model) {		
		return "buyer/login";
	}

	/**
	 * 买家登录时判断用户名是否存在
	 * 
	 * @param username 传入用户名
	 * @return 返回结果  success或账号不存在
	 */
	@RequestMapping("buyerUsnameExise")
	@ResponseBody
	public StringMsg usernameExise(String username) {
		return loginService.usernameExise(username);
	}

	/**
	 * 验证码是否正确
	 * 
	 * @param vcode 传入用户输入的验证码
	 * @return返回结果   验证码错误或success
	 */
	@RequestMapping("verCode")
	@ResponseBody
	public StringMsg vcode(String vcode, HttpServletRequest request) {
		String msg = "";  //接收下面判断的结果，以返回给前端
		// 这是系统的验证码
		String sVcode = (String) request.getSession().getAttribute("verifyCode");		
		if (!sVcode.equals(vcode)) {  //判断用户输入的验证码是否正确
			msg = Constants.VCODE_ERROR;  //验证码错误
		} else {
			msg = Constants.VCODE_CORRECT;   //验证码正确
		}
		return StringMsg.setMsgs(msg);
	}

	/**
	 * 买家登录
	 * 
	 * @param info  传入用户名和密码
	 * @param request  
	 * @return  返回一个结果  success或密码错误
	 */
	@RequestMapping("buyerLogin")
	@ResponseBody
	public String login(BuyerUserInfo info, HttpServletRequest request, HttpSession session) {
		
		System.out.println("用户尝试登陆：" + session);
		//将用户输入的密码进行加密
		String mpwd = MD5Util.EncoderByMd5(info.getPwd());
		info.setPwd(mpwd); //将加密后的密码放入info对象的密码属性中

		BuyerUserInfo uInfo = loginService.login(info); // 获取用户的信息		
		Integer counts = (Integer) session.getAttribute(Constants.LOGIN_COUNT_MSG);  
		System.out.println("获取的次数：" + counts);
		if (counts == null || counts == 0) {//首次登录
			if (uInfo == null) {
		   		session.setAttribute(Constants.LOGIN_COUNT_MSG, 1);  //设置登录的次数为1
				return Constants.PWD_ERROR;   //返回前端，密码错误
			} else {// 登录成功
				request.getSession().setAttribute("usinfo", uInfo);  //将登录用户的基本信息存入session中
				session.setAttribute(Constants.LOGIN_COUNT_MSG, 0);  //将登录的次数设置为0
				return Constants.SUCCESS; //返回前端，登录成功
			}
		} else if (counts >=Constants.LOGIN_COUNT) {// 密码错误大于3次 ,则需要输入验证码			
			return Constants.INPUT_VCODE;   //将生成的验证码返回给前端
		} else {
			if (uInfo == null) {  //用户信息的内容为空
				session.setAttribute(Constants.LOGIN_COUNT_MSG, ++counts); //登录次数加1
				return Constants.PWD_ERROR;  //返回前端，密码错误
			} else {				
				return Constants.SUCCESS;  //返回前端登录成功
			}
		}
		
	}
	
	/**
	 * 去买家注册页面
	 * 
	 * @param model 传值给页面
	 * @return 页面路径
	 */
	@RequestMapping("buyerToSignup")
	public String toSignup(Model model) {
		return "buyer/mailbox";
	}
	/**
	 * 买家注册时，查询该手机号码或邮箱号是否已经被注册
	 * 
	 * @param info  传入买家输入的手机号码或者邮箱号以及类型
	 * @return  返回结果  success或手机号已存在或者邮箱已存在
	 */
	@RequestMapping("buyerAccountExise")
	@ResponseBody	
	public StringMsg accountExise(BuyerUserInfo info) {
		//info.setEmail();  //测试数据，买家输入邮箱注册-邮箱号1698358976@qq.com
		//info.setuTel("13268066988");
		
		String result= loginService.accountExise(info);//将手机号或邮箱号是否存在的结果返回给前端		
		return StringMsg.setMsgs(result);  //将此对象返回给前端
	}
	
	/**
	 * 买家注册
	 * 
	 * @param info 传入买家输入的信息包括手机号码或邮箱号、密码
	 * @return  返回success或fail
	 */
	@RequestMapping("buyerSignup")
	@ResponseBody
	public StringMsg signup(BuyerUserInfo info) {
		//info.setPwd("aa");   // 测试数据  设置密码
		//info.setuTel("13200798009"); //测试数据  设置手机号码			
		
		String result=loginService.signup(info); //接收到注册的结果
		return StringMsg.setMsgs(result);//将注册的结果返回给前端
	}
	/**
	 * 发送验证码
	 * 
	 * @param info  传入手机号或者邮箱号
	 * @return  返回邮箱错误提示或空
	 */
	@RequestMapping("buyerConVcode")
	@ResponseBody
	public StringMsg buyerVcode(BuyerUserInfo info ,HttpSession session) {
		String result=Constants.VCODE_SENT; //用于接收关于邮箱的结果，返回给前端。默认已发送
		String realCode="";
		//验证邮箱的正则表达式
		String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)$";				  
		
		if(info.getEmail()==null) {  //用户使用手机号注册
			realCode=loginService.smsVcode(info.getuTel());
			
		}else {    //用户使用邮箱注册
			if(info.getEmail().matches(regex)) {  //用户输入的邮箱合法
				realCode=loginService.emailVcode(info.getEmail());  //系统生成的邮箱验证码
				
			}else {  //用户输入的邮箱不合法
				result=Constants.EMAIL_ERROR;  //提示用户：输入的邮箱有误
			}
			
		}
		session.setAttribute("realVcode", realCode);   //将系统输入的验证码放入session中
		return StringMsg.setMsgs(result);
	}
	/**
	 * 验证买家输入的短信或邮箱验证码是否正确
	 * 
	 * @param inputVcode  传入买家输入的短信或邮箱收到的验证码
	 * @param session  获取系统生成的验证码
	 * @return  返回success或验证码错误
	 */
	@RequestMapping("judgeVcode")
	@ResponseBody
	public StringMsg judgeVcode(String inputVcode ,HttpSession session) {
		String msg="";
		if(session.getAttribute("realVcode").equals(inputVcode)) {  //判断系统生成的验证码与买家输入的验证码是否一致
			 msg=Constants.VCODE_CORRECT;  //验证码正确
		}else {
			msg= Constants.VCODE_ERROR;   //验证码输入错误
		}
		return StringMsg.setMsgs(msg);
	}

}
