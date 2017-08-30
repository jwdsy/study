package cn.miao.test;

import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * @param <K>
 * @param <E>
 * @author 张飞
 * @version 1.0
 * @Description: 消息服务
 * @ClassName: MessageService
 * @date 2016年2月15日 下午6:25:15
 */
public class MiaoKafkaProducer<K, E> {

	static String topic = "topic200";

	public static void main(String[] args) {
//		String broker = "localhost:9091,localhost:9092,localhost:9093";
//		String broker = "192.168.21.135:9091,192.168.21.135:9092";
		String broker = "172.31.85.98:9091,172.31.85.98:9092,172.31.85.98:9093";
		String acks = "1";
		MiaoKafkaProducer producer = new MiaoKafkaProducer(broker, acks);
		Integer i = 1;
		while (true) {
			String key = "key00"+i;
			String message = "message00"+i;
			producer.produce(topic, key, message);
			System.err.printf("MiaoKafkaProducer    topic = %s, key = %s, message = %s\n", topic, key, message);
			try {
				Thread.sleep(1000);
				i++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
			}
		}
	}

	private final Producer<K, E> producer;

	public MiaoKafkaProducer(String broker, String acks) {
		Properties props = new Properties();
		props.put("metadata.broker.list", broker);// 此处配置的是kafka的端口
		props.put("serializer.class", "kafka.serializer.StringEncoder");// 配置value的序列化类
		props.put("key.serializer.class", "kafka.serializer.StringEncoder");// 配置key的序列化类
		props.put("request.required.acks", acks);
		producer = new Producer<K, E>(new ProducerConfig(props));
	}

	/**
	 * @param topic  消息主题
	 * @param key    消息key
	 * @param entity 消息内容
	 * @return void
	 * @Description: TODO
	 * @Title: produce
	 * @author 张飞
	 * @date 2016年2月15日 下午6:16:51
	 */
	@SuppressWarnings({"hiding", "rawtypes", "unchecked"})
	public <K, E> void produce(String topic, K key, E entity) {
		producer.send(new KeyedMessage(topic, key, entity));
	}

	@SuppressWarnings({"hiding", "rawtypes", "unchecked"})
	public <K, E> void produce(List messages) {
		producer.send(messages);
	}

	/**
	 * @return void
	 * @Description: 关闭生产者
	 * @Title: closeProducer
	 * @author 张飞
	 * @date 2016年2月15日 下午6:24:52
	 */
	public void closeProducer() {
		System.err.println("close producer start");
		if (producer != null) {
			producer.close();
		}
	}

}