package cn.miao.study.producerconsumer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 仓库类Storage实现缓冲区
 * <p>
 * Email:530025983@qq.com
 *
 * @author MONKEY.D.MENG 2011-03-15
 */
public class Storage {
	// 仓库最大存储量
	private final int MAX_SIZE = 100;

	private Integer serialNumber = 0;

	// 仓库存储的载体
	private LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>(100);

	// 生产num个产品
	public void produce(int num) {
		// 如果仓库剩余容量为0
		if (list.size() == MAX_SIZE) {
			System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");
		}

		// 生产条件满足情况下，生产num个产品
		for (int i = 1; i <= num; ++i) {
			try {
				synchronized (serialNumber) {
					System.err.println("当前生产产品：" + serialNumber);
					// 放入产品，自动阻塞
					list.put(serialNumber);
					serialNumber++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("【现仓储量为】:" + list.size());
		}
	}

	// 消费num个产品
	public void consume(int num) {
		// 如果仓库存储量不足
		if (list.size() == 0) {
			System.out.println("【库存量】:0/t暂时不能执行生产任务!");
		}

		// 消费条件满足情况下，消费num个产品
		for (int i = 1; i <= num; ++i) {
			try {
				// 消费产品，自动阻塞
				Integer take = list.take();
				System.err.println("当前消费产品:"+take);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("【现仓储量为】:" + list.size());
	}

	// set/get方法
	public LinkedBlockingQueue<Integer> getList() {
		return list;
	}

	public void setList(LinkedBlockingQueue<Integer> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
}
