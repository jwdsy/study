package cn.miao.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/23.
 */
public class ServiceProxy implements InvocationHandler {

	Object target;

	public ServiceProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.err.println("");
		return null;
	}

}
