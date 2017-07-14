package cn.miao.study.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/6/23.
 */
public class TestProxy {

	public static void main(String[] args) {
		StudyService service = new StudyServiceImpl();
		StudyService stydy = (StudyService) Proxy.newProxyInstance(StudyServiceImpl.class.getClassLoader(), new Class[]{StudyService.class}, new ServiceProxy(service));
		stydy.learn();
	}

}
