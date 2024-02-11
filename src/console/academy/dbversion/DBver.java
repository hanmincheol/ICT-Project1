package console.academy.dbversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import common.utils.CommonUtils;

public class DBver implements DBService {
	public Connection conn;
	public ResultSet rs;
	public PreparedStatement psmt;
	private Scanner sc = new Scanner(System.in);
	
	static {
		try {
			Class.forName(ORACLE_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
	}
	
	public DBver() {}
	
	public DBver(String url, String user, String password) {
		connect(url, user, password);
	}

	@Override
	public void connect(String url, String user, String password) {
		try {
			//System.out.println(url+"  "+user+"  "+password);
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("데이타베이스 연결실패" + e.getMessage());
		}
		
	}

	@Override
	public void execute() throws Exception {}

	@Override
	public void close() {
		try {
			if(conn != null) conn.close();
			if(rs != null) rs.close();
			if(psmt != null)psmt.close();
		} catch (SQLException e) {
			System.out.println("서버 종료시 오류");
		}
	}

}
