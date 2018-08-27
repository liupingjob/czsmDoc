package com.czsm.controller.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.czsm.util.VerifyCodeUtils;

/**
 * 公用工具类（验证码 短信 邮箱）
 * 
 * @author Mac (刘平) 20180804
 */
@Controller
public class CommonsController {

	/**
	 * 图片验证码生成方法 存放session
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字符串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入回话session
		HttpSession session = request.getSession(true);
		// 删除以前的验证码
		session.removeAttribute("verifyCode");
		session.setAttribute("verifyCode", verifyCode.toLowerCase());
		// 生成图片 高度 宽度
		int w = 75, h = 42;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 发送短信功能
	 * 
	 * @param phoneNum
	 * @return
	 */
	public String sentSms(String phoneNum) {
		return null;
	}

	/**
	 * 发送邮件功能
	 * 
	 * @param mailUrl
	 * @return
	 */
	public String sentMail(String mailUrl) {
		return null;
	}
}
