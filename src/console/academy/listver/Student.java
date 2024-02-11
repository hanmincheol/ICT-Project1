package console.academy.listver;

import java.util.Date;

public class Student extends Person {
	//[멤버변수(필드)]
	public String stNumber, saveTimeS; //새롭게 확장한 멤버
	
	public Student(String name, int age, String birthday,String stNumber, String saveTimeS) {
		super(name, age, birthday);
		this.stNumber = stNumber;
		this.saveTimeS = saveTimeS;
	}

	@Override
	public String get() {
		return String.format(blue+"%s, 학번:%s, 저장시간:%s", super.get(), stNumber, saveTimeS);
	}


	@Override
	public void print() {
		System.out.println(get());
	}
}
