package cn.miao.test;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest2 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "健康晚报1q?";
		System.err.println(str.getBytes("GBK").length);
	}

}