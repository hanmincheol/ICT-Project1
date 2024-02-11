package console.academy.listver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import common.utils.CommonUtils;
import oracle.sql.CHAR;

public class ListverLogic {
	List<Person> person;
	public static final String purple   = "\u001B[35m" ;
	public static final String blue     = "\u001B[34m" ;
	public static final String black    = "\u001B[30m" ;
	public static final String red      = "\u001B[31m" ;
	
	public ListverLogic() {
		person = new Vector<>();
	}
	
	private Scanner sc = new Scanner(System.in);
	
	public int getNumber(String me) {
		int a =0;
		while(true) {
		try {
			String numS = sc.nextLine();
			if("exit".equalsIgnoreCase(numS)) return -1;
			a = Integer.parseInt(numS);
			return a;
		} catch (Exception e) {
			System.out.println(red+me+"는 숫자만..."+black);
			}
		}
	}

	public void start() {
		printMainMenu();
		int num = getNumber("메뉴번호");
		switch(num) {
		case 1:
			insertMethod();
			break;
		case 2:
			printMethod();
			break;
		case 3:
			searchperson("수정");
			break;
		case 4:
			searchperson("삭제");
			break;
		case 5:
			searchperson("검색");
			break;
		case 6:
			clearAll();
			break;
		case 9:
			System.out.println(black+"프로그램을 종료합니다.");
			System.exit(0);
			break;
		default : System.out.println(red+"메뉴에 없는 번호입니다."+black);	
		}
		
	}///////////
	
	///전체삭제
	private void clearAll() {
		try {
		if(checkS()==0 && checkT()==0) {
			System.out.println(red+"삭제할 데이터가 없습니다.");
			return;
		}
		person = new ArrayList<>();
		File f = new File("src/console/academy/listver/savefile.txt");
		f.delete();
		System.out.println(black+"========================================================");
		System.out.println("   		       전체 삭제 성공!");
		System.out.println(black+"========================================================");
		} catch(Exception e) {
			System.out.println(red+"삭제할 데이터가 없습니다!"+black);
		}
	}

	////////검색 메소드
	private void searchperson(String pe) {
		person = new ArrayList<>();
		settingFile();
		String name = null;
		if(checkS()==0 && checkT()==0) {
			System.out.println(red+"저장된 데이터가 없습니다."+black);
			return;
		}
		printSearchMenu();
		while(true) {
		int num = getNumber("서브 메뉴번호");
		switch(num) {
			case 1:
				if(checkS()==0) {
					System.out.println(red+"저장된 학생이 없습니다."+black);
					return;
				}
				System.out.println(black+pe+"할 사람의 이름을 입력하세요");
				name = CommonUtils.getName();
				for(Person p : person) {
					if(p instanceof Student && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("검색")) {
						p.print();
						return;
					}else if(p instanceof Student && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("수정")){
						p.print();
						updateMethodS(p);
						return;
					}else if(p instanceof Student && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("삭제")){
						p.print();
						person.remove(p);
						saveFile(2);
						return;
					}
				}System.out.println(red+name+"로 저장된 사람이 없어요!"+black);
				return;
			case 2:
				if(checkT()==0) {
					System.out.println(red+"저장된 선생님이 없습니다."+black);
					return;
				}
				System.out.println(black+pe+"할 사람의 이름을 입력하세요");
				name = CommonUtils.getName();
				for(Person p : person) {
					if(p instanceof Teacher && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("검색")) {
						p.print();
						return;
					}else if(p instanceof Teacher && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("수정")) {
						p.print();
						updateMethodT(p);
						return;
					}else if(p instanceof Teacher && p.name.equalsIgnoreCase(name)&&pe.equalsIgnoreCase("삭제")) {
						p.print();
						person.remove(p);
						saveFile(2);
						return;
					}
				}
				System.out.println(red+name+"로 저장된 사람이 없어요!"+black);
				return;
			case 9 :
				return;
			default:System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}
		
		
		}
	}
		
		
		
	
private void updateMethodS(Person p) {
	updateMenuS();
	String saveT =null;
	while(true) {
		int num = getNumber("서브 메뉴번호");
		switch(num) {
		case 1:
			System.out.println(black+"수정할 이름을 입력하세요");
			String name = CommonUtils.getName();
			p.name = name;
			saveT = CommonUtils.saveTime();
			((Student)p).saveTimeS = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 2:
			System.out.println(black+"수정할 나이을 입력하세요");
			int age = getNumber("나이");
			p.age = age;
			saveT = CommonUtils.saveTime();
			((Student)p).saveTimeS = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 3:
			System.out.println(black+"수정할 생일을 입력하세요");
			String bir = getBir();
			p.birthday = bir;
			saveT = CommonUtils.saveTime();
			((Student)p).saveTimeS = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 4:
			System.out.println(black+"수정할 학번을 입력하세요");
			String stnum = sc.nextLine();
			((Student)p).stNumber = stnum;
			saveT = CommonUtils.saveTime();
			((Student)p).saveTimeS = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 9:
			return;
		default:System.out.println(red+"메뉴에 없는 번호입니다."+black);
		}
	}
		
}

private void updateMethodT(Person p) {
	updateMenuT();
	String saveT =null;
	while(true) {
		int num = getNumber("서브 메뉴번호");
		switch(num) {
		case 1:
			System.out.println(black+"수정할 이름을 입력하세요");
			String name = CommonUtils.getName();
			p.name = name;
			saveT = CommonUtils.saveTime();
			((Teacher)p).saveTimeT = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 2:
			System.out.println(black+"수정할 나이을 입력하세요");
			int age = getNumber("나이");
			p.age = age;
			saveT = CommonUtils.saveTime();
			((Teacher)p).saveTimeT = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 3:
			System.out.println(black+"수정할 생일을 입력하세요");
			String bir = getBir();
			p.birthday = bir;
			saveT = CommonUtils.saveTime();
			((Teacher)p).saveTimeT = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 4:
			System.out.println(black+"수정할 과목을 입력하세요");
			String sub = sc.nextLine();
			((Teacher)p).subject = sub;
			saveT = CommonUtils.saveTime();
			((Teacher)p).saveTimeT = saveT;
			person.remove(p);
			person.add(p);
			saveFile(2);
			return;
		case 9:
			return;
		default:System.out.println(red+"메뉴에 없는 번호입니다."+black);
		}
	}
		
}

	/////////프린트 메소드
	private void printMethod() {
		if(checkS()==0 && checkT()==0) {
			System.out.println(red+"저장된 데이터가 없습니다."+black);
			return;
		}
		printMenu();
		while(true) {
			int num = getNumber("서브 메뉴번호");
			switch(num) {
				case 1:printMethodS();
					return;
				case 2:printMethodT();
					return;
				case 3:printMethodA();
					return;
				case 9:
					return;
				default : System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}	
		}
	}
	////현재 저장된 사람수 체크 메소드
	private int checkT() {
		person = new ArrayList<>();
		settingFile();
		int countT =0;
		for(Person p : person) {
			if(p instanceof Teacher) countT++;
		} return countT;
	}
	
	private int checkS() {
		person = new ArrayList<>();
		settingFile();
		int countS =0;
		for(Person p : person) {
			if(p instanceof Student) countS++;
		}  return countS;
	}
	
//////학생 프린트
	private void printMethodS() {
		person = new ArrayList<>();
		settingFile();
		if(checkS()==0) {
			System.out.println(red+"저장된 학생이 없습니다."+black);
			return;
		}
		sortMenu();
		char temp = 'a';
		while(true) {
			int num = getNumber("서브 메뉴번호");
			switch(num) {
				case 1:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o1.name.compareTo(o2.name);
						}
					});
					for(Person p : person) {
						if(p instanceof Student) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println(black+"["+fN+"으로 시작하는 목록]");
							p.print();
							}
						}
					}
					return;
				case 2:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o2.name.compareTo(o1.name);
						}
					});
					for(Person p : person) {
						if(p instanceof Student) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println(black+"["+fN+"으로 시작하는 목록]");
							p.print();
							}
						}
					}
					return;
				default : System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}	
		}
	}
	
//////선생님 프린트
	private void printMethodT() {
		person = new ArrayList<>();
		settingFile();
		if(checkT()==0) {
			System.out.println(red+"저장된 선생님이 없습니다."+black);
			return;
		}
		sortMenu();
		char temp = 'a';
		while(true) {
			int num = getNumber("서브 메뉴번호");
			switch(num) {
				case 1:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o1.name.compareTo(o2.name);
						}
					});
					for(Person p : person) {
						if(p instanceof Teacher) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println("["+fN+"으로 시작하는 목록]");
							p.print();
							}
						}
					}
					return;
				case 2:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o2.name.compareTo(o1.name);
						}
					});
					for(Person p : person) {
						if(p instanceof Teacher) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println(black+"["+fN+"으로 시작하는 목록]");
							p.print();
							}
						}
					}
					return;
				default : System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}	
		}
	}
	
//////전체 프린트
	private void printMethodA() {
		person = new ArrayList<>();
		settingFile();
		sortMenu();
		char temp = 'a';
		while(true) {
			int num = getNumber("서브 메뉴번호");
			switch(num) {
				case 1:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o1.name.compareTo(o2.name);
						}
					});
					for(Person p : person) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println(black+"["+fN+"으로 시작하는 목록]");
							p.print();
							}
						}
					return;
				case 2:
					Collections.sort(person,new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							return o2.name.compareTo(o1.name);
						}
					});
					for(Person p : person) {
						char fN = CommonUtils.getInitialConsona(p.name);
						 if(fN == temp) {
							p.print();
							continue;
						 }
						else {
							temp = fN;
							System.out.println(black+"["+fN+"으로 시작하는 목록]");
							p.print();
						}
					}
					return;
				default : System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}	
		}
	}
	
	
	private void insertMethod() {
		printSearchMenu();
		System.out.printf("현재 저장된 선생님 수:%s명\r\n",checkT()); 
		System.out.printf("현재 저장된 학생 수:%s명\r\n",checkS()); 
		while(true){
			int num = getNumber("서브 메뉴번호");
			switch(num) {
			case 1:getData(1);
				return;
			case 2:getData(2);
				return;
			case 9:
				return;
			default:System.out.println(red+"메뉴에 없는 번호입니다."+black);
			}
		}
	
	}
	
	///값 받기
	private void getData(int me) {
		while(true){
			System.out.println(black+"메인화면으로 가시려면 exit");
			System.out.println(black+"이름을 입력하세요?");
			String name = CommonUtils.getName();
			if("-1".equalsIgnoreCase(name)) return;
			System.out.println(black+"나이를 입력하세요?");
			int num = getNumber("나이");
			if(-1==num) return;
			System.out.println(black+"생일를 입력하세요?");
			String bir = getBir();
			if("-1".equalsIgnoreCase(bir)) return;
			if(me == 1) {
				System.out.println(black+"학번을 입력하세요?");
				String stn = sc.nextLine();
				String saveT = CommonUtils.saveTime();
				if("exit".equalsIgnoreCase(stn)) return;
				person.add(new Student(name, num, bir, stn, saveT));
			}else {
				System.out.println(black+"과목을 입력하세요?");
				String sub = sc.nextLine();
				String saveT = CommonUtils.saveTime();
				if("exit".equalsIgnoreCase(sub)) return;
				person.add(new Teacher(name, num, bir, sub, saveT));
			}
			saveFile(2);
		}
	}
	
	//생일을 받는 메소드
	private String getBir() {
		while(true) {
			try {
			String bir = sc.nextLine();
			if("exit".equalsIgnoreCase(bir)) return "-1";
			SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
			Date date = SDF.parse(bir);
			return bir;
			}catch(Exception e) {
			System.out.println(red+"생일형식이 맞지 않습니다."+black);
			}
		}
	}
	
	////////파일 저장 메소드
	private void saveFile(int a) {
		ObjectOutputStream oos =null;
		try {
			if(a==1)
			//덮어쓰기
			oos = new ObjectOutputStream(new FileOutputStream("src/console/academy/listver/savefile.txt",true));
			else
			//덮어쓰기x
			oos = new ObjectOutputStream(new FileOutputStream("src/console/academy/listver/savefile.txt",false));
			oos.writeObject(person);
			System.out.println(black+"========================================================");
			System.out.println("   		       저장 성공!");
			System.out.println(black+"========================================================");
		} catch (FileNotFoundException e) {
			System.out.println(red+"저장할 데이터가 없어요!"+black);
		} catch (IOException e) {
			System.out.println(red+"파일 저장시 오류"+black);
		} finally {
			if(oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
					System.out.println(red+"파일 닫기 시 오류"+black);
				}
		}
	}
	
	////////파일 세팅 메소드
	private void settingFile() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("src/console/academy/listver/savefile.txt"));
			try {
				person = (List<Person>)ois.readObject();
			} catch (ClassNotFoundException e) {}
		} catch (FileNotFoundException e) {}
		  catch (IOException e) {
			System.out.println(red+"파일 불러오기 시 오류"+black);
		} finally {
			if(ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println(red+"파일 닫기 시 오류"+black);
				}
		}
	}
	
	///메인메뉴 출력
	public void printMainMenu() {
		System.out.println(purple+"================주소록 메인 메뉴===================");
		System.out.println(" 1.입력 2.출력 3.수정 4.삭제 5.검색 6.전체삭제 9.종료");
		System.out.println("==============================================");
		System.out.println(black+"메인 메뉴번호를 입력하세요?");
	}//////////////printMainMenu()
	
	///서브메뉴 출력
	private void printSearchMenu() {
		System.out.println(purple+"++++++++++++서브 메뉴+++++++++++");
		System.out.println("     1.학생 2.교사 9.뒤로가기");
		System.out.println("++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenu()
	
///서브메뉴 출력
	private void printMenu() {
		System.out.println(purple+"+++++++++++++++서브 메뉴++++++++++++++");
		System.out.println("     1.학생 2.교사 3.전체 9.뒤로가기");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenu()
	
	////////정렬 출력
	private void sortMenu() {
		System.out.println(purple+"+++++++++서브 메뉴++++++++++");
		System.out.println("    1.오름차순 2.내림차순 ");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenu1()
	
	private void updateMenuS() {
		System.out.println(purple+"++++++++++++++++서브 메뉴++++++++++++++++++");
		System.out.println("    1.이름 2.나이 3.생일 4.학번 9.뒤로가기");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenuS()
	
	private void updateMenuT() {
		System.out.println(purple+"++++++++++++++++서브 메뉴++++++++++++++++++");
		System.out.println("     1.이름 2.나이 3.생일 4.과목 9.뒤로가기");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenuS()

	public void verMenu() {
		System.out.println(purple+ "=======================버전 메뉴=========================");
		System.out.println("            1.컬렉션 버전 2.오라클 버전 9.종료");
		System.out.println("======================================================");
		System.out.println(black+"사용하실 버전 메뉴번호를 입력하세요?");
	}//////////////printMainMenu()
}

