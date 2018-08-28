package com.czsm.entity.buyer;

public class BuyerUserInfo {
	private int userid;   //用户编号
	private String username;   //用户名
	private String pwd;   //密码
	private String uTel;	//联系电话 
	private String vcode;   //图形验证码
	private String email;   //邮箱号
	private String inputVcode; //短信或邮箱验证码
	
	
	private String type;  //注册类型（1,2）1表示用户使用的是手机号码注册。2.表示用户使用的是邮箱注册

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getuTel() {
		return uTel;
	}

	public void setuTel(String uTel) {
		this.uTel = uTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputVcode() {
		return inputVcode;
	}

	public void setInputVcode(String inputVcode) {
		this.inputVcode = inputVcode;
	}
	
	
	
}
