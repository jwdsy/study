package cn.miao.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 签名工具类
 * @author huiwu
 *
 */
public final class SignUtil {
	
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);
	/**
	 * 默认的签名密钥
	 */
	private static final String DEFAULT_SECRET = "#a@!bcd<>ef@%^_g+-=*(7aweqweqwkjl$32123";
	
	/**
	 * 默认的签名参数名称
	 */
	private static final String SIGN_PARAM_NAME = "sign";
	
	private static final SignMethod SIGN_METHOD = SignMethod.MD5;
	
	public enum SignMethod{
		MD5,SHA1
	}
	
	private String signName = null;
	
	private SignMethod signMethod = null;
	
	private SignUtil(){
		this(SIGN_PARAM_NAME,SIGN_METHOD);
	}
	
	private SignUtil(String signName, SignMethod signMethod){
		this.signName = signName;
		this.signMethod = signMethod;
	}
	
	
	public static SignUtil build(){
		return new SignUtil();
	}
	
	public static SignUtil build(String signName){
		if (StringUtils.isBlank(signName)) {
			throw new NullPointerException("Sign param name is null");
		}
		return new SignUtil(signName,SIGN_METHOD);
	}
	
	public static SignUtil build(SignMethod signMethod){
		if (signMethod==null) {
			throw new NullPointerException("Sign param name is null");
		}
		return new SignUtil(SIGN_PARAM_NAME,signMethod);
	}
	
	public static SignUtil build(String signName,SignMethod signMethod){
		if (StringUtils.isBlank(signName)||signMethod==null) {
			throw new NullPointerException("Sign param name is null");
		}
		if (signMethod!= SignMethod.MD5&&signMethod!= SignMethod.SHA1) {
			throw new IllegalArgumentException("Not support sign method "+signMethod);
		}
		return new SignUtil(signName,signMethod);
	}
	
	/**
	 * 使用默认的secret对data签名
	 * @param data
	 * @return
	 */
	public  String sign(Map<String,Object> data){

		return sign(data, DEFAULT_SECRET);
	}
	
	/**
	 * 对data使用signKey进行签名
	 * @param data
	 * @param signSecret
	 * @return
	 */
	public  String 	sign(Map<String,Object> data,String signSecret){
		String req = mapToUrlParam(data, signSecret);
		logger.info("验签字符串是:{}", req);
		if (signMethod== SignMethod.MD5) {
			return DigestUtils.md5Hex(req).toUpperCase();
		}else{
			return DigestUtils.sha1Hex(req).toUpperCase();
		}
	}

	
	/**
	 * 使用默认的KEY签名
	 * @param data 格式如a=1&b=2&c=3
	 * @return
	 */
	public  String sign(String data){
		if (StringUtils.isBlank(data)) {
			return null;
		}
		return sign(data,DEFAULT_SECRET);
	}
	
	/**
	 * 
	 * @param data 格式如a=1&b=2&c=3
	 * @param signSecret 签名key
	 * @return
	 */
	public  String sign(String data,String signSecret){
		if (StringUtils.isBlank(data)) {
			return null;
		}
		Map<String,Object> params = urlParamToMap(data);
		return sign(params, signSecret);
	}
	
	
	public  boolean validate(String data,String signSecret){
		if (StringUtils.isBlank(data)||StringUtils.isBlank(signSecret)) {
			return false;
		}
		Map<String,Object> params = urlParamToMap(data);
		return validate(params, signSecret);
	}
	
	public  boolean validate(String data){
		return validate(data, DEFAULT_SECRET);
	}
	
	/**
	 * 验证签名是否正确，data中必须有签名参数
	 * @param data
	 * @return
	 */
	public  boolean validate(Map<String,Object> data){
		return validate(data, DEFAULT_SECRET);
	}
	
	/**
	 * 验证签名是否正确，data中必须有签名参数
	 * @param data
	 * @param signSecret
	 * @return
	 */
	public  boolean validate(Map<String,Object> data,String signSecret){
		if (data==null||data.isEmpty()||!data.containsKey(signName)) {
			return false;
		}
		Object sign = data.get(signName);
		data.remove(signName);
		return sign.equals(sign(data, signSecret));
	}
	
	private static String mapToUrlParam(Map<String, Object> data, String signSecret) {
		MapRemoveNullUtil.removeNullEntry(data);
		String str = Joiner.on("#").withKeyValueSeparator("=").join(data);
        List<String> list = Lists.newArrayList(Splitter.on("#").trimResults().splitToList(str));
        Collections.sort(list);
        String req = Joiner.on("&").join(list).concat(signSecret);
		return req;
	}
	
	private static Map<String,Object> urlParamToMap(String data){
		if (StringUtils.isBlank(data)) {
			return null;
		}
		Map<String,String> params = Splitter.on("&").omitEmptyStrings().withKeyValueSeparator("=").split(data);
		Map<String,Object> m = Maps.newHashMap();
		if (params!=null&&!params.isEmpty()){
			params.forEach((k,v)-> {if (StringUtils.isNotBlank(v)) {
				m.put(k,v);
			}});
		}
		return m;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		Map<String, Object> data = Maps.newHashMap();
		data.put("open_appid", "miaojiankang");
		data.put("timestamp", "1484645145802");
		data.put("open_id", "98056e73805befb36f1ea0267027658b");
		data.put("access_token", "1184c32ece5e0d2ccd78fd3385598543");
		data.put("device_sn", "MIO_LINK");
		data.put("device_no", "E0:68:58:E8:1C:9F");
		JSONArray ja = new JSONArray();
		JSONObject jo = null;
		for (int i = 50; i < 100; i++) {
			jo = new JSONObject();
			jo.put("value", i);
			jo.put("time", "14846451458"+i);
			ja.add(jo);
		}
		data.put("heart", ja.toJSONString());
		System.out.println(ja.toJSONString());
		String secret = "696f4bcc219a03b28044e0ce1cfd6b0a";
		String req = mapToUrlParam(data, secret);
		String sign = DigestUtils.md5Hex(req).toUpperCase();
		System.out.println(sign);
	}
	
	
}