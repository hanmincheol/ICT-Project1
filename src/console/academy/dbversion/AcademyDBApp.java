package console.academy.dbversion;

import common.utils.CommonUtils;
import common.utils.InnerEvent;

public class AcademyDBApp extends DBver {

	public static void main(String[] args) throws Exception {
		AcademyDBLogic adb=  new AcademyDBLogic();
		new InnerEvent();
		while(true) {
				adb.mainmenu();
		}
	}

}
