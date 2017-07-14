package cn.miao.study.datatype;

import org.junit.Test;

/**
 * Created by Administrator on 2017/6/26.
 */
public class DataType implements Runnable{

	static Integer staticdata = 1;

	@Override
	public void run() {
		staticdata++;
		System.err.println("staticdata:"+staticdata);
	}

	@Test
	public void dataTypeTest() {
		Long l1 = 2444L;
		System.err.println(l1 == 2444);
	}

}
