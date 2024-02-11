package console.academy.listver;

import java.util.Date;

public class Teacher extends Person {
	//[멤버변수](필드)
	String subject, saveTimeT; //새롭게 확장한 멤버
	

	public Teacher(String name, int age, String birthday,String subject, String saveTimeT) {
		super(name, age, birthday);
		this.subject = subject;
		this.saveTimeT = saveTimeT;
	}

	@Override
	String get() {
		return String.format(blue+"%s, 과목:%s, 저장시간:%s", super.get(), subject, saveTimeT);
	}

	@Override
	void print() {
		System.out.println(get());
	}
	
	
}
