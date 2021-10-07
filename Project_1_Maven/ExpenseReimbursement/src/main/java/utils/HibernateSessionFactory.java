package utils;

import java.io.FileReader;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		
		if (sessionFactory == null) {
			Properties props = new Properties();
			try {
			FileReader connectionProp = new FileReader("src/main/resources/connection.properties");
			props.load(connectionProp);
			
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "jdbc:mariadb://database-1.cwlo3jslufmc.us-east-2.rds.amazonaws.com:3306/database1")
					.setProperty("hibernate.connection.username", props.getProperty("username"))
					.setProperty("hibernate.connection.password" , props.getProperty("password"))
					.buildSessionFactory();
			}catch(Exception e) {
				e.printStackTrace();			}
		}
		return sessionFactory.getCurrentSession();
		
	}
}
