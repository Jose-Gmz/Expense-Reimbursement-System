# Expense Reimbursement System (JwA)

## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can log in and submit requests for reimbursements and view their past tickets and pending requests. Managers can log in and view all reimbursement requests and past history for all employees in the company. Managers are authorized to approve and deny requests for expense reimbursements.

## Technologies Used

*  JavaScript
*  HTML
*  CSS 
*  SQL 
*  Postman
*  AWS 
*  RDS
*  Java
*  Javalin
*  Hibernate
*  Maven

## Features

* Sumbit reimbursements
* Update reimbursements
* Display employee specific reimbursements
* Display all employee reimbursements


To-do list:
* Statistics page.
* Implement Spring Framework
* Implement Angular Framework

## Getting Started
* Create a AWS account
* Download:
   - MariaDB
   - Eclipse
   - Postman
   - Javalin
   - Maven
* Start a Maven Project in Eclipse
   - Add dependencies in pom.xml file
   - javalin
   
   `
   <dependency>
			<groupId>io.javalin</groupId>
			<artifactId>javalin</artifactId>
			<version>3.13.11</version>
	</dependency>
   `
   
   - mariadb
   
   `
   		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<version>2.7.4</version>
		</dependency>
   `
   
   - hibernate


   `
		<dependency>
    		<groupId>org.hibernate</groupId>
    	<artifactId>hibernate-core</artifactId>
    	<version>5.5.7.Final</version>
		</dependency>
   `

   - jackson

   `
		<dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.5</version>
        </dependency>
   `

* Create a connection to MariaDB

`
if (sessionFactory == null) {
			Properties props = new Properties();
			try {
			FileReader connectionProp = new FileReader("src/main/resources/connection.properties");
			props.load(connectionProp);
			sessionFactory = new Configuration().configure()
					.setProperty("hibernate.connection.url", "url")
					.setProperty("hibernate.connection.username", props.getProperty("username"))
					.setProperty("hibernate.connection.password" , props.getProperty("password"))
					.buildSessionFactory();
			}catch(Exception e) {
				e.printStackTrace();			}
		}
		return sessionFactory.getCurrentSession();
`

  - Create Tables in mariaDB

* Add Javalin to Maven project
   
   `
      Javalin app = Javalin.create().start(8000);
      app.after(ctx -> {
    	   ctx.res.addHeader("Access-Control-Allow-Origin", "null");
      });
   `
   
   - Create end points.
* Implement a front end with HTML,CSS and JavaScript.
* Use Ajax to create XMLHttpRequests and utilizing javalin.

`    let url = 'http://localhost:8000/getAllReimbursements
`

`
    let xhr = new XMLHttpRequest();
`
