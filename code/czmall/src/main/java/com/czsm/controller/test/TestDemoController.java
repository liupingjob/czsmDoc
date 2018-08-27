package com.czsm.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.czsm.entity.buyer.BuyerUserInfo;
import com.czsm.service.test.TestService;

/**
 * demo
 * 
 * @author Mac(刘平) 20180730
 */
@Controller
@RequestMapping("test")
public class TestDemoController {
	@Autowired
	private TestService testService;

	/**
	 * 普通返回页面和数据写法
	 * 
	 * @return
	 */
	@RequestMapping("test1")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		List<BuyerUserInfo> list = testService.findUserPager(2, 3);
		System.out.println("===========" + list.size());
		mav.addObject("list", list);
		mav.setViewName("test");
		return mav;
	}

	/**
	 * AJAX请求返回页面和数据写法
	 * 
	 * @return
	 */
	@RequestMapping("test2")
	@ResponseBody
	public List<BuyerUserInfo> testAjax(int pageNum) {
		List<BuyerUserInfo> list = testService.findUserPager(pageNum, 3);
		return list;
	}

	/**
	 * 店铺使用动态二级域名 获取动态url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shop/{shopName}", method = RequestMethod.GET)
	@ResponseBody
	public List<BuyerUserInfo> testPath(@PathVariable String shopName) {
		System.out.println(shopName);
		List<BuyerUserInfo> list = testService.findUserPager(3, 3);
		return list;
	}

}