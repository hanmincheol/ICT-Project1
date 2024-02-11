package console.academy.dbversion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import common.utils.CommonUtils;

public class AcademyDBLogic extends DBver {
	public static final String purple   = "\u001B[35m" ;
	public static final String blue     = "\u001B[34m" ;
	public static final String black    = "\u001B[30m" ;
	public static final String red      = "\u001B[31m" ;
	public AcademyDBLogic() {
		connect(ORACLE_MY_URK+"/xepdb1","HanMinCheolProject1","ICT1234");
	}
	
	private Scanner sc=new Scanner(System.in);

	@Override
	public void execute() {
		try {
			while(true) {
				printSearchMenu();
				int num = CommonUtils.getMenuNumber("메뉴번호");
				String sql="INSERT INTO person VALUES(seq.nextval,?,?,?,?,?,?,?,sysdate)";
				String value;
				switch(num) {
				case 1:
					while(true) {
						System.out.println(red+"입력종료를 원하신다면 exit"+black);
						psmt = conn.prepareStatement(sql);
						System.out.println(black+"이름을 입력하세요?");
						value = CommonUtils.getName();
						if("-1".equalsIgnoreCase(value)) return;
						psmt.setString(1,value);
						System.out.println(black+"나이를 입력하세요?");
						int age = CommonUtils.getMenuNumber("나이");
						if(age == -1) return;
						psmt.setInt(2, age);
						System.out.println(black+"주소를 입력하세요?");
						value = sc.nextLine().trim();
						if("exit".equalsIgnoreCase(value)) return;
						psmt.setString(3, value);
						System.out.println(black+"연락처를 입력하세요?(000-0000-0000)");
						value = CommonUtils.getTel();
						if("-1".equalsIgnoreCase(value)) return;
						psmt.setString(4, value);
						System.out.println(black+"생일을 입력하세요?(yyyy-MM-dd)");
						while(true) {
							value = sc.nextLine().trim();
							if("exit".equalsIgnoreCase(value)) return;
							if(!value.matches("^[1-9][0-9]{3}-[0-9]{2}-[0-9]{2}")) {
								System.out.println(red+"형식에 맞지 않습니다."+black);
								continue;
							}
							break;
						}
						psmt.setString(5, value);
						System.out.println(black+"학번을 입력하세요?");
						value = sc.nextLine().trim();
						if("exit".equalsIgnoreCase(value)) return;
						psmt.setString(6, value);
						psmt.setString(7, null);
						psmt.executeUpdate();
						System.out.println(black+"저장이 완료되었습니다.");
					}
				case 2:
					while(true) {
						System.out.println(red+"입력종료를 원하신다면 exit"+black);
						psmt = conn.prepareStatement(sql);
						System.out.println(black+"이름을 입력하세요?");
						value = CommonUtils.getName();
						if("-1".equalsIgnoreCase(value)) return;
						psmt.setString(1,value);
						System.out.println(black+"나이를 입력하세요?");
						int age = CommonUtils.getMenuNumber("나이");
						if(age == -1) return;
						psmt.setInt(2, age);
						System.out.println(black+"주소를 입력하세요?");
						value = sc.nextLine().trim();
						if("exit".equalsIgnoreCase(value)) return;
						psmt.setString(3, value);
						System.out.println(black+"연락처를 입력하세요?(000-0000-0000)");
						value = CommonUtils.getTel();
						if("-1".equalsIgnoreCase(value)) return;
						psmt.setString(4, value);
						System.out.println(black+"생일을 입력하세요?(yyyy-MM-dd)");
						while(true) {
							value = sc.nextLine().trim();
							if("exit".equalsIgnoreCase(value)) return;
							if(!value.matches("^[1-9][0-9]{3}-[0-9]{2}-[0-9]{2}")) {
								System.out.println(red+"형식에 맞지 않습니다."+black);
								continue;
							}
							break;
						}
						psmt.setString(5, value);
						System.out.println(black+"과목을 입력하세요?");
						value = sc.nextLine().trim();
						if("exit".equalsIgnoreCase(value)) return;
						psmt.setString(7, value);
						psmt.setString(6, null);
						psmt.executeUpdate();
						System.out.println(black+"저장이 완료되었습니다.");
					}
				case 9:
					return;
				default:
					System.out.println(red+"메뉴에 없는 번호입니다."+black);
					continue;
				}
			}
			
		} 
		catch (SQLException e) {
			System.out.println(red+"입력시 오류:"+e.getMessage()+black);
		}
		
	}
	
	public void mainmenu() {
		while(true) {
			printMainMenu();
			int number = CommonUtils.getMenuNumber("메뉴번호");
			switch(number) {
				case 1:
					execute();
					break;
				case 2:
					printExecute();
					break;
				case 3:updateExecute();
					break;
				case 4:deleteExecute("삭제");
					break;
				case 5:searchExecute("검색");
					break;
				case 9:
					endMethod();
					break;
				default:
					System.out.println(red+"메뉴에 없는 번호입니다."+black);
					continue;
			}
		}
	}
	
private int countPerson() {
	String sql="select count(*) from person";
	try {
		psmt = conn.prepareStatement(sql);
		rs=psmt.executeQuery();
		int count = 0;
		rs.next();
		count = rs.getInt(1);
		return count;
	} catch (SQLException e) {
		System.out.println(red+"출력시 오류 :"+e.getMessage()+black);
		return 0;
	}
	
}
	
	
////////끝내는 메소드
private void endMethod() {
	System.out.println(black+"Oracle Database 18c Express Edition Release 18.0.0.0.0 - Production\r\n"
			+ "Version 18.4.0.0.0에서 분리되었습니다.");
	close();
	System.exit(0);
}

	
/////////삭제용	
private void deleteExecute(String su) {
	if(countPerson()==0) {
		System.out.println(red+"저장된 데이터가 없습니다."+black);
		return;
	}
	String sql = "DELETE person WHERE no = ?";
	try {
		printAll();
		psmt = conn.prepareStatement(sql);
		System.out.println(black+su+"할 사람의 no를 입력하세요?");
		String value = sc.nextLine().trim();
		if("exit".equalsIgnoreCase(value)) return;
		psmt.setString(1, value);
		System.out.println(black+psmt.executeUpdate()+"행이 "+su+"되었습니다.");
		} catch (SQLException e) {
			System.out.println(red+"삭제시 오류:"+e.getMessage()+black);
		}
	}

/////////////검색용	
private void searchExecute(String su) {
	if(countPerson()==0) {
		System.out.println(red+"저장된 데이터가 없습니다."+black);
		return;
	}
		String sql = "SELECT * from person WHERE no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println(black+su+"할 사람의 no를 입력하세요?");
			String value = sc.nextLine().trim();
			if("exit".equalsIgnoreCase(value)) return;
			psmt.setString(1, value);
			//System.out.println();
			rs=psmt.executeQuery();
			print1(rs);
		} catch (SQLException e) {
			System.out.println(red+"검색시 오류:"+e.getMessage()+black);
		}
		return;
	}

private void update(String su) {
	try {String su1 = null;
		printAll();
		switch(su) {
			case "name": su1 = "이름을";
				break;
			case "age": su1 = "나이를";
				break;
			case "live": su1 = "주소를";
				break;
			case "tel": su1 = "연락처를";
				break;
			case "birth":  su1 = "생일을";
				break;
			case "STNUMBER": su1 = "학번을";
				break;
			case "SUBJECT": su1 = "과목을";
				break;
		}
		System.out.println(black+"수정할 사람의 no를 입력하세요?");
		String value = sc.nextLine().trim();
		if("exit".equalsIgnoreCase(value)) return;
		System.out.println(black+"수정할 "+ su1 +" 입력하세요?");
		String value1 = sc.nextLine().trim();
		if("exit".equalsIgnoreCase(value)) return;
		String sql = "update person set "+su+"=? where no=?";
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, value1);
		psmt.setString(2, value);
		System.out.println(black+psmt.executeUpdate()+"행이 수정되었습니다.");
		
	} catch (SQLException e) {
		System.out.println(red+"수정시 오류:"+e.getMessage()+black);
	}
	
}

private void printAll() {
	String sql="select * from person order by no";
	try {
		psmt = conn.prepareStatement(sql);
		rs=psmt.executeQuery();
		print1(rs);
	} catch (SQLException e) {
		System.out.println(red+"출력시 오류 :"+e.getMessage()+black);
	}
	
}


	//////////수정용	
	private void updateExecute() {
		if(countPerson()==0) {
			System.out.println(red+"저장된 데이터가 없습니다."+black);
			return;
		}
		printSearchChangeMenuS();
		int num = CommonUtils.getMenuNumber("메뉴번호");
		while(true) {
			switch(num) {
				case 1:
					update("name");
					return;
				case 2:update("age");
					return;
				case 3:update("live");
					return;
				case 4:update("tel");
					return;
				case 5:update("birth");
					return;
				case 6:update("stnumber");
					return;
				case 7:update("subject");
					return;
				case 9:
					return;
				default:
					System.out.println(red+"메뉴에 없는 번호입니다."+black);
					continue;
			}
		}
	}///////////
	
	////////프린트 메소드
	private void print1(ResultSet rs) {
	ResultSetMetaData rsmd;
	try {
		rsmd = rs.getMetaData();
	int colC = rsmd.getColumnCount();
	List<Integer> dc = new Vector<>();
	for(int i = 1; i<=colC; i++) {
		int colS = rsmd.getPrecision(i);
		int colT = rsmd.getColumnType(i);
		String colN = rsmd.getColumnName(i);
		switch(colT) {
		case Types.NCHAR:
		case Types.NVARCHAR:
			if(colN == rsmd.getColumnName(2))
			{
				dc.add(6); 
				break;
			}
			else if(colN == rsmd.getColumnName(1)) {
				dc.add(4); 
				break;
			}else {
				dc.add(20); 
				break;
			}
		case Types.NUMERIC:
			dc.add(5); break;
		case Types.TIMESTAMP:
			dc.add(15); break;
		default : dc.add(colS);
		}
		System.out.print(String.format(blue+"%-"+(dc.get(i-1)+1)+"s", colN));
	} System.out.println();
	for(Integer ig : dc) {
		for(int i = 1; i<=ig;i++) System.out.print(blue+"-");
		System.out.print(" ");
	}System.out.println();
	 int rowC=0;
	 while(rs.next()) {
		 for(int i = 1; i<=colC; i++) {
			 int colT = rsmd.getColumnType(i);
			 String colD;
			 if(colT==Types.TIMESTAMP) colD=rs.getDate(i).toString();
			 else colD=rs.getString(i);
			 System.out.print(String.format(blue+"%-"+(dc.get(i-1)+1)+"s", colD==null?"":colD));
		 }
		 System.out.println();
		rowC++;
	 }
	 System.out.println(black+rowC+"행이 출력되었습니다.");
	} catch (SQLException e) {
		System.out.println(red+"출력시 오류:"+e.getMessage()+black);
	}
}
	
///////////출력용
	private void print(String sub, String not) {
		printSearchMenu1();
		String sql;
		try {
			while(true) {
				int num = CommonUtils.getMenuNumber("메뉴번호");
				switch(num) {
				case 1:
					sql="select * from person " + not+ " order by "+ sub;
					psmt = conn.prepareStatement(sql);
					//psmt.execute();
					//rs= psmt.getResultSet();
					rs=psmt.executeQuery(sql);
					print1(rs);
					return;
				case 2:
					sql="select * from person " + not+ " order by "+ sub+" desc";
					psmt = conn.prepareStatement(sql);
					rs=psmt.executeQuery();
					print1(rs);
					return;
				default:
					System.out.println(red+"메뉴에 없는 번호입니다."+black);
					continue;
				}
			}
		} catch (SQLException e) {
			System.out.println(red+"출력시 오류:"+e.getMessage()+black);
		}
	}/////print
	
	////출력용 메소드
	private void printExecute() {
		if(countPerson()==0) {
			System.out.println(red+"저장된 데이터가 없습니다."+black);
			return;
		}
		while(true) {
			printSearchMenu();
			String sql;
			int num = CommonUtils.getMenuNumber("메뉴번호");
			switch(num) {
			case 1:
				while(true) {
				printSearchChangeMenu();
				int number = CommonUtils.getMenuNumber("메뉴번호");
				switch(number) {
				case 1:
					print("name","where subject is null");
					return;
				case 2:
					print("age","where subject is null");
					return;
				case 3:
					print("birth","where subject is null");
					return;
				case 4:
					print("savetime","where subject is null");
					return;
				case 9:
					return;
				default:
					System.out.println(red+"메뉴에 없는 번호입니다."+black);
					continue;
					}
				}
			case 2:
				while(true) {
					printSearchChangeMenu();
					int number = CommonUtils.getMenuNumber("메뉴번호");
					switch(number) {
					case 1:
						print("name","where subject is not null");
						return;
					case 2:
						print("age","where subject is not null");
						return;
					case 3:
						print("birth","where subject is not null");
						return;
					case 4:
						print("savetime","where subject is not null");
						return;
					case 9:
						return;
					default:
						System.out.println(red+"메뉴에 없는 번호입니다."+black);
						continue;
						}
					}
			case 3:
				while(true) {
					printSearchChangeMenu();
					int number = CommonUtils.getMenuNumber("메뉴번호");
					switch(number) {
					case 1:
						print("name"," ");
						return;
					case 2:
						print("age"," ");
						return;
					case 3:
						print("birth"," ");
						return;
					case 4:
						print("savetime"," ");
						return;
					case 9:
						return;
					default:
						System.out.println(red+"메뉴에 없는 번호입니다."+black);
						continue;
						}
					}
			case 9:
				return;
			default:
				System.out.println(red+"메뉴에 없는 번호입니다."+black);
				continue;
			}
		}
	}/////////deleteExecute()

	
	//메뉴 출력용 메소드
	public void printMainMenu() {
		System.out.println(purple+"=================주소록 메인 메뉴====================");
		System.out.println("        1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료");
		System.out.println("=================================================");
		System.out.println(black+"메인 메뉴번호를 입력하세요?");
	}//////////////printMainMenu()
	
	private void printSearchMenu() {
		System.out.println(purple+"+++++++++++++++++서브 메뉴++++++++++++++++");
		System.out.println("     1.학생 2.교사 3.전체 9.메인메뉴로 이동");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenu()
	
	private void printSearchChangeMenu() {
		System.out.println(purple+"+++++++++++++++++++서브 메뉴+++++++++++++++++++++");
		System.out.println("    1.이름 2.나이 3.생일 4.저장시간 9.메인으로 이동");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenuS()
	
	private void printSearchMenu1() {
		System.out.println(purple+"+++++++++서브 메뉴++++++++++");
		System.out.println("    1.오름차순 2.내림차순 ");
		System.out.println("++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenu1()
	
	private void printSearchChangeMenuS() {
		System.out.println(purple+"+++++++++++++++++++++++++서브 메뉴+++++++++++++++++++++++++++");
		System.out.println("   1.이름 2.나이 3.주소 4.연락처 5.생일 6.학번 7.과목 9.메인으로 이동");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(black+"서브 메뉴번호를 입력하세요?");
	}//////////printSearchMenuS()
	
}////////////class
