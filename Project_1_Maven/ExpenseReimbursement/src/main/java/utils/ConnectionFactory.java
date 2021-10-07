package utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static Connection connection;
	
	
	public ConnectionFactory() {
		
	}
	
	public static Connection getConnection() {
	if(connection == null) {
		try {
			Properties props = new Properties();
			FileReader connectionProperties = new FileReader("src/main/resources/connection.properties");
			props.load(connectionProperties);
				
			String connectionString = "jdbc:mariadb://" +
					props.getProperty("endpoint") + ":" +
					props.getProperty("port") + "/" +
					props.getProperty("dbname") + "?user=" +
					props.getProperty("username") + "&password=" +
					props.getProperty("password");
			
					
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return connection;
	}
}
