package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonUtils {
	//[문자열이 숫자형식이면 true, 아니면 false반환]
	public static final String black    = "\u001B[30m" ;
	public static final String red      = "\u001B[31m" ;
	public static boolean isNumber(String value) {
		for(int i=0;i < value.length();i++) {
			int codeValue = Character.codePointAt(value, i);
			if(!(codeValue >='0' && codeValue<='9')) return false;
		}
		return true;
		
	}///////////////////
	
	private static Scanner sc = new Scanner(System.in);
	
	//두 날짜 차이를 반환하는 메소드]
	//반환타입:long
	//매개변수:String타입의 두 날짜,날짜패턴,구분자(단위)
	public static long getDiffBetweenDates(String stFDate, String stSDate, String pattern, char delim) throws ParseException {
		//1]매개변수에 전달된 pattern으로 SimpleDateFormat객체 생성
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		//2]String => Date : parse()
		Date fDate = dateFormat.parse(stFDate);
		Date sDate = dateFormat.parse(stSDate);
		//3]시간차 구하기:getTime()
		long fTime = fDate.getTime();
		long sTime = sDate.getTime();
		long diff = Math.abs(fTime-sTime);
		//4]매개변수 delim에 따른 날짜 차이 반환
		switch(Character.toUpperCase(delim)) {
		case 'D' : return diff/1000/60/60/24;
		case 'H' : return diff/1000/60/60;
		case 'M' : return diff/1000/60;
		default : return diff/1000;
		}
	}///////////////
	
	//[문자열을 int[]배열로 변환]
	public static int[] toIntArray(String value) {
		int intArray[] = new int[value.length()];
		for(int i = 0; i<value.length(); i++) {
			intArray[i] = (int)value.charAt(i);
		}
		return intArray;
	}///////////////
	
	//주어진 문자의 초성을 추출하는 함수
	public static char getInitialConsona(String value) {
		if(!Pattern.matches("^[가-힣]{2,}$", value.trim())) return'0';
		char lastName = value.charAt(0);
		int Index = (lastName-'가')/28/21;//초성의 인덱스 얻기
		char[] initialconsonants = {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		return initialconsonants[Index];
	}///////////////
	
	//숫자 입력용 메소드
	public static int getMenuNumber(String message) {
		//방법1]isNumber()메소드 정의해서 처리
		String menuStr;
		int number;
		while(true) {
			menuStr = sc.nextLine().trim();
			try {
				if("exit".equalsIgnoreCase(menuStr)) return -1;
				if(!isNumber(menuStr)) {
					System.out.println(red+message+"는 숫자만..."+black);
					continue;
				}
				number = Integer.parseInt(menuStr);
				break;
			} catch(NumberFormatException e) {
				System.out.println(red+message+"는 숫자만..."+black);
			}
		}
		return number;
	}////////////////getMenuNumber()
	
	/////시간 저장 메소드
	public static String saveTime() {
		Date saveT = new Date();
		SimpleDateFormat save = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss EEEE");
		String saveTime = save.format(saveT);
		return saveTime;
	}
	
	//////이름 받는 메소드
	public static String getName() {
		String name;
		while(true) {
			name = sc.nextLine().trim();
			if("exit".equalsIgnoreCase(name)) return "-1";
			if(!name.matches("[가-힣]{2,4}")) {
				System.out.println(red+"형식에 맞지 않습니다."+black);
				continue;
			}
			break;
		}
		return name;
	}/////getName()
	
	////// 연락처 받는 메소드
	public static String getTel() {
		String tel;
		while(true) {
			tel = sc.nextLine();
			if("exit".equalsIgnoreCase(tel)) return "-1";
			if(!tel.matches("[0-9]{3}-[0-9]{3,4}-[0-9]{4}")) {
				System.out.println(red+"형식에 맞지 않습니다."+black);
				continue;
			}
			break;
		}
		return tel;
	}////////getTel()
	
	
}
