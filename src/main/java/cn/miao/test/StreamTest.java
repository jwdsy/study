package cn.miao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) throws Exception{
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			Person p = new Person();
			p.setAge(i);
			p.setName("name"+i);
			p.setSex(i/2);
			persons.add(p);
		}
		Stream<Object[]> stream = persons.parallelStream().map(person -> new Object[]{person.getName(), person.getSex(), person.getAge()});
		System.err.println(stream);
		Stream<Integer> integerStream = persons.parallelStream().map(Person::getAge);
		System.err.println(integerStream);
		List<Integer> transactionsIds = persons.parallelStream().
				filter(person -> person.getSex() == 0).//过滤
				map(Person::getAge).collect(Collectors.toList());//取出每个对象中的年龄
		System.err.println(transactionsIds);
		System.err.println("");
	}

}

class Person{
	int age;
	String name;
	int sex;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
