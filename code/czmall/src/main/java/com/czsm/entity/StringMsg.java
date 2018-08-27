package com.czsm.entity;

/**
 * JSON对象返回给前端包装类
 * 
 * @author Mac （刘平） 2018年8月24日
 */
public class StringMsg {
	private int id;
	private String msg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 设置返回消息
	 * 
	 * @param msg
	 * @return
	 */
	public static StringMsg setMsgs(String msg) {
		StringMsg str = new StringMsg();
		str.setMsg(msg);
		return str;
	}
}
