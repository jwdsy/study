package cn.miao.test;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("启动线程"+Thread.currentThread().getName());
		// 创建一个固定大小的线程池
		ExecutorService service = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("启动线程"+Thread.currentThread().getName());
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			System.out.println("执行线程"+Thread.currentThread().getName());
			// 在未来某个时间执行给定的命令
//			service.execute(run);
			service.submit(run);
		}
		// 关闭启动线程
		service.shutdown();
		// 等待子线程结束，再继续执行下面的代码
		service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		System.out.println("all thread complete");
	}
}