package cn.miao.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("asdflkdsflkjdaslk");
		list.add("asdflk3434lkjdaslk");
		list.add("asdf3lkds56flkjdaslk");
		list.add("asdfl223kdsflkjdaslk");
		list.add("asdfl44kdsflkj77daslk");
		list.add("rasdr44flkdsflykjdaslk");
		list.stream().map(String::toLowerCase).forEach(System.out::println);

		List<String> collect2 = list.stream().map((str) -> str.toLowerCase()).collect(Collectors.toList());
		System.err.println(JSON.toJSONString(collect2));
	}

}