package console.academy.dbversion;

public interface DBService {
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_MY_URK = "jdbc:oracle:thin:@192.168.0.111:1521";

	void connect(String url, String user, String password);
	void execute() throws Exception;
	void close();
}
