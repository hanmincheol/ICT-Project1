package console.academy.listver;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
	//필드
	public static final String blue     = "\u001B[34m" ;
	public String name,birthday;
	public int age;
	//[기본 생성자]
	public Person() {}
	//[인자 생성자]
	public Person(String name, int age, String birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}
	//[멤버 메소드]
	String get() {
		return String.format(blue+"이름:%s, 나이:%s, 생일:%s", name, age, birthday);
	}
	
	void print() {
		System.out.println(get());
	}
	
}
