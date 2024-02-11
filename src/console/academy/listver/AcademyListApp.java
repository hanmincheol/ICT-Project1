package console.academy.listver;

import common.utils.InnerEvent;

public class AcademyListApp {

	public static void main(String[] args) {
		ListverLogic llc = new ListverLogic();
		new InnerEvent();
		while(true) {
			llc.start();
		}
	}
}
