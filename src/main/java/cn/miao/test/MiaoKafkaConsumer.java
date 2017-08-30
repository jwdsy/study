package cn.miao.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MiaoKafkaConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
//		props.put("bootstrap.servers", "192.168.21.135:9091,192.168.21.135:9092");
//		props.put("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093");
		props.put("bootstrap.servers", "172.31.85.98:9091,172.31.85.98:9092,172.31.85.98:9093");
		props.put("group.id", "ABC");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(MiaoKafkaProducer.topic));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("MiaoKafkaConsumer    partition = %d, offset = %d, key = %s, value = %s\n", record.partition(),record.offset(), record.key(), record.value());
		}
	}
}
