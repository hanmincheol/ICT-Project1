package academy.app;

import common.utils.InnerEvent;
import console.academy.dbversion.AcademyDBLogic;
import console.academy.listver.ListverLogic;

public class FinalApp {
	public static final String black    = "\u001B[30m" ;
	public static final String red      = "\u001B[31m" ;
	public static void main(String[] args) {
		AcademyDBLogic adb=  new AcademyDBLogic();
		ListverLogic llc = new ListverLogic();
		new InnerEvent();
		llc.verMenu();
		int num = llc.getNumber("버전 메뉴번호");
		while(true) {
			switch(num) {
				case 1:
					llc.start();
					break;
				case 2:
					adb.mainmenu();
					break;
				case 9:
					System.out.println(black+"프로그램을 종료합니다.");
					System.exit(0);
					break;
				default:System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}
		}
	}
}
