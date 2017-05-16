package cn.miao.test;

import cn.miao.exception.HttpExeption;
import cn.miao.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class MiaoPlusTest {

	private static String url = "http://openapitest.miaomore.com";
	private static String appid = "101";
	private static String open_appid = "miaojiankang";
	private static String open_appsecret = "696f4bcc219a03b28044e0ce1cfd6b0a";

	public static void main(String[] args) throws HttpExeption {
		Map<String, Object> headers = new HashMap<>();
		headers.put("gid", "021fe2b5f0c1ffcfb8600a342eff8a49009");
		headers.put("open_appid", appid);
		headers.put("plat", "2");
		headers.put("sver", "59280");
		headers.put("sys", "androidd");
		headers.put("sysver", "5.1.1.2");
		headers.put("mfo", "smartisan1");
		headers.put("mfov", "SM8011");
		Map<String, Object> params = new HashMap<>();
		params.put("open_appid", open_appid);
		params.put("user_id", 40979);
		params.put("timestamp", System.currentTimeMillis());
		String sign = SignUtil.build(SignUtil.SignMethod.MD5).sign(params, open_appsecret);
		params.put("sign", sign);
		JSONObject jsonObject = HttpClientUtil.buildPostRequest(url+"/v1/user/token").addParams(params).executeToJson();
		System.err.println(jsonObject);
	}

}
