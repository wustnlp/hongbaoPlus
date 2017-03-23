package chinaums.util;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SqlHelper {
	
	private static ApplicationContext context;
	private static DataSource dataSource;
	
	static {
		context = new ClassPathXmlApplicationContext("spring-hibernate.xml");          
		dataSource = (DataSource) context.getBean("dataSource");  
	}
	
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
    public static void close(ResultSet rs, Statement sm, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if (sm != null) {
            try {
                sm.close();
                sm = null;
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
