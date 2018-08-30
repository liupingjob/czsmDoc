package com.czsm.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Mac(刘平)
 * Created on 18/8/3. 阿里云短信API产品    
 *
 * 备注:工程编码采用UTF-8
 */
public class SmsUtil {

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	//  此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "yourAccessKeyId";
	static final String accessKeySecret = "yourAccessKeySecret";

	public static void sendSms(String phoneNum, String code, String username) {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNum);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("云通信");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_1000000");
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		username = username == null ? "创展世贸" : username;
		request.setTemplateParam("{\"name\":\"" + username + "\", \"code\":\"" + code + "\"}");

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("czsmSmsId");

		// hint 此处可能会抛出异常，注意catch
		try {
			acsClient.getAcsResponse(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClientException, InterruptedException {

		// 发短信
		sendSms("18617136779", "2345", "Mac");

	}
}
